package br.com.dataminas.cbrasileiro.models;

import org.codehaus.jackson.annotate.JsonProperty;

public class Jogo {
	@JsonProperty("local")
	private String local;
	
	@JsonProperty("time_casa")
	private String timeCasa;
	
	@JsonProperty("time_dns_casa")
	private String timeDNSCasa;
	
	@JsonProperty("time_fora")
	private String timeFora;
	
	@JsonProperty("time_dns_fora")
	private String timeDNSFora;
	
	@JsonProperty("placar_casa")
	private String placarCasa;
	
	@JsonProperty("placar_fora")
	private String placarFora;
	
	@JsonProperty("data_jogo")
	private String dataJogo;
	
	@JsonProperty("hora_jogo")
	private String horaJogo;
	
	@JsonProperty("sigla_casa")
	private String siglaCasa;
	
	@JsonProperty("sigla_fora")
	private String siglaFora;
	
	@JsonProperty("rodada")
	private String rodada;
	
	@JsonProperty("data_completa")
	private String dataCompleta;
	
	public String getLocal() {
		return local;
	}
	
	public void setLocal(String local) {
		this.local = local;
	}

	public String getTimeCasa() {
		return timeCasa;
	}

	public void setTimeCasa(String timeCasa) {
		this.timeCasa = timeCasa;
	}

	public String getTimeDNSCasa() {
		return timeDNSCasa;
	}

	public void setTimeDNSCasa(String timeDNSCasa) {
		this.timeDNSCasa = timeDNSCasa;
	}

	public String getTimeFora() {
		return timeFora;
	}

	public void setTimeFora(String timeFora) {
		this.timeFora = timeFora;
	}

	public String getTimeDNSFora() {
		return timeDNSFora;
	}

	public void setTimeDNSFora(String timeDNSFora) {
		this.timeDNSFora = timeDNSFora;
	}

	public String getPlacarCasa() {
		return placarCasa;
	}

	public void setPlacarCasa(String placarCasa) {
		this.placarCasa = placarCasa;
	}

	public String getPlacarFora() {
		return placarFora;
	}

	public void setPlacarFora(String placarFora) {
		this.placarFora = placarFora;
	}

	public String getDataJogo() {
		return dataJogo;
	}

	public void setDataJogo(String dataJogo) {
		this.dataJogo = dataJogo;
	}

	public String getHoraJogo() {
		return horaJogo;
	}

	public void setHoraJogo(String horaJogo) {
		this.horaJogo = horaJogo;
	}

	public String getSiglaCasa() {
		return siglaCasa;
	}

	public void setSiglaCasa(String siglaCasa) {
		this.siglaCasa = siglaCasa;
	}

	public String getSiglaFora() {
		return siglaFora;
	}

	public void setSiglaFora(String siglaFora) {
		this.siglaFora = siglaFora;
	}

	public String getRodada() {
		return rodada;
	}

	public void setRodada(String rodada) {
		this.rodada = rodada;
	}

	public String getDataCompleta() {
		return dataCompleta;
	}

	public void setDataCompleta(String dataCompleta) {
		this.dataCompleta = dataCompleta;
	}
	
	
}
