package com.alex.produtos.oferta.Resource;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.produtos.oferta.Service.OfertaService;
import com.alex.produtos.oferta.model.Endereco;
import com.alex.produtos.oferta.model.Oferta;

@RestController
@RequestMapping("/carga")
public class CargaResource {
	
	@Autowired
	private OfertaService ofertaService;

	@GetMapping("/inicial")
	public String salvar(){
		
		Oferta oferta = new Oferta();
		oferta.setNomeProduto("Ervilha enlatada");
		oferta.setPreco(new Double(2.88));
		oferta.setCategoria("Enlatados");
		oferta.setEndereco(new Endereco("R. Cantareira, 56 - Centro Histórico de São Paulo", Arrays.asList(-23.543176174619234, -46.629202673795525)));
		ofertaService.save(oferta);
		
		oferta.setNomeProduto("Laranja kg");
		oferta.setPreco(new Double(1.74));
		oferta.setCategoria("hortifruti");
		oferta.setEndereco(new Endereco("R. Cantareira, 56 - Centro Histórico de São Paulo", Arrays.asList(-23.543176174619234, -46.629202673795525)));
		ofertaService.save(oferta);
		
		oferta.setNomeProduto("Uva kg");
		oferta.setPreco(new Double(4.72));
		oferta.setCategoria("hortifruti");
		oferta.setEndereco(new Endereco("R. Cantareira, 56 - Centro Histórico de São Paulo", Arrays.asList(-23.543176174619234, -46.629202673795525)));
		ofertaService.save(oferta);

		oferta = new Oferta();
		oferta.setNomeProduto("Leite litro");
		oferta.setPreco(new Double(3.79));
		oferta.setCategoria("Laticinio");
		oferta.setEndereco(new Endereco("R. Cantareira, 42 - Centro Histórico de São Paulo, São Paulo - SP, 01024-000", Arrays.asList(-23.543176174619234, -46.629202673795525)));
		ofertaService.save(oferta);
		
		oferta = new Oferta();
		oferta.setNomeProduto("Sorvete pote 2l");
		oferta.setPreco(new Double(19.80));
		oferta.setCategoria("Sobremesa");
		oferta.setEndereco(new Endereco("R. Cantareira, 48 - Centro Histórico de São Paulo, São Paulo - SP, 01024-000", Arrays.asList(-23.543176174619234, -46.629202673795525)));
		ofertaService.save(oferta);
		
		oferta = new Oferta();
		oferta.setNomeProduto("Coca-cola 2l");
		oferta.setPreco(new Double(7.80));
		oferta.setCategoria("Bebida");
		oferta.setEndereco(new Endereco("Av. Paulista, 171 - Bela Vista, São Paulo - SP, 01310-300", Arrays.asList(-23.570339, -46.646218)));
		ofertaService.save(oferta);
		
		oferta = new Oferta();
		oferta.setNomeProduto("Sprite 2l");
		oferta.setPreco(new Double(7.80));
		oferta.setCategoria("Bebida");
		oferta.setEndereco(new Endereco("Av. Paulista, 171 - Bela Vista, São Paulo - SP, 01310-300", Arrays.asList(-23.570339, -46.646218)));
		ofertaService.save(oferta);
		
		oferta = new Oferta();
		oferta.setNomeProduto("Fanta Uva 2l");
		oferta.setPreco(new Double(7.80));
		oferta.setCategoria("Bebida");
		oferta.setEndereco(new Endereco("Av. Paulista, 171 - Bela Vista, São Paulo - SP, 01310-300", Arrays.asList(-23.570339, -46.646218)));
		ofertaService.save(oferta);
		
		return "OK";	
	}
}
