import java.util.LinkedList;
import java.util.List;

public class Igra {
		
	// Število istih krogcev ki jih moramo imeti v vrstici/stolpcu/diagonali da zmagamo.
	
	public static final int M = 5;
	
	// Ustvarimo vektorje s katerimi bomo kasneje preverjali ali imamo 5 v vrsto ali ne.
	
	private static final List<Vektor> smeri = new LinkedList<Vektor>();
	
	// Atributi ki jih ima igra.
	
	private Plosca igralna_plošèa;
	private Igralec na_potezi;
	
	// Dodamo vektorje za preverajanje
	
	{
	
		smeri.add(new Vektor(0, 1));
		smeri.add(new Vektor(1, 1));
		smeri.add(new Vektor(1, 0));
		smeri.add(new Vektor(1, -1));
	
	}
	
	// Ustvarimo novo igralno plošèo dimenzije N x N.
	// z i oznaèimo stolpce, z j pa vrstice.
	
	public Igra() {

		igralna_plošèa = new Plosca();
			for (int i = 0; i < Plosca.N; i++) {
				for (int j = 0; j < Plosca.N; j++) {
					igralna_plošèa.setPlosca(i,j,Polje.PRAZNO);
				}
			}
		na_potezi = Igralec.CRNI;
	}
	

	// Odigramo potezo.
	
	public boolean odigrajPotezo(Poteza p) {
		if (igralna_plošèa.getPlosca(p.getX(),p.getY()) == Polje.PRAZNO) {
			igralna_plošèa.setPlosca(p.getX(),p.getY(),na_potezi.getPolje());
			
			System.out.println(na_potezi.getPolje());
			System.out.println(p.getX());
			System.out.println(p.getY());
			
			if(aliJeKdoZmagal(p.getX(), p.getY())) {
				System.out.println("GJ" + " " + na_potezi + " " + "zmagu si");
				System.exit(1);
			} else {
			na_potezi = na_potezi.nasprotnik();
			}
			
			// Pogledamo ali obstaja še kaka poteza
			
			if (!aliObstajaŠeKakšnaPoteza()) {
				System.out.println("Igra se je konèala neodloèeno");
			}
			
			return true;
		}
		else {
			return false;
		}
	}
	
	// Ali obstaja še kaka poteza.
	
	public boolean aliObstajaŠeKakšnaPoteza() {
		
		for (int i = 0; i < Plosca.N; i++) {
			for (int j = 0; j < Plosca.N; j++) {
				if(igralna_plošèa.getPlosca(i,j) == Polje.PRAZNO) {
					return true;
				} 
			}
		}
		return false;
	}
	
	// Preverimo ali je kdo zmagal.
	
	public boolean aliJeKdoZmagal(int a, int b) {

		// (a, b) koordinate polja 
		
		int x; // x koordinata polja ki ga preverjamo
		int y; // y koordinata polja ki ga preverjamo
		
		
		// vektorji (0, 1), (1, 1), (1, 0), (1, -1)
		
		for(Vektor v : smeri) {
			
			int S = 0; // Koliko smo jih že našli v vrsti/stoplcu/diagonali
			
			for (int i =  -M; i < M; i++) {
				x = a + i*v.getX();
				y = b + i*v.getY();
				
				if ((0 <= x) && (x < Plosca.N) && (0 <= y) && (y < Plosca.N)) {
				
					if(igralna_plošèa.getPlosca(x, y) == na_potezi.getPolje()) {
						S++;
						System.out.println(S);
						if (S >= M) {
							return true;
						}
					} else {
						S = 0;
					}
				}
			}

		}
		return false;
	}
	
}
