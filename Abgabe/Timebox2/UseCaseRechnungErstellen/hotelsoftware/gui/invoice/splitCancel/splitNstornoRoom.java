/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelsoftware.gui.invoice.splitCancel;

import hotelsoftware.controller.data.invoice.InvoiceItemData;
import hotelsoftware.controller.data.service.ExtraServiceData;
import hotelsoftware.controller.data.service.HabitationData;
import hotelsoftware.gui.invoice.InvoiceGUIControler;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Johannes
 */
public class splitNstornoRoom extends javax.swing.JPanel
{
    /**
     * Creates new form splitNstornoRoom
     */
    private HabitationData habitation;
    private Boolean[] selected;
    private LinkedList<ButtonPanel> buttons = new LinkedList<ButtonPanel>();
    private LinkedList<CheckTextPane> checkTexts = new LinkedList<CheckTextPane>();
    private LinkedList<InvoiceItemData> items = new LinkedList<InvoiceItemData>();
    private final JCheckBox c;

    public splitNstornoRoom(HabitationData habitation, final JCheckBox c)
    {
        if (habitation == null)
        {
            throw new NullPointerException("Habitation is null");
        }
        if (c == null)
        {
            throw new NullPointerException("CheckBox is null");
        }
        c.setSelected(true);
        c.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (c.isSelected())
                {
                    jTable1.setEnabled(true);
                    selectAll();
                }
                else
                {
                    jTable1.setEnabled(false);
                    deselectAll();
                }
            }
        });
        this.c = c;
        this.habitation = habitation;
        items = new LinkedList<InvoiceItemData>(habitation.getInvoiceItemsData());
        selected = new Boolean[habitation.getInvoiceItemsData().size()];
        initComponents();
        initTable();
    }

    private void initTable()
    {
        jTable1.addMouseListener(new JTableButtonMouseListener(jTable1));

        jTable1.setModel(new DefaultTableModel(
                (items == null ? new Object[50][] : getTableModel()),
                new String[]
                {
                    "Selection amount", "Total amount", "Description", "Single price", "Total price", "Cancellation"
                })
        {
            boolean[] canEdit = new boolean[]
            {
                true, false, false, false, false, true
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }

            @Override
            public Class getColumnClass(int c)
            {
                return getValueAt(0, c).getClass();
            }
        });
        jTable1.getColumn("Selection amount").setCellRenderer(new CheckTextPaneEditorAndRenderer(jTable1.getDefaultRenderer(jTable1.getColumnClass(0)), jTable1.getDefaultEditor(jTable1.getColumnClass(0))));
        jTable1.getColumn("Selection amount").setCellEditor(new CheckTextPaneEditorAndRenderer(jTable1.getDefaultRenderer(jTable1.getColumnClass(0)), jTable1.getDefaultEditor(jTable1.getColumnClass(0))));
        jTable1.getColumn("Cancellation").setCellRenderer(new JButtonEditorAndRenderer(jTable1.getDefaultRenderer(jTable1.getColumnClass(5)), jTable1.getDefaultEditor(jTable1.getColumnClass(5))));
        jTable1.getColumn("Cancellation").setCellEditor(new JButtonEditorAndRenderer(jTable1.getDefaultRenderer(jTable1.getColumnClass(5)), jTable1.getDefaultEditor(jTable1.getColumnClass(5))));
        jTable1.setRowHeight(30);

    }

    private Object[][] getTableModel()
    {
        int i = 0;

        Object[][] value = new Object[items.size()][];

        for (InvoiceItemData data : items)
        {
            String descritpion = "Habitation";
            if (data.getServiceData() instanceof ExtraServiceData)
            {
                descritpion = ((ExtraServiceData) data.getServiceData()).getName();
            }
            ButtonPanel bPanel = new ButtonPanel();
            CheckTextPane checkTextPane = new CheckTextPane(data.getAmount());
            bPanel.addActionListener(getAL(data, descritpion));
            buttons.add(bPanel);
            checkTexts.add(checkTextPane);
            selected[i] = true;

            value[i] = new Object[]
            {
                checkTextPane, data.getAmount() + "", descritpion, data.getServiceData().getPrice(), data.getServiceData().getPrice().doubleValue() * data.getAmount(), bPanel
            };
            i++;
        }
        return value;
    }

    private ActionListener getAL(final InvoiceItemData iid, final String str)
    {
        ActionListener al = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int amount = (new StornoFrame(str, iid.getAmount(), jTable1)).getValue();
                if (amount > 0)
                {
                    if (!(InvoiceGUIControler.getInstance().cancelItems(iid, amount)))
                    {
                        JOptionPane.showMessageDialog(jTable1, "You don not have the permission to cancel items", "Permission invalid", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        updateAmounts();
                    }
                }
            }
        };
        return al;
    }

    private void updateAmounts()
    {
        items = new LinkedList<InvoiceItemData>(habitation.getInvoiceItemsData());
        buttons.removeAll(buttons);
        checkTexts.removeAll(checkTexts);

        jTable1.setModel(new DefaultTableModel(
                (habitation.getInvoiceItemsData() == null ? new Object[50][] : getTableModel()),
                new String[]
                {
                    "Selection amount", "Total amount", "Description", "Single price", "Total price", "Cancellation"
                })
        {
            boolean[] canEdit = new boolean[]
            {
                true, false, false, false, false, true
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit[columnIndex];
            }

            @Override
            public Class getColumnClass(int c)
            {
                return getValueAt(0, c).getClass();
            }
        });
        jTable1.getColumn("Selection amount").setCellRenderer(new CheckTextPaneEditorAndRenderer(jTable1.getDefaultRenderer(jTable1.getColumnClass(0)), jTable1.getDefaultEditor(jTable1.getColumnClass(0))));
        jTable1.getColumn("Selection amount").setCellEditor(new CheckTextPaneEditorAndRenderer(jTable1.getDefaultRenderer(jTable1.getColumnClass(0)), jTable1.getDefaultEditor(jTable1.getColumnClass(0))));
        jTable1.getColumn("Cancellation").setCellRenderer(new JButtonEditorAndRenderer(jTable1.getDefaultRenderer(jTable1.getColumnClass(5)), jTable1.getDefaultEditor(jTable1.getColumnClass(5))));
        jTable1.getColumn("Cancellation").setCellEditor(new JButtonEditorAndRenderer(jTable1.getDefaultRenderer(jTable1.getColumnClass(5)), jTable1.getDefaultEditor(jTable1.getColumnClass(5))));
        jTable1.setRowHeight(30);
        jTable1.repaint();
    }

    Map<InvoiceItemData, Integer> getSelectedItems()
    {
        HashMap<InvoiceItemData, Integer> values = new HashMap<InvoiceItemData, Integer>();
        int i = 0;
        for (Iterator iter = checkTexts.listIterator(); iter.hasNext();)
        {
            CheckTextPane pane = (CheckTextPane) iter.next();
            if (pane.isSelected())
            {
                values.put(items.get(i), pane.getInteger());
            }
            i++;
        }
        return values;
    }

    private void selectAll()
    {
        for (CheckTextPane cb : checkTexts)
        {
            cb.getCheckBox().setSelected(true);
        }
        repaint();
    }

    private void deselectAll()
    {
        for (CheckTextPane cb : checkTexts)
        {
            cb.getCheckBox().setSelected(false);
        }
        repaint();
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(32767, 600));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private class StornoFrame
    {
        private SpinnerPane spinnerPane;
        private Component comp;

        public StornoFrame(String description, int max, Component comp)
        {
            spinnerPane = new SpinnerPane(description, max);
            this.comp = comp;
        }

        public int getValue()
        {
            Object[] options =
            {
                "Confirm"
            };
            int n = JOptionPane.showOptionDialog(comp, spinnerPane, "Cancellation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, //do not use a custom Icon
                    options, //the titles of buttons
                    options[0]); //default button title

            if (n == JOptionPane.OK_OPTION)
            {
                return spinnerPane.getValue();
            }
            else
            {
                return 0;
            }
        }

        private class SpinnerPane extends JPanel
        {
            private JSpinner spinner = new JSpinner();
            private JLabel label;

            public SpinnerPane(String description, int max)
            {
                setLayout(new GridLayout(0, 1));
                label = new JLabel("How many " + description + " you want to cancel?");
                spinner.setModel(new SpinnerNumberModel(0, 0, max, 1));
                add(label);
                add(spinner);
            }

            public int getValue()
            {
                return Integer.parseInt(spinner.getValue().toString());
            }
        }
    }

    private class JButtonEditorAndRenderer extends AbstractCellEditor implements TableCellEditor, TableCellRenderer
    {
        private TableCellRenderer __defaultRenderer;
        private TableCellEditor __defaultEditor;
        private ButtonPanel bPanel;

        public JButtonEditorAndRenderer(TableCellRenderer renderer, TableCellEditor editor)
        {
            __defaultRenderer = renderer;
            __defaultEditor = editor;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column)
        {
            if (value instanceof ButtonPanel)
            {
                bPanel = (ButtonPanel) value;

                return bPanel;
            }
            else
            {
                bPanel = null;
                return __defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }

        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column)
        {
            if (value instanceof ButtonPanel)
            {
                bPanel = (ButtonPanel) value;

                return bPanel;
            }
            else
            {
                bPanel = null;
                return __defaultEditor.getTableCellEditorComponent(table, value, isSelected, row, column);
            }

        }

        @Override
        public boolean isCellEditable(EventObject ev)
        {
            return true;
        }

        @Override
        public boolean shouldSelectCell(EventObject ev)
        {
            return false;
        }

        @Override
        public Object getCellEditorValue()
        {
            return bPanel;
        }
    }

    private class JTableButtonMouseListener extends MouseAdapter
    {
        private final JTable table;

        public JTableButtonMouseListener(JTable table)
        {
            this.table = table;
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            int column = table.getColumnModel().getColumnIndexAtX(e.getX());
            int row = e.getY() / table.getRowHeight();

            Object value = table.getValueAt(row, column);
            if (value instanceof ButtonPanel)
            {
                if (e.getPoint().x > ((ButtonPanel) value).getButtonBounds().x
                        && e.getPoint().x < ((ButtonPanel) value).getButtonBounds().x + ((ButtonPanel) value).getButtonBounds().width
                        && e.getPoint().y > ((ButtonPanel) value).getButtonBounds().y
                        && e.getPoint().y < ((ButtonPanel) value).getButtonBounds().y + ((ButtonPanel) value).getButtonBounds().height)
                {
                    ((ButtonPanel) value).doClick();
                }

            }
        }
    }

    private class ButtonPanel extends JPanel
    {
        private JButton button = new JButton(" X ");

        public ButtonPanel()
        {
            super(new FlowLayout(FlowLayout.CENTER, 2, 2));
            button.setForeground(Color.red);
            button.setMargin(new Insets(2, 0, 2, 0));

            setBackground(Color.WHITE);
            add(button);
        }

        public void doClick()
        {
            button.doClick();
        }

        public boolean isSelected()
        {
            return button.isSelected();

        }

        public void setButtonForeground(Color c)
        {
            button.setForeground(c);
        }

        public void setButtonBackground(Color c)
        {
            button.setBackground(c);
        }

        public void addActionListener(ActionListener al)
        {
            button.addActionListener(al);
        }

        public Rectangle getButtonBounds()
        {
            return button.getBounds();
        }
    }

    private class CheckTextPane extends JPanel
    {
        private JCheckBox checki = new JCheckBox();
        private JTextField texti = new JTextField();
        private final int max;

        public CheckTextPane(final int max)
        {
            super(new FlowLayout(FlowLayout.LEFT));
            this.max = max;
            checki.setSelected(true);
            texti.setColumns(5);
            texti.setText(max + "");
            setBackground(Color.white);
            //texti.setDocument(new JTextFieldLimit(max));
            texti.setInputVerifier(new InputVerifier()
            {
                @Override
                public boolean verify(JComponent input)
                {
                    if (Integer.parseInt(((JTextField) input).getText()) <= max && Integer.parseInt(((JTextField) input).getText()) >= 0)
                    {
                        return true;
                    }
                    JOptionPane.showMessageDialog(input, "Invalit input", "Invalid input", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            });
            texti.addKeyListener(new KeyAdapter()
            {
                @Override
                public void keyTyped(KeyEvent e)
                {
                    char c = e.getKeyChar();
                    if (!((c >= '1') && (c <= '9')
                            || (c == KeyEvent.VK_BACK_SPACE)
                            || (c == KeyEvent.VK_DELETE)))
                    {
                        Toolkit.getDefaultToolkit().beep();
                        e.consume();
                    }
                }
            });


            add(checki);
            add(texti);
        }

        public boolean isSelected()
        {
            return checki.isSelected();
        }

        public JCheckBox getCheckBox()
        {
            return checki;
        }

        public String getText()
        {
            return texti.getText();
        }

        public Integer getInteger()
        {
            return new Integer(texti.getText());
        }
    }

    private class CheckTextPaneEditorAndRenderer extends AbstractCellEditor implements TableCellEditor, TableCellRenderer
    {
        private TableCellRenderer __defaultRenderer;
        private TableCellEditor __defaultEditor;
        private CheckTextPane cPanel;

        public CheckTextPaneEditorAndRenderer(TableCellRenderer renderer, TableCellEditor editor)
        {
            __defaultRenderer = renderer;
            __defaultEditor = editor;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column)
        {
            if (value instanceof CheckTextPane)
            {
                cPanel = (CheckTextPane) value;
                return cPanel;
            }
            else
            {
                cPanel = null;
                return __defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column)
        {
            if (value instanceof CheckTextPane)
            {
                cPanel = (CheckTextPane) value;
                return cPanel;
            }
            else
            {
                cPanel = null;
                return __defaultEditor.getTableCellEditorComponent(table, value, isSelected, row, column);
            }
        }

        @Override
        public boolean isCellEditable(EventObject ev)
        {
            return true;
        }

        @Override
        public boolean shouldSelectCell(EventObject ev)
        {
            return false;
        }

        @Override
        public Object getCellEditorValue()
        {
            return cPanel;
        }
    }
}