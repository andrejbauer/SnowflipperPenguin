package Inteligenca;

import javax.swing.SwingWorker;
import GUI.Okno;
import logika.Igra;
import logika.Igralec;
import logika.Poteza;

public class PotezeAI extends SwingWorker<Poteza, Object> {

	private Okno master;
	
	private int globina;
	
	private Igralec kogaIgramo;

	private int alfa = Integer.MIN_VALUE;
	
	private int beta = Integer.MAX_VALUE;
	
	
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
	//	System.out.println(p.vrednost);
	//	System.out.println(p.poteza.getX());
	//	System.out.println(p.poteza.getY());
		return p.poteza;
	
	}
	
	public void done() {
		try {
			Poteza p = this.get();
			if (p != null) { master.odigraj(p);}
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
		
		// Če smo dosegli globino preneham z rekurzijo in vrnemo oceno.
		
		if (k >= globina) {
			int x = Ocena.ocenaPlosce(kogaIgramo, igra);
			alfa = x;
			return new OcenjenaPoteza(null, x);
		}
		
		Poteza najboljsa = null;
		int ocenaNajboljse = 0;
		
		for (Poteza p : igra.optimalnePoteze()) {

			Igra kopijaIgre = new Igra(igra);
			kopijaIgre.odigrajPotezo(p);
	
			// Če v igri najde zmagovalno petrko (se pravi da je bila poteza p zmagovalna) potem vrne kar oceno 10000000
			
			int ocenaP = minMax(k+1, kopijaIgre).vrednost;

			if (najboljsa == null )
			{
				najboljsa = p;
				ocenaNajboljse = ocenaP;	
			}			
			else if(
				 (naPotezi == kogaIgramo && ocenaP >= ocenaNajboljse) // maksimiziramo
				|| (naPotezi != kogaIgramo && ocenaP <= ocenaNajboljse) // minimiziramo
				) 
			{
				// Zamenja najboljšo potezo samo če je ta pogoj izpolnjen
				
				if((kopijaIgre.odigrane).size()%2==0) {
				najboljsa = p;
				ocenaNajboljse = ocenaP;
				}	
				najboljsa = p;
				ocenaNajboljse = ocenaP;
			}	
		}
		
		// Vrnemo najbolj�o najdeno potezo in njeno oceno
	
		assert (najboljsa != null);
		return new OcenjenaPoteza(najboljsa, ocenaNajboljse);
	}
}

