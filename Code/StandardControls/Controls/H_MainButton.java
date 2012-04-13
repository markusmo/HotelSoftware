//BEISPIEL: H_MainButton x = new H_MainButton("TEST", KeyEvent.VK_F3,"buch.jpg");
//			H_MainButton y = new H_MainButton("ASDF", KeyEvent.VK_F4,"buch.jpg");

//WICHTIG: wird ein Button geklickt MUSS clicked() aufgerufen werden!

package Controls;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public class H_MainButton extends JButton{

	// der aktuell ausgewählte Button
	private static H_MainButton _activatedButton = new H_MainButton("default",
			1, "default");

	// falls man einen button klickt wählt es diesen falls möglich aus und gibt
	// dementsprechend true / false zurück
	public boolean clicked() {
		if (_activatedButton != this) {
			_activatedButton.setEnabled(true);
			_activatedButton = this;
			this.setEnabled(false);
			System.out.println("ASDF");
			return true;
		}
		return false;
	}

	// konstruktor mit standartgröße
	public H_MainButton(String aufschrift, int shortcut, String picturepath) {
		this.setIcon(new ImageIcon(picturepath));
		this.setText(aufschrift + " (F" + (shortcut - 111) + ")");
		this.setFocusable(false);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		//this.setPreferredSize(new Dimension(100, 50));
	}

	// konstruktor mit manueller größe
	public H_MainButton(String aufschrift, int shortcut, String picturepath,
			int weight, int height) {
		this(aufschrift, shortcut, picturepath);
		this.setPreferredSize(new Dimension(weight, height));
	}

}