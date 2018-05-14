package logika;

import java.util.ArrayList;
import java.util.List;

public class Peterka {
	
	public List<Integer> x = new ArrayList<Integer>();
	public List<Integer> y = new ArrayList<Integer>();
	
	
	public Peterka(List<Integer> zmagovalna_mnozica_x, List<Integer> zmagovalna_mnozica_y) {
		super();
		this.x = zmagovalna_mnozica_x;
		this.y = zmagovalna_mnozica_y;
	}
	
	public int getZacetekX() {
		return x.get(0) ;
	}
	
	public int getZacetekY() {
		return y.get(0) ;
	}
	
	public int getKonecX() {
		return x.get(Igra.M-1) ;
	}
	
	public int getKonecY() {
		return y.get(Igra.M-1) ;
	}
}
