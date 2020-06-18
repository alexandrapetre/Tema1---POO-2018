/**
 * @author Alexandra Petre
 *
 */

public class Basic extends Free {
 
	int nr_subscriptii_Basic = 0; // numar cereri basic
	
	public Basic() {
	}
	
	public Basic(String nume) {
		super(nume);
	}
	
	/**
	 * @param : nume, nr_subscriptii_basic
	 *
	 *	Seteaza numele si numarul de cereri basic pentru obiect
	 *
	 */
	public Basic(String nume, int nr_subscriptii_Basic) {
		super(nume);
		this.nr_subscriptii_Basic = nr_subscriptii_Basic;
	}
	
	/**
	 * @param : -
	 * Scade numarul de cereri ale elementului atunci cand se citeste din fisier comanda GET.
	 * Daca numarul de cereri este 0, se apeleaza clasa Free.
	 * 
	 */
	public void scadere() {
		if(nr_subscriptii_Basic > 0)
			this.nr_subscriptii_Basic--;
		else
			super.scadere();
	}
	
	/**
	 * @param : -
	 * Se afiseaza numele in functie de numarul de cereri
	 * Daca numarul de cereri basic este 0 se urca in lantul de mostenire. (elemenutl devine free)
	 *
	 */
	
	public String toString() {
		if(nr_subscriptii_Basic > 0) {
			return "Basic";
		}
		return super.toString();
	}
}