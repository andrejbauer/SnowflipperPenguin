
public class Igra {
	
	// Igralna plošèa je velikosti N x N.
	
	public static final int N = 19;
		
	
	private Plošèa[][] igralna_plošèa;
	private Igralec na_potezi;
	
	
	public void zaèetek_igre() {
		// Ustvarimo novo igralno plošèo dimenzije N x N.
		igralna_plošèa = new Plošèa[N][N];
		// z i oznaèimo vrstice, z j pa stolpce.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					igralna_plošèa[i][j] = Plošèa.Prazno;
				}
			}
		na_potezi = Igralec.È;
	}
	
	
	
}
