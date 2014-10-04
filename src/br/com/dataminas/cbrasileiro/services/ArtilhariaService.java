package br.com.dataminas.cbrasileiro.services;

import br.com.dataminas.cbrasileiro.util.HTTPRequest;

import com.loopj.android.http.AsyncHttpResponseHandler;

public class ArtilhariaService {
	
	public void getArtilheiros(AsyncHttpResponseHandler responseHandler){
		HTTPRequest.getInstance().getJSONArtilharia(responseHandler);
	}
}
