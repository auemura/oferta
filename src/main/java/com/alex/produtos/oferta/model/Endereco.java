package com.alex.produtos.oferta.model;

import java.util.List;

public class Endereco {
	
	private String descricao;
	
	private List<Double> coordinates;
	
	private String type = "Point";
	
	public Endereco() {}
	
	public Endereco(String descricao){
		this.descricao = descricao;
	}
	
	public Endereco(String descricao, List<Double> coordinates){
		this.descricao = descricao;
		this.coordinates = coordinates;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Double> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
