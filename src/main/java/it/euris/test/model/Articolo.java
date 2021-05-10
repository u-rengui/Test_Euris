package it.euris.test.model;

import it.euris.test.util.IMappablePro;

public class Articolo implements IMappablePro {

	/**
	 * codice identificativo univoco dell'articolo 
	 */
	private String cod_id;
	
	/**
	 * nome del articolo
	 */
	private String name;
	
	/**
	 * Un pound valeva 20 shilling e uno shilling 12 pence <br>
	 * (quindi un pound 240 pence)<br>
	 * pound < shilling  < pence <br><hr>
	 * 1s (shilling) = 12p (pence) <br>
	 * 1d (pound) = 20s (shilling) <br>
	 * 1d (pound) = 240p (pence) <br>
	 */
	private String price;
	
	public String getCod_id() {
		return cod_id;
	}
	public void setCod_id(String cod_id) {
		this.cod_id = cod_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Articolo(String cod_id, String name, String price) {
		super();
		this.cod_id = cod_id;
		this.name = name;
		this.price = price;
	}
	
	public Articolo() {
		super();
	}
	
	@Override
	public String toString() {
		return "Articolo [cod_id=" + cod_id + ", name=" + name + ", price=" + price + "]";
	}
	
	
	
}
