package br.com.dataminas.cbrasileiro.services;

import br.com.dataminas.cbrasileiro.util.HTTPRequest;

import com.loopj.android.http.AsyncHttpResponseHandler;

public class ClassificacaoService {

	
	public void getClassificacao(AsyncHttpResponseHandler responseHandler){
		HTTPRequest.getInstance().getJSONClassificacao(responseHandler);
	}
}
