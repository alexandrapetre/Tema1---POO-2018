import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Scanner;


/**
 * @author Alexandra Petre
 *
 */
public class Main {

	public static void main(String[] argv) throws FileNotFoundException {
		
		
		File inFile = new File(argv[0]); // fisier de intrare
		PrintWriter writer = new PrintWriter(argv[1]); // fisier de iesire 
		Scanner sc = null;
		ArrayList<Subscriptie> memorie_principala = new ArrayList<Subscriptie>();  // vectorul de subscriptii care reprezinta memoria principala
		String line;
		int nr_max_cache; 
		int nr_comenzi;
		int nr_b, nr_p; // numarul de cereri basic (nr_b) si numarul de cereri premium (nr_p)
		int k;
		int semafor = 0;
		int idx = 0;
		int index = 0, index2 = 0 ;
		
		try {
			sc = new Scanner(inFile);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String metoda = sc.nextLine();
			
		line = sc.nextLine();
		nr_max_cache = Integer.parseInt(line);

		LRUCache l = new LRUCache(nr_max_cache);
		FIFOCache f = new FIFOCache(nr_max_cache);
		LFUCache lf = new LFUCache(nr_max_cache);
		line = sc.nextLine();
		nr_comenzi = Integer.parseInt(line);
		
		
		/**
		 *
		 * Se aduga elemente in memoria principala 
		 * Se creaza in functie de element subscriptii basic sau premium
		 * Se verifica in functie de memoria Cache daca la o adaugare noua elementul a mai fost adaugat sau nu
		 * Daca elementul este introdus in memoria principala a doua oara acesta este suprascris
		 * Se verifica si daca elementul suprascris a existat si in Cache, daca da, se elemina din Cache
		 *
		 */
		for(int i = 0; i < nr_comenzi; i++) {
			line = sc.nextLine();
			String[] parts = line.split(" ");
				
				if(parts[0].equals("ADD")) {
					
					line = parts[2];
					nr_b = Integer.parseInt(line);
					
					if(parts.length == 4) {
						line = parts[3];
						nr_p = Integer.parseInt(line);
						Premium p = new Premium(parts[1], nr_p);
						p.nr_subscriptii_Basic = nr_b;
						
						semafor = 0;
						for( int j = 0; j < memorie_principala.size(); j++) {
							if(memorie_principala.get(j).nume.equals(parts[1])) {
								idx = j;
								semafor = 3;
								break;
							}
						}
						if(semafor == 3) {
							
							memorie_principala.set(idx, p);
							
							for(int j = 0; j < f.Fifo.size(); j++) {
								if(f.Fifo.get(j).nume.equals(parts[1])) {
									f.Fifo.remove(j);
									break;
								}		
							}
							
							for(int j = 0; j < l.Lru.size(); j++) {
								if(l.Lru.get(j).nume.equals(parts[1])) {
									l.Lru.remove(j);
									break;
								}
							}
							
							for(int j = 0; j < lf.Lfu.size(); j++) {
								if(lf.Lfu.get(j).nume.equals(parts[1])) {
									lf.Lfu.remove(j);
									break;
								}
							}
							
						}
						else {
							memorie_principala.add(p);
						}
					}
					
					else{
						semafor = 0;
						Basic b = new Basic(parts[1], nr_b);
					

							for( int j = 0; j < memorie_principala.size(); j++) {
								if(memorie_principala.get(j).nume.equals(parts[1])) {
									idx = j;
									semafor = 3;
									break;
								}

							}
							if(semafor == 3) {
								
								memorie_principala.set(idx, b);
								
								for(int j = 0; j < f.Fifo.size(); j++) {
									if(f.Fifo.get(j).nume.equals(parts[1])) {
										f.Fifo.remove(j);
										break;
									}		
								}
								
								for(int j = 0; j < l.Lru.size(); j++) {
									if(l.Lru.get(j).nume.equals(parts[1])) {
										l.Lru.remove(j);
										break;
									}
								}
								
								for(int j = 0; j < lf.Lfu.size(); j++) {
									if(lf.Lfu.get(j).nume.equals(parts[1])) {
										lf.Lfu.remove(j);
										break;
									}
								}
							}
							else {
								memorie_principala.add(b);
							}
						}	
					}
				/**
				 * Se cauta elementul dat in functie de memoria Cache
				 * Prima oara in memoria principala, daca acesta este in memoria principala se cauta si in Cache
				 * Daca elementul nu a fost gasit in Cache, dar este in memoria principala, este introdus in memoria Cache si afisat
				 *
				 */
				
					if(parts[0].equals("GET")) {
						
						k = 2;
						
						if(metoda.equals("FIFO")) {
						
							for(int j = 0; j < memorie_principala.size(); j++) {
								if(memorie_principala.get(j).nume.equals(parts[1])) {
									k = 1;
									index = j;
								}
							}
						
							for(int j = 0; j < f.Fifo.size(); j++) {
								if(f.Fifo.get(j).nume.equals(parts[1])) {
									k = 0;
									index2 = j;
								}
							}
					
							if(k == 2) {
								writer.print(k+ "\n");
							}
							if(k == 1) {
						
								writer.print("1 " + memorie_principala.get(index).toString() + "\n");
								memorie_principala.get(index).scadere();
								f.add(memorie_principala.get(index));
							}
							if(k == 0) {
								writer.print("0 " + f.Fifo.get(index2).toString() + "\n");
								f.Fifo.get(index2).scadere();
							}
						}
						
						if(metoda.equals("LRU")) {
							
							for(int j = 0; j < memorie_principala.size(); j++) {
								if(memorie_principala.get(j).nume.equals(parts[1])) {
									k = 1;
									index = j;
								}
							}
						
							for(int j = 0; j < l.Lru.size(); j++) {
								if(l.Lru.get(j).nume.equals(parts[1])) {
									k = 0;
									index2 = j;
									l.getTime(l.Lru.get(index2));
									break;
								}
							}
					
							if(k == 2) {
								writer.print(k+ "\n");
							}
							
							if(k == 1) {
						
								writer.print("1 " + memorie_principala.get(index).toString() + "\n");
								l.add(memorie_principala.get(index));
								memorie_principala.get(index).scadere();
							}
							
							if(k == 0) {
								writer.print("0 " + l.Lru.get(index2).toString() + "\n");
								l.Lru.get(index2).scadere();
							}
						}
						
						if(metoda.equals("LFU")) {
							
							for(int j = 0; j < memorie_principala.size(); j++) {
								if(memorie_principala.get(j).nume.equals(parts[1])) {
									k = 1;
									index = j;
								}
							}
						
							for(int j = 0; j < lf.Lfu.size(); j++) {
								if(lf.Lfu.get(j).nume.equals(parts[1])) {
									k = 0;
									index2 = j;
									lf.set_frequency(lf.Lfu.get(j));
									break;
								}
							}
					
							if(k == 2) {
								writer.print(k + "\n");
							}
							
							if(k == 1) {
								writer.print("1 " + memorie_principala.get(index).toString() + "\n");
								lf.add(memorie_principala.get(index));
								memorie_principala.get(index).scadere();
							}
							
							if(k == 0) {
								writer.print("0 " + lf.Lfu.get(index2).toString() + "\n");
								lf.Lfu.get(index2).scadere();
							}

						} 
						
				}
		}
	writer.close(); // inchiderea fisierului in care se scrie
	}		
}
