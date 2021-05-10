package it.euris.test.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import it.euris.test.model.Articolo;
import it.euris.test.util.BasicDao;

@Repository
public class DaoArticoli extends BasicDao implements IDaoArticoli {

	private static final int ONE_SHILLING_PENCE = 12;
	private static final int ONE_POUND_PENCE = 240;
	private static final String DELETE_ARTICOLI = "DELETE FROM articoli ";
	private static final String UPDATE_ARTICOLI_NAME_PRICE = "UPDATE articoli SET name = ?, price = ? ";
	private static final String INSERT_INTO_ARTICOLI = "INSERT INTO articoli (cod_id, name, price) VALUE (? , ? , ?)";
	private static final String WHERE_COD_ID = "WHERE cod_id = ? ";
	private static final String SELECT_ARTICOLI = "SELECT * FROM articoli ";

	public DaoArticoli(
			@Value("${db.address}") String dbAddress, 
			@Value("${db.user}") String user,
			@Value("${db.pass}") String password) {
		super(dbAddress, user, password);
	}

	@Override
	public List<Articolo> listArticoli() {
		List<Articolo> listArticoli = new ArrayList<Articolo>();
		List<Map<String,String>> maps = getAll(SELECT_ARTICOLI);
		for (Map<String, String> map : maps) {
			Articolo a = new Articolo();
			a.fromMap(map);
			listArticoli.add(a);
		}
		return listArticoli;
	}

	@Override
	public Articolo getArticolo(String codId) {
		Articolo articolo = null;		
		Map<String, String> map = getOne(SELECT_ARTICOLI + WHERE_COD_ID, codId);
		if(map != null) {
			articolo = new Articolo();
			articolo.fromMap(map);
		}		
		return articolo;
	}

	@Override
	public void addArticolo(Articolo articolo) {		
		execute(INSERT_INTO_ARTICOLI, 
				articolo.getCod_id(), articolo.getName(), articolo.getPrice() );
	}

	@Override
	public void updateArticolo(Articolo articolo) {
		execute(UPDATE_ARTICOLI_NAME_PRICE + WHERE_COD_ID, 
				articolo.getName(), articolo.getPrice(), articolo.getCod_id());		
	}

	@Override
	public void deleteArticolo(String codId) {
		execute(DELETE_ARTICOLI + WHERE_COD_ID, codId);		
	}

	
	private int soloPence(String prezzo) {
		int pence = 0;
		String[] p = prezzo.split("\\s+");
		for(int x =0; x < p.length; x++) {
			p[x] = p[x].substring(0, p[x].length()-1);			
		}
		for(int x =0; x < p.length; x++) {
			if(x == 0) {
				pence += Integer.parseInt(p[x]);
				continue;
			}else if (x == 1) {
				pence += (Integer.parseInt(p[x]) * ONE_SHILLING_PENCE);
				continue;
			}else if (x == 2) {
				pence += (Integer.parseInt(p[x]) * ONE_POUND_PENCE) ;
				break;
			}
		}
		return pence;
	}
	
	private String convertFromPenceToMultyCoin(int totPence) {
		String[] tot = new String[3];
		if((totPence / ONE_POUND_PENCE) >= 0 ) {
			int d = (int) (totPence / ONE_POUND_PENCE);
			d = d * ONE_POUND_PENCE;
			tot[2] = d + "d";
			totPence -= d;
		}
		if((totPence / ONE_SHILLING_PENCE) >= 0) {
			int s = (int) (totPence / ONE_SHILLING_PENCE);
			s *= ONE_SHILLING_PENCE;
			tot[1] = s + "s";
			totPence -= s;
		}
		tot[0] = totPence + "p";		
		String multi = "";		
		for(String r : tot) {
			multi += r + " ";
		}
		return multi;
	}
	
	@Override
	public String sommaPrezzo(String a1, String a2) {	
		int p1 = soloPence(a1);
		int p2 = soloPence(a2);
		int totPence = p1 + p2;
		return convertFromPenceToMultyCoin(totPence);		
	}

	@Override
	public String sottPrezzo(String a1, String a2) {
		int p1 = soloPence(a1);
		int p2 = soloPence(a2);
		int totPence = p1 - p2;
		return convertFromPenceToMultyCoin(totPence);		
	}

	@Override
	public String multiPrezzo(String prezzo, int mul) {
		int p = soloPence(prezzo);
		int totPence = p * mul;
		return convertFromPenceToMultyCoin(totPence);	
	}

	@Override
	public String divPrezzo(String prezzo, int div) {
		int p = soloPence(prezzo);
		double totPence = p / div;
		System.out.println(totPence);
		return null;
	}
	


}
