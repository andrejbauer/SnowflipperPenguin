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
//		OcenjenaPoteza p = minMax(0, igra);
//		assert (p.poteza != null);
//		return p.poteza;
		
		LinkedList<Poteza> moznePoteze = igra.moznePoteze();
		LinkedList<Integer> vseOcene = new LinkedList<Integer>();

		for (int i = 0; i < 1; i++) {
			System.out.println("razmi�ljam...");
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
		
		System.out.println("za�eli smo minimax");
		System.out.println(k);
		
		switch (igra.stanje()) {
		case NA_POTEZI_CRNI : naPotezi = Igralec.CRNI; break;
		case NA_POTEZI_BELI : naPotezi = Igralec.BELI; break;
		default : naPotezi = null; break;
		}
		
		assert (naPotezi != null);
		
		if (k >= globina) {
			return new OcenjenaPoteza(null, igra.igralna_plosca.ocenaPlosce(kogaIgramo));
		}
		
		Poteza najboljsa = null;
		int ocenaNajboljse = 0;
		
		for (Poteza p : igra.moznePoteze()) {

			Igra kopijaIgre = new Igra(igra);
			kopijaIgre.odigrajPotezo(p);
			
			if (igra.zmagovalna_peterka != null){
				return new OcenjenaPoteza(p, 100000);
			}

			int ocenaP = minMax(k+1, kopijaIgre).vrednost;

			if (najboljsa == null 
				|| (naPotezi == kogaIgramo && ocenaP > ocenaNajboljse) // maksimiziramo
				|| (naPotezi != kogaIgramo && ocenaP < ocenaNajboljse) // minimiziramo
				) {
				najboljsa = p;
				ocenaNajboljse = ocenaP;
			}
		}
		// Vrnemo najbolj�o najdeno potezo in njeno oceno
		
		assert (najboljsa != null);
		return new OcenjenaPoteza(najboljsa, ocenaNajboljse);
	}
}

