//Clase escrita por Jos� Adolfo S�nchez Micalco - A01635093 y Diego Bernardo S�nchez Micalco - A01635164
import java.awt.Graphics;
import java.io.File;

public class SmallAsteroid extends Asteroid {
  
	public SmallAsteroid() {	
		super();
		this.size= -5;
		this.velX = rand.nextInt(7+1+7)-7;
		this.velY = rand.nextInt(7+1+7)-7;

	}
	
//	public void pintaSmallAsteroid(Graphics g) {	
//		super.pinta(g);
//	}
	
	
//	public File explosion() {
//		return super.explosion();
//	}

}
