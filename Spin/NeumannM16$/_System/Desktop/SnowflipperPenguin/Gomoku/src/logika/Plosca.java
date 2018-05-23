package logika;

import java.util.HashMap;

public class Plosca {
	// Igralna plo��a je velikosti N x N.
	
	public static final int N=19;
	
	//Definiramo plo��o
	
	public Polje [][] plosca;
	
	private HashMap<Integer, Integer> odprte_n_terice = new HashMap<Integer, Integer>();
	private HashMap<Integer, Integer> pol_odprte_n_terice = new HashMap<Integer, Integer>(); 
	
	//Definimano igralno plo��o
	
	protected Plosca() {
		super();
		plosca=new Polje [N][N];
	}
	
	public Polje[][] kopirajPlosco() {
		return this.plosca;
	}
	
	//Dobimo vsebino polja na koordinatah (x,y)
	
	public Polje getPlosca(int x, int y) {
		
		return plosca[x][y];
	}
	
	// Nastavimo vsebino polja na koordinatah (x,y)
	
	public void setPlosca(int x, int y, Polje f) {
		
		plosca[x][y]=f;
	}
	
	private void generirajHashMape(Igralec naPotezi) {
		odprte_n_terice.put(0, 0);
		odprte_n_terice.put(1, 0);
		odprte_n_terice.put(2, 0);
		odprte_n_terice.put(3, 0);
		odprte_n_terice.put(4, 0);
		odprte_n_terice.put(5, 0);

		pol_odprte_n_terice.put(0, 0);
		pol_odprte_n_terice.put(1, 0);
		pol_odprte_n_terice.put(2, 0);
		pol_odprte_n_terice.put(3, 0);
		pol_odprte_n_terice.put(4, 0);
		pol_odprte_n_terice.put(5, 0);
		
		// Navpi�e n terice
		
		for (int i = 0; i < N; i++) {
			
			// �tevilo najdenih zapored
			
			int S = 0;
			
			// Spremljamo ali je bilo prej�nje ne naPotezi.getPolje() polje prazno
			
			boolean prazno = false;
			
			for (int j = 0; j < N; j++) {
				if (plosca[i][j] == Polje.PRAZNO) {
					if(prazno) {
						odprte_n_terice.put(S, odprte_n_terice.get(S) + 1);
					} else {
						pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
					}
					prazno = true;
					S = 0;
				} else if (plosca[i][j] == naPotezi.getPolje()) {
					S++;
					if (i == N && prazno) {
						pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
					}
				} else {
					if (prazno) {
					pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
					prazno = false;
					}
					S = 0;
				}
			}
		}
		
		// Vodoravne n terice
		
		for (int j = 0; j < N; j++) {
			
			// �tevilo najdenih zapored
			
			int S = 0;
			
			// Spremljamo ali je bilo prej�nje ne naPotezi.getPolje() polje prazno
			
			boolean prazno = false;
			
			for (int i = 0; i < N; i++) {
				if (plosca[i][j] == Polje.PRAZNO) {
					if(prazno) {
						odprte_n_terice.put(S, odprte_n_terice.get(S) + 1);
					} else {
						pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
					}
					prazno = true;
					S = 0;
				} else if (plosca[i][j] == naPotezi.getPolje()) {
					S++;
					if (i == N && prazno) {
						pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
					}
				} else {
					if (prazno) {
					pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
					prazno = false;
					}
					S = 0;
				}
			}
		}
		
		// Diagonalne n terice levo zgoraj / desno dol, z za�etkom v zgornjih poljih
		
		for (int i = 0; i < N; i++) {
			
			// �tevilo najdenih zapored
			
			int S = 0;
			
			// Spremljamo ali je bilo prej�nje ne naPotezi.getPolje() polje prazno
			
			boolean prazno = false;
			
			for (int j = -N + 1; j < 0 - i; j++) {
				if (plosca[i + N - 1 + j][-j] == Polje.PRAZNO) {
					if(prazno) {
						odprte_n_terice.put(S, odprte_n_terice.get(S) + 1);
					} else {
						pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
					}
					prazno = true;
					S = 0;
				} else if (plosca[i + N - 1 + j][-j] == naPotezi.getPolje()) {
					S++;
					if (i == N && prazno) {
						pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
					}
				} else {
					if (prazno) {
					pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
					prazno = false;
					}
					S = 0;
				}
			}
		}
		
		
		// Diagonalne n terice levo zgoraj / desno dol, z za�etkom v levih poljih
		
		for (int j = -N + 2; j < 0; j++) {
			
			// �tevilo najdenih zapored
			
			int S = 0;
			
			// Spremljamo ali je bilo prej�nje ne naPotezi.getPolje() polje prazno
			
			boolean prazno = false;
			
			for (int i = 0; i < -j; i++) {
				if (plosca[i][-j - i] == Polje.PRAZNO) {
					if(prazno) {
						odprte_n_terice.put(S, odprte_n_terice.get(S) + 1);
					} else {
						pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
					}
					prazno = true;
					S = 0;
				} else if (plosca[i][-j - i] == naPotezi.getPolje()) {
					S++;
					if (i == N && prazno) {
						pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
					}
				} else {
					if (prazno) {
					pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
					prazno = false;
					}
					S = 0;
				}
			}
		}
		
		// Diagonalne n terice levo spodaj / desno gor, z za�etkom v spodnejih poljih
		
		for (int i = 0; i < N; i++) {
			
			// �tevilo najdenih zapored
			
			int S = 0;
			
			// Spremljamo ali je bilo prej�nje ne naPotezi.getPolje() polje prazno
			
			boolean prazno = false;
			
			for (int j = 0; j < N - i; j++) {
				if (plosca[i + j][j] == Polje.PRAZNO) {
					if(prazno) {
						odprte_n_terice.put(S, odprte_n_terice.get(S) + 1);
					} else {
						pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
					}
					prazno = true;
					S = 0;
				} else if (plosca[i + j][j] == naPotezi.getPolje()) {
					S++;
					if (i == N && prazno) {
						pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
					}
				} else {
					if (prazno) {
					pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
					prazno = false;
					}
					S = 0;
				}
			}
		}
		
		// Diagonalne n terice levo spodaj / desno gor, z za�etkom v levih poljih
		
				for (int j = 1; j < N; j++) {
					
					// �tevilo najdenih zapored
					
					int S = 0;
					
					// Spremljamo ali je bilo prej�nje ne naPotezi.getPolje() polje prazno
					
					boolean prazno = false;
					
					for (int i = 0; i < N - j; i++) {
						if (plosca[i][j + i] == Polje.PRAZNO) {
							if(prazno) {
								odprte_n_terice.put(S, odprte_n_terice.get(S) + 1);
							} else {
								pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
							}
							prazno = true;
							S = 0;
						} else if (plosca[i][j + i] == naPotezi.getPolje()) {
							S++;
							if (i == N && prazno) {
								pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
							}
						} else {
							if (prazno) {
							pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
							prazno = false;
							}
							S = 0;
						}
					}
				}
		
		
	}
	
	public int ocenaPlosce(Igralec naPotezi) {
		generirajHashMape(naPotezi);
		
		int ocena = 0;
		
		for(int i : odprte_n_terice.keySet()) {
			ocena += odprte_n_terice.get(i)*i*i;
		}
		
		for (int i : pol_odprte_n_terice.keySet()) {
			ocena += pol_odprte_n_terice.get(i)*i*i/2;
		}
		
		return ocena;
	}

}
