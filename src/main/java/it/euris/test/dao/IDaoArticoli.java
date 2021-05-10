package it.euris.test.dao;

import java.util.List;

import it.euris.test.model.Articolo;

public interface IDaoArticoli {
	
	/**
	 * lista dei articoli nel catalogo
	 * @return List<Articolo> lista dei articoli
	 */
	List<Articolo> listArticoli();
	
	/**
	 * prende l'articolo richiesto
	 * @param codId codice identificativo unico dell'articolo
	 * @return Articolo richiesto , <br>
	 * ritorna <b>null</b> se l'articolo non c'é
	 */
	Articolo getArticolo(String codId);
	
	/**
	 * aggiunge l'articolo
	 * @param articolo da aggiungere
	 */
	void addArticolo(Articolo articolo);
	
	/**
	 * articolo da aggiornare o modficare <br>
	 * solamente il <u>nome</u> e il <u>prezzo</u>
	 * @param articolo da modificare/aggiornare
	 */
	void updateArticolo(Articolo articolo);
	
	/**
	 * articolo da cancellare 
	 * @param codId codice identificativo unico dell'articolo
	 */
	void deleteArticolo(String codId );
	
	
	String sommaPrezzo(String a1, String a2);
	
	String sottPrezzo(String a1, String a2);
	
	String multiPrezzo(String prezzo, int mul);
	
	String divPrezzo(String prezzo, int div);

}
