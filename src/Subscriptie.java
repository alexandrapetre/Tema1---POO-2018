public abstract class Subscriptie {

	String nume;
	int timestamp;
	int frequency = 0, add_time ;
	
	public Subscriptie(String nume) {
		this.nume = nume;
	}
	
	public Subscriptie() {
	}
	
	public abstract void scadere();

}