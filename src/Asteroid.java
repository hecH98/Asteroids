//Clase escrita por José Adolfo Sánchez Micalco - A01635093 y Diego Bernardo Sánchez Micalco - A01635164
import java.awt.Graphics;
import java.io.File;
import java.util.Random;

public class Asteroid {

	protected int xAst,
	            yAst,
	            velX,
	            velY;
	protected int[] xArre;
	protected int[] yArre;
	protected int corner ;
	protected int size;
	
	protected File explosion;
	protected Random rand = new Random();

	public Asteroid() {
		explosion = new File("94185__nbs-dark__explosion.wav");
		size=9;
		 velX = rand.nextInt(5+1+5)-5;
		 velY = rand.nextInt(5+1+5)-5;
		corner = rand.nextInt(4-0)+0;
		
		if(corner == 0) {
			  xAst= 0;
	    	  yAst= rand.nextInt(950) + 1;
		} else if(corner== 1) {
			 xAst = rand.nextInt(750) + 1;
	    	 yAst= 0;
		}else if(corner== 2) {
			 xAst = rand.nextInt(750) + 1;
	    	  yAst= 0;
		}else if(corner == 3) {
			  xAst = 0;
	    	  yAst= rand.nextInt(950) + 1;
		}
	 
		
	}
	
	public void pinta(Graphics g) {
     this.setCoordinates(xAst, yAst);
        if(corner == 0 || corner ==2 ) {
		int[] xArre = {xAst,xAst+(10+size),xAst+(20+size),xAst+(18+size),xAst+(20+size),xAst,xAst-(20+size),xAst-(30+size),xAst-(30+size),xAst-(14+size)};
		 int[]yArre= {yAst-(20+size),yAst-(20+size),yAst-(10+size),yAst+(3+size),yAst+(16+size),yAst+(30+size),yAst+(30+size),yAst+(14+size),yAst-(10+size),yAst-(30+size)};
		 g.drawPolygon(xArre,yArre,10);
        } else if(corner == 1||corner==3) {
    	int[] xArre = {xAst,xAst+(10+size),xAst+(20+size),xAst+(18+size),xAst+(20+size),xAst,xAst-(15+size),xAst-(30+size),xAst-(30+size),xAst-(14+size)};
    	int[] yArre = {yAst-(20+size),yAst-(20+size),yAst-(10+size),yAst+(3+size),yAst+(16+size),yAst+(30+size),yAst+(20+size),yAst+(14+size),yAst-(10+size),yAst-(30+size)};
    	g.drawPolygon(xArre,yArre,10);
        } 
       
	}
	
	
	public void setVel() {
	
		this.xAst += velX;
		this.yAst += velY;
	}
	
	public int getX() {
		return this.xAst;
	}
	public int getY() {
		return this.yAst;
	}
	
	  public void setCoordinates(int x, int y) {
	    	 this.xAst = x;
	    	 this.yAst = y;
	     }
	  
	  
	 public File explosion() {
		 return explosion;
	 }

	  }


