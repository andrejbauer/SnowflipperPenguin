package Inteligenca;

import java.util.Collections;
import java.util.LinkedList;

import javax.swing.SwingWorker;
import GUI.Okno;
import logika.Igra;
import logika.Igralec;
import logika.Poteza;

public class PotezeAI extends SwingWorker<Poteza, Object> {

	private Okno master;
	
	private int globina;
	
	private Igralec kogaIgramo;

	public PotezeAI(Okno master, int globina, Igralec kogaIgramo) {
		super();
		this.master = master;
		this.globina = globina;
		this.kogaIgramo = kogaIgramo;
	}
	
	protected Poteza doInBackground() throws Exception {
		Igra igra = master.kopirajIgro();
		OcenjenaPoteza p = minMax(0, igra);
		assert (p.poteza != null);
		return p.poteza;
/*		
		LinkedList<Poteza> moznePoteze = igra.moznePoteze();
		LinkedList<Integer> vseOcene = new LinkedList<Integer>();

		for (int i = 0; i < 1; i++) {
			System.out.println("razmišljam...");
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) { }
			if (this.isCancelled()) {
				System.out.println("nihaj");
				return null;
			}
		}
				
		for (Poteza p : moznePoteze) {
			igra.odigrajPotezo(p);
			if (igra.zmagovalna_peterka != null){
				return p;
			}
			vseOcene.add(igra.igralna_plosca.ocenaPlosce(igra.naPotezi()));
//			int minOcena = Collections.min(vseOcene);
			igra = master.kopirajIgro();
		}
		System.out.println(vseOcene);
		
		int maxOcena = Collections.max(vseOcene);
		
		int minOcena = Collections.min(vseOcene);
		
		Poteza poteza = moznePoteze.get(vseOcene.indexOf(minOcena));
		
		return poteza;
*/		
	}
	
	public void done() {
		try {
			Poteza p = this.get();
			if (p != null) { master.odigraj(p); }
		} catch (Exception e) {
		}
	}
	
	private OcenjenaPoteza minMax(int k, Igra igra) {
		
		Igralec naPotezi = null;
		
		switch (igra.stanje()) {
		case NA_POTEZI_CRNI : naPotezi = Igralec.CRNI; break;
		case NA_POTEZI_BELI : naPotezi = Igralec.BELI; break;
		case ZMAGA_CRNI:
			return new OcenjenaPoteza(
					null,
					(kogaIgramo == Igralec.CRNI ? Ocena.ZMAGA : Ocena.ZGUBA));
		case ZMAGA_BELI:
			return new OcenjenaPoteza(
					null,
					(kogaIgramo == Igralec.BELI ? Ocena.ZMAGA : Ocena.ZGUBA));
		case NEODLOCENO:
			return new OcenjenaPoteza(null, 0);
		}
		
		assert (naPotezi != null);
		
		if (k >= globina) {
			return new OcenjenaPoteza(null, Ocena.ocenaPlosce(kogaIgramo, igra.igralna_plosca));
		}
		
		Poteza najboljsa = null;
		int ocenaNajboljse = 0;
		
		for (Poteza p : igra.optimalnePoteze()) {

			Igra kopijaIgre = new Igra(igra);
			kopijaIgre.odigrajPotezo(p);
//			kopijaIgre.zmagovalna_peterka = null;

			
			
			if (igra.zmagovalna_peterka != null){
				ocenaNajboljse = 10000000;
			}
			
			

			
			int ocenaP = minMax(k+1, kopijaIgre).vrednost;
						

			if (najboljsa == null 
				|| (naPotezi == kogaIgramo && ocenaP > ocenaNajboljse) // maksimiziramo
				|| (naPotezi != kogaIgramo && ocenaP < ocenaNajboljse) // minimiziramo
				) {
				najboljsa = p;
				ocenaNajboljse = ocenaP;
				System.out.print(p.getX() + " ");
				System.out.print(p.getY() + " ocena: ");
				System.out.println(ocenaP);

				
			}
		}
		// Vrnemo najboljšo najdeno potezo in njeno oceno
	
		assert (najboljsa != null);
		return new OcenjenaPoteza(najboljsa, ocenaNajboljse);
	}
}

