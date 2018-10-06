package Inteligenca;

import java.util.LinkedList;
import java.util.List;

import logika.Igra;
import logika.Igralec;
import logika.Plosca;
import logika.Polje;
import logika.Vektor;
import logika.Poteza;

public class Ocena {
	public static final int ZMAGA = 100000000; // vrednost zmage, ve� kot vsaka druga ocena pozicije
	public static final int ZGUBA = -ZMAGA;  // vrednost izgube, mora biti -ZMAGA
	public static final int NEODLOCENO = 0; // vrednost neodlo�ene igre
	
	// Točkovanje ki smo si ga zmislili

	public static final int[] OCENA = {0, 1, 10, 100, 1000, ZMAGA};
	
	// Ustvarimo vektorje s katerimi bomo kasneje preverjali ali imamo 5 v vrsto ali ne.
	
	private static final List<Vektor> smeri = new LinkedList<Vektor>();
	

	static {
		
		smeri.add(new Vektor(0, 1));
		smeri.add(new Vektor(1, 1));
		smeri.add(new Vektor(1, 0));
		smeri.add(new Vektor(1, -1));
	
	}
	
	public static int ocenaPlosce(Igralec naPotezi, Igra igra){
		
		Plosca plosca = igra.igralna_plosca;
		int ocena = 0;
		int prazno = 0;
		int stNasprotnikovih = 0;
		boolean nasliSmoNasprotnika = false;
		
		int xZac = Plosca.N;	// kje bomo začeli iskati na x osi
		int xKon = 0;			// kje bomo končali iskati na x osi
		int yZac = Plosca.N;	// kje bomo začeli iskati na y osi
		int yKon = 0;			// kje bomo končali iskati na y osi
		
		for(Poteza p : igra.odigrane){
			if (p.getX() < xZac) {
				xZac = p.getX();
			}
			if (p.getX() > xKon) {
				xKon = p.getX();
			}
			if (p.getY() < yZac) {
				yZac = p.getY();
			}
			if (p.getY() > yKon) {
				yKon = p.getY();
			}
		}
		
		if (xKon + 4 < Plosca.N) {
			xKon = xKon + 4;
		}
		else {
			xKon = Plosca.N;
		}
		
		if(yZac - 4 > 0) {
			yZac = yZac - 4;
		}
		else {
			yZac = 0;
		}
		
		for (int i = xZac; i < xKon; i++){
			for (int j = yZac; j < yKon; j++){
				
				for (Vektor v : smeri){
					if ((0 <= i + 4*v.getX()) && (i + 4*v.getX() < Plosca.N) && (0 <= j + 4*v.getY()) && (j + 4*v.getY() < Plosca.N)) {
						
					for (int a = 0; a < Igra.M; a++) {
						
						int x = i + a*v.getX();
						int y = j + a*v.getY();			
							
							if(plosca.getPlosca(x, y) == naPotezi.nasprotnik().getPolje()) {
								stNasprotnikovih ++;
								nasliSmoNasprotnika = true;
							} else if (plosca.getPlosca(x, y) == Polje.PRAZNO) {
								prazno++;
							} else if (nasliSmoNasprotnika) {
								stNasprotnikovih = 0;
								prazno = 5;
								break;
							}		
						}
					
						ocena += OCENA[5-prazno];
						ocena -= OCENA[stNasprotnikovih];
						prazno = 0;
						stNasprotnikovih = 0;
						nasliSmoNasprotnika = false;
					}
				}
			}
		}
		return ocena;
	}
}
