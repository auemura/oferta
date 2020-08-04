package com.alex.produtos.oferta.codecs;

import java.util.ArrayList;
import java.util.List;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import com.alex.produtos.oferta.model.Endereco;
import com.alex.produtos.oferta.model.Oferta;


public class OfertaCodec implements CollectibleCodec<Oferta> {
	
	private Codec<Document> codec;
	
	public OfertaCodec(Codec<Document> codec) {
		this.codec = codec;
	}

	@Override
	public void encode(BsonWriter writer, Oferta oferta, EncoderContext encoder) {
		ObjectId id = oferta.getId();
		String nomeProduto = oferta.getNomeProduto();
		Double preco = oferta.getPreco();
		String categoria = oferta.getCategoria();
		Endereco endereco = oferta.getEndereco();
		
		Document document = new Document();
		
		document.put("_id", id);
		document.put("nomeProduto", nomeProduto);
		document.put("preco", preco);
		document.put("categoria", categoria);
		
		List<Double> coordinates = new ArrayList<Double>();
		for(Double location : endereco.getCoordinates()){
			coordinates.add(location);
		}
		
		document.put("endereco", new Document()
				.append("descricao" , endereco.getDescricao())
				.append("coordinates", coordinates)
				.append("type", endereco.getType()));
		
		codec.encode(writer, document, encoder);
		
	}

	@Override
	public Class<Oferta> getEncoderClass() {
		return Oferta.class;
	}

	@Override
	public Oferta decode(BsonReader reader, DecoderContext decoder) {
		Document document = codec.decode(reader, decoder);
		
		Oferta oferta = new Oferta();
		
		oferta.setId(document.getObjectId("_id"));
		oferta.setNomeProduto(document.getString("nomeProduto"));
		oferta.setPreco(document.getDouble("preco"));
		oferta.setCategoria(document.getString("categoria"));
		
		Document endereco = (Document) document.get("endereco");
		if (endereco != null) {
			String descricao = endereco.getString("descricao");
			List<Double> coordinates = (List<Double>) endereco.get("coordinates");
			oferta.setEndereco(new Endereco(descricao, coordinates));
		}
		
		return oferta;
	}

	@Override
	public boolean documentHasId(Oferta oferta) {
		return oferta.getId() == null;
	}

	@Override
	public Oferta generateIdIfAbsentFromDocument(Oferta oferta) {
		return documentHasId(oferta) ? oferta.criarId() : oferta;
	}

	@Override
	public BsonValue getDocumentId(Oferta oferta) {
		if (!documentHasId(oferta)) {
			throw new IllegalStateException("Esse Document nao tem id");
		}
		
		return new BsonString(oferta.getId().toHexString());
	}

}
