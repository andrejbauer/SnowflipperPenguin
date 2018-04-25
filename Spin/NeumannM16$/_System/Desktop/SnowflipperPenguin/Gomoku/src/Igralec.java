
public enum Igralec {
	
	// Imamo dva igralca èrnega in belega
	
	CRNO, BELO;
	
	// Zamenja to kdo je na potezi.
	
	public Igralec nasprotnik() {
		return (this == CRNO ? BELO : CRNO);
	}
	
	// Vrne polje tistega ki je na potezi.

	public Polje getPolje() {
		return (this == CRNO ? Polje.CRNO : Polje.BELO);
	}

	
}
