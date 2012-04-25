/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.login;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Markus Mohanty <markus.mo at gmx.net>
 */
public class LogoPanel extends JPanel
{
    BufferedImage bimg;

    public LogoPanel()
    {
        Image img = new ImageIcon(LogoPanel.class.getClassLoader().getResource("resources/images/roomanizerlogo.jpg")).getImage();

        bimg = new BufferedImage(img.getWidth(null), img.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bimg.getGraphics();
        g.drawImage(img, 0, 0, null);

        Dimension size = new Dimension(bimg.getWidth(), bimg.getHeight());
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(bimg, 0, 0, null);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension((int) (bimg.getWidth(null)),
                (int) (bimg.getHeight(null)));
    }
}
