package Controls;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class HTextField extends JTextField {

	//Platz für 20 chars
	public HTextField(String name) {
		super(name, 15);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(120,22));
	}

}
