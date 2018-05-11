package logika;

public enum Stanje {
	NA_POTEZI_CRNI,
	NA_POTEZI_BELI,
	ZMAGA_CRNI,
	ZMAGA_BELI,
	NEODLOCENO;

	private Peterka zmagovalna;
	
	private Stanje() { zmagovalna = null; }
}
