package strikeball;

/**
 *
 * @author Dominus
 */
public class Countdown extends Thread {
	private int tempo;
	private boolean connesso;
        
	public Countdown (int tempo) {
		this.tempo=tempo;
		this.connesso=false;
	}
	
	public void run(){
		while(tempo>0 && connesso==false)
		{
			try {
				System.out.println(tempo/1000);
				this.sleep(1000);
				tempo=tempo-1000;
			} catch (InterruptedException ex) {
				System.out.println("ERRORE Countdown");
			}
		}
	}

	
	public void setConnesso(boolean connesso){
		this.connesso=connesso;
	}
        
}