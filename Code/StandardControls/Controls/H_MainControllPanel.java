// Zur oberen Ansicht einfach mit Buttonliste befüllbar

package Controls;

import java.awt.FlowLayout;
import java.util.LinkedList;

import javax.swing.JPanel;

public class H_MainControllPanel extends JPanel{

	public H_MainControllPanel(LinkedList<H_MainButton> buttons)
	{
		this.setLayout(new FlowLayout());
		for(H_MainButton B: buttons)
		{
			this.add(B);
		}
	}
}
