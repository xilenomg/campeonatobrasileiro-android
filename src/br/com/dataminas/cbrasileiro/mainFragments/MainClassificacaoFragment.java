package br.com.dataminas.cbrasileiro.mainFragments;

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
import br.com.dataminas.cbrasileiro.models.Time;
import br.com.dataminas.cbrasileiro.services.ClassificacaoService;
import br.com.dataminas.cbrasileiro.util.DataSerializer;
import br.com.dataminas.cbrasileiro.util.HTTPRequest;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainClassificacaoFragment extends Fragment {

	List<Time> classificacao = null;
	LinearLayout tableClassificacaoView = null;
	ImageButton refreshButton;	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_classificacao, container, false);

		// Create global configuration and initialize ImageLoader with this configuration
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory().cacheOnDisc().build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity().getApplicationContext()).defaultDisplayImageOptions(defaultOptions).build();
		ImageLoader.getInstance().init(config);

		tableClassificacaoView = (LinearLayout) rootView.findViewById(R.id.classificacaoTable);

		refreshButton = (ImageButton) rootView.findViewById(R.id.refreshButton);

		customizingHeader(rootView);

		addButtonsListeners();

		loadClassificacao();
		return rootView;
	}


	private void customizingHeader(View rootView) {
		
//		((TextView) rootView.findViewById(R.id.posicao)).setText("1");
//		((TextView) rootView.findViewById(R.id.sigla)).setText("2");
		
		((TextView) rootView.findViewById(R.id.pontos)).setText("P");
		((TextView) rootView.findViewById(R.id.jogos)).setText("J");
		((TextView) rootView.findViewById(R.id.vitorias)).setText("V");
		((TextView) rootView.findViewById(R.id.empates)).setText("E");
		((TextView) rootView.findViewById(R.id.derrotas)).setText("D");
		((TextView) rootView.findViewById(R.id.percentage)).setText("%");
		((TextView) rootView.findViewById(R.id.gols_pro)).setText("GP");
		((TextView) rootView.findViewById(R.id.gols_contra)).setText("GC");
		((TextView) rootView.findViewById(R.id.saldo_gols)).setText("SG");

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
		loadClassificacao();
	}

	private void updateTableView() {
		try {
			removeTableViewRows();

			for (Time time : classificacao) {
				View classificacaoView = createClassificacaoRow(time);
				if (classificacaoView != null) {
					tableClassificacaoView.addView(classificacaoView);
				}
			}

		} catch (Exception e) {
//			showCustomMessage("Impossible to rebuild the table");
			e.printStackTrace();
		}
	}

	private View createClassificacaoRow(Time time) {
		try {
			View v;
			LayoutInflater inflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.row_classificacao, null);
			
			if ( time.getFaixaOrdem().equals("1") ){
				v.setBackgroundColor(getResources().getColor(R.color.libertadores));
			}
			else if (time.getFaixaOrdem().equals("2") ){
				v.setBackgroundColor(getResources().getColor(R.color.rebaixamento));
			}

			TextView posicao = (TextView) v.findViewById(R.id.posicao);
			ImageView timeImagem = (ImageView) v.findViewById(R.id.time_image);
			TextView sigla = (TextView) v.findViewById(R.id.sigla);
			TextView pontos = (TextView) v.findViewById(R.id.pontos);
			TextView jogos = (TextView) v.findViewById(R.id.jogos);
			TextView vitorias = (TextView) v.findViewById(R.id.vitorias);
			TextView empates = (TextView) v.findViewById(R.id.empates);
			TextView derrotas = (TextView) v.findViewById(R.id.derrotas);
			TextView aproveitamento = (TextView) v.findViewById(R.id.percentage);

			TextView golsPro = (TextView) v.findViewById(R.id.gols_pro);
			TextView golsContra = (TextView) v.findViewById(R.id.gols_contra);
			TextView saldoGols = (TextView) v.findViewById(R.id.saldo_gols);

			posicao.setText(time.getPosicao());
			ImageLoader.getInstance().displayImage(HTTPRequest.IMAGES_URL + time.getEscudo(), timeImagem);
			sigla.setText(time.getSigla());
			pontos.setText(time.getPontos());
			jogos.setText(time.getJogos());
			vitorias.setText(time.getVitorias());
			empates.setText(time.getEmpates());
			derrotas.setText(time.getDerrotas());
			aproveitamento.setText(time.getAproveitamento());

			golsPro.setText(time.getGolsPro());
			golsContra.setText(time.getGolsContra());
			saldoGols.setText(time.getSaldoGols());


			return v;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void removeTableViewRows() {
		tableClassificacaoView.removeAllViews();
	}

	public List<Time> serializeClassificacao(String json) {
		try {
			DataSerializer dataSerializer = DataSerializer.getInstance();
			JSONObject result = new JSONObject(json);

			JSONArray classificacaoJA = result.getJSONArray("classificacao");
			List<Time> times = new ArrayList<Time>();
			for (int i = 0; i < classificacaoJA.length(); i++) {
				times.add((Time) dataSerializer.toObject(classificacaoJA.get(i).toString(), Time.class));
			}
			if (times.size() > 0) {
				return times;
			}
			return null;
		} catch (Exception e) {
//			showUnexpectedErrorMessage();
			e.printStackTrace();
			return null;
		}

	}

	private void loadClassificacao() {
//		showLoading();
		ClassificacaoService classificacaoService = new ClassificacaoService();
		classificacaoService.getClassificacao(new ClassificacaoResponseHandler());
	}

	
	class ClassificacaoResponseHandler extends AsyncHttpResponseHandler{
		ProgressDialog dialog = new ProgressDialog(getActivity());
		
		public ClassificacaoResponseHandler() {
			dialog.setMessage("Carregando Classificação");
			dialog.show();
		}
		
		@Override
		public void onSuccess(int arg0, String jsonContent) {
			super.onSuccess(arg0, jsonContent);
			classificacao = serializeClassificacao(jsonContent);
			if (classificacao != null) {
				updateTableView();
			}
		}

		@Override
		public void onFailure(Throwable arg0, String arg1) {
			super.onFailure(arg0, arg1);
//			showInternetIssueMessage();
		}

		@Override
		public void onFinish() {
			super.onFinish();
			dialog.hide();
		}
	}

}
