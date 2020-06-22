//Clase escrita por José Adolfo Sánchez Micalco - A01635093 y Diego Bernardo Sánchez Micalco - A01635164
import java.awt.Graphics;

public class ShipLife {

	private int x,
		y,
		lives;
	
	public ShipLife(int x, int y, int lives) {
		this.x = x;
		this.y = y;
		this.lives = lives;
	}
	
	public void pinta(Graphics g) {
	
		int[] xArre = {x+10,x,x-10,x};
		int[] yArre = {y+10,y-20,y+10,y};
		int[] xArre2 = {x+40,x+30,x+20,x+30};
		int[] yArre2 = {y+10,y-20,y+10,y};
		int[] xArre3 = {x+70,x+60,x+50,x+60};
		int[] yArre3 = {y+10,y-20,y+10,y};
		
		if(lives == 1) {
			g.drawPolygon(xArre,yArre,4);
		} else if (lives == 2){
			g.drawPolygon(xArre,yArre,4);
			g.drawPolygon(xArre2,yArre2,4);

		} else if(lives==3) {
			g.drawPolygon(xArre,yArre,4);
			g.drawPolygon(xArre2,yArre2,4);
			g.drawPolygon(xArre3,yArre3,4);
		}
		

		}
	
	public void updateShipLife(int lives) {
		this.lives = lives;
	}
	
}
