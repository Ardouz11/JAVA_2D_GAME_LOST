package PROJETJAVA;

import java.awt.event.MouseEvent;

public class Menu {

	boolean mousePress;

	void mousePress(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if (x >= 60 && x < 210 && y > 250 && y < 290) // Play button
			// play ===> size(150,40) , 60<=x =<210 et 250<=y=<290
			StartingClass.State = "game";
		else if (x >= 60 && x <= 210 && y >= 300 && y <= 340) // exit button
			// size exit ==(150,40) , 60<=x =<210 et 300<=y=<340
			System.exit(1);

	}
}
