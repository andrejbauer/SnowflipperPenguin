package Inteligenca;

import java.util.Random;

import GUI.okno;
import logika.Igra;
import logika.Plosca;
import logika.Poteza;

public class NakljucnePoteze {

	private okno master;

	public NakljucnePoteze(okno master) {
		super();
		this.master = master;
	}
	
	protected Poteza doInBackground() throws Exception {
		Igra igra = master.copyIgra();
		for (int i = 0; i < 5; i++) {
			System.out.println("razmišljam...");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) { }
			if (this.isCancelled()) {
				System.out.println("nihaj");
				return null;
			}
		}
		System.out.println("sem");
		Random a = new Random();
		Random b = new Random();
		
		int x = a.nextInt(Plosca.N);
		int y = a.nextInt(Plosca.N);
		
		Poteza poteza = new Poteza(x, y);
		igra.odigrajPotezo(poteza);
		return poteza;
	}
	
	public void done() {
		try {
			Poteza p = this.get();
			if (p != null) { master.odigraj(p); }
		} catch (Exception e) {
		}
	}

}
