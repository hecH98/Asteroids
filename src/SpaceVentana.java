//Clase escrita por Jos� Adolfo S�nchez Micalco - A01635093 y Diego Bernardo S�nchez Micalco - A01635164
import javax.swing.JFrame;

public class SpaceVentana extends JFrame{
        
	
	public SpaceVentana() {
		super("Asteroids 2018");
		Menu menu= new Menu();
		this.add(menu);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}
	public static void main(String[] args) {
         SpaceVentana ven = new SpaceVentana();
	}
	


}
