import java.util.LinkedList;
import java.util.List;

public class Igra {
	
	// Igralna plo��a je velikosti N x N.
	
	public static final int N = 19;
	
	// �tevilo istih krogcev ki jih moramo imeti v vrstici/stolpcu/diagonali da zmagamo.
	
	public static final int M = 5;
	
	// Ustvarimo vektorje s katerimi bomo kasneje preverjali ali imamo 5 v vrsto ali ne.
	
	private static final List<Vektor> vektorji_za_preverjanje = new LinkedList<Vektor>();
	
	// Atributi ki jih ima igra.
	
	private Polje[][] igralna_plo��a;
	private Igralec na_potezi;
	
	// Dodamo vektorje za preverajanje
	
	public void dodamoVektorje() {
		vektorji_za_preverjanje.add(new Vektor(0, 1));
		vektorji_za_preverjanje.add(new Vektor(1, 1));
		vektorji_za_preverjanje.add(new Vektor(1, 0));
		vektorji_za_preverjanje.add(new Vektor(1, -1));
	}
	
	// Ustvarimo novo igralno plo��o dimenzije N x N.
	// z i ozna�imo stolpce, z j pa vrstice.
	
	public void za�etek_igre() {
		dodamoVektorje();
		igralna_plo��a = new Polje[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					igralna_plo��a[i][j] = Polje.Prazno;
				}
			}
		na_potezi = Igralec.�;
	}
	
	// Odigramo potezo.
	
	public boolean odigrajPotezo(Poteza p) {
		if (igralna_plo��a[p.getX()][p.getY()] == Polje.Prazno) {
			igralna_plo��a[p.getX()][p.getY()] = na_potezi.getPolje();
			
			System.out.println(na_potezi.getPolje());
			System.out.println(p.getX());
			System.out.println(p.getY());
			
			if(aliJeKdoZmagal(p)) {
				System.out.println("GJ" + " " + na_potezi + " " + "zmagu si");
			} else {
			na_potezi = na_potezi.nasprotnik();
			}
			
			// Pogledamo ali obstaja �e kaka poteza
			
			if (!aliObstaja�eKak�naPoteza()) {
				System.out.println("Igra se je kon�ala neodlo�eno");
			}
			
			return true;
		}
		else {
			return false;
		}
	}
	
	// Ali obstaja �e kaka poteza.
	
	public boolean aliObstaja�eKak�naPoteza() {
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(igralna_plo��a[i][j] == Polje.Prazno) {
					return true;
				} 
			}
		}
		return false;
	}
	
	// Preverimo ali je kdo zmagal.
	
	public boolean aliJeKdoZmagal(Poteza p) {
		
		int S = 1; // Koliko smo jih �e na�li v vrsti/stoplcu/diagonali
		int x; // x koordinata polja ki ga preverjamo
		int y; // y koordinata polja ki ga preverjamo
		
		
		// vektorji (0, 1), (1, 1), (1, 0), (1, -1)
		
		for(Vektor v : vektorji_za_preverjanje) {
			
			for (int i = 1; i < M; i++) {
				x = p.getX() + i*v.getX();
				y = p.getY() + i*v.getY();
				
				if ((0 <= x) && (x < N) && (0 <= y) && (y < N)) {
				
					if(igralna_plo��a[x][y] == na_potezi.getPolje()) {
						S++;
					} else {
						break;
					}
				} else {
					break;
				}
			}
			
			for (int i = 1; i < M; i++) {
				x = p.getX() - i*v.getX();
				y = p.getY() - i*v.getY();
				
				if ((0 <= x) && (x < N) && (0 <= y) && (y < N)) {
				
					if(igralna_plo��a[x][y] == na_potezi.getPolje()) {
						S++;
					} else {
						break;
					}
				} else {
					break;
				}
			}
			
			System.out.println(S);
			
			if (S >= M) {
				
				return true;
			} else {
				S = 1;
			}
		}
		
		return false;
	}
	
	
}
