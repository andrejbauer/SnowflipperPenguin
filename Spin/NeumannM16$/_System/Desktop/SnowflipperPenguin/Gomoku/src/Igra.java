
public class Igra {
	
	// Igralna plo��a je velikosti N x N.
	
	public static final int N = 19;
	
	// �tevilo istih krogcev ki jih moramo imeti v vrstici/stolpcu/diagonali da zmagamo.
	
	public static final int M = 5;
	
	// Atributi ki jih ima igra.
	
	private Polje[][] igralna_plo��a;
	private Igralec na_potezi;
	
	// Ustvarimo novo igralno plo��o dimenzije N x N.
	// z i ozna�imo vrstice, z j pa stolpce.
	
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
		
		// definiri smeri v kater je treba pregledat (8 smeri) in se zapelji �ez nje in preglej do M (5) koordinat.
		
		return false;
	}
	
}
