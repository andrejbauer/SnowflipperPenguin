
public class Igra {
	
	// Igralna plo��a je velikosti N x N.
	
	public static final int N = 19;
		
	
	private Plo��a[][] igralna_plo��a;
	private Igralec na_potezi;
	
	
	public void za�etek_igre() {
		// Ustvarimo novo igralno plo��o dimenzije N x N.
		igralna_plo��a = new Plo��a[N][N];
		// z i ozna�imo vrstice, z j pa stolpce.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					igralna_plo��a[i][j] = Plo��a.Prazno;
				}
			}
		na_potezi = Igralec.�;
	}
	
	
	
}
