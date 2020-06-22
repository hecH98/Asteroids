//Clase escrita por José Adolfo Sánchez Micalco - A01635093 y Diego Bernardo Sánchez Micalco - A01635164
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.util.Random;

public class Spaceship {
	private  int  x,y,x1,y1,x2,y2,x3,y3,
              velX,
              velY,
              rotator,
              dir,
              lives,
              acumX,
              acumY;
	private double r;
	protected File explosion, laser, propulsion;

     public Spaceship() {
    	
    	 //posicion inicial
    	 x=500;
    	 y=400;
    	 //direccion de la vista de la nava, si es negativa es 1 si es positiva es 0
    	 dir = 0;
    	 //velocidad inicial, estatica
    	 velX = 0;
    	 velY= 0;
    	 //direccion en grados de la nave, en orientacion reloj
    	 rotator = 0;
    	 //radio de rotacion de la figura
    	 r=-20;
    	 //numero de vidas iniciales
    	 lives = 3;
    	
    	 //acomulador de aceleracion
    	 acumX= 0;
    	 acumY=0;
    	
    	 explosion = new File("94185__nbs-dark__explosion.wav");
    	 laser = new File("344512__sharesynth__laser05.wav");
    	 propulsion = new File("168984__lavik89__digital-hit.wav");

     }
     
     
     public void pinta(Graphics2D g2) {
    	 g2.setColor(Color.WHITE);
    	
    	 //figura de la nave ajustada por la rotacion
    	 x1 =(int) ((int) x + r * Math.sin(Math.toRadians(-rotator)));
    	 y1 =(int) ((int) y + r * Math.cos(Math.toRadians(-rotator)));
    	 x2 =(int) ((int) x + (5*Math.sqrt(5)) * Math.sin(Math.toRadians(-rotator+75)));
    	 y2 =(int) ((int) y + (5*Math.sqrt(5))*Math.cos(Math.toRadians(-rotator+75)));
    	 x3 =(int) ((int) x + (5*Math.sqrt(5))* Math.sin(Math.toRadians(-rotator-75)));
    	 y3 =(int) ((int) y +(5*Math.sqrt(5))* Math.cos(Math.toRadians(-rotator-75)));
         
         
    	//rotacion de 0 a 360 grados
    	 this.rotator = (this.rotator < 0)? 360:rotator;
    	 this.rotator = (this.rotator > 360)? 0:rotator;
  
    	 //metodo que permite rotar la variable de origen
    	 AffineTransform affineTransform = new AffineTransform();
     	 affineTransform.rotate(Math.toRadians(rotator),this.x,this.y);
     	
     	//aqui ya se dibuja la figura
    	 int[] xArre = {x,x2,x1,x3};
    	 int[] yArre = {y,y2,y1,y3}; 	
    	 g2.drawPolygon(xArre, yArre, 4);
    	
       //this.tail(g2);

    	
    	 
     }
     
     public void moveFront() {
    	//calcular el vector a traves de el punto de origen y el punto que representa la direccion de la nave
     	velX =(int) Math.sqrt((Math.pow(x1-x, 2))+Math.pow((y-y), 2));
     	velY=(int) Math.sqrt((Math.pow(x1-x1, 2))+Math.pow((y1-y), 2));
     	this.velX = (x1 < this.x)? velX*-1:velX;
     	this.velY = (y1 < this.y)? velY*-1:velY;
      
    

   	   //agrega constantemente la velocidad de manera gradual con el acomulador de velocidad
    	 this.x += acumX;
       	 this.y += acumY;
    	
    			 
     }
     
     public void rotate(int dir) {
    	 //actualiza la direccion que la nave esta volteando
    	 this.dir = dir;
         this.rotator = (dir== 0)? this.rotator+=15: (this.rotator-=15);
    	
     }
     
   
     //setter de la posicion de la nave para poder usar la habilidad de salto
     public void setCoordinates(int x, int y) {
    	 this.x = x;
    	 this.y = y;
     }
     
     //getter de la cooredenada x
     public int getX() {
    	return this.x;
     }
     
     //getter de la cooredenada y
     public int getY() {
    	 return this.y;
    
     }
     
     //getter de la cooredenada x1, la punta de la nave
     public int getX1() {
     	return this.x1;
      }
      
     //getter de la cooredenada y1, la punta de la nave
      public int getY1() {
     	 return this.y1;
      }
    
      //habilidad de salto
     public void salto() {
    	 Random rand = new Random();
    	 int  x = rand.nextInt(750) + 1;
    	 int  y= rand.nextInt(950) + 1;
    	 this.setCoordinates(x, y);
     }
     
     //getter de numero de vidas
     public int getLives() {
    	 return this.lives;
     }
     
     //setter de numero de vidas
     public void setLives(int lives) {
    	 this.lives = lives;
     }
    
     //setter de que tan rapido de acelera la nave
	public void setAcum(int rate) {
		this.acumX +=velX/rate;
		this.acumY +=velY/rate;
	}
	
	//desacelera la nave
	public void slowAcum(int rate) {
		this.acumX +=rate;
		this.acumY +=rate;
	}
	
	public int getAcumX() {	
		return this.acumX +=velX/10;
	}
	
	public int getAcumY() {
		return this.acumY +=velY/10;
	}
	
	public void tail(Graphics g) {
		int[] xTail = {x1-3,x1,x1+3};
		int[] yTail = {y1+10,y1-13,y1+10};
		g.drawPolygon(xTail,yTail,3);
	}
	
 
	 public File explosion() {
		 return explosion;
	 }
	 
	 public File laser() {
		 return laser;
	 }
	 
	 public File propulsion() {
		 return propulsion;
	 }
}

