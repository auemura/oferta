package com.alex.produtos.oferta.Resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alex.produtos.oferta.Service.OfertaService;
import com.alex.produtos.oferta.model.Oferta;

@RestController
@RequestMapping("/oferta")
public class OfertaResource {

	@Autowired
	private OfertaService ofertaService;

	@GetMapping("/localizacao")
	public ResponseEntity<List<Oferta>> pesquisaPorGeolocalizacao(@RequestParam Double latitude, @RequestParam Double longitude){
		List<Oferta> ofertas = ofertaService.pesquisaPorGeolocalizacao(Arrays.asList(latitude, longitude));
		return ResponseEntity.ok(ofertas);
	}
	
}
