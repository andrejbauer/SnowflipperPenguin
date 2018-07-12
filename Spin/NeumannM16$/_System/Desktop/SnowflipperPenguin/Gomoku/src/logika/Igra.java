package logika;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Inteligenca.Ocena;
import Inteligenca.OcenjenaPoteza;

public class Igra {
		
	// stevilo istih krogcev ki jih moramo imeti v vrstici/stolpcu/diagonali da zmagamo.
	
	public static final int M = 5;
	
	// Ustvarimo vektorje s katerimi bomo kasneje preverjali ali imamo 5 v vrsto ali ne.
	
	private static final List<Vektor> smeri = new LinkedList<Vektor>();
	
	// Zmagovalna peterka
	
	public static Peterka zmagovalna_peterka;
	
	public List<Poteza> odigrane =  new LinkedList<Poteza>();
	
	// Atributi ki jih ima igra.
	
	public Plosca igralna_plosca;
	private Stanje stanje;
	
	// Dodamo vektorje za preverajanje
	
	
	static {
		
		smeri.add(new Vektor(0, 1));
		smeri.add(new Vektor(1, 1));
		smeri.add(new Vektor(1, 0));
		smeri.add(new Vektor(1, -1));
	
	}
	
	// Ustvarimo novo igralno plosco dimenzije N x N.
	// z i oznacimo stolpce, z j pa vrstice.
	
	public Igra() {

		igralna_plosca = new Plosca();
			for (int i = 0; i < Plosca.N; i++) {
				for (int j = 0; j < Plosca.N; j++) {
					igralna_plosca.setPlosca(i,j,Polje.PRAZNO);
				}
			}
		zmagovalna_peterka = null;
		stanje = Stanje.NA_POTEZI_CRNI;
	}
	
	// Ustvari novo igro, ki je kopija podane igre.
	
	public Igra(Igra igra) {
		Plosca plosca = new Plosca();
		for (int i = 0; i < Plosca.N; i++) {
			for (int j = 0; j < Plosca.N; j++) {
				plosca.setPlosca(i, j, igra.igralna_plosca.getPlosca(i, j));
			}
		}
		List<Poteza> odigrane =  new LinkedList<Poteza>();
		for (Poteza p : igra.odigrane){
			odigrane.add(p);
		}
		
		this.zmagovalna_peterka = igra.zmagovalna_peterka;
		this.odigrane = odigrane;
		
		switch (igra.stanje()) {
		case NA_POTEZI_CRNI : this.stanje = Stanje.NA_POTEZI_CRNI; break;
		case NA_POTEZI_BELI : this.stanje = Stanje.NA_POTEZI_BELI; break;
		case ZMAGA_CRNI : this.stanje = Stanje.ZMAGA_CRNI; break;
		case ZMAGA_BELI : this.stanje = Stanje.ZMAGA_BELI; break;
		case NEODLOCENO : this.stanje = Stanje.NEODLOCENO; break;
		}
		
//		this.stanje = igra.stanje;
		this.igralna_plosca = plosca;
	}

	// Odigramo potezo.
	
	public boolean odigrajPotezo(Poteza p) {
		
		if (stanje != Stanje.ZMAGA_BELI && stanje != Stanje.ZMAGA_CRNI) {
			
			if (igralna_plosca.getPlosca(p.getX(),p.getY()) != Polje.PRAZNO) {
				return false;
			} else {
				igralna_plosca.setPlosca(p.getX(),p.getY(), naPotezi().getPolje());
				
				odigrane.add(new Poteza(p.getX(), p.getY()));
				
				if(aliJeKdoZmagal(p.getX(), p.getY())) {
					if (naPotezi() == Igralec.CRNI){
						stanje = Stanje.ZMAGA_CRNI;
					} else {
						stanje = Stanje.ZMAGA_BELI;
					}
					
				} else {
				
				switch (naPotezi()) {
				case CRNI: stanje = Stanje.NA_POTEZI_BELI; break;
				case BELI: stanje = Stanje.NA_POTEZI_CRNI; break;
				default: break;
				
				}
				
				}
				
				// Pogledamo ali obstaja se kaka poteza
				
				if (!aliObstajaseKaksnaPoteza()) {
					System.out.println("Igra se je koncala neodloceno");
					stanje = Stanje.NEODLOCENO;
				}
			}
		return true;
		}
		return false;
	}
	
	// Ali obstaja se kaka poteza.
	
	public boolean aliObstajaseKaksnaPoteza() {
		
		for (int i = 0; i < Plosca.N; i++) {
			for (int j = 0; j < Plosca.N; j++) {
				if(igralna_plosca.getPlosca(i,j) == Polje.PRAZNO) {
					return true;
				} 
			}
		}
		
		switch (stanje) {
		case ZMAGA_CRNI: break;
		case ZMAGA_BELI: break;
		default: stanje = Stanje.NEODLOCENO;
		} 

		return false;
	}
	
	// Preverimo ali je kdo zmagal.
	
	public boolean aliJeKdoZmagal(int a, int b) {

		// (a, b) koordinate polja 
		int x; // x koordinata polja ki ga preverjamo
		int y; // y koordinata polja ki ga preverjamo
		
		
		// vektorji (0, 1), (1, 1), (1, 0), (1, -1)
		
		for(Vektor v : smeri) {

			int S = 0; // Koliko smo jih �e na�li v vrsti/stoplcu/diagonali

			
			for (int i =  -M; i < M; i++) {
				x = a + i*v.getX();
				y = b + i*v.getY();
				
				if ((0 <= x) && (x < Plosca.N) && (0 <= y) && (y < Plosca.N)) {
				
					if(igralna_plosca.getPlosca(x, y) == naPotezi().getPolje()) {
						S++;
						if (S >= M) {
							if (stanje == stanje.NA_POTEZI_CRNI) {
								stanje = Stanje.ZMAGA_CRNI;
							} else {
								stanje = Stanje.ZMAGA_BELI;
							}
							
							List<Integer> zmagovalna_mnozica_x = new ArrayList<Integer>();
							List<Integer> zmagovalna_mnozica_y = new ArrayList<Integer>();
							
							
							for (int j = -M + 1; j < 1; j++) {
								zmagovalna_mnozica_x.add(x + j*v.getX());
								zmagovalna_mnozica_y.add(y + j*v.getY());
							}
							
							// Ustvarimo zmagovalno peterko.
							
							zmagovalna_peterka = new Peterka(zmagovalna_mnozica_x, zmagovalna_mnozica_y);
							
							return true;
						}
					} else {
						S = 0;
					}
				}
			}

		}
		return false;
	}
	
	public Polje[][] getPlosca() {
		return igralna_plosca.plosca;
	}
	
	public Stanje stanje() {
		return stanje;	
	}
	
	// Vrne listo možnih potez
	
	public LinkedList<Poteza> moznePoteze(){
		LinkedList<Poteza> moznePoteze = new LinkedList<Poteza>();
		
		for (int i = 0; i < Plosca.N; i++) {
			for (int j = 0; j < Plosca.N; j++) {
				if(igralna_plosca.getPlosca(i, j) == Polje.PRAZNO) {
					moznePoteze.add(new Poteza(i, j));
				} 
			}
		}
		return moznePoteze;
	}
	
	// Vrne listo optimalnih potez (poteze okrog tistih ki smo jih že igrali).
	
	public LinkedList<Poteza> optimalnePoteze(){
		
		LinkedList<Poteza> optimalnePoteze =  new LinkedList<Poteza>();
		boolean aliJePotezaZeNoter = false;
		
		for (Poteza p : odigrane){
			for (Vektor v : smeri){
				aliJePotezaZeNoter = false;
				int x = p.getX() + v.getX();
				int y = p.getY() + v.getY();
				
				if ((0 <= x) && (x < Plosca.N) && (0 <= y) && (y < Plosca.N)){
				
					if (igralna_plosca.getPlosca(x, y) == Polje.PRAZNO){
						
						for (Poteza q : optimalnePoteze){
							if (optimalnePoteze.isEmpty()){
								optimalnePoteze.add(new Poteza(x, y));
		//						System.out.println(optimalnePoteze);
								aliJePotezaZeNoter = true;
								break;
							}
							
							if ((new Poteza(x, y)).aliStaPoteziEnaki(q)){
								aliJePotezaZeNoter = true;
								break;
							}
						}
						
						if (!aliJePotezaZeNoter){
							optimalnePoteze.add(new Poteza(x, y));
							aliJePotezaZeNoter = false;
						}		
					}
				}
				aliJePotezaZeNoter = false;
					x = p.getX() - v.getX();
					y = p.getY() - v.getY();
					
				if ((0 <= x) && (x < Plosca.N) && (0 <= y) && (y < Plosca.N)){
					
					if (igralna_plosca.getPlosca(x, y) == Polje.PRAZNO){
						
						for (Poteza q : optimalnePoteze){
							if (optimalnePoteze.isEmpty()){
								optimalnePoteze.add(new Poteza(x, y));
								System.out.println(optimalnePoteze);
								aliJePotezaZeNoter = true;
								break;
							}
							
							if ((new Poteza(x, y)).aliStaPoteziEnaki(q)){
								aliJePotezaZeNoter = true;
								break;
							}
						}
						
						if (!aliJePotezaZeNoter){
							optimalnePoteze.add(new Poteza(x, y));
							aliJePotezaZeNoter = false;
						}
					}
				}
			}
		}
		
		if (odigrane.isEmpty()){
			
			optimalnePoteze.add(new Poteza(9, 9));
		}
	
		
		for (Poteza p : optimalnePoteze){
			System.out.print("( " + p.getX() + ", " + p.getY() + " ) ");
		}
		System.out.println("Odigrane: ");
		for (Poteza p : odigrane){
//			System.out.println("Odigrane: ");
			System.out.print("( " + p.getX() + ", " + p.getY() + " ) ");
		}
		
		
		return optimalnePoteze;
	}
	
	// Vrne kdo je na potezi (kliči samo če si prepričan da je nekdo na potezi)
	
	public Igralec naPotezi() {
		if(stanje == stanje.NA_POTEZI_BELI){
			return Igralec.BELI;
		} else {
			return Igralec.CRNI;
		}
	}
	
	
}
