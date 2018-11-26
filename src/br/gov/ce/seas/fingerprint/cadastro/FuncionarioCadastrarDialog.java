package br.gov.ce.seas.fingerprint.cadastro;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.MaskFormatter;

import br.gov.ce.seas.fingerprint.modelo.dao.ColaboradorDao;


public class FuncionarioCadastrarDialog extends JDialog {

    private static final long serialVersionUID = 2975996736785488786L;

    public FuncionarioCadastrarDialog(ColaboradorDao pacienteDao) {
	//super(owner);

	setLayout(null);
	setTitle("Cadastrar funcion�rio");
	setSize(480, 370);
	setLocation((Toolkit.getDefaultToolkit()
			    .getScreenSize()
			    .getSize().width
		- this.getSize().width) / 2,
		(Toolkit.getDefaultToolkit()
			.getScreenSize()
			.getSize().height - this.getSize().height) / 2);
	setResizable(false);
	setModal(true);
	setVisible(false);

	//setarListas();
	/*if (CadastroFrame.tipoLogin.equals("email")) {
	    titUsuario = new JLabel("Usuário");
	    titUsuario.setFont(CadastroFrame.FONTE_NEGRITO);
	    add(titUsuario);
	    titUsuario.setBounds(10, 10, 460, 22);

	    vlrUsuario = new JComboBox(listaUsuario);
	    vlrUsuario.setFont(CadastroFrame.FONTE_PADRAO);
	    add(vlrUsuario);
	    vlrUsuario.setBounds(10, 30, 460, 22);
	    vlrUsuario.addItemListener(new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
		    ObjetoComboBox objetoComboBox = (ObjetoComboBox) e.getItem();
		    System.out.println(objetoComboBox.toString()
						     .indexOf("("));
		    if (objetoComboBox.toString()
				      .indexOf("(") > -1)
			vlrNome.setText(objetoComboBox.toString()
						      .substring(0, objetoComboBox.toString()
										  .indexOf("("))
						      .trim());
		}
	    });
	}*/

	titNome = new JLabel("Nome Completo");
	titNome.setFont(CadastroFrame.FONTE_NEGRITO);
	add(titNome);
	titNome.setBounds(10, 50, 460, 22);

	vlrNome = new JTextField(115);
	vlrNome.setFont(CadastroFrame.FONTE_PADRAO);
	add(vlrNome);
	vlrNome.setBounds(10, 70, 460, 22);

	titMatricula = new JLabel("Matricula");
	titMatricula.setFont(CadastroFrame.FONTE_NEGRITO);
	add(titMatricula);
	titMatricula.setBounds(10, 90, 120, 22);

	vlrMatricula = new JTextField(115);
	vlrMatricula.setFont(CadastroFrame.FONTE_PADRAO);
	add(vlrMatricula);
	vlrMatricula.setBounds(10, 110, 120, 22);

	vlrMatricula.setDisabledTextColor(Color.GRAY);

	titDataIni = new JLabel("Data Admiss�o");
	titDataIni.setFont(CadastroFrame.FONTE_NEGRITO);
	add(titDataIni);
	titDataIni.setBounds(140, 90, 90, 22);

	try {
	    vlrDataIni = new JFormattedTextField(new MaskFormatter("##/##/####"));
	} catch (ParseException e1) {
	    e1.printStackTrace();
	}
	vlrDataIni.setFont(CadastroFrame.FONTE_PADRAO);
	add(vlrDataIni);
	vlrDataIni.setBounds(140, 110, 70, 22);

	titDataFim = new JLabel("Data Demiss�o");
	titDataFim.setFont(CadastroFrame.FONTE_NEGRITO);
	add(titDataFim);
	titDataFim.setBounds(240, 90, 90, 22);

	try {
	    vlrDataFim = new JFormattedTextField(new MaskFormatter("##/##/####"));
	} catch (ParseException e1) {
	    e1.printStackTrace();
	}
	vlrDataFim.setFont(CadastroFrame.FONTE_PADRAO);
	add(vlrDataFim);
	vlrDataFim.setBounds(240, 110, 70, 22);

	titCargaHoraria = new JLabel("Carga hor�ria");
	titCargaHoraria.setFont(CadastroFrame.FONTE_NEGRITO);
	add(titCargaHoraria);
	titCargaHoraria.setBounds(340, 90, 120, 22);

	vlrCargaHoraria = new JSpinner(new SpinnerNumberModel(8, 1, 14, 1));
	vlrCargaHoraria.setFont(CadastroFrame.FONTE_PADRAO);
	add(vlrCargaHoraria);
	vlrCargaHoraria.setBounds(340, 110, 120, 22);

	titLotacao = new JLabel("Lota��o");
	titLotacao.setFont(CadastroFrame.FONTE_NEGRITO);
	add(titLotacao);
	titLotacao.setBounds(10, 130, 150, 22);

	vlrLotacao = new JComboBox<Object>(listaLotacao);
	vlrLotacao.setFont(CadastroFrame.FONTE_PADRAO);
	add(vlrLotacao);
	vlrLotacao.setBounds(10, 150, 460, 22);

	titGrade = new JLabel("Grade");
	titGrade.setFont(CadastroFrame.FONTE_NEGRITO);
	add(titGrade);
	titGrade.setBounds(10, 170, 150, 22);

	vlrGrade = new JComboBox<Object>(listaGrade);
	vlrGrade.setFont(CadastroFrame.FONTE_PADRAO);
	add(vlrGrade);
	vlrGrade.setBounds(10, 190, 460, 22);

	titOrgao = new JLabel("Órgão");
	titOrgao.setFont(CadastroFrame.FONTE_NEGRITO);
	add(titOrgao);
	titOrgao.setBounds(10, 210, 150, 22);

	vlrOrgao = new JComboBox<Object>(listaOrgao);
	vlrOrgao.setFont(CadastroFrame.FONTE_PADRAO);
	add(vlrOrgao);
	vlrOrgao.setBounds(10, 230, 460, 22);

	titBaterPonto = new JLabel("Bater Ponto ?");
	titBaterPonto.setFont(CadastroFrame.FONTE_NEGRITO);
	add(titBaterPonto);
	titBaterPonto.setBounds(10, 250, 80, 22);

	vlrBaterPontoSim = new JRadioButton("Sim", true);
	vlrBaterPontoSim.setFont(CadastroFrame.FONTE_PADRAO);
	add(this.vlrBaterPontoSim);
	vlrBaterPontoSim.setBounds(10, 270, 50, 22);

	vlrBaterPontoNao = new JRadioButton("Não");
	vlrBaterPontoNao.setFont(CadastroFrame.FONTE_PADRAO);
	add(this.vlrBaterPontoNao);
	vlrBaterPontoNao.setBounds(60, 270, 60, 22);

	titTerceirizado = new JLabel("Terceirizado ?");
	titTerceirizado.setFont(CadastroFrame.FONTE_NEGRITO);
	add(titTerceirizado);
	titTerceirizado.setBounds(150, 250, 80, 22);

	vlrTerceirizadoSim = new JRadioButton("Sim", true);
	vlrTerceirizadoSim.setFont(CadastroFrame.FONTE_PADRAO);
	add(this.vlrTerceirizadoSim);
	vlrTerceirizadoSim.setBounds(150, 270, 50, 22);

	vlrTerceirizadoNao = new JRadioButton("Não");
	vlrTerceirizadoNao.setFont(CadastroFrame.FONTE_PADRAO);
	add(this.vlrTerceirizadoNao);
	vlrTerceirizadoNao.setBounds(200, 270, 60, 22);

	titTrabalhaFds = new JLabel("Trabalha FDS ?");
	titTrabalhaFds.setFont(CadastroFrame.FONTE_NEGRITO);
	add(titTrabalhaFds);
	titTrabalhaFds.setBounds(290, 250, 80, 22);

	vlrTrabalhaFdsSim = new JRadioButton("Sim", true);
	vlrTrabalhaFdsSim.setFont(CadastroFrame.FONTE_PADRAO);
	add(this.vlrTrabalhaFdsSim);
	vlrTrabalhaFdsSim.setBounds(290, 270, 50, 22);

	vlrTrabalhaFdsNao = new JRadioButton("Não");
	vlrTrabalhaFdsNao.setFont(CadastroFrame.FONTE_PADRAO);
	add(this.vlrTrabalhaFdsNao);
	vlrTrabalhaFdsNao.setBounds(350, 270, 60, 22);

	ButtonGroup buttonGroup = new ButtonGroup();
	buttonGroup.add(vlrBaterPontoNao);
	buttonGroup.add(vlrBaterPontoSim);

	ButtonGroup buttonGroup2 = new ButtonGroup();
	buttonGroup2.add(vlrTerceirizadoSim);
	buttonGroup2.add(vlrTerceirizadoNao);

	ButtonGroup buttonGroup3 = new ButtonGroup();
	buttonGroup3.add(vlrTrabalhaFdsSim);
	buttonGroup3.add(vlrTrabalhaFdsNao);

	btnCancelar = new JButton("Cancelar");
	btnCancelar.setFont(CadastroFrame.FONTE_PADRAO);
	btnCancelar.setMnemonic(KeyEvent.VK_C);
	add(btnCancelar);
	btnCancelar.setBounds(10, 300, 75, 25);
	btnCancelar.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		cancelar();
	    }
	});

	btnSalvar = new JButton("Salvar");
	btnSalvar.setFont(CadastroFrame.FONTE_PADRAO);
	btnSalvar.setMnemonic(KeyEvent.VK_S);
	add(btnSalvar);
	btnSalvar.setBounds(90, 300, 75, 25);
	btnSalvar.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		salvar();
	    }
	});

	btnCapturarDigital = new JButton("Capturar digital");
	btnCapturarDigital.setFont(CadastroFrame.FONTE_PADRAO);
	btnCapturarDigital.setMnemonic(KeyEvent.VK_S);
	add(btnCapturarDigital);
	btnCapturarDigital.setBounds(350, 300, 120, 25);
	btnCapturarDigital.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		capturar();
	    }
	});

    }

    private void cancelar() {
	limpar();
	setVisible(false);
    }

    private void salvar() {
	/*if (CadastroFrame.tipoLogin.equals("email"))
	    if (vlrUsuario.getSelectedIndex() == 0) {
		JOptionPane.showMessageDialog(this, "O campo Usuário é obrigatório.", getTitle(),
			JOptionPane.ERROR_MESSAGE);
		return;
	    }

	if (vlrNome.getText()
		   .trim()
		   .equals("")) {
	    JOptionPane.showMessageDialog(this, "O campo Nome Completo é obrigatório.", getTitle(),
		    JOptionPane.ERROR_MESSAGE);
	    return;
	}

	if (vlrMatricula.getText()
			.trim()
			.equals("")) {
	    JOptionPane.showMessageDialog(this, "O campo Matricula é obrigatório.", getTitle(),
		    JOptionPane.ERROR_MESSAGE);
	    return;
	}

	if (vlrDataIni.getText()
		      .trim()
		      .equals("")) {
	    JOptionPane.showMessageDialog(this, "O campo Data Início é obrigatório.", getTitle(),
		    JOptionPane.ERROR_MESSAGE);
	    return;
	}

	if (vlrLotacao.getSelectedIndex() == 0) {
	    JOptionPane.showMessageDialog(this, "O campo Lotação é obrigatório.", getTitle(),
		    JOptionPane.ERROR_MESSAGE);
	    return;
	}

	if (vlrGrade.getSelectedIndex() == 0) {
	    JOptionPane.showMessageDialog(this, "O campo Grade é obrigatório.", getTitle(), JOptionPane.ERROR_MESSAGE);
	    return;
	}

	if (vlrOrgao.getSelectedIndex() == 0) {
	    JOptionPane.showMessageDialog(this, "O campo Órgão é obrigatório.", getTitle(), JOptionPane.ERROR_MESSAGE);
	    return;
	}*/

	/*this.pacienteDao = new PacienteDao();
	pacienteDao.(vlrMatricula.getText());
	pacienteDao.setMatricula(vlrMatricula.getText());
	//pacienteDao.setNomeCompleto(vlrNome.getText());
	//pacienteDao.setCargaHoraria(Short.parseShort(vlrCargaHoraria.getValue()
								    .toString()));
	SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
	try {
	    funcionario.setDataInicio(formatarData.parse(vlrDataIni.getText()));
	    if (!vlrDataFim.getText()
			   .equals("  /  /    "))
		funcionario.setDataFim(formatarData.parse(vlrDataFim.getText()));
	    else
		funcionario.setDataFim(null);
	} catch (ParseException e1) {
	    e1.printStackTrace();
	}
	if (vlrBaterPontoSim.isSelected())
	    funcionario.setBatePonto(new Short("0"));
	if (vlrBaterPontoNao.isSelected())
	    funcionario.setBatePonto(new Short("1"));

	if (vlrTerceirizadoSim.isSelected())
	    funcionario.setTerceirizado(true);
	if (vlrTerceirizadoNao.isSelected())
	    funcionario.setTerceirizado(false);

	if (vlrTrabalhaFdsSim.isSelected())
	    funcionario.setTrabalhaFds(true);
	if (vlrTrabalhaFdsNao.isSelected())
	    funcionario.setTrabalhaFds(false);

	ObjetoComboBox obj = (ObjetoComboBox) vlrLotacao.getSelectedItem();
	funcionario.setLotacao(obj.getCodigo());

	obj = (ObjetoComboBox) vlrGrade.getSelectedItem();
	funcionario.setGrade(Short.parseShort(obj.getCodigo()));

	obj = (ObjetoComboBox) vlrOrgao.getSelectedItem();
	funcionario.setOrgaoOrigem(Short.parseShort(obj.getCodigo()));

	if (CadastroFrame.tipoLogin.equals("email"))
	    obj = (ObjetoComboBox) vlrUsuario.getSelectedItem();

	if (digitalEsquerda != null || digitalDireita != null) {
	    funcionario.setDigitalDireita(digitalDireita);
	    funcionario.setDigitalEsquerda(digitalEsquerda);
	}
	try {
	    User user = new User();
	    if (CadastroFrame.tipoLogin.equals("email"))
		user.setUsername(obj.getCodigo());
	    else
		user.setUsername(funcionario.getMatricula());
	    user.setNomeCompleto(funcionario.getNomeCompleto());
	    userDao.insert(user);
	    funcionario.setLogin(user.getUsername());
	    funcionarioDao.insertOrUpdate(funcionario, insert);

	    Escala escala = new Escala();
	    escala.getId()
		  .setFuncionario(funcionario);
	    boolean listaVazia = escalaDao.find(escala)
					  .isEmpty();

	    // verifica se funcionário trabalha no fds (sabado)
	    if (funcionario.getTrabalhaFds()) {
		if (funcionario.getDataFim() == null) {
		    // se ainda não tiver nenhuma escala cadastrada
		    // anteriormente
		    if (listaVazia) {
			// insere escala da data de contratacao ate 2015
			// (substituido para 2018)
			// escalaDao.insertOrUpdate(getEscalasFds(funcionario));
			escalaDao.insertOrUpdate(getEscalasFdsHojePraFrente(funcionario));
		    } else {
			// caso funcionario trabalhe no fds, caso ele nao esteja
			// demitido, caso lista nao esteja vazia

			// verifica se ja tem lista de hoje pra frente, se nao
			// tem cadastra
			if (!escalaDao.existeEscalaMaiorIgualHoje(escala)) {
			    escalaDao.insertOrUpdate(getEscalasFdsHojePraFrente(funcionario));
			}

			// escalaDao.deletaEscalaMaiorIgualAHoje(escalaDao.find(escala));
			// cadastra
			// escalaDao.insertOrUpdate(getEscalasFdsHojePraFrente(funcionario));
		    }
		} else {
		    // se funcionário não trabalha mais aqui
		    // se tem lista de escala
		    if (!listaVazia) {
			// apaga toda lista de escala de hoje em diante
			escalaDao.deletaEscalaMaiorIgualAHoje(escalaDao.find(escala));
		    }
		}

	    } else {
		// se não trabalha no fds
		if (!listaVazia) {

		    // tenho que verificar se tem pelo menos um registro de
		    // ponto antes de hoje para o fds(sabado), se nunca tiver
		    // batido, apaga tudo.
		    // se tiver pelo menos um registro de batida no sabado,
		    // apaga de hoje pra frente

		    // coleta todas as batidas do dia de sabado
		    List<Batida> batidas = new ArrayList<Batida>();
		    batidas = batidaDao.retornarTodasAsBatidas(funcionario);

		    // se não houver nenhuma batida no sabado, apaga tudo
		    if (batidas.size() == 0) {
			escalaDao.delete(escalaDao.find(escala));
		    } else {
			// se houver pelo menos uma batida no sabado, apaga de
			// hoje em diante
			escalaDao.deletaEscalaMaiorIgualAHoje(escalaDao.find(escala));
		    }
		}

	    }

	    setVisible(false);
	    limpar();
	    JOptionPane.showMessageDialog(this, "Funcionário salvo com sucesso!", getTitle(),
		    JOptionPane.INFORMATION_MESSAGE);
	    frame.funcionarioConsultarDialog.pesquisar(0);
	} catch (Exception e) {
	    JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.INFORMATION_MESSAGE);
	}*/
    }

    /*public List<Escala> getEscalasFds(Funcionario funcionario) {
	java.util.Date dataAtual = new java.util.Date();
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(funcionario.getDataInicio());
	// calendar.setTime(dataAtual);

	Calendar dataFinal = Calendar.getInstance();
	dataFinal.set(2014, 12, 31);

	Escala escala;
	EscalaId eId;
	List<Escala> escalas = new ArrayList<Escala>();

	while (calendar.before(dataFinal)) {
	    if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
		calendar.set(Calendar.HOUR_OF_DAY, 7);
		calendar.set(Calendar.MINUTE, 0);

		eId = new EscalaId();
		eId.setDataEscala(calendar.getTime());
		eId.setFuncionario(funcionario);
		escala = new Escala();
		escala.setId(eId);
		escala.setEntrada1(calendar.getTime());

		calendar.set(Calendar.HOUR_OF_DAY, 11);
		calendar.set(Calendar.MINUTE, 0);
		escala.setSaida1(calendar.getTime());
		escala.setLog12e(new Short("1"));
		escala.setCargaHoraria(new Short("4"));
		escalas.add(escala);
	    }
	    calendar.add(Calendar.DATE, 1);
	}

	return escalas;
    }*/

    /*public List<Escala> getEscalasFdsHojePraFrente(Funcionario funcionario) {

	java.util.Date dataAtual = new java.util.Date();
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(dataAtual);

	Calendar dataFinal = Calendar.getInstance();
	dataFinal.set(2018, 12, 31);

	Escala escala;
	EscalaId eId;
	List<Escala> escalas = new ArrayList<Escala>();

	while (calendar.before(dataFinal)) {
	    if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
		calendar.set(Calendar.HOUR_OF_DAY, 7);
		calendar.set(Calendar.MINUTE, 0);

		eId = new EscalaId();
		eId.setDataEscala(calendar.getTime());
		eId.setFuncionario(funcionario);
		escala = new Escala();
		escala.setId(eId);
		escala.setEntrada1(calendar.getTime());

		calendar.set(Calendar.HOUR_OF_DAY, 11);
		calendar.set(Calendar.MINUTE, 0);
		escala.setSaida1(calendar.getTime());
		escala.setLog12e(new Short("1"));
		escala.setCargaHoraria(new Short("4"));
		escalas.add(escala);
	    }
	    calendar.add(Calendar.DATE, 1);
	}

	return escalas;
    }*/

    public void novo() {
	//limpar();
	////setarListas();
	//vlrMatricula.setEditable(true);
	//vlrMatricula.setEnabled(true);
	//insert = true;
	//setVisible(true);
    }

    /*public void abrir(Funcionario funcionario, Integer linha) {
	insert = false;
	limpar();
	setarListas();
	this.funcionario = funcionario;
	SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
	try {
	    this.funcionario = funcionarioDao.findById(funcionario.getMatriculaCompleta());
	    vlrMatricula.setText(funcionario.getMatriculaCompleta());
	    vlrCargaHoraria.setValue(new Integer(funcionario.getCargaHoraria()));
	    vlrNome.setText(funcionario.getNomeCompleto());
	    vlrDataIni.setText(formatarData.format(funcionario.getDataInicio()));
	    if (funcionario.getDataFim() != null)
		vlrDataFim.setText(formatarData.format(funcionario.getDataFim()));
	    digitalEsquerda = funcionario.getDigitalEsquerda();
	    digitalDireita = funcionario.getDigitalDireita();
	    vlrMatricula.setEditable(false);
	    vlrMatricula.setEnabled(false);
	    vlrLotacao.setSelectedItem(new ObjetoComboBox(funcionario.getLotacao()
								     .toString(),
		    ""));
	    vlrGrade.setSelectedItem(new ObjetoComboBox(funcionario.getGrade()
								   .toString(),
		    ""));
	    vlrOrgao.setSelectedItem(new ObjetoComboBox(funcionario.getOrgaoOrigem()
								   .toString(),
		    ""));

	    if (CadastroFrame.tipoLogin.equals("email"))
		if (funcionario.getLogin() != null && funcionario.getNomeCompleto() != null)
		    vlrUsuario.setSelectedItem(
			    new ObjetoComboBox(funcionario.getLogin(), funcionario.getNomeCompleto()));
		else
		    vlrUsuario.setSelectedItem(new ObjetoComboBox("", ""));

	    if (funcionario.getBatePonto() != null) {
		if (funcionario.getBatePonto()
			       .equals(new Short("0")))
		    vlrBaterPontoSim.setSelected(true);
		if (funcionario.getBatePonto()
			       .equals(new Short("1")))
		    vlrBaterPontoNao.setSelected(true);
	    }

	    if (funcionario.getTerceirizado() != null) {
		if (funcionario.getTerceirizado())
		    vlrTerceirizadoSim.setSelected(true);
		else
		    vlrTerceirizadoNao.setSelected(true);
	    }

	    if (funcionario.getTrabalhaFds() != null) {
		if (funcionario.getTrabalhaFds())
		    vlrTrabalhaFdsSim.setSelected(true);
		else
		    vlrTrabalhaFdsNao.setSelected(true);
	    }

	    setVisible(true);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }*/

    private void limpar() {
	this.digitalDireita = null;
	this.digitalEsquerda = null;
	this.vlrGrade.setSelectedIndex(0);
	this.vlrLotacao.setSelectedIndex(0);
	this.vlrOrgao.setSelectedIndex(0);
	/*if (CadastroFrame.tipoLogin.equals("email"))
	    this.vlrUsuario.setSelectedIndex(0);*/
	this.vlrMatricula.setText("");
	this.vlrCargaHoraria.setValue(8);
	this.vlrNome.setText("");
	this.vlrDataIni.setText("");
	this.vlrDataFim.setText("");
	this.vlrBaterPontoSim.setText("Sim");
	this.vlrBaterPontoNao.setText("Não");
	this.vlrTerceirizadoSim.setText("Sim");
	this.vlrTerceirizadoNao.setText("Não");
	this.vlrTrabalhaFdsSim.setText("Sim");
	this.vlrTrabalhaFdsNao.setText("Não");
    }

    private void capturar() {
	//frame.funcionarioCapturarDigitalDialog.iniciar(this);
    }

    public void setDigitalDireita(byte[] digital) {
	this.digitalDireita = digital;
    }

    public void setDigitalEsquerda(byte[] digital) {
	this.digitalEsquerda = digital;
    }

    public byte[] getDigitalDireita() {
	return this.digitalDireita;
    }

    public byte[] getDigitalEsquerda() {
	return this.digitalEsquerda;
    }

    /*private Object[] getUsuarios() {
	List<UsuarioLdap> lista;
	List<ObjetoComboBox> listaObjetos = new ArrayList<ObjetoComboBox>();
	try {
	    lista = usuarioDao.findByEmailDomain(this.frame.getEmailDomain());
	    ObjetoComboBox objeto = null;
	    objeto = new ObjetoComboBox("", "");
	    listaObjetos.add(objeto);
	    for (UsuarioLdap elemento : lista) {
		objeto = new ObjetoComboBox(elemento.getEmail(), elemento.getNome() + " (" + elemento.getEmail() + ")");
		listaObjetos.add(objeto);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return listaObjetos.toArray();
    }*/

    /*private Object[] getLotacoes() {
	List<Lotacao> lista = lotacaoDao.find(new Lotacao());
	ObjetoComboBox objeto = null;
	List<ObjetoComboBox> listaObjetos = new ArrayList<ObjetoComboBox>();
	objeto = new ObjetoComboBox("", "");
	listaObjetos.add(objeto);
	for (Lotacao elemento : lista) {
	    objeto = new ObjetoComboBox(elemento.getCodigo(), elemento.getDescricao());
	    listaObjetos.add(objeto);
	}

	return listaObjetos.toArray();
    }*/

    /*private Object[] getGrades() {
	List<Grade> lista = gradeDao.find(new Grade());
	ObjetoComboBox objeto = null;
	List<ObjetoComboBox> listaObjetos = new ArrayList<ObjetoComboBox>();
	objeto = new ObjetoComboBox("", "");
	listaObjetos.add(objeto);
	for (Grade elemento : lista) {
	    objeto = new ObjetoComboBox(elemento.getId()
						.toString(),
		    elemento.getDescricao());
	    listaObjetos.add(objeto);
	}

	return listaObjetos.toArray();
    }*/

    /*private Object[] getOrgaos() {
	List<Orgao> lista = orgaoDao.find(new Orgao());
	ObjetoComboBox objeto = null;
	List<ObjetoComboBox> listaObjetos = new ArrayList<ObjetoComboBox>();
	objeto = new ObjetoComboBox("", "");
	listaObjetos.add(objeto);
	for (Orgao elemento : lista) {
	    objeto = new ObjetoComboBox(elemento.getCodigo()
						.toString(),
		    elemento.getNome());
	    listaObjetos.add(objeto);
	}

	return listaObjetos.toArray();
    }*/

    /*private void setarListas() {
	listaLotacao = getLotacoes();
	listaGrade = getGrades();
	listaOrgao = getOrgaos();
	if (CadastroFrame.tipoLogin.equals("email"))
	    listaUsuario = getUsuarios();
    }*/

    Object[] listaLotacao;

    Object[] listaGrade;

    Object[] listaOrgao;

    Object[] listaUsuario;

    private JLabel titMatricula;

    private JTextField vlrMatricula;

    private JLabel titNome;

    private JTextField vlrNome;

    private JLabel titLotacao;

    private JComboBox<Object> vlrLotacao;

    private JLabel titGrade;

    private JComboBox<Object> vlrGrade;

    private JLabel titCargaHoraria;

    private JSpinner vlrCargaHoraria;

    private JLabel titOrgao;

    private JComboBox<Object> vlrOrgao;

    private JLabel titBaterPonto;

    private JLabel titDataIni;

    private JLabel titDataFim;

    private JTextField vlrDataIni;

    private JTextField vlrDataFim;

    private JRadioButton vlrBaterPontoSim;

    private JRadioButton vlrBaterPontoNao;

    private JLabel titTerceirizado;

    private JRadioButton vlrTerceirizadoSim;

    private JRadioButton vlrTerceirizadoNao;

    private JLabel titTrabalhaFds;

    private JRadioButton vlrTrabalhaFdsSim;

    private JRadioButton vlrTrabalhaFdsNao;

    private JButton btnSalvar;

    private JButton btnCapturarDigital;

    private JButton btnCancelar;

    private byte[] digitalDireita;

    private byte[] digitalEsquerda;

}
