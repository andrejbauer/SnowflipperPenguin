package logika;

import java.util.LinkedList;
import java.util.List;

public class Igra {
		
	// stevilo istih krogcev ki jih moramo imeti v vrstici/stolpcu/diagonali da zmagamo.
	
	public static final int M = 5;
	
	// Ustvarimo vektorje s katerimi bomo kasneje preverjali ali imamo 5 v vrsto ali ne.
	
	private static final List<Vektor> smeri = new LinkedList<Vektor>();
	
	// Atributi ki jih ima igra.
	
	private Plosca igralna_plosca;
	private Igralec na_potezi;
	
	// Dodamo vektorje za preverajanje
	
	{
	
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
		na_potezi = Igralec.CRNI;
	}
	
	public Igra(Igra igra) {
		Plosca plosca = new Plosca();
		for (int i = 0; i < Plosca.N; i++) {
			for (int j = 0; j < Plosca.N; j++) {
				plosca.setPlosca(i, j, igra.igralna_plosca.getPlosca(i, j));
			}
		}
		this.na_potezi = igra.na_potezi;
	}

	// Odigramo potezo.
	
	public boolean odigrajPotezo(Poteza p) {
		if (igralna_plosca.getPlosca(p.getX(),p.getY()) != Polje.PRAZNO) {
			return false;
		} else {
			igralna_plosca.setPlosca(p.getX(),p.getY(),na_potezi.getPolje());
			
			if(aliJeKdoZmagal(p.getX(), p.getY())) {
				System.out.println("GJ" + " " + na_potezi + " " + "zmagu si");
			} else {
			na_potezi = na_potezi.nasprotnik();
			}
			
			// Pogledamo ali obstaja se kaka poteza
			
			if (!aliObstajaseKaksnaPoteza()) {
				System.out.println("Igra se je koncala neodloceno");
			}
			
			return true;
		}
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
		return false;
	}
	
	// Preverimo ali je kdo zmagal.
	
	public boolean aliJeKdoZmagal(int a, int b) {

		// (a, b) koordinate polja 
		int x; // x koordinata polja ki ga preverjamo
		int y; // y koordinata polja ki ga preverjamo
		
		
		// vektorji (0, 1), (1, 1), (1, 0), (1, -1)
		
		for(Vektor v : smeri) {
			

			int S = 0; // Koliko smo jih že našli v vrsti/stoplcu/diagonali

			
			for (int i =  -M; i < M; i++) {
				x = a + i*v.getX();
				y = b + i*v.getY();
				
				if ((0 <= x) && (x < Plosca.N) && (0 <= y) && (y < Plosca.N)) {
				
					if(igralna_plosca.getPlosca(x, y) == na_potezi.getPolje()) {
						S++;
						if (S >= M) {
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
		if (!aliObstajaseKaksnaPoteza()) {
			return Stanje.NEODLOCENO;
		} else if (na_potezi == Igralec.CRNI) {
			return Stanje.NA_POTEZI_CRNI;
		} else  {
			return Stanje.NA_POTEZI_BELI;
		}
		
	}
	
}
