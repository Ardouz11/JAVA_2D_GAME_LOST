package PROJETJAVA;

import java.awt.event.MouseEvent;

public class Death {
	void mousePress(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

	

		if (mx > 60 && mx < 210 && my > 350 && my < 390) {
			StartingClass.State = "menu";
		} else if (mx > 60 && mx < 210 && my > 400 && my < 440) {
			StartingClass.restart();
			StartingClass.State = "game";
			
		}
	}
}
