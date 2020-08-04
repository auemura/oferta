package com.alex.produtos.oferta.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.produtos.oferta.model.Oferta;
import com.alex.produtos.oferta.repository.OfertaRepository;

@Service
public class OfertaService {
	
	@Autowired
	OfertaRepository ofertaRepository;
	
	public void save(Oferta oferta) {
		try {
			//List<Double> latElong = geolocalizacaoService.obterLatELongPor(oferta.getEndereco());
			//oferta.getEndereco().setCoordinates(latElong);
			ofertaRepository.salvar(oferta);
		} catch (Exception e) {
			System.out.println("Endereco nao localizado");
			e.printStackTrace();
		} 
	}
	
	public List<Oferta> pesquisaPorGeolocalizacao(List<Double> coordinates){
		return ofertaRepository.pesquisaPorGeolocalizacao(coordinates);
	}

}
