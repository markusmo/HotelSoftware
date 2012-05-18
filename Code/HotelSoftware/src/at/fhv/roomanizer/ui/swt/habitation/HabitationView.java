package at.fhv.roomanizer.ui.swt.habitation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import at.fhv.roomanizer.ui.swt.GuiController;

/**
 * This composite contains the content of the habitation-tab in the MainShell
 *
 * @author Daniel Rotter <daniel.rotter@students.fhv.at>
 */
public class HabitationView extends Composite
{
    private HabitationTable _habitationTable;
    private Button _btnPayment;
    private Text _txtPayment;

    public HabitationView(Composite parent, int style)
    {
        super(parent, style);
        initUI();
    }

    private void initUI()
    {
        RowLayout mainLayout = new RowLayout();
        mainLayout.type = SWT.VERTICAL;
        this.setLayout(mainLayout);

        _habitationTable = new HabitationTable(this, SWT.NONE);

        //Buttons
        Composite buttons = new Composite(this, SWT.NONE);
        buttons.setLayout(mainLayout);

        RowLayout lineLayout = new RowLayout();
        lineLayout.type = SWT.HORIZONTAL;

        Composite paymentLine = new Composite(buttons, SWT.NONE);
        paymentLine.setLayout(lineLayout);
        _txtPayment = new Text(paymentLine, SWT.BORDER);
        _btnPayment = new Button(paymentLine, SWT.NONE);
        _btnPayment.setText("Prepayment");
        _btnPayment.addListener(SWT.Selection, new Listener()
        {
            @Override
            public void handleEvent(Event arg0)
            {
                try
                {
                    GuiController.getInstance().prepayment(HabitationView.this);
                    MessageBox msgBox = new MessageBox(getShell());
                    msgBox.setMessage("Prepayment successfully booked.");
                    msgBox.open();
                }
                catch (NumberFormatException e)
                {
                    MessageBox msgBox = new MessageBox(getShell());
                    msgBox.setMessage("Enter a valid number!");
                    msgBox.open();
                }
            }
        });
    }

    public String getPrepayment()
    {
        return _txtPayment.getText();
    }

    public HabitationTable getHabitationTable()
    {
        return _habitationTable;
    }
}
