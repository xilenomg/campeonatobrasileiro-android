package br.com.dataminas.cbrasileiro.models;

import org.codehaus.jackson.annotate.JsonProperty;

public class Artilheiro {
	
	@JsonProperty("id")
	String id;
	@JsonProperty("nome_jogador")
	String nomeJogador;
	@JsonProperty("gols")
	String gols;
	@JsonProperty("sigla_time")
	String siglaTime;
	@JsonProperty("time_dns")
	String timeDNS;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNomeJogador() {
		return nomeJogador;
	}
	public void setNomeJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
	}
	public String getGols() {
		return gols;
	}
	public void setGols(String gols) {
		this.gols = gols;
	}
	public String getSiglaTime() {
		return siglaTime;
	}
	public void setSiglaTime(String siglaTime) {
		this.siglaTime = siglaTime;
	}
	public String getTimeDNS() {
		return timeDNS;
	}
	public void setTimeDNS(String timeDNS) {
		this.timeDNS = timeDNS;
	}

}
