
public class Igra {
	
	// Igralna plo��a je velikosti N x N.
	
	public static final int N = 19;
	
	// �tevilo istih krogcev ki jih moramo imeti v vrstici/stolpcu/diagonali da zmagamo.
	
	public static final int M = 5;
	
	// Atributi ki jih ima igra.
	
	private Polje[][] igralna_plo��a;
	private Igralec na_potezi;
	
	// Ustvarimo novo igralno plo��o dimenzije N x N.
	// z i ozna�imo stolpce, z j pa vrstice.
	
	public void za�etek_igre() {
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
			
			
			
			if(aliJeKdoZmagal(p)) {
				System.out.println("GJ zmagu si");
			} else {
			na_potezi = na_potezi.nasprotnik();
			}
			
			// Pogledamo ali obstaja �e kaka poteza
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(igralna_plo��a[i][j] == Polje.Prazno) {
						break;
					} else {
						System.out.println("Igra se je kon�ala neodlo�eno");
					}
				}
			}
			
			return true;
		}
		else {
			return false;
		}
	}
	
	// Preverimo ali je kdo zmagal.
	
	public boolean aliJeKdoZmagal(Poteza p) {
		
		int S = 0; // Koliko smo jih �e na�li v vrsti/stoplcu/diagonali
		
		// vektorji (0, 1), (1, 1), (1, 0), (1, -1)
		
		// Pregled na X osi
		
		for (int i = 0; i < M; i++) {
			if(igralna_plo��a[p.getX() + i][p.getY()] == na_potezi.getPolje()) {
				S++;
			} else {
				break;
			}
		}
		
		for (int i = 0; i < M; i++) {
			if(igralna_plo��a[p.getX() - i][p.getY()] == na_potezi.getPolje()) {
				S++;
			} else {
				break;
			}
		}
		
		if (S >= 5) {
			return true;
		} else {
			S = 0;
		}
		
		// Pregled na premici y = x
		
		for (int i = 0; i < M; i++) {
			if(igralna_plo��a[p.getX() + i][p.getY() + i] == na_potezi.getPolje()) {
				S++;
			} else {
				break;
			}
		}
		
		for (int i = 0; i < M; i++) {
			if(igralna_plo��a[p.getX() - i][p.getY() - i] == na_potezi.getPolje()) {
				S++;
			} else {
				break;
			}
		}
		
		if (S >= 5) {
			return true;
		} else {
			S = 0;
		}
		
		// Pregled na y osi
		
		for (int i = 0; i < M; i++) {
			if(igralna_plo��a[p.getX()][p.getY() + i] == na_potezi.getPolje()) {
				S++;
			} else {
				break;
			}
		}
		
		for (int i = 0; i < M; i++) {
			if(igralna_plo��a[p.getX()][p.getY() - i] == na_potezi.getPolje()) {
				S++;
			} else {
				break;
			}
		}
		
		if (S >= 5) {
			return true;
		} else {
			S = 0;
		}
		
		// Pregled na premici y = -x
		
		for (int i = 0; i < M; i++) {
			if(igralna_plo��a[p.getX() + i][p.getY() - i] == na_potezi.getPolje()) {
				S++;
			} else {
				break;
			}
		}
		
		for (int i = 0; i < M; i++) {
			if(igralna_plo��a[p.getX() - i][p.getY() + i] == na_potezi.getPolje()) {
				S++;
			} else {
				break;
			}
		}
		
		if (S >= 5) {
			return true;
		} else {
			S = 0;
		}
		
		return false;
	}
	
	 
	
}
