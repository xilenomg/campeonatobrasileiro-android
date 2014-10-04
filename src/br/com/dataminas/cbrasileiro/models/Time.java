package br.com.dataminas.cbrasileiro.models;

import org.codehaus.jackson.annotate.JsonProperty;

public class Time {

	@JsonProperty
	String posicao;
	@JsonProperty
	String id;
	@JsonProperty
	String nome;
	@JsonProperty
	String dns;
	@JsonProperty
	String escudo;
	@JsonProperty
	String sigla;
	@JsonProperty
	String vitorias;
	@JsonProperty
	String jogos;
	@JsonProperty
	String empate;
	@JsonProperty
	String derrotas;
	@JsonProperty
	String aproveitamento;
	@JsonProperty
	String pontos;
	@JsonProperty
	String serie;
	@JsonProperty("gols_pro")
	String golsPro;
	@JsonProperty("faixa_ordem")
	String faixaOrdem;
	@JsonProperty("gols_contra")
	String golsContra;
	@JsonProperty("saldo_gols")
	String saldoGols;

	public String getGolsPro() {
		return golsPro;
	}
	
	public String getGolsContra() {
		return golsContra;
	}
	
	public String getFaixaOrdem() {
		return faixaOrdem;
	}
	
	public String getSaldoGols() {
		return saldoGols;
	}

	public String getPosicao() {
		return posicao;
	}
	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDns() {
		return dns;
	}
	public void setDns(String dns) {
		this.dns = dns;
	}
	public String getEscudo() {
		return escudo;
	}
	public void setEscudo(String escudo) {
		this.escudo = escudo;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getVitorias() {
		return vitorias;
	}
	public void setVitorias(String vitorias) {
		this.vitorias = vitorias;
	}
	public String getJogos() {
		return jogos;
	}
	public void setJogos(String jogos) {
		this.jogos = jogos;
	}
	public String getEmpates() {
		return empate;
	}
	public void setEmpates(String empates) {
		this.empate = empates;
	}
	public String getDerrotas() {
		return derrotas;
	}
	public void setDerrotas(String derrotas) {
		this.derrotas = derrotas;
	}
	public String getAproveitamento() {
		return aproveitamento;
	}
	public void setAproveitamento(String aproveitamento) {
		this.aproveitamento = aproveitamento;
	}
	public String getPontos() {
		return pontos;
	}
	public void setPontos(String pontos) {
		this.pontos = pontos;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}
}
