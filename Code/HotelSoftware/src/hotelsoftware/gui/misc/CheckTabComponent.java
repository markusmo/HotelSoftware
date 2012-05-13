package hotelsoftware.gui.misc;

import java.awt.FlowLayout;
import javax.swing.*;

/**
 * Wird vom tabComponent verwendet;
 * enthält ein Label um Text auf einem Tab anzuzeigen
 */
public class CheckTabComponent extends JPanel
{
    private final JTabbedPane pane;
    private final JCheckBox checkBox;

    public CheckTabComponent(final JTabbedPane pane, final JCheckBox checkBox)
    {
        //unset default FlowLayout' gaps
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        if (pane == null)
        {
            throw new NullPointerException("TabbedPane is null");
        }
        if(checkBox == null)
        {
            throw new NullPointerException("CheckBox is null");
        }
        this.pane = pane;
        this.checkBox = checkBox;
        setOpaque(false);

        //make JLabel read titles from JTabbedPane
        JLabel label = new JLabel()
        {
            @Override
            public String getText()
            {
                int i = pane.indexOfTabComponent(CheckTabComponent.this);
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
        //CheckBox;
        add(checkBox);
        //add more space to the top of the component
        setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
    }
}
