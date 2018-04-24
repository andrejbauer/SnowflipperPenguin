
public class Igra {
	
	// Igralna plošèa je velikosti N x N.
	
	public static final int N = 19;
	
	// število istih krogcev ki jih moramo imeti v vrstici/stolpcu/diagonali da zmagamo.
	
	public static final int M = 5;
	
	// Atributi ki jih ima igra.
	
	private Polje[][] igralna_plošèa;
	private Igralec na_potezi;
	
	// Ustvarimo novo igralno plošèo dimenzije N x N.
	// z i oznaèimo stolpce, z j pa vrstice.
	
	public void zaèetek_igre() {
		igralna_plošèa = new Polje[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					igralna_plošèa[i][j] = Polje.Prazno;
				}
			}
		na_potezi = Igralec.È;
	}
	
	// Odigramo potezo.
	
	public boolean odigrajPotezo(Poteza p) {
		if (igralna_plošèa[p.getX()][p.getY()] == Polje.Prazno) {
			igralna_plošèa[p.getX()][p.getY()] = na_potezi.getPolje();
			
			
			
			if(aliJeKdoZmagal(p)) {
				System.out.println("GJ zmagu si");
			} else {
			na_potezi = na_potezi.nasprotnik();
			}
			
			// Pogledamo ali obstaja še kaka poteza
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(igralna_plošèa[i][j] == Polje.Prazno) {
						break;
					} else {
						System.out.println("Igra se je konèala neodloèeno");
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
		
		int S = 0; // Koliko smo jih že našli v vrsti/stoplcu/diagonali
		
		// vektorji (0, 1), (1, 1), (1, 0), (1, -1)
		
		// Pregled na X osi
		
		for (int i = 0; i < M; i++) {
			if(igralna_plošèa[p.getX() + i][p.getY()] == na_potezi.getPolje()) {
				S++;
			} else {
				break;
			}
		}
		
		for (int i = 0; i < M; i++) {
			if(igralna_plošèa[p.getX() - i][p.getY()] == na_potezi.getPolje()) {
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
			if(igralna_plošèa[p.getX() + i][p.getY() + i] == na_potezi.getPolje()) {
				S++;
			} else {
				break;
			}
		}
		
		for (int i = 0; i < M; i++) {
			if(igralna_plošèa[p.getX() - i][p.getY() - i] == na_potezi.getPolje()) {
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
			if(igralna_plošèa[p.getX()][p.getY() + i] == na_potezi.getPolje()) {
				S++;
			} else {
				break;
			}
		}
		
		for (int i = 0; i < M; i++) {
			if(igralna_plošèa[p.getX()][p.getY() - i] == na_potezi.getPolje()) {
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
			if(igralna_plošèa[p.getX() + i][p.getY() - i] == na_potezi.getPolje()) {
				S++;
			} else {
				break;
			}
		}
		
		for (int i = 0; i < M; i++) {
			if(igralna_plošèa[p.getX() - i][p.getY() + i] == na_potezi.getPolje()) {
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
