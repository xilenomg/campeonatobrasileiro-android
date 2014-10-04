package br.com.dataminas.cbrasileiro.subfragments;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.dataminas.cbrasileiro.R;
import br.com.dataminas.cbrasileiro.models.Jogo;
import br.com.dataminas.cbrasileiro.services.JogosService;
import br.com.dataminas.cbrasileiro.util.DataSerializer;
import br.com.dataminas.cbrasileiro.util.HTTPRequest;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class RodadaFragment extends Fragment {

	List<Jogo> jogos = null;
	int rodadaAtual = 0;
	int rodadaLida = 0;
	LinearLayout tableJogosView = null;

	//buttons
	ImageButton refreshButton;

	//TextViews
	TextView rodadaTextView;


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory().cacheOnDisc().build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity().getApplicationContext()).defaultDisplayImageOptions(defaultOptions).build();
		ImageLoader.getInstance().init(config);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.rodada, container, false);
		
		try{
			if ( jogos == null ){
				// Create global configuration and initialize ImageLoader with this configuration
				

				tableJogosView = (LinearLayout) view.findViewById(R.id.jogosTable);


				rodadaTextView = (TextView) view.findViewById(R.id.rodadaLida);

				refreshButton = (ImageButton) view.findViewById(R.id.refreshButton);

				addButtonsListeners();

				loadJogos(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return view;
	}

	private void addButtonsListeners() {

		refreshButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				refreshJogos();
			}
		});

	}

	private void refreshJogos(){
		loadJogos(rodadaLida);
	}

	private void updateTableView() {
		try {
			removeTableViewRows();
			rodadaTextView.setText("Rodada: " + rodadaLida);

			for (Jogo jogo : jogos) {
				View jogoView = createJogoRow(jogo);
				if (jogoView != null) {
					tableJogosView.addView(jogoView);
				}
			}

		} catch (Exception e) {
//			activity.showCustomMessage("Impossible to rebuild the table");
			e.printStackTrace();
		}
	}

	private View createJogoRow(Jogo jogo) {
		try {
			View v;
			LayoutInflater inflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.row_jogos, null);

			TextView timeCasaSigla = (TextView) v.findViewById(R.id.time_casa_sigla);
			TextView timeForaSigla = (TextView) v.findViewById(R.id.time_fora_sigla);
			TextView placarCasa = (TextView) v.findViewById(R.id.placar_casa);
			TextView placarFora = (TextView) v.findViewById(R.id.placar_fora);
			ImageView timeCasaImage = (ImageView) v.findViewById(R.id.time_casa_image);
			ImageView timeForaImage = (ImageView) v.findViewById(R.id.time_fora_image);
			TextView localJogo = (TextView) v.findViewById(R.id.local_jogo);
			TextView dataJogo = (TextView) v.findViewById(R.id.data_jogo);

			timeCasaSigla.setText(jogo.getSiglaCasa());
			timeForaSigla.setText(jogo.getSiglaFora());
			placarCasa.setText(jogo.getPlacarCasa());
			placarFora.setText(jogo.getPlacarFora());
			localJogo.setText("Estádio: " + jogo.getLocal());
			dataJogo.setText(jogo.getDataCompleta());

			ImageLoader.getInstance().displayImage(HTTPRequest.IMAGES_URL + jogo.getTimeDNSCasa() + ".png", timeCasaImage);
			ImageLoader.getInstance().displayImage(HTTPRequest.IMAGES_URL + jogo.getTimeDNSFora() + ".png", timeForaImage);

			return v;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void removeTableViewRows() {
		tableJogosView.removeAllViews();
	}

	public List<Jogo> serializeJogos(String json) {
		try {
			DataSerializer dataSerializer = DataSerializer.getInstance();
			JSONObject result = new JSONObject(json);
			if (rodadaAtual == 0) {
				rodadaAtual = Integer.parseInt(result.getString("rodada_lida"));
			}
			rodadaLida = Integer.parseInt(result.getString("rodada_lida"));

			JSONArray jogosJA = result.getJSONArray("jogos");
			List<Jogo> jogos = new ArrayList<Jogo>();
			for (int i = 0; i < jogosJA.length(); i++) {
				jogos.add((Jogo) dataSerializer.toObject(jogosJA.get(i).toString(), Jogo.class));
			}
			if (jogos.size() > 0) {
				return jogos;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public void loadJogos(int rodada) {
		JogosService jogosService = new JogosService();
		jogosService.getJogos(rodada, new JogosResponseHandler(rodada));
	}

	class JogosResponseHandler extends AsyncHttpResponseHandler{
		ProgressDialog dialog = new ProgressDialog(getActivity());
		
		public JogosResponseHandler(int rodada) {
			dialog.setMessage("Carregando rodada: " + (rodada == 0 ? "atual" : rodada));
			dialog.show();
		}
		
		@Override
		public void onSuccess(int arg0, String jsonContent) {
			super.onSuccess(arg0, jsonContent);
			jogos = serializeJogos(jsonContent);
			if (jogos != null) {
				updateTableView();
			}
		}
	
		@Override
		public void onFailure(Throwable arg0, String arg1) {
			super.onFailure(arg0, arg1);
		}
		
		@Override
		public void onFinish() {
			super.onFinish();
			dialog.hide();
		}
	}
	
}

