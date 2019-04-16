package MedicalServices;

import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.Renderer;

import MedicalServices.JComboCheckBox.ComboBoxRenderer;

public class JComboCheckBox extends JComboBox {
	public JComboCheckBox(Vector v)
	{
		super(v);
	
	
	setRenderer(new ComboBoxRenderer());
	addActionListener(new ActionListener()
	{
			public void actionPerformed(ActionEvent ae)
			{
				ourItemSelected();
			}
	});	
	}
	
	private void ourItemSelected()
	{
		Object selected = getSelectedItem();
		if (selected instanceof JCheckBox)
		{
			JCheckBox chk = (JCheckBox) selected;
			chk.setSelected(!chk.isSelected());
			repaint();
		
		Object[] selections = chk.getSelectedObjects();
		if (selections != null)
		{
			for (Object lastItem: selections)
			{
				
			}
		}
		}
	}

	class ComboBoxRenderer implements ListCellRenderer {
		private JLabel label;
		
		public ComboBoxRenderer() {
			setOpaque(true);
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			if (value instanceof Component)
			{
				Component c = (Component) value;
				if (isSelected)
				{
					c.setBackground(list.getSelectionBackground());
					c.setForeground(list.getSelectionForeground());
				}
				else
				{
					c.setBackground(list.getBackground());
					c.setForeground(list.getForeground());
				}
				return c;
			}else
			{
				if (label == null)
				{
					label = new JLabel(value.toString());
				}else
				{
					label.setText(value.toString());
				}
				return label;
			}
		}
	}
}
