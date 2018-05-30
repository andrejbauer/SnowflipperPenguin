package logika;

public class Poteza {
	
	private int x;
	private int y;
	
	public Poteza(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean aliStaPoteziEnaki(Poteza p){
		return p.getX() == this.x && p.getY() == this.y;
	}
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
