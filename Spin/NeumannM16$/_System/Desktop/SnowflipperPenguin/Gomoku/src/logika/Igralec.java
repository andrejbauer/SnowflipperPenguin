package logika;

public enum Igralec {
	
	// Imamo dva igralca èrnega in belega
	
	CRNI, BELI;
	
	// Zamenja to kdo je na potezi.
	
	public Igralec nasprotnik() {
		return (this == CRNI ? BELI : CRNI);
	}
	
	// Vrne polje tistega ki je na potezi.

	public Polje getPolje() {
		return (this == CRNI ? Polje.CRNO : Polje.BELO);
	}

	
}
