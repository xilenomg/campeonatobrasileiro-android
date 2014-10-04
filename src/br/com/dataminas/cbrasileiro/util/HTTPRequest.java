package br.com.dataminas.cbrasileiro.util;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class HTTPRequest {

	public final static String BASE_URL = "http://campeonatobrasileiro.dataminas.com.br";
	public final static String CLASSIFICACAO_URL = BASE_URL + "/iphone/classificacao.php";
	public final static String JOGOS_URL = BASE_URL + "/iphone/jogos.php";
	public final static String ARTILHARIA_URL = BASE_URL + "/iphone/artilharia.php";
	public static final String IMAGES_URL = BASE_URL + "/_imagens/times/";
	
	private static final int TIMEOUT = 40000;
	
	AsyncHttpClient httpClient;
	
	private static HTTPRequest instance;
	
	
	private HTTPRequest() {
		httpClient = new AsyncHttpClient();
		httpClient.setTimeout(TIMEOUT);
	}
	
	public static HTTPRequest getInstance(){
		if (instance == null){
			instance = new HTTPRequest();
		}
		
		return instance;
	}
	
	public void getJSONArtilharia(AsyncHttpResponseHandler responseHandler){
		httpClient.get(ARTILHARIA_URL, responseHandler);
	}
	
	public void getJSONJogos(int rodada, AsyncHttpResponseHandler responseHandler){
		if (rodada == 0 ){
			httpClient.get(JOGOS_URL, responseHandler);
		}
		else{
			httpClient.get(JOGOS_URL + "?rodada=" + rodada, responseHandler);
		}
	}
	
	public void getJSONClassificacao(AsyncHttpResponseHandler responseHandler){
		httpClient.get(CLASSIFICACAO_URL, responseHandler);
	}
	
}