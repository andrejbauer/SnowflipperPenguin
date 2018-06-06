package logika;

import java.util.LinkedList;
import java.util.List;

public class Smeri {
	
	public static final List<Vektor> smeri = new LinkedList<Vektor>();
	public static int N=4;
	static {
		
		smeri.add(new Vektor(0, 1));
		smeri.add(new Vektor(1, 1));
		smeri.add(new Vektor(1, 0));
		smeri.add(new Vektor(1, -1));
	
	}
}
