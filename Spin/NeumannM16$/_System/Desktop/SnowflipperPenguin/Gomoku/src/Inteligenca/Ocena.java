package Inteligenca;

import java.util.HashMap;

import logika.Igralec;
import logika.Plosca;
import logika.Polje;

public class Ocena {
	public static final int ZMAGA = 10000000; // vrednost zmage, ve� kot vsaka druga ocena pozicije
	public static final int ZGUBA = -ZMAGA;  // vrednost izgube, mora biti -ZMAGA
	public static final int NEODLOCENO = 0; // vrednost neodlo�ene igre
	
	// Točkovanje ki smo si ga zmislili
	
	public static final int[] ODPRTE = {0, 3, 9, 30, 300, 300000};
	public static final int[] POLODPRTE = {0, 1, 3, 10, 100, 100000};


	// Ustvarimo slovarje za odprte_n_terice (_XX_) in za pol_odprte_n_terice (OXX_)
	
	private static HashMap<Integer, Integer> odprte_n_terice = new HashMap<Integer, Integer>();
	private static HashMap<Integer, Integer> pol_odprte_n_terice = new HashMap<Integer, Integer>(); 
	
	private static void generirajHashMape(Igralec naPotezi, Plosca plosca) {
		
		for (int i = 0; i < 6; i++) {
			odprte_n_terice.put(i, 0);
			pol_odprte_n_terice.put(i, 0);
		}
				
				// Navpi�e n terice
				
				for (int i = 0; i < plosca.N; i++) {
					
					// �tevilo najdenih zapored
					
					int S = 0;
					
					// Spremljamo ali je bilo prej�nje  naPotezi.getPolje() polje prazno
					
					boolean prazno = false;
					
					for (int j = 0; j < plosca.N; j++) {
						if (plosca.plosca[i][j] == Polje.PRAZNO) {
							if(prazno) {
								odprte_n_terice.put(S, odprte_n_terice.get(S) + 1);
							} else {
								pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
							}
							prazno = true;
							S = 0;
						} else if (plosca.plosca[i][j] == naPotezi.getPolje()) {
							S++;
							if (i == plosca.N && prazno) {
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
				
				for (int j = 0; j < plosca.N; j++) {
					
					// �tevilo najdenih zapored
					
					int S = 0;
					
					// Spremljamo ali je bilo prej�nje ne naPotezi.getPolje() polje prazno
					
					boolean prazno = false;
					
					for (int i = 0; i < plosca.N; i++) {
						if (plosca.plosca[i][j] == Polje.PRAZNO) {
							if(prazno) {
								odprte_n_terice.put(S, odprte_n_terice.get(S) + 1);
							} else {
								pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
							}
							prazno = true;
							S = 0;
						} else if (plosca.plosca[i][j] == naPotezi.getPolje()) {
							S++;
							if (i == plosca.N && prazno) {
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
				
				for (int i = 0; i < plosca.N; i++) {
					
					// �tevilo najdenih zapored
					
					int S = 0;
					
					// Spremljamo ali je bilo prej�nje ne naPotezi.getPolje() polje prazno
					
					boolean prazno = false;
					
					for (int j = -plosca.N + 1; j < 0 - i; j++) {
						if (plosca.plosca[i + plosca.N - 1 + j][-j] == Polje.PRAZNO) {
							if(prazno) {
								odprte_n_terice.put(S, odprte_n_terice.get(S) + 1);
							} else {
								pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
							}
							prazno = true;
							S = 0;
						} else if (plosca.plosca[i + plosca.N - 1 + j][-j] == naPotezi.getPolje()) {
							S++;
							if (i == plosca.N && prazno) {
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
				
				for (int j = -plosca.N + 2; j < 0; j++) {
					
					// �tevilo najdenih zapored
					
					int S = 0;
					
					// Spremljamo ali je bilo prej�nje ne naPotezi.getPolje() polje prazno
					
					boolean prazno = false;
					
					for (int i = 0; i < -j; i++) {
						if (plosca.plosca[i][-j - i] == Polje.PRAZNO) {
							if(prazno) {
								odprte_n_terice.put(S, odprte_n_terice.get(S) + 1);
							} else {
								pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
							}
							prazno = true;
							S = 0;
						} else if (plosca.plosca[i][-j - i] == naPotezi.getPolje()) {
							S++;
							if (i == plosca.N && prazno) {
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
				
				for (int i = 0; i < plosca.N; i++) {
					
					// �tevilo najdenih zapored
					
					int S = 0;
					
					// Spremljamo ali je bilo prej�nje ne naPotezi.getPolje() polje prazno
					
					boolean prazno = false;
					
					for (int j = 0; j < plosca.N - i; j++) {
						if (plosca.plosca[i + j][j] == Polje.PRAZNO) {
							if(prazno) {
								odprte_n_terice.put(S, odprte_n_terice.get(S) + 1);
							} else {
								pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
							}
							prazno = true;
							S = 0;
						} else if (plosca.plosca[i + j][j] == naPotezi.getPolje()) {
							S++;
							if (i == plosca.N && prazno) {
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
				
						for (int j = 1; j < plosca.N; j++) {
							
							// �tevilo najdenih zapored
							
							int S = 0;
							
							// Spremljamo ali je bilo prej�nje ne naPotezi.getPolje() polje prazno
							
							boolean prazno = false;
							
							for (int i = 0; i < plosca.N - j; i++) {
								if (plosca.plosca[i][j + i] == Polje.PRAZNO) {
									if(prazno) {
										odprte_n_terice.put(S, odprte_n_terice.get(S) + 1);
									} else {
										pol_odprte_n_terice.put(S, pol_odprte_n_terice.get(S) + 1);
									}
									prazno = true;
									S = 0;
								} else if (plosca.plosca[i][j + i] == naPotezi.getPolje()) {
									S++;
									if (i == plosca.N && prazno) {
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
			
	// Izračunamo oceno glede na najdene n-terice
	
		public static int ocenaPlosce(Igralec naPotezi, Plosca plosca) {
			generirajHashMape(naPotezi, plosca);
				
				int ocena = 0;
				
				for(int i : odprte_n_terice.keySet()) {					
					ocena += odprte_n_terice.get(i)*ODPRTE[i];
				}
				
				for (int i : pol_odprte_n_terice.keySet()) {
					ocena += POLODPRTE[i]*pol_odprte_n_terice.get(i);
				}				
			return ocena;
		}
	
}
