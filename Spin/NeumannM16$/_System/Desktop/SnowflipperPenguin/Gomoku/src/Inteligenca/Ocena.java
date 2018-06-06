package Inteligenca;

import java.util.Vector;

import logika.Igra;
import logika.Igralec;
import logika.Plosca;
import logika.Polje;
import logika.Smeri;
import logika.Vektor;

public class Ocena {
	 private static final int[] SCORES = {19, 15, 11, 7, 3};
	 public static int ocenaPolje(Igralec igralec ,int x, int y,Plosca igralna_plosca) {
	        int score = 0;
	        
	        for (Vektor v : Smeri.smeri) {
	        	for(int a =-4;a<=0;a=a+1) {
	        		int empty = 0;
      	            int stones = 0;
	        		for(int b=a;b<=a+4;b=b+1) {
	        		   
	      	            int m = x + b*v.getX();
	      	            int n = y + b*v.getY();
	      	            if ((0 <= m) && (m < Plosca.N) && (0 <= n) && (n < Plosca.N)) {
	      	            	
	      	            if(igralna_plosca.getPlosca(m, n) == igralec.getPolje2()) {
	      	            	
	      	            		stones=stones+1;

		      	            	System.out.println("Sedaj sem v 1. in stones = "+ stones);
	      	            	}
	      	            else if(igralna_plosca.getPlosca(m, n) == Polje.PRAZNO) {
	      	            	
	      	           
	      	            	empty=empty+1;
	      	            	System.out.println("Sedaj sem v 2. in empty = "+ empty);
	      	            	
	      	            }
	      	            else {
	      	            	System.out.println("Sedaj sem v 3. in nic ");
	      	                break;
	      	            }
	      	          if(empty == 0 || empty == 5) continue;

	                  // Window contains only empty spaces and player stones, can form
	                  // a five, get score based on how many moves needed
	                  if(stones + empty == 5) {
	                      score += SCORES[empty];
	                  }
	              }
	   
	      	            }
	        		}
	        	}
	        return score;
	 }
		
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        /*

	        // Pass a window of 5 across the field array
	       
	        for(int i = 0; (i + 4) < Smeri.N; i++) {
	            int empty = 0;
	            int stones = 0;
	            for(int j = 0; j <= 4; j++) {
	                if(direction[i + j].index == 0) {
	                    empty++;
	                }
	                else if(direction[i + j].index == index) {
	                    stones++;
	                } else {
	                    // Opponent stone in this window, can't form a five
	                    break;
	                }
	            }
	            // Ignore already formed fives, and empty windows
	            if(empty == 0 || empty == 5) continue;

	            // Window contains only empty spaces and player stones, can form
	            // a five, get score based on how many moves needed
	            if(stones + empty == 5) {
	                score += SCORES[empty];
	            }
	        }
	        return score;
       }
       */
	}

