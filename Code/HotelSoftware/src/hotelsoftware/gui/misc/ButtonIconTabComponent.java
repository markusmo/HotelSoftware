/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package hotelsoftware.gui.misc;

import javax.swing.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
  * Diese Klasse verwendet ein Panel
  * um Texte anzuzeigen
  */
public class ButtonIconTabComponent extends JPanel
{
    private final JTabbedPane pane;
    private ImagePanel imagePanel;
    private java.util.List list;
    private ImageIcon finishIcon = new ImageIcon(ButtonIconTabComponent.class.getClassLoader().getResource("resources/images/gh.png"));
    private ImageIcon unfinishIcon = new ImageIcon(ButtonIconTabComponent.class.getClassLoader().getResource("resources/images/rotes_x.gif"));
    private boolean finished = false;

    public ButtonIconTabComponent(final JTabbedPane pane, java.util.List list)
    {
        //unset default FlowLayout' gaps
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        if (pane == null)
        {
            throw new NullPointerException("TabbedPane is null");
        }
        this.list = list;
        this.imagePanel = new ImagePanel(unfinishIcon.getImage());
        this.pane = pane;
        setOpaque(false);

        addComponents();
        //add more space to the top of the component
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }

    public void setFinished()
    {
        if (!finished)
        {
            this.imagePanel = new ImagePanel(finishIcon.getImage());
            finished = true;
            this.removeAll();
            addComponents();
        }
    }

    public void setUnFinished()
    {
        if (finished)
        {
            this.imagePanel = new ImagePanel(unfinishIcon.getImage());
            finished = false;
            this.removeAll();
            addComponents();
        }
    }

    private void addComponents()
    {
        //make JLabel read titles from JTabbedPane
        JLabel label = new JLabel()
        {
            public String getText()
            {
                int i = pane.indexOfTabComponent(ButtonIconTabComponent.this);
                if (i != -1)
                {
                    return pane.getTitleAt(i);
                }
                return null;
            }
        };

        add(label);
        //add more space between the label and the button
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        add(imagePanel);
        //tab button
        JButton button = new TabButton();
        add(button);
    }

    private class ImagePanel extends JPanel
    {
        private Image img;

        public ImagePanel(String img)
        {
            this(new ImageIcon(img).getImage());
        }

        public ImagePanel(Image img)
        {
            this.img = img;
            Dimension size = new Dimension(20, 20);
            setPreferredSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setSize(size);
            setLayout(null);
        }

        public void paintComponent(Graphics g)
        {
            g.drawImage(img, 0, 0, null);
        }
    }

    private class TabButton extends JButton implements ActionListener
    {
        public TabButton()
        {
            int size = 17;
            setPreferredSize(new Dimension(size, size));
            setToolTipText("close this tab");
            //Make the button looks the same for all Laf's
            setUI(new BasicButtonUI());
            //Make it transparent
            setContentAreaFilled(false);
            //No need to be focusable
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            //Making nice rollover effect
            //we use the same listener for all buttons
            addMouseListener(buttonMouseListener);
            setRolloverEnabled(true);
            //Close the proper tab by clicking the button
            addActionListener(this);
        }

        public void actionPerformed(ActionEvent e)
        {
            int i = pane.indexOfTabComponent(ButtonIconTabComponent.this);
            if (i != -1)
            {
                pane.remove(i);
                list.remove(i);
            }
        }

        //we don't want to update UI for this button
        public void updateUI()
        {
        }

        /**
         * Diese Methode zeichnet eine Komponente auf
         * @param g 
         */
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            //shift the image for pressed buttons
            if (getModel().isPressed())
            {
                g2.translate(1, 1);
            }
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.BLACK);
            if (getModel().isRollover())
            {
                g2.setColor(Color.MAGENTA);
            }
            int delta = 6;
            g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta - 1);
            g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta - 1);
            g2.dispose();
        }
    }
    private final static MouseListener buttonMouseListener = new MouseAdapter()
    {
        public void mouseEntered(MouseEvent e)
        {
            Component component = e.getComponent();
            if (component instanceof AbstractButton)
            {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(true);
            }
        }

        public void mouseExited(MouseEvent e)
        {
            Component component = e.getComponent();
            if (component instanceof AbstractButton)
            {
                AbstractButton button = (AbstractButton) component;
                button.setBorderPainted(false);
            }
        }
    };
}
