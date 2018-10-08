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
	public static final int ZMAGA = 10000000; // vrednost zmage, ve� kot vsaka druga ocena pozicije
	public static final int ZGUBA = -ZMAGA;  // vrednost izgube, mora biti -ZMAGA
	public static final int NEODLOCENO = 0; // vrednost neodlo�ene igre
	
	// Točkovanje ki smo si ga zmislili

	public static final int[] OCENA = {0, ZMAGA/100000, ZMAGA/10000, ZMAGA/1000, ZMAGA/100, ZMAGA/10};
	
	// Ustvarimo vektorje s katerimi bomo kasneje preverjali ali imamo 5 v vrsto ali ne.
	
	private static final List<Vektor> smeri = new LinkedList<Vektor>();
	

	static {
		
		smeri.add(new Vektor(0, 1));
		smeri.add(new Vektor(1, 1));
		smeri.add(new Vektor(1, 0));
		smeri.add(new Vektor(1, -1));
	
	}
	
	public static int ocenaPlosce(Igralec naPotezi, Igralec kogaIgramo, Igra igra){
		
		Plosca plosca = igra.igralna_plosca;
		int ocena = 0;

		int xKon = Plosca.N;
		int xZac = 0;
		int yKon = Plosca.N;
		int yZac = 0;

//		int xZac = Plosca.N;	// kje bomo začeli iskati na x osi
//		int xKon = 0;			// kje bomo končali iskati na x osi
//		int yZac = Plosca.N;	// kje bomo začeli iskati na y osi
//		int yKon = 0;			// kje bomo končali iskati na y osi
//		
//		for(Poteza p : igra.odigrane){
//			if (p.getX() < xZac) {
//				xZac = p.getX();
//			}
//			if (p.getX() > xKon) {
//				xKon = p.getX();
//			}
//			if (p.getY() < yZac) {
//				yZac = p.getY();
//			}
//			if (p.getY() > yKon) {
//				yKon = p.getY();
//			}
//		}
//		
//		if (xZac - 4 < 0) {
//			xZac = xZac - 4;
//		}
//		else {
//			xZac = 0;
//		}
//		
//		if(yZac - 4 > 0) {
//			yZac = yZac - 4;
//		}
//		else {
//			yZac = 0;
//		}
		
		for (int i = xZac; i < xKon; i++){
			for (int j = yZac; j < yKon; j++){

				for (Vektor v : smeri){
					if ((0 <= i + 4*v.getX()) && (i + 4*v.getX() < Plosca.N) && (0 <= j + 4*v.getY()) && (j + 4*v.getY() < Plosca.N)) {
						int stNaPotezi = 0;
						int stNiNaPotezi = 0;

						for (int a = 0; a < Igra.M; a++) {
							int x = i + a*v.getX();
							int y = j + a*v.getY();			

							if (plosca.getPlosca(x, y) == naPotezi.nasprotnik().getPolje()) {
								stNiNaPotezi ++;
							} else if (plosca.getPlosca(x, y) == naPotezi.getPolje()) {
								stNaPotezi++;
							}	
						}
						if (stNaPotezi > 0 && stNiNaPotezi == 0) {
//							if (stNaPotezi == 4) {
//								System.out.println("stNaPotezi == 4 na polju (" + i + ", " + j + ") smer (" + v.getX() + ", " + v.getY() + ")");
//								System.out.println("kogaIgramo == " + kogaIgramo);
//								System.out.println("naPotezi == " + naPotezi);
//							}
							// tisti, ki je na potezi, ima uporaben petorček
							if (naPotezi == kogaIgramo) {
								// mi smo na potezi
								// mi imamo uporaben petorček in smo na potezi
								ocena +=  OCENA[stNaPotezi];
							} else {
								// nasprotnik je na potezi
								// nasprotnik ima uporaben petorček in je na potezi
								ocena -= OCENA[stNaPotezi];								
							}
						} else if (stNiNaPotezi > 0 && stNaPotezi == 0) {
							// tisti, ki ni na potezi, ima uporaben petorček
//							if (stNiNaPotezi == 4) {
//								System.out.println("stNiNaPotezi == 4 na polju (" + i + ", " + j + ") smer (" + v.getX() + ", " + v.getY() + ")");
//								System.out.println("kogaIgramo == " + kogaIgramo);
//								System.out.println("naPotezi == " + naPotezi);
//							}
							if (naPotezi == kogaIgramo) {
								// mi smo na potezi
								// mi smo na potezi, a nasprotnik ima uporaben petorček
								ocena -= OCENA[stNiNaPotezi] / 2;
							} else {
								// nasprotnik je na potezi
								// nasprotnik je na potezi, a mi imamo uporaben petorček
								ocena += OCENA[stNiNaPotezi] / 2;
							}
						}
					}
				}
			}
		}
		return ocena;
	}
}
