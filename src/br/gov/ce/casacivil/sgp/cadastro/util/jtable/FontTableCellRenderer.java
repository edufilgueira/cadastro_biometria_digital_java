package br.gov.ce.casacivil.sgp.cadastro.util.jtable;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FontTableCellRenderer extends DefaultTableCellRenderer
{
	
	private static final long serialVersionUID = 4185854680347912847L;
	
	private Font font;
	
	public FontTableCellRenderer(Font font, int alignment)
	{
		this.font = font;
		setHorizontalAlignment(alignment);
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		comp.setFont(font);
		return comp;
	}
	
}
