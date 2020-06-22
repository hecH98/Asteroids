//Clase escrita por José Adolfo Sánchez Micalco - A01635093 y Diego Bernardo Sánchez Micalco - A01635164
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
 class Menu extends JPanel implements ActionListener {
	
	private JLabel ltitle;
	private JButton btnPlay,
					btnQuit;
	private Font font1;
	private static File bckMusic;

	
	public Menu() {
		this.setPreferredSize(new Dimension(1000, 800));
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		this.font1=new Font("OCR A Extended", Font.BOLD, 100);
		this.ltitle=new JLabel("Asteroids");
		this.ltitle.setFont(this.font1);
		this.ltitle.setForeground(Color.WHITE);
		this.add(this.ltitle);
		this.ltitle.setBounds(220, 100, 600, 100);
		
		this.btnPlay=new JButton("Play");
		this.btnPlay.addActionListener(this);
		this.add(this.btnPlay);
		this.btnPlay.setBounds(450, 250, 100, 40);
		
		
		this.btnQuit=new JButton("Quit");
		this.btnQuit.addActionListener(this);
		this.add(this.btnQuit);
		this.btnQuit.setBounds(450, 350, 100, 40);
		this.bckMusic=new File("273149__tristan-lohengrin__spaceship-atmosphere-04.wav");
		playMusic();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnPlay) {
			playMusic();
			JFrame frame=new JFrame();
			SpacePanel sp= new SpacePanel();
			frame.add(sp);
			frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
			frame.setResizable(false);
			Window win = SwingUtilities.getWindowAncestor(btnPlay);
			win.dispose();
			
		} else if (e.getSource()==this.btnQuit) {
			/*Window win = SwingUtilities.getWindowAncestor(btnQuit);
			win.dispose();*/
			System.exit(0);
		}
	}
	

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.setFont(new Font("OCR A Extended", Font.BOLD, 15));
		g.drawString("How to play?", 10, 670);
		g.drawString("Press 'X' to shoot", 10, 710);
		g.drawString("Press 'C' to teleport", 10, 730);
		g.drawString("Press Up arrow key to move foward", 10, 750);
		g.drawString("Press left and right arrow keys to rotate the ship", 10, 770);
		
		
	}
	public static void playMusic() {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(bckMusic));
			clip.loop(-1);
			clip.start();
			
			} catch(Exception e) {
			System.out.println("Excepcion");
			}
	}



	
	

}
