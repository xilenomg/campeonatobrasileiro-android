package br.com.dataminas.cbrasileiro.mainFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.dataminas.cbrasileiro.R;

public class MainRodadasFragment extends Fragment {

	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_rodadas, container, false);
        return rootView;
    }
	
	@Override
	public void onDestroyView() {
	    super.onDestroyView();
	}

}
