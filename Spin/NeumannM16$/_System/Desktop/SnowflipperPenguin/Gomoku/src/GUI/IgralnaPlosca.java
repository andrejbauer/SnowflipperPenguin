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
		setBackground(Color.white);
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
		
		private void paintX(Graphics2D g2, int i, int j) {
			double w = squareWidth();
			double r = w * (1.0 - LINE_WIDTH - 2.0 * PADDING); // sirina X
			double x = w * (i + 0.5 * LINE_WIDTH + PADDING);
			double y = w * (j + 0.5 * LINE_WIDTH + PADDING);
			g2.setColor(Color.blue);
			g2.setStroke(new BasicStroke((float) (w * LINE_WIDTH)));
			g2.drawLine((int)x, (int)y, (int)(x + r), (int)(y + r));
			g2.drawLine((int)(x + r), (int)y, (int)x, (int)(y + r));
		}
		
		private void paintO(Graphics2D g2, int i, int j) {
			double w = squareWidth();
			double r = w * (1.0 - LINE_WIDTH - 2.0 * PADDING); // premer O
			double x = w * (i + 0.5 * LINE_WIDTH + PADDING);
			double y = w * (j + 0.5 * LINE_WIDTH + PADDING);
			g2.setColor(Color.red);
			g2.setStroke(new BasicStroke((float) (w * LINE_WIDTH)));
			g2.drawOval((int)x, (int)y, (int)r , (int)r);
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
				
		}

		
		
		
		
		
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
