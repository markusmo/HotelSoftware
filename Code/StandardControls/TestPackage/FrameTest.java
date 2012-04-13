package TestPackage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Controls.CommitPanel;
import Controls.HTextField;
import Controls.H_MainButton;
import Controls.H_MainControllPanel;

public class FrameTest extends Frame implements WindowListener {

	public FrameTest()
	{
		LinkedList<H_MainButton> buttons = new LinkedList<H_MainButton>();
		buttons.add(new H_MainButton("Home", KeyEvent.VK_F1,"home.jpg"));
		buttons.add(new H_MainButton("Reservierung", KeyEvent.VK_F2,"schluessel.jpg"));
		buttons.add(new H_MainButton("Buchung", KeyEvent.VK_F3,"buch.jpg"));
		buttons.add(new H_MainButton("Journale", KeyEvent.VK_F4,"journal.jpg"));
		this.add(new H_MainControllPanel(buttons));
		//this.add(new HTextField("ASDf"));
		pack();
		
		setVisible(true);
		addWindowListener(this);
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		dispose();
		setVisible(false);
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public static void main(String[] args)
	{
		new FrameTest();
	}
	
}
