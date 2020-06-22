//Clase escrita por José Adolfo Sánchez Micalco - A01635093 y Diego Bernardo Sánchez Micalco - A01635164
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Random;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class SpacePanel extends JPanel implements Runnable, KeyListener {
    private Graphics2D g2;
	private Spaceship ship;
	private Thread hilo;
	private int height=800, width=1000;
	private Asteroid[] asteroid;
	private SmallAsteroid[] miniasteroid;
	private Laser[] laser;
	private ShipLife shiplives;
	private Font myFont = new Font ("OCR A Extended", 1, 40);
	private int score;
	private int lives = 3;
	private float timer;
	int laser1;
	public SpacePanel() {
		super();
		this.timer = 0;
		this.laser1=0;
		this.setPreferredSize(new Dimension(width,height));
		this.setBackground(Color.BLACK);
		//inicializacion de la nave
		this.ship = new Spaceship();
		//inicializacion del laser
		this.laser = new Laser[10];
		//inicializacion del score en 0
	    this.score= 0;
		//contador de miniasteroides creados
		//inicializador de asteroides
		this.asteroid = new Asteroid[10];
		this.miniasteroid = new SmallAsteroid[20];
		
		this.shiplives = new ShipLife(width/19, height/12,lives);
		for(int i = 0; i<asteroid.length; i++) {
			asteroid[i] = new Asteroid();
		}
		//inicializador de el thread
		this.hilo = new Thread(this);
	    this.hilo.start();	
	
		//agregar el key listener al panel
		this.addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
	}
	
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//aqui se dibuja el score de puntos
		g.setFont (myFont);
		g.setColor(Color.WHITE);
		
		
		String score1 = Integer.toString(score);
		g.drawString(score1, 45, 35);
		
		g2 = (Graphics2D) g;
		
		this.shiplives.pinta(g);
		//aqui se pinta la nave
		if(ship != null) {
		ship.pinta(g2);
		}

	 //aqui se pintan los asteroides
		for(int i = 0; i<asteroid.length; i++) {
			if(asteroid[i] != null)
		      asteroid[i].pinta(g);
		}
		
		//aqui se pintan los miniasteroides
		for(int i = 0; i<miniasteroid.length; i++) {
			if(miniasteroid[i] != null)
		      miniasteroid[i].pinta(g);
		}
		
		//aque se pintan los disparos
		for(int i = 0; i<laser.length; i++) {
			if(laser[i] != null)
		      laser[i].pinta(g);
		}
		
	
		//aqui agrega el comportamientode las paredes de la ventana
		this.walls();
		//aqui se agregan los comportamientos de destruccion de objetos
		this.destruction();
		//aqui se dibuja en el caso de perder
		if(ship == null) {
			g.drawString("GAME OVER", width/2-100, height/2);
			g.drawString("Press SPACE to return to Menu", width/2-400, 700);
		}
		
		//Aqui se permite reiniciar los asteroides 
		this.infiniteAsteroids();
	}
		
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
    while(this.lives!=10000) {
		try {
			ship.moveFront();
	    
			for(int i = 0; i<asteroid.length; i++) {
				if(asteroid[i] != null)
					asteroid[i].setVel();
		
			}
		
			for(int i = 0; i<miniasteroid.length; i++) {
				if(miniasteroid[i] != null)
					miniasteroid[i].setVel();
		
			}
	
			for(int i = 0; i<laser.length; i++) {
				if(laser[i] != null) {
					 
				   laser[i].shoot();
					
					 
				}	
	}			
		repaint();	
		Thread.sleep(50);
		
	}
	catch(InterruptedException e) {
		System.out.println(e);
	} catch(NullPointerException ex) {
		
	}
		
    }
		}
	

	@Override
	public void keyPressed(KeyEvent k) {
		//timer que limita la aceleracion de la nave
		timer+=1;
		//Aqui se define el moviemiento de la nave con las teclas
		if(ship!=null) {
			if(k.getKeyCode()==KeyEvent.VK_UP) {
			        if(timer<7) {
						SpacePanel.PlaySound(ship.propulsion);
						ship.moveFront();
						ship.setAcum(10);
			        }
				
			}
			if(k.getKeyCode()==KeyEvent.VK_RIGHT) {
				ship.rotate(0);
			} else if(k.getKeyCode()==KeyEvent.VK_LEFT) {
				ship.rotate(1);
			}
			if(k.getKeyCode()==KeyEvent.VK_C) {
				ship.salto();
			}
		
			if(k.getKeyCode()==KeyEvent.VK_X) {
				SpacePanel.PlaySound(ship.laser);
				for(int i = laser1; i<laser.length; i++) {
					this.laser[i] = new Laser(this.ship.getX(),this.ship.getY(),this.ship.getX1(),this.ship.getY1());
					 
				}
				  laser1+=1;
				  if(laser1>10) {
					  laser1=0;
				  }
	            
			}
	}
		
		//al presionar space regresa al menu
		if(k.getKeyCode()==KeyEvent.VK_SPACE) {
			JFrame frame=new JFrame();
			Menu sp= new Menu();
			frame.add(sp);
			frame.pack();
			frame.setVisible(true);
			frame.setResizable(false);
			Window win = SwingUtilities.getWindowAncestor(this);
			win.dispose();
			/*if(ship== null) {
			this.score = 0;
	        ship = new Spaceship();
	        lives = 3;
			shiplives.updateShipLife(lives);

	    	for(int i = 0; i<asteroid.length; i++) {
				
			     asteroid[i] = new Asteroid();
			    
			}
	        }*/
	  }
		repaint();
	}
	
	

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub

	   //aqui se evita que se acomule la aceleracion al apretar demasiado la tecla
        if(timer>=7) {
        	if(ship!=null) {
        		ship.slowAcum(0);
        	}
        
        }
	    timer=0;
		repaint();
	}
	

	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub
	//	int key = k.getKeyCode();

	}
	
	
	//aqui se manejan los casos de interaccion entre los objetos y los bordes de la vetnana
	public void walls() {
	if(ship!=null) {
		if(ship.getX()>width) {
			ship.setCoordinates(0, ship.getY());
		} else if(ship.getX()<0) {
			ship.setCoordinates(width, ship.getY());
		}
		
		if(ship.getY()>height) {
			ship.setCoordinates(ship.getX(), 0);
		} else if(ship.getY()<0) {
			ship.setCoordinates(ship.getX(),height );
		}
		for(int i = 0; i<asteroid.length; i++) {
			if(asteroid[i] != null) {
				if(asteroid[i].getX()>width) {
					asteroid[i].setCoordinates(0, asteroid[i].getY());
				} else if(asteroid[i].getX()<0) {
					asteroid[i].setCoordinates(width, asteroid[i].getY());
				}
				
				if(asteroid[i].getY()>height) {
					asteroid[i].setCoordinates(asteroid[i].getX(), 0);
				} else if(asteroid[i].getY()<0) {
					asteroid[i].setCoordinates(asteroid[i].getX(),height );
				}
			}
		}
		
		
		for(int i = 0; i<miniasteroid.length; i++) {
			if(miniasteroid[i] != null) {
				if(miniasteroid[i].getX()>width) {
					miniasteroid[i].setCoordinates(0, miniasteroid[i].getY());
				} else if(miniasteroid[i].getX()<0) {
					miniasteroid[i].setCoordinates(width, miniasteroid[i].getY());
				}
				
				if(miniasteroid[i].getY()>height) {
					miniasteroid[i].setCoordinates(miniasteroid[i].getX(), 0);
				} else if(miniasteroid[i].getY()<0) {
					miniasteroid[i].setCoordinates(miniasteroid[i].getX(),height );
				}
			}
		}
	}
	}
	
	//aqui se manejan todos los casos de interaccion entre la nave y los meteoritos, o los disparos y los meteritos
	public void destruction() {
		for(int i = 0; i<asteroid.length; i++) {
			if(asteroid[i] != null ) {
			  if(ship !=null) {
				if(ship.getX() <= asteroid[i].getX()+30 &&ship.getY() <= asteroid[i].getY()+30 && ship.getX() >= asteroid[i].getX()-30 &&ship.getY() >= asteroid[i].getY()-30) {
					lives =lives-1;
					ship.setLives(lives);
					shiplives.updateShipLife(lives);
					ship.setCoordinates(width/2, height/2);
					SpacePanel.PlaySound(ship.explosion);
					if(lives<0) {	
				     ship = null;
					}
				   }
			  }
		
		for(int j = 0; j<laser.length; j++) {
				if(asteroid[i] != null) {
					if(laser[j] != null) {
						if(laser[j].getX1() <= asteroid[i].getX()+45 &&laser[j].getY1() <= asteroid[i].getY()+45 && laser[j].getX1() >= asteroid[i].getX()-45 &&laser[j].getY1() >= asteroid[i].getY()-45){
							this.score+=100; 
							SpacePanel.PlaySound(asteroid[i].explosion);
							 this.miniasteroid[i] = new SmallAsteroid();
							 this.miniasteroid[i+1] = new SmallAsteroid();
							 this.miniasteroid[i].setCoordinates(asteroid[i].getX()+35,asteroid[i].getY()+35);
							 this.miniasteroid[i+1].setCoordinates(asteroid[i].getX()-35,asteroid[i].getY()-35);
							 this.asteroid[i] = null;
			 
						}
					}
				}
		}
			}
		}
		
		for(int i = 0; i<miniasteroid.length; i++) {
			if(miniasteroid[i] != null) {
				if(ship!=null) {
					if(ship.getX() <= miniasteroid[i].getX()+22 &&ship.getY() <= miniasteroid[i].getY()+22 && ship.getX() >= miniasteroid[i].getX()-22 &&ship.getY() >= miniasteroid[i].getY()-22){
						lives -=1;
						ship.setLives(lives);
						shiplives.updateShipLife(lives);
						ship.setCoordinates(width/2, height/2);
						SpacePanel.PlaySound(ship.explosion);
						if(lives<0) {
							ship = null;
						}
					}
				}
				for(int j = 0; j<laser.length; j++) {
					if(miniasteroid[i] != null) {
						if(laser[j] != null) {
							if(laser[j].getX1() <= miniasteroid[i].getX()+18 &&laser[j].getY1() <= miniasteroid[i].getY()+18 && laser[j].getX1() >= miniasteroid[i].getX()-18&&laser[j].getY1() >= miniasteroid[i].getY()-18){
								SpacePanel.PlaySound(miniasteroid[i].explosion);
								this.miniasteroid[i] = null;
								this.score+=150; 
							}	
						}						
					}
				}
			}
		}
		if(ship==null) {
			for(int l = 0; l<miniasteroid.length; l++) {
				miniasteroid[l]=null;
			}
		}
	}
	

	//Esto nos permite reiniciar los asteroides
	public void infiniteAsteroids() {
		int cont = 0;
		for(int i = 0; i<asteroid.length; i++) {
			if(asteroid[i]==null)
           cont+=1;
		}
		for(int k = 0; k<miniasteroid.length; k++) {
			if(miniasteroid[k] == null) {
				cont+=1;
				if(cont == 30) {
					for(int j = 0; j<asteroid.length; j++) {
						
					     asteroid[j] = new Asteroid();	    
					}
				}
			}
		}
	}
	
	//Esto nos permite tocar los sonidos 
	public static void PlaySound(File Sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength()/100000);
			} catch(Exception e) {
				
			}
	}
	

	}
	
 

