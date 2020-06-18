import java.util.*;

/**
 * @author Alexandra Petre
 *
 */
public class LFUCache implements Cache{

	int nr_max; // numar maxim de elemente din cache
	ArrayList<Subscriptie> Lfu = new ArrayList<Subscriptie>();
	
	/**
	 * @param : n
	 * Seteaza numarul maxim de elemente din memoria Cache
	 *
	 */
	public LFUCache(int n) {
		this.nr_max = n;
	}
	
	/**
	 * @param : Subscriptie s
	 * Pentru subscriptia s, se seteaza momentul de timp cand a fost adaugat elementul in cache
	 * Valoarea 0 indica cel mai recent adaugat element
	 *
	 */

	public void set_time_add(Subscriptie s) {
		for(int i = 0; i < Lfu.size(); i++) {
			if(Lfu.get(i).nume.equals(s.nume))
				Lfu.get(i).add_time = 0;
			else
				Lfu.get(i).add_time++;
		}
	}
	
	/**
	 * @param : Subscriptie s
	 * Metoda creste cu o unitate variabila frequency specifica fiecarui element in functie de comenzile GET si ADD
	 *
	 */
	public void set_frequency(Subscriptie s) {
		for(int i = 0; i < Lfu.size(); i++) {
			if(Lfu.get(i).nume.equals(s.nume)) {
				Lfu.get(i).frequency++;
				break;
			}
		}
	}
	
	/**
	 * @param : Subscriptie s
	 * Se adauga in memoria Cache un nou element
	 *
	 */
	
	public void add(Subscriptie s) {
		if(Lfu.size() < nr_max) {
			Lfu.add(s);
			set_time_add(s);
			for(int i = 0; i < Lfu.size(); i++) {
				if(Lfu.get(i).nume.equals(s.nume)) {
					Lfu.get(i).frequency = 0;
					break;
				}
			}
		}
		else {
			remove();
			Lfu.add(s);
			set_time_add(s);
			for(int i = 0; i < Lfu.size(); i++) {
				if(Lfu.get(i).nume.equals(s.nume)) {
					Lfu.get(i).frequency = 0;
					break;
				}
			}
		}
	}
	
	/**
	 * @param : -
	 * Stergerea unui element din memoria Cache se face in functie de frecventa
	 * Daca frecventa cea mai mica se gaseste la mai multe elemente se va elimina elementul cel mai vechi
	 *
	 */
	public void remove() {
		
		int ref = Lfu.get(0).frequency;
		int ref2 = Lfu.get(0).add_time;
		int index = 0;
		
		for(int i = 0; i < Lfu.size(); i++) {
			if(Lfu.get(i).frequency < ref) {
				ref = Lfu.get(i).frequency;
				index = i;
			}		
		}
		
		ref2 = Lfu.get(index).add_time;
		
		for(int i = 0; i < Lfu.size(); i++) {
			if(Lfu.get(i).frequency == ref) {
				if(Lfu.get(i).add_time > ref2) {
					ref2 = Lfu.get(i).add_time;
					index = i;
				}
			}
		}	
		
		Lfu.remove(index);
	}

}
