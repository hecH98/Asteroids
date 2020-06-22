//Clase escrita por José Adolfo Sánchez Micalco - A01635093 y Diego Bernardo Sánchez Micalco - A01635164
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Laser {
       private int x,
       				y,
       				x1,
       				y1,
       				velX,
       				velY;
      
       public Laser(int x,int y,int x1, int y1) {
    	 
    	   this.x = x;
    	   this.y = y;
    	  
    	   this.x1 = x1;
    	   this.y1 = y1;
    	
       }
       
       public void pinta(Graphics g) {
    	   Graphics2D g2 = (Graphics2D) g;
    	   g2.setStroke(new BasicStroke(2));
    	   g2.setColor(Color.WHITE);
    	   g2.drawLine(x1, y1, x1, y1); 
       }
       
    
       
       public void shoot() {
        
    	    velX =(int) (Math.sqrt((Math.pow(x1-x, 2))+Math.pow((y-y), 2)))/2;
        	velY=(int) (Math.sqrt((Math.pow(x1-x1, 2))+Math.pow((y1-y), 2)))/2;
        	this.velX = (x1 < this.x)? velX*-1:velX;
         	this.velY = (y1 < this.y)? velY*-1:velY;
         	
         	this.x1+=velX;
        	this.y1+=velY;
          
          
       }
       
       public int getX1() {
        	return this.x1;
         }
         
         public int getY1() {
        	 return this.y1;
        
         }
         
        public void setVel() {
        	velX= 0;
        	velY=0;
        }
         
         
}
