package Inteligenca;

import java.util.HashMap;

import logika.Igralec;
import logika.Plosca;
import logika.Polje;

public class Ocena {

	private static HashMap<Integer, Integer> odprte_n_terice = new HashMap<Integer, Integer>();
	private static HashMap<Integer, Integer> pol_odprte_n_terice = new HashMap<Integer, Integer>(); 
	
	private static void generirajHashMape(Igralec naPotezi, Plosca plosca) {
		
		for (int i = 0; i < 5; i++) {
			odprte_n_terice.put(i, 0);
			pol_odprte_n_terice.put(i, 0);
		}
		/*		
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
		*/
				
				// Navpièe n terice
				
				for (int i = 0; i < plosca.N; i++) {
					
					// Število najdenih zapored
					
					int S = 0;
					
					// Spremljamo ali je bilo prejšnje ne naPotezi.getPolje() polje prazno
					
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
					
					// Število najdenih zapored
					
					int S = 0;
					
					// Spremljamo ali je bilo prejšnje ne naPotezi.getPolje() polje prazno
					
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
				
				// Diagonalne n terice levo zgoraj / desno dol, z zaèetkom v zgornjih poljih
				
				for (int i = 0; i < plosca.N; i++) {
					
					// Število najdenih zapored
					
					int S = 0;
					
					// Spremljamo ali je bilo prejšnje ne naPotezi.getPolje() polje prazno
					
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
				
				
				// Diagonalne n terice levo zgoraj / desno dol, z zaèetkom v levih poljih
				
				for (int j = -plosca.N + 2; j < 0; j++) {
					
					// Število najdenih zapored
					
					int S = 0;
					
					// Spremljamo ali je bilo prejšnje ne naPotezi.getPolje() polje prazno
					
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
				
				// Diagonalne n terice levo spodaj / desno gor, z zaèetkom v spodnejih poljih
				
				for (int i = 0; i < plosca.N; i++) {
					
					// Število najdenih zapored
					
					int S = 0;
					
					// Spremljamo ali je bilo prejšnje ne naPotezi.getPolje() polje prazno
					
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
				
				// Diagonalne n terice levo spodaj / desno gor, z zaèetkom v levih poljih
				
						for (int j = 1; j < plosca.N; j++) {
							
							// Število najdenih zapored
							
							int S = 0;
							
							// Spremljamo ali je bilo prejšnje ne naPotezi.getPolje() polje prazno
							
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
			
		public static int ocenaPlosce(Igralec naPotezi, Plosca plosca) {
			generirajHashMape(naPotezi, plosca);
				
				int ocena = 0;
				
				for(int i : odprte_n_terice.keySet()) {
					ocena += Math.pow(2, i)*odprte_n_terice.get(i)*i;
				}
				
				for (int i : pol_odprte_n_terice.keySet()) {
					ocena += Math.pow(2, i)*pol_odprte_n_terice.get(i)*i/3;
				}				
			return ocena;
		}
	
}
