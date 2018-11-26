package br.gov.ce.seas.fingerprint.cadastro;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.gov.ce.seas.fingerprint.modelo.dao.ColaboradorDao;




public class CadastroFrame extends JFrame {
	
	public static final Font FONTE_PADRAO = new Font("Arial", Font.PLAIN, 11);
    public static final Font FONTE_NEGRITO = new Font("Arial", Font.BOLD, 11);
    
    private static ColaboradorDao pacienteDao = new ColaboradorDao();
    
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					CadastroFrame frame = new CadastroFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public CadastroFrame() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		init();
	}

    private static final long serialVersionUID = 2261084469042091994L;

	

    //public FuncionarioCadastrarDialog funcionarioCadastrarDialog;

    public FuncionarioCapturarDigitalDialog funcionarioCapturarDigitalDialog;

    public FuncionarioConsultarDialog funcionarioConsultarDialog;


    @PostConstruct
    public void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
	    UnsupportedLookAndFeelException {
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	SwingUtilities.updateComponentTreeUI(this);

	addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		System.exit(0);
	    }
	});

	setTitle("Sistema de Gestão de Ponto (v 1.3.0)");
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	setResizable(false);
	setSize(610, 500);
	setLocation((Toolkit.getDefaultToolkit()
			    .getScreenSize()
			    .getSize().width
		- this.getSize().width) / 2,
		(Toolkit.getDefaultToolkit()
			.getScreenSize()
			.getSize().height - this.getSize().height) / 2);

	funcionarioConsultarDialog = new FuncionarioConsultarDialog(pacienteDao);
	funcionarioConsultarDialog.setVisible(true);
	getContentPane().add(funcionarioConsultarDialog);

	funcionarioCapturarDigitalDialog = new FuncionarioCapturarDigitalDialog();
	
	//funcionarioCadastrarDialog = new FuncionarioCadastrarDialog(this, userDao, funcionarioDao, escalaDao, batidaDao,
	//	gradeDao, lotacaoDao, orgaoDao, usuarioDao);
	//funcionarioCapturarDigitalDialog = new FuncionarioCapturarDigitalDialog(funcionarioCadastrarDialog);
    }

}