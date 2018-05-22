package Inteligenca;

import java.util.LinkedList;
import java.util.Random;

import javax.swing.SwingWorker;

import GUI.Okno;
import logika.Igra;
import logika.Plosca;
import logika.Poteza;

public class PotezeAI extends SwingWorker<Poteza, Object> {

	private Okno master;

	public PotezeAI(Okno master) {
		super();
		this.master = master;
	}
	
	protected Poteza doInBackground() throws Exception {
		Igra igra = master.kopirajIgro();
		System.out.println(igra);
		System.out.println(igra.moznePoteze());
//		LinkedList<Poteza> moznePoteze = igra.moznePoteze();
		LinkedList<Integer> vseOcene = new LinkedList<Integer>();

		for (int i = 0; i < 5; i++) {
			System.out.println("razmišljam...");
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) { }
			if (this.isCancelled()) {
				System.out.println("nihaj");
				return null;
			}
		}
		
		
//		for (Poteza p : moznePoteze) {
//			igra.odigrajPotezo(p);
//			vseOcene.add(igra.igralna_plosca.ocenaPlosce(igra.naPotezi()));
//			System.out.println(vseOcene);
//			igra = master.kopirajIgro();
//		}
//		System.out.println(vseOcene);
		
		
		
		System.out.println("sem");
		Random a = new Random();
		Random b = new Random();
		
		int x = a.nextInt(Plosca.N);
		int y = b.nextInt(Plosca.N);
		
		System.out.println(x);
		System.out.println(y);
		
		Poteza poteza = new Poteza(x, y);
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
