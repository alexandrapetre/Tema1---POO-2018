public class Premium extends Basic {

	int nr_subscriptii_Premium = 0; // numar cereri premium
	
	public Premium() {
	}
	
	/**
	 * @param : nume, nr_subscriptii_basic
	 *
	 *	Seteaza numele si numarul de cereri premium pentru obiect
	 *
	 */
	
	public Premium(String nume, int nr_subscriptii_Premium) {
		super(nume);
		this.nr_subscriptii_Premium = nr_subscriptii_Premium;
	}
	/**
	 * @param : -
	 * Scade numarul de cereri al subscriptiei premium atunci cand se citeste din fisier comanda GET.
	 * Daca numarul de cereri premium este 0, se apeleaza aceeasi metoda din clasa basic.
	 * 
	 */
	
	public void scadere() {
		if(nr_subscriptii_Premium > 0)
			nr_subscriptii_Premium--;
		else{
				super.scadere();
		}
	}
	
	/**
	 * @param : -
	 * Se afiseaza numele in functie de numarul de cereri
	 * Daca numarul de cereri premium este 0 se urca in lantul de mostenire. (elemenutl devine basic)
	 *
	 */
	public String toString() {
		if(nr_subscriptii_Premium > 0) {
			return "Premium";
		}
		return super.toString();
	}
}