
public class Igra {
	
	// Igralna plošèa je velikosti N x N.
	
	public static final int N = 19;
		
	
	private Polje[][] igralna_plošèa;
	private Igralec na_potezi;
	
	
	public void zaèetek_igre() {
		// Ustvarimo novo igralno plošèo dimenzije N x N.
		igralna_plošèa = new Polje[N][N];
		// z i oznaèimo vrstice, z j pa stolpce.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					igralna_plošèa[i][j] = Polje.Prazno;
				}
			}
		na_potezi = Igralec.È;
	}
	
	
	
}
