package logika;

public class Plosca {
	// Igralna plo��a je velikosti N x N.
	
	public static final int N=19;
	
	//Definiramo plo��o
	
	public Polje [][] plosca;
	
	//Definimano igralno plo��o
	
	protected Plosca() {
		super();
		plosca=new Polje [N][N];
	}
	
	public Polje[][] kopirajPlosco() {
		return this.plosca;
	}
	
	//Dobimo vsebino polja na koordinatah (x,y)
	
	public Polje getPlosca(int x, int y) {
		
		return plosca[x][y];
	}
	
	// Nastavimo vsebino polja na koordinatah (x,y)
	
	public void setPlosca(int x, int y, Polje f) {
		
		plosca[x][y]=f;
	}

}
