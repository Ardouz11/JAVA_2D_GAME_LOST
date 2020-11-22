package PROJETJAVA;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartingClass extends JPanel implements Runnable, KeyListener, MouseListener {
	int x=0;
	String BGM="./audio/beegees.wav";
	Audio MP=new Audio();
	static int var, var2 = 500, var1 = 500, cpt = 0;// initialisation du point de départ du jeu
	private boolean espace_press;
	
	ArrayList projectiles;
	public static int move, score;
	static StartingClass starter = new StartingClass();

	private static Robot robot;
	private BufferedImage currentSprite, c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, s0, s1, s2, s3, s4, s5, s6,
			s7, s8, s9, s10, s11, crouch_r0, crouch_r1, crouch_r2, crouch_r3, crouch_l0, crouch_l1, crouch_l2,
			crouch_l3, menu, bulletr, bulletl, characterJumped_r, characterJumped_l, background1, background2, el, e2l,
			er, e2r, play, exit, logo, start, deathimg, menubutton, playagain;
	private static Background bg1, bg2, bg3, bg4;
	public Animation anim_r, anim_l, anim, anim_still_l, crouchdown_r, 
			crouchdown_l;
	public static Animation enemy_l, enemy_r;

	public static BufferedImage tiledirt, grasstop, tilestone, tiletree, tilerock;
	private static ArrayList<Tile> tilearray = new ArrayList<Tile>();

	static String State = "start";
	Menu MENU;
	Death DEATH;
	public static void restart() {
		
		score = 0;
		move = 0;
		robot.getProjectiles().clear();
		tilearray.clear();
		Enemy.enemies.clear();
		bg1 = new Background(0, 0);
		bg2 = new Background(1920, 0);
		bg3 = new Background(-1920, 0);
		bg4 = new Background(-1920 * 2, 0);
		robot = new Robot();

		try {
			starter.loadMap("Images/map1.txt"); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void init() throws Exception {

		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		
		// Image Setups

		characterJumped_r = ImageIO.read(new File("Images/jumpedr.png"));
		characterJumped_l = ImageIO.read(new File("Images/jumpedl.png"));

		background1 = ImageIO.read(new File("Images/bg1.png"));
		background2 = ImageIO.read(new File("Images/bg2.png"));
		tiledirt = ImageIO.read(new File("Images/tiledirt.png"));
		grasstop = ImageIO.read(new File("Images/grasstop.png"));
		tilestone = ImageIO.read(new File("Images/tilestone.png"));
		tiletree = ImageIO.read(new File("Images/tiletree.png"));
		tilerock = ImageIO.read(new File("Images/tilerock.png"));
		;

		bulletr = ImageIO.read(new File("Images/bulletr.png"));
		bulletl = ImageIO.read(new File("Images/bulletl.png"));

		c0 = ImageIO.read(new File("Images/walk/walk-r (1).png"));//animation de la marche du robot à gauche
		c1 = ImageIO.read(new File("Images/walk/walk-r (2).png"));
		c2 = ImageIO.read(new File("Images/walk/walk-r (3).png"));
		c3 = ImageIO.read(new File("Images/walk/walk-r (4).png"));
		c4 = ImageIO.read(new File("Images/walk/walk-r (5).png"));
		c5 = ImageIO.read(new File("Images/walk/walk-r (6).png"));
		c6 = ImageIO.read(new File("Images/walk/walk-r (7).png"));
		c7 = ImageIO.read(new File("Images/walk/walk-r (8).png"));
		c8 = ImageIO.read(new File("Images/walk/walk-r (9).png"));
		c9 = ImageIO.read(new File("Images/walk/walk-r (10).png"));
		c10 = ImageIO.read(new File("Images/walk/walk-r (11).png"));
		c11 = ImageIO.read(new File("Images/walk/walk-r (12).png"));

		s0 = ImageIO.read(new File("Images/walk/0.png"));//animation de la marche du robot à gauche
		s1 = ImageIO.read(new File("Images/walk/1.png"));
		s2 = ImageIO.read(new File("Images/walk/2.png"));
		s3 = ImageIO.read(new File("Images/walk/3.png"));
		s4 = ImageIO.read(new File("Images/walk/4.png"));
		s5 = ImageIO.read(new File("Images/walk/5.png"));
		s6 = ImageIO.read(new File("Images/walk/6.png"));
		s7 = ImageIO.read(new File("Images/walk/7.png"));
		s8 = ImageIO.read(new File("Images/walk/8.png"));
		s9 = ImageIO.read(new File("Images/walk/9.png"));
		s10 = ImageIO.read(new File("Images/walk/10.png"));
		s11 = ImageIO.read(new File("Images/walk/11.png"));

		crouch_r0 = ImageIO.read(new File("Images/crouch/r0.png"));//animation de decked du robot à droite
		crouch_r1 = ImageIO.read(new File("Images/crouch/r1.png"));
		crouch_r2 = ImageIO.read(new File("Images/crouch/r2.png"));
		crouch_r3 = ImageIO.read(new File("Images/crouch/r3.png"));

		crouch_l0 = ImageIO.read(new File("Images/crouch/1.png"));//animation de decked du robot à droite
		crouch_l1 = ImageIO.read(new File("Images/crouch/2.png"));
		crouch_l2 = ImageIO.read(new File("Images/crouch/3.png"));
		crouch_l3 = ImageIO.read(new File("Images/crouch/4.png"));

		// Menu Images

		menu = ImageIO.read(new File("Images/test.png"));
		logo = ImageIO.read(new File("Images/logo.png"));
		// teamlogo = ImageIO.read(new File("Images/teamlogo.png"));
		play = ImageIO.read(new File("Images/play.png"));
		exit = ImageIO.read(new File("Images/exit.png"));

		start = ImageIO.read(new File("Images/intro1.png"));

		deathimg = ImageIO.read(new File("Images/deathimg.png"));
		menubutton = ImageIO.read(new File("Images/menuButton.png"));
		playagain = ImageIO.read(new File("Images/playagainButton.png"));

		el = ImageIO.read(new File("Images/heliboy.png"));
		e2l = ImageIO.read(new File("Images/heliboy2.png"));

		er = ImageIO.read(new File("Images/heliboyl.png"));
		e2r = ImageIO.read(new File("Images/heliboy2l.png"));

		enemy_l = new Animation(false);//animation left
		enemy_l.addFrame(el, 300);
		enemy_l.addFrame(e2l, 300);

		enemy_r = new Animation(false);//animation right
		enemy_r.addFrame(er, 300);
		enemy_r.addFrame(e2r, 300);

		crouchdown_r = new Animation(true);//animation robot decked right
		crouchdown_r.addFrame(crouch_r0, 20);
		crouchdown_r.addFrame(crouch_r1, 20);
		crouchdown_r.addFrame(crouch_r2, 20);
		crouchdown_r.addFrame(crouch_r3, 20);

		crouchdown_l = new Animation(true);//animation robot decked left
		crouchdown_l.addFrame(crouch_l0, 20);
		crouchdown_l.addFrame(crouch_l1, 20);
		crouchdown_l.addFrame(crouch_l2, 20);
		crouchdown_l.addFrame(crouch_l3, 20);

		anim_r = new Animation(false);//animation robot marche right
		anim_r.addFrame(c0, 50);
		anim_r.addFrame(c1, 50);
		anim_r.addFrame(c2, 50);
		anim_r.addFrame(c3, 50);
		anim_r.addFrame(c4, 50);
		anim_r.addFrame(c5, 50);
		anim_r.addFrame(c6, 50);
		anim_r.addFrame(c7, 50);
		anim_r.addFrame(c8, 50);
		anim_r.addFrame(c9, 50);
		anim_r.addFrame(c10, 50);
		anim_r.addFrame(c11, 50);

		anim_l = new Animation(false);//animation robot marche left
		anim_l.addFrame(s0, 50);
		anim_l.addFrame(s1, 50);
		anim_l.addFrame(s2, 50);
		anim_l.addFrame(s3, 50);
		anim_l.addFrame(s4, 50);
		anim_l.addFrame(s5, 50);
		anim_l.addFrame(s6, 50);
		anim_l.addFrame(s7, 50);
		anim_l.addFrame(s8, 50);
		anim_l.addFrame(s9, 50);
		anim_l.addFrame(s10, 50);
		anim_l.addFrame(s11, 50);

		anim = anim_r;

		currentSprite = c0;
	}

	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(1920, 0);
		bg3 = new Background(-1920, 0);
		bg4 = new Background(-1920 * 2, 0);
		robot = new Robot();
		MENU = new Menu();
		DEATH = new Death();
		score = 0;
		move = 0;

		// Initialize Tiles
		try {
			loadMap("Images/map1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			
	switch (State) {

      			case "game":
				gameUpdate();
				break;

		     	}
			repaint();

			try {
				
				Thread.sleep(17);
				
			} 
			catch (InterruptedException e) {
				
				e.printStackTrace();
				
			}
		}
	}

	public void gameUpdate() {

		robot.update();
		bg1.update();
		bg2.update();
		bg3.update();
		bg4.update();
		Enemy.update();
		updateTiles();

		enemy_l.update(50);
		enemy_r.update(50);
		
		ArrayList projectiles = robot.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			if (p.isVisible() == true) {
				p.update();
			} else {
				projectiles.remove(i);
			}
		}

		for (int i = 0; i < Enemy.enemies.size(); i++) {
			if (Enemy.enemies.get(i).getIsDead())
				Enemy.enemies.remove(i);

		}

		if (robot.isJumped() && robot.getDirection() == "right") {
			currentSprite = characterJumped_r;
		} else if (robot.isJumped() && robot.getDirection() == "left") {
			currentSprite = characterJumped_l;
		} else if ((robot.getDirection() == "right") && (robot.getSpeedX() == 0) && robot.isDucked() == false) {
			currentSprite = c0;
		} else if ((robot.getDirection() == "left") && (robot.getSpeedX() == 0) && robot.isDucked() == false) {
			currentSprite = s0;
		}

		else {
			if (robot.getSpeedX() < 0) {
				anim = anim_l;
			} else if (robot.getSpeedX() > 0) {
				anim = anim_r;
			} else if (robot.isDucked() && robot.getDirection() == "right") {
				anim = crouchdown_r;
			} else if (robot.isDucked() && robot.getDirection() == "left") {
				anim = crouchdown_l;
			}
			currentSprite = anim.getImage();
			anim.update(10);//temps de gif animation la vitesse de la transformation des photos
		}

		if (robot.getCenterY() > 1400) {
			State = "dead";
		}
	}

	@Override
	public void paint(Graphics g) {

		switch (State) {

		case "start":
			g.drawImage(start, 0, 0, this);

			break;

		case "menu":

			try {
				g.drawImage(menu, 0, -160, this);
				g.drawImage(logo, 510, 32, this);
				g.drawImage(play, 60, 250, this);
				g.drawImage(exit, 60, 300, this);
			
			} catch (Exception e) {
			}
			break;

		case "dead":
				
			g.drawImage(deathimg, 0, 0, this);
			g.drawImage(menubutton, 60, 350, this);
			g.drawImage(playagain, 60, 400, this);
			Font font = new Font("Serif", Font.LAYOUT_NO_START_CONTEXT, 100);
			

			g.setFont(font);
			g.drawString("votre score final  ", 20 + getWidth() / 4, 160 + getHeight() / 2);
			g.drawString("" + score, 290 + getWidth() / 4, getHeight() / 2 + 240);
			break;

		case "game":

			g.drawImage(background1, bg1.getBgX(), bg1.getBgY(), this);
			g.drawImage(background2, bg2.getBgX(), bg2.getBgY(), this);
			g.drawImage(background2, bg3.getBgX(), bg3.getBgY(), this);
			g.drawImage(background1, bg4.getBgX(), bg4.getBgY(), this);
			paintTiles(g);
			paintProjectiles(g);
			paintEnemies(g);
			g.drawImage(currentSprite, robot.getCenterX() - 61, robot.getCenterY() - 63, this);
			Font font1 = new Font("Serif", Font.LAYOUT_NO_START_CONTEXT, 100);
			g.setFont(font1);
			g.setColor(Color.green);
			g.drawString("score   : " + score, 100, 100);
			break;

		}
	}

	private void updateTiles() {

		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
		}
	}

	private void paintEnemies(Graphics g) {
		for (Enemy i : Enemy.enemies) {
			if (i.getCenterX() > -50 && i.getCenterX() < 1366) {
				if (i.direction == "right")
					g.drawImage(enemy_r.getImage(), i.getCenterX() - 48, i.getCenterY() - 48, this);
				else if (i.direction == "left")
					g.drawImage(enemy_l.getImage(), i.getCenterX() - 48, i.getCenterY() - 48, this);
			}
		}
	}

	private void paintProjectiles(Graphics g) {
		projectiles = robot.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);

			if (robot.getDirection() == "right")
				g.drawImage(bulletr, p.getX(), p.getY(), this);
			else if (robot.getDirection() == "left")
				g.drawImage(bulletl, p.getX(), p.getY(), this);
		}
	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}

	private void loadMap(String filename) throws IOException {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while (true) {
			String line = reader.readLine();
			// no more lines to read
			if (line == null) {
				reader.close();
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());

			}
		}
		height = lines.size();

		for (int j = 0; j < 20; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {

				if (i < line.length()) {
					char ch = line.charAt(i);
					if (ch == '*') {
						Enemy.enemies.add(new Enemy(i * 40, j * 40 - 80));
					} else {
						Tile t = new Tile(i, j, Character.getNumericValue(ch));
						tilearray.add(t);
					}
				}

			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (State == "start") {
			if (e.getKeyCode() == KeyEvent.VK_SPACE)
				State = "menu";
		}

	
		if (State == "game") {

			switch (e.getKeyCode()) {

			case KeyEvent.VK_ESCAPE:
			
				System.exit(1);
				break;

			case KeyEvent.VK_DOWN:
				robot.setDucked(true);
				break;

			case KeyEvent.VK_LEFT:

				robot.moveLeft();
				var1 = robot.centerX;
				var = var2;
				break;

			case KeyEvent.VK_RIGHT:
				var2 = var1;
				robot.moveRight();
				var2 = robot.centerX;

				if (var2 >= var) {
					move++;

					cpt++;
					if (cpt >= 10) {
						cpt = 0;
						score++;
					}
				}
				break;

			case KeyEvent.VK_UP:
				robot.jump();
				break;
			case KeyEvent.VK_ENTER:
				State = "game";
				break;

			case KeyEvent.VK_SPACE:
				espace_press = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_UP:
			if (State == "game") {
				robot.setMovingLeft(false);
				robot.setMovingRight(false);
			}
			break;

		case KeyEvent.VK_DOWN:
			if (State == "game") {
				crouchdown_r.currentFrame = 0;
				crouchdown_l.currentFrame = 0;
				robot.setDucked(false);
			}
			break;

		case KeyEvent.VK_LEFT:
			if (State == "game")
				robot.stopLeft();
			break;

		case KeyEvent.VK_RIGHT:
			if (State == "game")
				robot.stopRight();
			break;
		case KeyEvent.VK_SPACE:
			if (State == "game") {
				if (espace_press) {
					robot.shoot();
				}
			}
			break;

		
		case KeyEvent.VK_ENTER:
			if(x==0)
			{
				MP.setFile(BGM);
				MP.loop();
				x=1;
			
				
				break;
				
			}
			else {
				MP.stop();
				x=0;
			
				break;
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent me) {

	}

	@Override
	public void mousePressed(MouseEvent me) {

		switch (State) {

		case "dead":
			DEATH.mousePress(me);
			break;
		case "menu":
			MENU.mousePress(me);
			break;

		}
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		if (State == "menu")
			MENU.mousePress = false;
	}

	@Override
	public void mouseEntered(MouseEvent me) {

	}

	@Override
	public void mouseExited(MouseEvent me) {

	}

	// Getters

	public static Background getBg1() {
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static Background getBg3() {
		return bg3;
	}

	public static Background getBg4() {
		return bg4;
	}

	public static Robot getRobot() {
		return robot;
	}

	public static ArrayList getTileArray() {
		return tilearray;
	}

	// Main Function

	public static void main(String[] args) {

		JFrame frame = new JFrame("LOST");
		frame.setSize(1366, 768);
		frame.setBackground(Color.black);
		frame.add(starter);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			starter.init();
			starter.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}