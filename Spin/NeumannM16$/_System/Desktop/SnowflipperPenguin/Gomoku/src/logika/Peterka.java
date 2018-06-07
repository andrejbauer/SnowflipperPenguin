package logika;

import java.util.ArrayList;
import java.util.List;

public class Peterka {
	
	// Peterka je sestavljena iz dveh listov (1 list x-ov in 1 list y-onov)
	
	public List<Integer> x = new ArrayList<Integer>();
	public List<Integer> y = new ArrayList<Integer>();
	
	
	public Peterka(List<Integer> zmagovalna_mnozica_x, List<Integer> zmagovalna_mnozica_y) {
		super();
		this.x = zmagovalna_mnozica_x;
		this.y = zmagovalna_mnozica_y;
	}
	
	// Vrne listo x-ov
	
	public List<Integer> xMnozica(){
		return x;
	}
	
	// Vrne listo y-onov
	
	public List<Integer> yMnozica(){
		return y;
	}
	
	// Vrne prvi element x liste
	
	public int getZacetekX() {
		return x.get(0) ;
	}
	
	// Vrne prvi element y liste
	
	public int getZacetekY() {
		return y.get(0) ;
	}
	
	// Vrne zadnji element x liste
	
	public int getKonecX() {
		return x.get(Igra.M-1) ;
	}
	
	// Vrne zadnji element y liste
	
	public int getKonecY() {
		return y.get(Igra.M-1) ;
	}
}
