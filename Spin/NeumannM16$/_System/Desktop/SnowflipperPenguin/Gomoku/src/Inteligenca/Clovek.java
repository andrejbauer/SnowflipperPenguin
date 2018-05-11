package Inteligenca;

import GUI.Okno;
import logika.Poteza;

public class Clovek extends Strateg {

	private Okno master;
	
	
	public Clovek(Okno master) {
		super();
		this.master = master;
	}

	@Override
	public void na_potezi() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prekini() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void klik(int i, int j) {
		master.odigraj(new Poteza(i, j));		
	}

}
