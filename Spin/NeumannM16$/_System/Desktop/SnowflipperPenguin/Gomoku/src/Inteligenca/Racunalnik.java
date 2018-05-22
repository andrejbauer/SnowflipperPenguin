package Inteligenca;

import javax.swing.SwingWorker;

import GUI.Okno;
import logika.Igralec;
import logika.Poteza;

public class Racunalnik extends Strateg {

	private Okno master;
	private SwingWorker<Poteza, Object> mislec;
	private boolean prekini;
	private Igralec kogaIgramo;
	
	public Racunalnik(Okno master, Igralec kogaIgramo) {
		super();
		this.master = master;
		this.kogaIgramo = kogaIgramo;
	}

	@Override
	public void na_potezi() {
		mislec = new PotezeAI(master, 2, kogaIgramo);
		mislec.execute();
		
		
	}

	@Override
	public void prekini() {
		if (mislec != null) {
			mislec.cancel(true);
		}
		
	}

	@Override
	public void klik(int i, int j) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
