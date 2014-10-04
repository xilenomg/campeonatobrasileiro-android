package br.com.dataminas.cbrasileiro.subfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.dataminas.cbrasileiro.R;

public class ListRodadasFragment extends Fragment {

	LinearLayout listLayout;
	static int lastClicked = 0;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list_rodadas, container, false);

		listLayout = (LinearLayout) view.findViewById(R.id.rodadasListView);
		addRodadas(inflater);

		return view;
	}

	private void addRodadas(LayoutInflater inflater) {


		for ( int i = 1; i < 39; i++){
			View v = inflater.inflate(R.layout.row_rodadas, null);
			TextView rodada = (TextView) v.findViewById(R.id.rodadaTextView);

			v.setOnClickListener(new RodadaClickListener(i));
			rodada.setText("" + i);
			listLayout.addView(v);
		}
	}

	class RodadaClickListener implements OnClickListener{
		int i;
		public RodadaClickListener(int i) {
			this.i = i;
		}

		@Override
		public void onClick(View v) {
			clearBackground();
			v.setBackgroundColor(getResources().getColor(R.color.yellow));
			RodadaFragment rodadaFragment = (RodadaFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.rodada_fragment);
			rodadaFragment.loadJogos(i);
		}

		private void clearBackground() {
			int size =  listLayout.getChildCount();
			for ( int i = 0; i < size; i++ ){
				View v = (View) listLayout.getChildAt(i);
				v.setBackgroundColor(getResources().getColor(R.color.white));
			}
		}
		
		
	}

}
