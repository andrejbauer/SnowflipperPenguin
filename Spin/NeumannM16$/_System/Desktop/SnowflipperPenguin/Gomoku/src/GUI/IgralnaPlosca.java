package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import logika.Igra;
import logika.Plosca;
import logika.Polje;

@SuppressWarnings({ "serial", "unused" })
public class IgralnaPlosca extends JPanel implements MouseListener{
	private Okno master;
	
	/**
	 * Relativna širina èrte
	 */
	private final static double LINE_WIDTH = 0.1;
	
	/**
	 * Relativni prostor okoli X in O
	 */
	private final static double PADDING = 0.1;
	
	//public IgralnaPlosca(	)
	public IgralnaPlosca(Okno master) {
		super();
		setBackground(Color.orange);
		this.master = master;
		this.addMouseListener(this);
	}
	

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(400, 400);
	}
		private double squareWidth() {
			return Math.min(getWidth(), getHeight()) / Plosca.N;
		}
		
		private void paintCrno(Graphics2D g2, int i, int j) {
			double w = squareWidth();
			double r = w * (1.0 - LINE_WIDTH - 2.0 * PADDING); // premer X
			double x = w * (i + 0.5 * LINE_WIDTH + PADDING);
			double y = w * (j + 0.5 * LINE_WIDTH + PADDING);
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke((float) (w * LINE_WIDTH)));
			g2.fillOval((int)x, (int)y, (int)r , (int)r);
		}
		
		private void paintBelo(Graphics2D g2, int i, int j) {
			double w = squareWidth();
			double r = w * (1.0 - LINE_WIDTH - 2.0 * PADDING); // premer O
			double x = w * (i + 0.5 * LINE_WIDTH + PADDING);
			double y = w * (j + 0.5 * LINE_WIDTH + PADDING);
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke((float) (w * LINE_WIDTH)));
			g2.fillOval((int)x, (int)y, (int)r , (int)r);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			// širina kvadratka
			double w = squareWidth();
			// èrte
			g2.setColor(Color.black);
			g2.setStroke(new BasicStroke((float) (w * LINE_WIDTH)));
			for (int i = 1; i < Plosca.N; i++) {
				g2.drawLine((int)(i * w),
						    (int)(LINE_WIDTH * w),
						    (int)(i * w),
						    (int)((Plosca.N - LINE_WIDTH) * w));
				g2.drawLine((int)(LINE_WIDTH * w),
						    (int)(i * w),
						    (int)((Plosca.N - LINE_WIDTH) * w),
						    (int)(i * w));
			}
			
			// križci in krožci
			Polje[][] plosca = master.getPlosca();
			if (plosca != null) {
				for (int i = 0; i < Plosca.N; i++) {
					for (int j = 0; j < Plosca.N; j++) {
						switch(plosca[i][j]) {
						case CRNO: paintCrno(g2, i, j); break;
						case BELO: paintBelo(g2, i, j); break;
						default: break;
						}
					}
				}
			}
			
			System.out.println("rišem");
			
			if (Igra.zmagovalna_peterka != null) {
				System.out.println("nekdo je zmagal");
				Graphics2D g3 = (Graphics2D)g;
				// širina kvadratka
				double z = squareWidth();
				// èrte
				g3.setColor(Color.red);
				g3.setStroke(new BasicStroke((float) (z * LINE_WIDTH)));
//				for (int i = 1; i < Plosca.N; i++) {
				
				System.out.println("Hello");
				System.out.println(z/2);
				
					g3.drawLine((int)(Igra.zmagovalna_peterka.getZacetekX()*z + z/2),
							    (int)(Igra.zmagovalna_peterka.getZacetekY()*z + z/2),
							    (int)(Igra.zmagovalna_peterka.getKonecX()*z + z/2),
							    (int)(Igra.zmagovalna_peterka.getKonecY()*z + z/2));
//			}
		}
		}
		

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int w = (int)(squareWidth());
		int i = x / w ;
		double di = (x % w) / squareWidth() ;
		int j = y / w ;
		double dj = (y % w) / squareWidth() ;
		if (0 <= i && i < Plosca.N &&
		    0.5 * LINE_WIDTH < di && di < 1.0 - 0.5 * LINE_WIDTH &&
		    0 <= j && j < Plosca.N && 
		    0.5 * LINE_WIDTH < dj && dj < 1.0 - 0.5 * LINE_WIDTH) {
			master.klikNaPolje(i, j);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
