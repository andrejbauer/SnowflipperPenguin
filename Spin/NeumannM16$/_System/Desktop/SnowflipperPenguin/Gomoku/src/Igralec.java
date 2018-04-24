
public enum Igralec {
	
	// Imamo dva igralca �rnega in belega
	
	�, B;
	
	// Zamenja to kdo je na potezi.
	
	public Igralec nasprotnik() {
		return (this == � ? B : �);
	}
	
	// Vrne polje tistega ki je na potezi.

	public Polje getPolje() {
		return (this == � ? Polje.� : Polje.B);
	}

	
}
