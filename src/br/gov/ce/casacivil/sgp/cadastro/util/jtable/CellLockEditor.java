package br.gov.ce.casacivil.sgp.cadastro.util.jtable;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class CellLockEditor extends AbstractCellEditor implements TableCellEditor
{
	
	private static final long serialVersionUID = -1842743897371147416L;
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	{
		return null;
	}
	
	public Object getCellEditorValue()
	{
		return null;
	}
	
	public boolean isCellEditable(EventObject e)
	{
		return false;
	}
}