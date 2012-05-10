import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JFrame;
public class Main
{
    public JFrame jf = new JFrame();
    public JPopupMenu pm = new JPopupMenu();
    public Main()
    {
        jf.setUndecorated(true);
        jf.setSize(300,300);
        JMenu m = new JMenu("test");
        JMenuItem eins = new JMenuItem("Eins");
        JMenuItem zwei = new JMenuItem("Zwei");
        JMenuItem drei = new JMenuItem("Drei");
        m.add(eins);
        m.add(zwei);
        m.add(drei);
        pm.add(m);
        jf.getContentPane().addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e) {
            }
 
            public void mousePressed(MouseEvent e) {
                System.out.println("sdf");
                pm.show(jf,e.getX(),e.getY());
            }
 
            public void mouseReleased(MouseEvent e) {
            }
 
            public void mouseEntered(MouseEvent e) {
            }
 
            public void mouseExited(MouseEvent e) {
            }
 
        });
        jf.setVisible(true);
    }
    public static void main(String[] args)
    {
        new Main();
    }
}