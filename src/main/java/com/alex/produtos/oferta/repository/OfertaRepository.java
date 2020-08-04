package com.alex.produtos.oferta.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.alex.produtos.oferta.codecs.OfertaCodec;
import com.alex.produtos.oferta.model.Oferta;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;

@Repository
public class OfertaRepository {

	@Value("${app.alex.oferta.find-max-distance}")
	private Double maxDistance;
	
	@Value("${app.alex.oferta.find-min-distance}")
	private Double minDistance;
	
	@Value("${app.alex.oferta.mongo-url}")
	private String mongoUrl;
	
	@Value("${app.alex.oferta.mongo-database}")
	private String mongoDB;
	
	private MongoClient cliente;
	private MongoDatabase bancoDeDados;
	
	private void criarConexao() {
		Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
		
		OfertaCodec ofertaCodec = new OfertaCodec(codec);
		
		CodecRegistry registro = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromCodecs(ofertaCodec));
		
		MongoClientOptions opcoes = MongoClientOptions.builder().codecRegistry(registro).build();
		
		this.cliente = new MongoClient(mongoUrl.trim(), opcoes);
		this.bancoDeDados = cliente.getDatabase(mongoDB.trim());
		
	}
	
	public void salvar(Oferta oferta){
		
		criarConexao();
		MongoCollection<Oferta> ofertas = this.bancoDeDados.getCollection("Ofertas", Oferta.class);
		if (oferta.getId() == null) {
			ofertas.insertOne(oferta);
		}else{
			ofertas.updateOne(Filters.eq("_id", oferta.getId()), new Document("$set", oferta));
		}
		
		fecharConexao();
	}

	
	
	public List<Oferta> obterTodosOfertas(){
		criarConexao();
		MongoCollection<Oferta> ofertas = this.bancoDeDados.getCollection("Ofertas", Oferta.class);
		
		MongoCursor<Oferta> resultados = ofertas.find().iterator();
		
		List<Oferta> ofertasEncontradas = popularOfertas(resultados);
		fecharConexao();
		
		return ofertasEncontradas;
		
	}
	
	public Oferta obterOfertaPor(String id){
		criarConexao();
		MongoCollection<Oferta> ofertas = this.bancoDeDados.getCollection("Ofertas", Oferta.class);
		Oferta oferta = ofertas.find(Filters.eq("_id", new ObjectId(id))).first();
		
		return oferta;
		
	}

	public List<Oferta> pesquisarPor(String nome) {
		criarConexao();
		MongoCollection<Oferta> OfertaCollection = this.bancoDeDados.getCollection("Ofertas" , Oferta.class);
		MongoCursor<Oferta> resultados = OfertaCollection.find(Filters.eq("nome", nome), Oferta.class).iterator();
		List<Oferta> alunos = popularOfertas(resultados);
		
		fecharConexao();
		
		return alunos;
	}

	private void fecharConexao() {
		this.cliente.close();
	}
	
	private List<Oferta> popularOfertas(MongoCursor<Oferta> resultados){
		List<Oferta> ofertas = new ArrayList<>();
		while(resultados.hasNext()){
			ofertas.add(resultados.next());
		}
		return ofertas;
	}


	public List<Oferta> pesquisaPorGeolocalizacao(List<Double> coordinates) {
		criarConexao();
		MongoCollection<Oferta> ofertaCollection = this.bancoDeDados.getCollection("Ofertas", Oferta.class);
		
		ofertaCollection.createIndex(Indexes.geo2dsphere("endereco")); 
		
		Point pontoReferencia = new Point(new Position(coordinates.get(0), coordinates.get(1)));
		
		MongoCursor<Oferta> resultados = ofertaCollection.find(Filters.nearSphere("endereco", pontoReferencia, maxDistance, minDistance)).iterator();
		
		List<Oferta> ofertas = popularOfertas(resultados);
		
		fecharConexao();
		return ofertas;
	}

}
