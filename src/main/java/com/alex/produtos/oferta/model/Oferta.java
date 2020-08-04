package com.alex.produtos.oferta.model;

import org.bson.types.ObjectId;

public class Oferta {
	
	private ObjectId id;
	
	private String nomeProduto;
	
	private Double preco;
	
	private String categoria;
	
	private Endereco endereco;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Oferta criarId() {
		setId(new ObjectId());
		return this;
	}

}
