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
import br.com.dataminas.cbrasileiro.models.Artilheiro;
import br.com.dataminas.cbrasileiro.services.ArtilhariaService;
import br.com.dataminas.cbrasileiro.util.DataSerializer;
import br.com.dataminas.cbrasileiro.util.HTTPRequest;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainArtilhariaFragment extends Fragment {

	List<Artilheiro> artilheiros = null;
	LinearLayout tableArtilheirosView = null;
	ImageButton refreshButton;	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_artilheiros, container, false);

		// Create global configuration and initialize ImageLoader with this configuration
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory().cacheOnDisc().build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity().getApplicationContext()).defaultDisplayImageOptions(defaultOptions).build();
		ImageLoader.getInstance().init(config);

		tableArtilheirosView = (LinearLayout) rootView.findViewById(R.id.artilheirosTable);

		refreshButton = (ImageButton) rootView.findViewById(R.id.refreshButton);

		addButtonsListeners();

		loadArtilheiros();

		return rootView;
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
		loadArtilheiros();
	}

	private void updateTableView() {
		try {
			removeTableViewRows();

			for (Artilheiro artilheiro : artilheiros) {
				View classificacaoView = createArtilheiroRow(artilheiro);
				if (classificacaoView != null) {
					tableArtilheirosView.addView(classificacaoView);
				}
			}

		} catch (Exception e) {
//			showCustomMessage("Impossible to rebuild the table");
			e.printStackTrace();
		}
	}

	private View createArtilheiroRow(Artilheiro artilheiro) {
		try {
			View v;
			LayoutInflater inflater = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.row_artilheiros, null);
			
			TextView posicao = (TextView) v.findViewById(R.id.posicao);
			ImageView timeImagem = (ImageView) v.findViewById(R.id.time_image);
			TextView sigla = (TextView) v.findViewById(R.id.time_sigla);
			TextView gols = (TextView) v.findViewById(R.id.gols);
			TextView jogador = (TextView) v.findViewById(R.id.jogador_nome);
			
			posicao.setText(artilheiro.getId());
			ImageLoader.getInstance().displayImage(HTTPRequest.IMAGES_URL + artilheiro.getTimeDNS() + ".png", timeImagem);
			sigla.setText(artilheiro.getSiglaTime());
			jogador.setText(artilheiro.getNomeJogador());
			gols.setText(artilheiro.getGols());
			
			return v;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void removeTableViewRows() {
		tableArtilheirosView.removeAllViews();
	}

	public List<Artilheiro> serializeArtilheiros(String json) {
		try {
			DataSerializer dataSerializer = DataSerializer.getInstance();
			JSONObject result = new JSONObject(json);

			JSONArray artilheirosJA = result.getJSONArray("artilharia");
			List<Artilheiro> artilheiros = new ArrayList<Artilheiro>();
			for (int i = 0; i < artilheirosJA.length(); i++) {
				artilheiros.add((Artilheiro) dataSerializer.toObject(artilheirosJA.get(i).toString(), Artilheiro.class));
			}
			if (artilheiros.size() > 0) {
				return artilheiros;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private void loadArtilheiros() {
		ArtilhariaService artilhariaService = new ArtilhariaService();
		artilhariaService.getArtilheiros(new ArtilhariaResponseHandler());
	}
	
	class ArtilhariaResponseHandler extends AsyncHttpResponseHandler{
		ProgressDialog dialog = new ProgressDialog(getActivity());
		
		public ArtilhariaResponseHandler() {
			dialog.setMessage("Carregando Artilharia");
			dialog.show();
		}
		
		@Override
		public void onSuccess(int arg0, String jsonContent) {
			super.onSuccess(arg0, jsonContent);
			artilheiros = serializeArtilheiros(jsonContent);
			if (artilheiros != null) {
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
