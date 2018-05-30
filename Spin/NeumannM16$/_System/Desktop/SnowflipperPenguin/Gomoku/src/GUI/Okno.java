package GUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Inteligenca.Clovek;
import Inteligenca.Racunalnik;
import Inteligenca.Strateg;
import logika.Igra;
import logika.Igralec;
import logika.Polje;
import logika.Poteza;

@SuppressWarnings("serial")
public class Okno extends JFrame implements ActionListener {
	
	/**
	 * JPanel, v katerega rišemo X in O
	 */
	private IgralnaPlosca polje;

	/**
	 * Statusna vrstica v spodnjem delu okna
	 */
	private JLabel status;

	private KdoJeIgralec kdo_je_igralec = KdoJeIgralec.OBA;
	
	private Strateg crn_igralec = new Clovek(this); // default na igralec proti igralcu
	
	private Strateg bel_igralec = new Clovek(this);	// default na igralec proti igralcu
	
	/**
	 * Logika igre, null če se igra trenutno ne igra
	 */
	protected Igra igra;
	
	// Izbire v meniju
	private JMenuItem nova_igra;
	private JMenuItem igralec_proti_igralcu;
//	private JMenuItem igralec_proti_racunalniku;
	private JMenuItem igraj_kot_beli;
	private JMenuItem igraj_kot_crni;
	private JMenuItem racunalnik_proti_racunalniku;
	
	public Okno() {
		this.setTitle("Gomoku");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		
		// menu
				JMenuBar menu_bar = new JMenuBar();
				this.setJMenuBar(menu_bar);
				JMenu igra_menu = new JMenu("Igra");
				JMenu igralec_proti_racunalniku = new JMenu("igralec proti računalniku");
				menu_bar.add(igra_menu);
				nova_igra = new JMenuItem("Nova igra");
				igralec_proti_igralcu = new JMenuItem("igralec proti igralcu");
//				igralec_proti_racunalniku = new JMenuItem("igralec proti računalniku");
				igraj_kot_beli = new JMenuItem("igraj kot beli");
				igraj_kot_crni = new JMenuItem("igraj kot crni");
				racunalnik_proti_racunalniku = new JMenuItem("računalnik proti računalniku");
				igra_menu.add(nova_igra);
				igra_menu.add(igralec_proti_igralcu);
				igra_menu.add(igralec_proti_racunalniku);
				igralec_proti_racunalniku.add(igraj_kot_beli);
				igralec_proti_racunalniku.add(igraj_kot_crni);
				igra_menu.add(racunalnik_proti_racunalniku);
				nova_igra.addActionListener(this);
				igralec_proti_igralcu.addActionListener(this);
				igralec_proti_racunalniku.addActionListener(this);
				igraj_kot_beli.addActionListener(this);
				igraj_kot_crni.addActionListener(this);
				racunalnik_proti_racunalniku.addActionListener(this);
				
				
	// igralno polje
				polje = new IgralnaPlosca(this);
				GridBagConstraints polje_layout = new GridBagConstraints();
				polje_layout.gridx = 0;
				polje_layout.gridy = 0;
				polje_layout.fill = GridBagConstraints.BOTH;
				polje_layout.weightx = 1.0;
				polje_layout.weighty = 1.0;
				getContentPane().add(polje, polje_layout);
		
				
				
	// statusna vrstica za sporočila
				status = new JLabel();
				status.setFont(new Font(status.getFont().getName(),
									    status.getFont().getStyle(),
									    20));
				GridBagConstraints status_layout = new GridBagConstraints();
				status_layout.gridx = 0;
				status_layout.gridy = 1;
				status_layout.anchor = GridBagConstraints.CENTER;
				getContentPane().add(status, status_layout);
		
				nova_igra();
	}
		

	private void nova_igra() {
		if (bel_igralec != null) { bel_igralec.prekini(); }
		if (crn_igralec != null) { crn_igralec.prekini(); }
//		System.out.println("Začeli smo novo igro");
		this.igra = new Igra();
		
//		bel_igralec = new Clovek(this);
//		crn_igralec = new Racunalnik(this);
		// Tistemu, ki je na potezi, to povemo
		switch (igra.stanje()) {
		case NA_POTEZI_BELI: bel_igralec.na_potezi(); break;
		case NA_POTEZI_CRNI: crn_igralec.na_potezi(); break;
		default: break;
		}
		
		osveziGUI();
		repaint();
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nova_igra) {
			nova_igra();
		}
		
		if (e.getSource() == igralec_proti_igralcu) {
			System.out.println("pvp");
			kdo_je_igralec = KdoJeIgralec.OBA;
			bel_igralec = new Clovek(this);
			crn_igralec = new Clovek(this);
			nova_igra();
		}
		
		if (e.getSource() == igraj_kot_beli) {
			System.out.println("beli");
			kdo_je_igralec = KdoJeIgralec.BELI;
			bel_igralec = new Clovek(this);
			crn_igralec = new Racunalnik(this, Igralec.CRNI);
			nova_igra();
		}
		
		if (e.getSource() == igraj_kot_crni) {
			System.out.println("crni");
			kdo_je_igralec = KdoJeIgralec.CRNI;
			bel_igralec = new Racunalnik(this, Igralec.BELI);
			crn_igralec = new Clovek(this);
			nova_igra();
		}
		
		if (e.getSource() == racunalnik_proti_racunalniku) {
			System.out.println("eve");
			kdo_je_igralec = KdoJeIgralec.NOBEN;
			bel_igralec = new Racunalnik(this, Igralec.BELI);
			crn_igralec = new Racunalnik(this, Igralec.CRNI);
			nova_igra();
		}
		
		
	}

	public void odigraj(Poteza p) {
		
//		System.out.println(igra.igralna_plosca.ocenaPlosce(igra.naPotezi()));
		
		igra.odigrajPotezo(p);
		
//		osveziGUI();
		
		System.out.println(igra.stanje());
		switch (igra.stanje()) {
		case NA_POTEZI_BELI: bel_igralec.na_potezi(); break;
		case NA_POTEZI_CRNI: crn_igralec.na_potezi(); break;
		case NEODLOCENO: break;
		case ZMAGA_BELI: break;
		case ZMAGA_CRNI: break;
		default: break;
		}
		
		osveziGUI();
	}
	
	public void osveziGUI() {
		if (igra == null) {
			status.setText("igra se ne izvaja");
		} else {
			switch (igra.stanje()) {
			case NA_POTEZI_BELI: status.setText("Na potezi je beli"); break;
			case NA_POTEZI_CRNI: status.setText("Na potezi je črni"); break;
			case ZMAGA_CRNI: status.setText("Zmagal je črni"); break;
			case ZMAGA_BELI: status.setText("Zmagla je beli"); break;
			case NEODLOCENO: status.setText("igra se je končala neodločeno"); break;
			}
		}
		polje.repaint();
	}

	public Igra kopirajIgro() {
		return new Igra(igra);
	}
	
	public void klikNaPolje(int i, int j) {

		switch (igra.stanje()) {
		case NA_POTEZI_BELI:
			if (kdo_je_igralec == KdoJeIgralec.BELI || kdo_je_igralec == KdoJeIgralec.OBA) {
				odigraj(new Poteza(i, j));
			}
			break;
		case NA_POTEZI_CRNI:
			if (kdo_je_igralec == KdoJeIgralec.CRNI || kdo_je_igralec == KdoJeIgralec.OBA) {
				odigraj(new Poteza(i, j));
			}
			break;
		default: break;
		}
	}


	public Polje[][] getPlosca() {
		return (igra == null ? null : igra.getPlosca());
	}
	
}