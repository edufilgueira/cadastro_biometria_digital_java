package br.gov.ce.seas.fingerprint.cadastro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import br.gov.ce.casacivil.sgp.cadastro.util.jtable.CellLockEditor;
import br.gov.ce.casacivil.sgp.cadastro.util.jtable.FontTableCellRenderer;
import br.gov.ce.seas.fingerprint.entidade.Colaborador;
import br.gov.ce.seas.fingerprint.framework.dao.UpdateDaoException;
import br.gov.ce.seas.fingerprint.modelo.dao.ColaboradorDao;
import br.gov.ce.seas.sgp.cadastro.util.NumberDocument;
import br.gov.ce.seas.sgp.cadastro.util.UpperCaseDocument;

public class FuncionarioConsultarDialog extends JPanel {

    private static final long serialVersionUID = -4173963942706688015L;
    
    private ColaboradorDao colaboradorDao = new ColaboradorDao();
    
    private Integer id;
    private byte[] digitalEsquerda;
    private byte[] digitalDireita;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public byte[] getDigitalEsquerda() {
		return digitalEsquerda;
	}

	public void setDigitalEsquerda(byte[] digitalEsquerda) {
		this.digitalEsquerda = digitalEsquerda;
	}
	
	public byte[] getDigitalDireita() {
		return digitalDireita;
	}

	public void setDigitalDireita(byte[] digitalDireita) {
		this.digitalDireita = digitalDireita;
	}

	
    public FuncionarioConsultarDialog(ColaboradorDao colaboradorDao) {
	
	this.colaboradorDao = colaboradorDao;

	setLayout(null);
	// setTitle("Consultar funcionários");
	//setSize(600, 460);
	// setResizable(false);
	// setModal(false);
	// setVisible(false);

	titMatricula = new JLabel("Matrícula");
	titMatricula.setFont(CadastroFrame.FONTE_NEGRITO);
	add(titMatricula);
	titMatricula.setBounds(10, 10, 90, 22);

	vlrMatricula = new JTextField(115);
	vlrMatricula.setDocument(new NumberDocument());
	vlrMatricula.setFont(CadastroFrame.FONTE_PADRAO);
	add(vlrMatricula);
	vlrMatricula.setBounds(10, 30, 90, 22);

	titNome = new JLabel("Nome");
	titNome.setFont(CadastroFrame.FONTE_NEGRITO);
	add(titNome);
	titNome.setBounds(105, 10, 490, 22);

	vlrNome = new JTextField(115);
	vlrNome.setDocument(new UpperCaseDocument());
	vlrNome.setFont(CadastroFrame.FONTE_PADRAO);
	add(vlrNome);
	vlrNome.setBounds(105, 30, 490, 22);

	titTerceirizado = new JLabel("Terceirizado ?");
	titTerceirizado.setFont(CadastroFrame.FONTE_NEGRITO);
	add(titTerceirizado);
	titTerceirizado.setBounds(10, 50, 80, 22);

	vlrTerceirizadoSim = new JRadioButton("Sim", true);
	vlrTerceirizadoSim.setFont(CadastroFrame.FONTE_PADRAO);
	add(this.vlrTerceirizadoSim);
	vlrTerceirizadoSim.setBounds(10, 70, 50, 22);

	vlrTerceirizadoNao = new JRadioButton("Não");
	vlrTerceirizadoNao.setFont(CadastroFrame.FONTE_PADRAO);
	add(this.vlrTerceirizadoNao);
	vlrTerceirizadoNao.setBounds(60, 70, 60, 22);

	qtdRegistros = new JLabel("0" + registrosEncontrados);
	qtdRegistros.setFont(CadastroFrame.FONTE_PADRAO);
	add(qtdRegistros);
	qtdRegistros.setBounds(460, 110, 300, 22);

	ButtonGroup buttonGroup = new ButtonGroup();
	buttonGroup.add(vlrTerceirizadoSim);
	buttonGroup.add(vlrTerceirizadoNao);

	btnPesquisar = new JButton("Pesquisar");
	btnPesquisar.setFont(CadastroFrame.FONTE_PADRAO);
	btnPesquisar.setMnemonic(KeyEvent.VK_P);
	add(btnPesquisar);
	btnPesquisar.setBounds(10, 110, 90, 25);
	btnPesquisar.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
			try {
				pesquisar(0);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
	    }
	});

	btnNovo = new JButton("Todos");
	btnNovo.setFont(CadastroFrame.FONTE_PADRAO);
	btnNovo.setMnemonic(KeyEvent.VK_N);
	add(btnNovo);
	btnNovo.setBounds(105, 110, 75, 25);
	btnNovo.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
			try {
				novo();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
	});

	btnEditar = new JButton("Editar");
	btnEditar.setFont(CadastroFrame.FONTE_PADRAO);
	btnEditar.setMnemonic(KeyEvent.VK_E);
	add(btnEditar);
	btnEditar.setBounds(10, 415, 75, 25);
	btnEditar.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		editar();
	    }
	});

	btnFechar = new JButton("Fechar");
	btnFechar.setFont(CadastroFrame.FONTE_PADRAO);
	btnFechar.setMnemonic(KeyEvent.VK_F);
	add(btnFechar);
	btnFechar.setBounds(515, 415, 75, 25);
	btnFechar.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});
	// this.addWindowListener(new WindowAdapter()
	// {
	//
	// @Override
	// public void windowClosing(WindowEvent e)
	// {
	// System.exit(0);
	// }
	// });
	Vector<String> colunas = new Vector<String>();
	colunas.addElement("Matrícula");
	colunas.addElement("Funcioário");
	colunas.addElement("Tem Digital?");
	//colunas.addElement("Terceirizado?");
	//colunas.addElement("");

	Vector<String> linhas = new Vector<String>();

	tabela = new JTable();
	tabela.addMouseListener(new MouseAdapter() {

	    public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
			    abrir();
			}
	    }

	});
	tabela.setModel(new DefaultTableModel(linhas, colunas));

	tabela.getColumnModel()
	      .getColumn(0)
	      .setPreferredWidth(100);
	tabela.getColumnModel()
	      .getColumn(0)
	      .setResizable(false);
	tabela.getColumnModel()
	      .getColumn(0)
	      .setCellEditor(new CellLockEditor());
	tabela.getColumnModel()
	      .getColumn(0)
	      .setCellRenderer(new FontTableCellRenderer(CadastroFrame.FONTE_PADRAO, SwingConstants.LEFT));

	tabela.getColumnModel()
	      .getColumn(1)
	      .setPreferredWidth(360);
	tabela.getColumnModel()
	      .getColumn(1)
	      .setResizable(false);
	tabela.getColumnModel()
	      .getColumn(1)
	      .setCellEditor(new CellLockEditor());
	tabela.getColumnModel()
	      .getColumn(1)
	      .setCellRenderer(new FontTableCellRenderer(CadastroFrame.FONTE_PADRAO, SwingConstants.LEFT));

	tabela.getColumnModel()
	      .getColumn(2)
	      .setPreferredWidth(130);
	tabela.getColumnModel()
	      .getColumn(2)
	      .setResizable(false);
	tabela.getColumnModel()
	      .getColumn(2)
	      .setCellEditor(new CellLockEditor());
	tabela.getColumnModel()
	      .getColumn(2)
	      .setCellRenderer(new FontTableCellRenderer(CadastroFrame.FONTE_PADRAO, SwingConstants.LEFT));

	/*tabela.getColumnModel()
	      .getColumn(3)
	      .setPreferredWidth(130);
	tabela.getColumnModel()
	      .getColumn(3)
	      .setResizable(false);
	tabela.getColumnModel()
	      .getColumn(3)
	      .setCellEditor(new CellLockEditor());
	tabela.getColumnModel()
	      .getColumn(3)
	      .setCellRenderer(new FontTableCellRenderer(CadastroFrame.FONTE_PADRAO, SwingConstants.LEFT));

	tabela.getColumnModel()
	      .getColumn(4)
	      .setMaxWidth(0);
	tabela.getColumnModel()
	      .getColumn(4)
	      .setMinWidth(0);
	tabela.getColumnModel()
	      .getColumn(4)
	      .setPreferredWidth(0);*/

	JScrollPane scroll = new JScrollPane(tabela);
	add(scroll);
	scroll.setBounds(10, 145, 580, 260);
    }
	/*
	 * Esse código realiza a pesquisa dos colaboradores
	 */
    public void pesquisar(Integer linha) throws ClassNotFoundException {
		
	
		Colaborador colaborador = new Colaborador();
		//if (!vlrMatricula.getText()
		//		 .equals("")) {
		//    filtro.setMatriculaCompleta(vlrMatricula.getText());
		//    filtro.setMatricula(vlrMatricula.getText());
		//}
		if (!vlrNome.getText().trim().equals(""))
		{
			colaborador.setNome(vlrNome.getText());
			buscarPorNome(colaborador.getNome());
		}
		else
		{
			colaborador.setNome("");
			listaTodosColaboradores(0);
		}
	
		//colaborador.setCargaHoraria(new Short("0"));
		//colaborador.setLotacao("");
		//colaborador.setDataInicio(new Date());
		//if (vlrTerceirizadoSim.isSelected())
		//    colaborador.setTerceirizado(true);
		//if (vlrTerceirizadoNao.isSelected())
		//    colaborador.setTerceirizado(false);
		
		
    }
    
    
    private void buscarPorNome(String nome) throws ClassNotFoundException {
    	colaboradores = colaboradorDao.buscarPorNome(nome);
    	popularGridVew(colaboradores, 0);
    }
    
    private void listaTodosColaboradores(Integer linha) throws ClassNotFoundException {
    	colaboradores = colaboradorDao.listaTodosColaboradores();
    	popularGridVew(colaboradores, linha);
    }
    
    private void popularGridVew(List<Colaborador> lista, Integer linha) {
    	((DefaultTableModel) tabela.getModel()).setRowCount(0);
	    /*try {
		    lista = colaboradorDao.find(filtro);
		} catch (SGPException e) {
		    JOptionPane.showMessageDialog(this, e.getMessage());
		}*/
    	
		if (lista != null && lista.size() > 0) {
		    for (Colaborador func : lista) 
		    {
				Vector<Object> column = new Vector<Object>();
				column.addElement(func.getId());
				column.addElement(func.getNome());
				column.addElement(func.getHasDigital() ? "Sim" : "Não");
				//column.addElement(func.getTerceirizado() ? "Sim" : "Não");
				//column.addElement(func);
		
				((DefaultTableModel) tabela.getModel()).addRow(column);
		    }
		} else
	    JOptionPane.showMessageDialog(FuncionarioConsultarDialog.this, "Nenhum funcionário encontrado.", "Atenção", JOptionPane.WARNING_MESSAGE);
		qtdRegistros.setText(lista.size() + " " + registrosEncontrados);
		tabela.changeSelection(linha == null ? 0 : linha, 0, false, false);
    }

    public void novo() throws ClassNotFoundException {
    	listaTodosColaboradores(0);
    }

    public void editar() {
	int linha = tabela.getSelectedRow();
	if (linha >= 0) {
	    DefaultTableModel dados = (DefaultTableModel) tabela.getModel();
	    //frame.funcionarioCadastrarDialog.abrir(((Funcionario) dados.getValueAt(linha, 4)), linha); 
	    id = (Integer) dados.getValueAt(linha,0);
	    
	    FuncionarioCapturarDigitalDialog funcionarioCapturarDigitalDialog = new FuncionarioCapturarDigitalDialog();
	    funcionarioCapturarDigitalDialog.iniciar(this);
	    funcionarioCapturarDigitalDialog.setVisible(true);
	    
	    
	} else
	    JOptionPane.showMessageDialog(FuncionarioConsultarDialog.this, "Nenhum funcionário selecionado.", "Atenção", JOptionPane.WARNING_MESSAGE);
    }

    public void iniciar() {
	vlrMatricula.setText("");
	vlrNome.setText("");
	qtdRegistros.setText("0" + registrosEncontrados);

	((DefaultTableModel) tabela.getModel()).setRowCount(0);

	setVisible(true);
    }

    public void abrir() {
	int linha = tabela.getSelectedRow();
	if (linha >= 0) {
	    DefaultTableModel dados = (DefaultTableModel) tabela.getModel();
	    //frame.funcionarioCadastrarDialog.abrir(((Funcionario) dados.getValueAt(linha, 4)), linha);
	    id = (Integer) dados.getValueAt(linha,0);
	    FuncionarioCapturarDigitalDialog funcionarioCapturarDigitalDialog = new FuncionarioCapturarDigitalDialog();
	    funcionarioCapturarDigitalDialog.iniciar(this);
	    funcionarioCapturarDigitalDialog.setVisible(true);
	    
	} else
	    JOptionPane.showMessageDialog(FuncionarioConsultarDialog.this, "Nenhum funcionário selecionado.", "Atenção", JOptionPane.WARNING_MESSAGE);
    }
    
    public void atualizar() throws UpdateDaoException, ClassNotFoundException {
    	
    	Colaborador colaborador = new Colaborador();
    	colaborador.setId(this.id);
    	colaborador.setDigitalDireita(this.digitalDireita);
    	colaborador.setDigitalEsquerda(this.digitalEsquerda);
    	colaboradorDao.atualizar(colaborador);
    }

    private String registrosEncontrados = " registros encontrados.";

    private JTable tabela;

    private JLabel titMatricula;

    private JTextField vlrMatricula;

    private JLabel titNome;

    private JTextField vlrNome;

    private JButton btnPesquisar;

    private JButton btnNovo;

    private JButton btnEditar;

    private JButton btnFechar;

    private JLabel titTerceirizado;

    private JRadioButton vlrTerceirizadoSim;

    private JRadioButton vlrTerceirizadoNao;

    private JLabel qtdRegistros;

	private List<Colaborador> colaboradores;

}
