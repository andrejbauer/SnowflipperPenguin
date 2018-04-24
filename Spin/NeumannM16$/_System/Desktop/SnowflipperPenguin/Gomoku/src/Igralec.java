
public enum Igralec {
	
	// Imamo dva igralca èrnega in belega
	
	È, B;
	
	// Zamenja to kdo je na potezi.
	
	public Igralec nasprotnik() {
		return (this == È ? B : È);
	}
	
	// Vrne polje tistega ki je na potezi.

	public Polje getPolje() {
		return (this == È ? Polje.È : Polje.B);
	}

	
}
