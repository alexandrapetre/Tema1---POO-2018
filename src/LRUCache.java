import java.util.*;

/**
 * @author Alexandra Petre
 *
 */
public class LRUCache implements Cache {

	ArrayList<Subscriptie> Lru = new ArrayList<Subscriptie>();
	int nr_max;  // numarul maxim de elemente din memoria Cache
	
	
	/**
	 * @param : n
	 * Seteaza numarul maxim de elemente din memoria Cache
	 *
	 */
	public LRUCache(int n) {
		this.nr_max = n;
	}
	
	/**
	 * @param : Subscriptie s
	 * Metoda seteaza pentru subscriptia s cu 0 momentul in care a fost accesat elementul 
	 * Valoarea 0 indica cel mai recent element utilizat
	 *
	 */
	public void getTime(Subscriptie s) {
		
		for(int i = 0; i < Lru.size(); i++) {
			if(Lru.get(i).nume.equals(s.nume))
				Lru.get(i).timestamp = 0;
			else
				Lru.get(i).timestamp++;
		}
	}
	
	/**
	 * @param : Subscriptie s
	 * Se adauga in memoria Cache un nou element in functie de numarul maxim de elemente la momentul adaugarii
	 *
	 */
	
	public void add(Subscriptie s) {
		
		if(Lru.size() < this.nr_max) {
			Lru.add(s);
			getTime(s);
		}
		else{
			remove();
			Lru.add(s);
			getTime(s);
		}
	}
	
	/**
	 * @param : - 
	 * Se elimina din memoria Cache elementul care are indicele de timp cel mai mare (timestamp maxim)
	 */
	
	public void remove() {
		
		int ref = Lru.get(0).timestamp;
		int index = 0;
		
		for(int i = 0; i < Lru.size(); i++ ) {
			if(Lru.get(i).timestamp > ref) {
				ref = Lru.get(i).timestamp;
				index = i;
			}
		}
		Lru.remove(index);
	}
}

