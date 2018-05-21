package Inteligenca;

import logika.Igra;
import logika.Igralec;
import logika.Plosca;
import logika.Polje;
import logika.Smeri;
import logika.Vektor;

public class Ocena {
    @SuppressWarnings("unused")
	private static final Ocena INSTANCE = new Ocena();
    private static final int[] SCORES = {19, 15, 11, 7, 3};
    

    private Ocena() {}
    
	
	

  public static int oceniSmer(Plosca plosca,Igralec igralec,int x,int y){
	  
	
  
	int ocena=0;
	for (Vektor v:Smeri.smeri){
		int prazno=0;
		int polno=0;

		for (int i =  -Igra.M; i < Igra.M; i++) {
			x = x + i*v.getX();
			y = y + i*v.getY();
			
			if ((0 <= x) && (x < Plosca.N) && (0 <= y) && (y < Plosca.N)) {
				if(plosca.getPlosca(x, y)==Polje.PRAZNO) {
					
                    prazno++;
                    System.out.println(prazno+" prazno");
                }
                else if(plosca.getPlosca(x, y)==igralec.getPolje()) {
                    polno++;
                    System.out.println(polno+" polno");
                } else {
                    break;
                }
			}
			if(prazno == 0 || prazno == 5) continue;
			if(polno + prazno == 5) {
	                ocena += SCORES[prazno];
	                System.out.println(ocena+" ocena");
				}
			}  
		}
	return ocena;
  }
  
  	
  }



