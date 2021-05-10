package it.euris.test.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import it.euris.test.dao.IDaoArticoli;
import it.euris.test.model.Articolo;

@RestController
@RequestMapping("/articoli")
public class ControllerArticoli {
	
	@Autowired
	private IDaoArticoli dao;
	
	@GetMapping
	public List<Articolo> get(){
		return dao.listArticoli();
	}
	
	/**
	 * risponde 404 notFound se l'articolo non c'é
	 * @param codId codice identificativo dell'articolo
	 * @return 404 || Articolo
	 */
	@GetMapping("/{codId}")
	public ResponseEntity<Articolo> getAricolo(@PathVariable String codId) {
		Articolo art =  dao.getArticolo(codId);
		if (art == null) {
			//risponde -> notFound (404) al chiamante
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(art);
		}
	}
	
	@PostMapping
	public void addArticolo(@RequestBody Articolo articolo) {
		dao.addArticolo(articolo);
	}
	
	@PutMapping
	public void updateArticolo(@RequestBody Articolo articolo) {
		dao.updateArticolo(articolo);
	}
	
	@DeleteMapping("/{codId}")
	public void deteleArticolo(@PathVariable String codId) {
		dao.deleteArticolo(codId);
	}
	
	@PutMapping("/{codId}/{mod}")
	public ResponseEntity<String> updatePrezzo( @PathVariable String mod, @PathVariable String codId, @RequestBody Articolo soloPrezzo){
		if(mod.equals("inc")) {			
			Articolo a = dao.getArticolo(codId);
			String s = dao.sommaPrezzo(a.getPrice(), soloPrezzo.getPrice());
			return ResponseEntity.ok(s);
		}
		if(mod.equals("sot")) {			
			Articolo a = dao.getArticolo(codId);
			String s = dao.sottPrezzo(a.getPrice(), soloPrezzo.getPrice());
			return ResponseEntity.ok(s);
		}
		if(mod.equals("mul")) {			
			Articolo a = dao.getArticolo(codId);
			String s = dao.multiPrezzo(a.getPrice(), Integer.parseInt(soloPrezzo.getPrice()));
			return ResponseEntity.ok(s);
		}
		if(mod.equals("div")) {			
			Articolo a = dao.getArticolo(codId);
			String s = dao.divPrezzo(a.getPrice(), Integer.parseInt(soloPrezzo.getPrice()));
			return ResponseEntity.ok(s);
		}
		return ResponseEntity.ok("comando non valido");
	}

}
