import java.util.*;

/**
 * @author Alexandra Petre
 *
 */
public class FIFOCache implements Cache{

	ArrayList<Subscriptie> Fifo = new ArrayList<Subscriptie>();
	int nr_max; // numar maxim de elemente din Cache
	
	/**
	 * @param : n
	 * Seteaza numarul maxim de elemente din memoria Cache
	 *
	 */
	public FIFOCache(int n) {
		this.nr_max = n;
	}
	
	/**
	 * @param : Subscriptie s
	 * Adauga in memoria Cache un element doar daca numarul de elemente din array nu e maxim
	 * Daca numarul este maxim scoate primul element si il adauga pe cel noi
	 *
	 */
	
	public void add(Subscriptie s) {
		if(Fifo.size() < nr_max)
			Fifo.add(s);
		else {
			if(Fifo.size() == nr_max) {
				remove();
				Fifo.add(s);
			}
		}
	}
	
	/**
	 * @param : -
	 * Elimina primul element din array (First in, First out)
	 *
	 */

	public void remove() {
		Fifo.remove(0);
	}
}