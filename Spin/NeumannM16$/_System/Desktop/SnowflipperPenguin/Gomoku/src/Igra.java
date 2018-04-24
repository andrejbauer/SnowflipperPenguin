
public class Igra {
	
	// Igralna plošèa je velikosti N x N.
	
	public static final int N = 19;
	
	// število istih krogcev ki jih moramo imeti v vrstici/stolpcu/diagonali da zmagamo.
	
	public static final int M = 5;
	
	// Atributi ki jih ima igra.
	
	private Polje[][] igralna_plošèa;
	private Igralec na_potezi;
	
	// Ustvarimo novo igralno plošèo dimenzije N x N.
	// z i oznaèimo vrstice, z j pa stolpce.
	
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
			}else {
			na_potezi = na_potezi.nasprotnik();
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	// Preverimo ali je kdo zmagal.
	
	public boolean aliJeKdoZmagal(Poteza p) {
		
		// definiri smeri v kater je treba pregledat (8 smeri) in se zapelji èez nje in preglej do M (5) koordinat.
		
		return false;
	}
	
}
