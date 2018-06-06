package Inteligenca;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import logika.Igra;
import logika.Igralec;
import logika.Plosca;
import logika.Polje;
import logika.Poteza;
import logika.Smeri;
import logika.Vektor;

public class Ocena {
	 private static final int[] SCORES = {100000,10000, 1000, 8, 2};
	 public static int ocenaPolje(Igralec igralec,List<Poteza>list ,Plosca igralna_plosca) {
	        int score = 0;
	        for (Poteza p :list) {
	        	
	        for (Vektor v : Smeri.smeri) {
	 
	        		int empty = 0;
      	            int stones = 0;
      	            for (int a=-4;a<=0;a=a+1) {
	        		for(int b=a;b<=a+4;b=b+1) {
	        		   
	      	            int m = p.getX() + b*v.getX();
	      	            int n = p.getY() + b*v.getY();
	      	            if ((0 <= m) && (m < Plosca.N) && (0 <= n) && (n < Plosca.N)) {
	      	            	
	      	            if(igralna_plosca.getPlosca(m, n) == igralec.getPolje2()) {
	      	            	
	      	            		stones=stones+1;

		      	            	
	      	            	}
	      	            else if(igralna_plosca.getPlosca(m, n) == Polje.PRAZNO) {
	      	            	
	      	           
	      	            	empty=empty+1;

	      	            	
	      	            }
	      	            else {
	      	            	
	      	                break;
	      	            }
	      	          if(empty == 0 || empty == 5) continue;
	      	        if(empty == 0 )
	      	        {
	      	        	return 1000000;
	      	        }

	                  // Window contains only empty spaces and player stones, can form
	                  // a five, get score based on how many moves needed
	                  if(stones + empty == 5) {
	                      score += SCORES[empty];
	                  }
	              }
	   
	      	            }
	        		}
	        	}
	        
	 
	 }
	        return score;
	 }
	 
	 public static int boljsaOcenaPolje(Igralec igralec,List<Poteza>list ,Plosca igralna_plosca) {
	        int player=ocenaPolje(igralec,list , igralna_plosca);
	        int pc=ocenaPolje(igralec.nasprotnik(),list , igralna_plosca);
	        return player-pc;
	}
}

