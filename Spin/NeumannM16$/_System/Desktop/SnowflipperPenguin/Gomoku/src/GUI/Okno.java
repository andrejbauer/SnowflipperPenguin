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

import logika.Igra;
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

	
	/**
	 * Logika igre, null èe se igra trenutno ne igra
	 */
	private Igra igra;
	
	// Izbire v meniju
	private JMenuItem nova_igra;
	public Okno() {
		this.setTitle("Gomoku");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridBagLayout());
		
		
		// menu
				JMenuBar menu_bar = new JMenuBar();
				this.setJMenuBar(menu_bar);
				JMenu igra_menu = new JMenu("Igra");
				menu_bar.add(igra_menu);
				nova_igra = new JMenuItem("Nova igra");
				igra_menu.add(nova_igra);
				nova_igra.addActionListener(this);
				
				
				
	// igralno polje
				polje = new IgralnaPlosca(this);
				GridBagConstraints polje_layout = new GridBagConstraints();
				polje_layout.gridx = 0;
				polje_layout.gridy = 0;
				polje_layout.fill = GridBagConstraints.BOTH;
				polje_layout.weightx = 1.0;
				polje_layout.weighty = 1.0;
				getContentPane().add(polje, polje_layout);
		
				
				
	// statusna vrstica za sporoèila
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
		System.out.println("Zaèeli smo novo igro");
		this.igra = new Igra();
		osveziGUI();
		repaint();
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nova_igra) {
			nova_igra();
		}
		
	}

	public void odigraj(Poteza p) {
		System.out.println("odigrali smo" + p);
		igra.odigrajPotezo(p);
		osveziGUI();
	}
	
	public void osveziGUI() {
		polje.repaint();
	}

	public Igra kopirajIgro() {
		return new Igra(igra);
	}
	
	public void klikNaPolje(int i, int j) {
		System.out.println("kliknili smo na polje");
		odigraj(new Poteza(i, j));
	}


	public Polje[][] getPlosca() {
		return (igra == null ? null : igra.getPlosca());
	}
	
}