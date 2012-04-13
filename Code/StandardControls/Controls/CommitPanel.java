//Beispiel für Panel OK / Cancel

package Controls;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.JPanel;

public class CommitPanel extends JPanel {

		public H_OkButton ok;
		public H_CancelButton cancel;
	
	public CommitPanel()
	{
		this.setMinimumSize(new Dimension(200,35));
		this.setMaximumSize(new Dimension(200,35));
		this.setPreferredSize(new Dimension(200,35));
		this.setLayout(new FlowLayout());
		ok = new H_OkButton();
		cancel = new H_CancelButton();
		
		this.add(ok);
		this.add(new Label("         "));
		this.add(cancel);
	}
}
