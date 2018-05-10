package Inteligenca;

import javax.swing.SwingWorker;

import GUI.Okno;
import logika.Poteza;

public class Racunalnik extends Strateg {

	private Okno master;
	private SwingWorker<Poteza, Object> mislec;
	private boolean prekini;
	
	public Racunalnik(Okno master) {
		super();
		this.master = master;
	}

	@Override
	public void na_potezi() {
		mislec = new NakljucnePoteze(master);
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
