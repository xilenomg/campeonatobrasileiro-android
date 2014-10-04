package br.com.dataminas.cbrasileiro.services;

import br.com.dataminas.cbrasileiro.util.HTTPRequest;

import com.loopj.android.http.AsyncHttpResponseHandler;

public class JogosService {
	public void getJogos(int rodada, AsyncHttpResponseHandler responseHandler){
		HTTPRequest.getInstance().getJSONJogos(rodada, responseHandler);
	}
}
