package GUI;


public class Test {

	public static void main(String[] args) {
		
		Okno okno=new Okno();
		IgralnaPlosca plosca= new IgralnaPlosca(okno);
		okno.pack();
		okno.setVisible(true);
}
}