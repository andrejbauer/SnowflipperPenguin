
public class Test_logike_igre {

	public static void main(String[] args) {
		Igra igra = new Igra();
			for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 2; j++) {
				igra.odigrajPotezo(new Poteza(j, i));
			}
		}
	}
}