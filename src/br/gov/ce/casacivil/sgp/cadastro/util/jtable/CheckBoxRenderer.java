package br.gov.ce.casacivil.sgp.cadastro.util.jtable;

import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer
{
	
	private static final long serialVersionUID = 3266462762084214553L;
	
	public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue)
	{
	}
	
	public void firePropertyChange(String propertyName, Object oldValue, Object newValue)
	{
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		Boolean newSelectedValue = (Boolean) value;
		setSelected(newSelectedValue.booleanValue());
		return this;
	}
	
	public void repaint(long tm, int x, int y, int width, int height)
	{
	}
	
	public void repaint(Rectangle r)
	{
	}
	
	public void revalidate()
	{
	}
	
	public void validate()
	{
	}
}