package br.gov.ce.seas.fingerprint.cadastro;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;

import br.gov.ce.seas.fingerprint.framework.dao.UpdateDaoException;

public class FuncionarioCapturarDigitalDialog extends JDialog {

    private static final long serialVersionUID = -3077364240023724739L;
    
    public FuncionarioCapturarDigitalDialog() {
	//super(owner, true);

	setLayout(null);
	setTitle("Captura de digital");
	setSize(370, 180);
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

	imagens = new JLabel[4];
	imagens[0] = new JLabel();
	imagens[0].setPreferredSize(new Dimension(80, 95));
	imagens[0].setBorder(BorderFactory.createLoweredBevelBorder());
	imagens[0].setBounds(10, 10, 80, 95);
	add(imagens[0]);

	imagens[1] = new JLabel();
	imagens[1].setPreferredSize(new Dimension(80, 95));
	imagens[1].setBorder(BorderFactory.createLoweredBevelBorder());
	imagens[1].setBounds(100, 10, 80, 95);
	add(imagens[1]);

	imagens[2] = new JLabel();
	imagens[2].setPreferredSize(new Dimension(80, 95));
	imagens[2].setBorder(BorderFactory.createLoweredBevelBorder());
	imagens[2].setBounds(190, 10, 80, 95);
	add(imagens[2]);

	imagens[3] = new JLabel();
	imagens[3].setPreferredSize(new Dimension(80, 95));
	imagens[3].setBorder(BorderFactory.createLoweredBevelBorder());
	imagens[3].setBounds(280, 10, 80, 95);
	add(imagens[3]);

	btnCancelar = new JButton("Cancelar");
	btnCancelar.setFont(CadastroFrame.FONTE_PADRAO);
	btnCancelar.setMnemonic(KeyEvent.VK_C);
	add(btnCancelar);
	btnCancelar.setBounds(147, 120, 75, 25);
	
	btnCancelar.addActionListener(new ActionListener() {

	    public void actionPerformed(ActionEvent e) {
		fecharCaptureDireito();
		fecharCaptureEsquerdo();
	    }
	});

	this.addComponentListener(new ComponentAdapter() {

	    @Override
	    public void componentShown(ComponentEvent e) {
	    }

	    @Override
	    public void componentHidden(ComponentEvent e) {
		fecharCaptureDireito();
		fecharCaptureEsquerdo();
	    }

	});
    }

    public void iniciar(FuncionarioConsultarDialog funcionarioConsultarDialog) {
	if (capturerDireito != null && capturerDireito.isStarted())
	    capturerDireito.stopCapture();

	if (capturerEsquerdo != null && capturerEsquerdo.isStarted())
	    capturerEsquerdo.stopCapture();

	this.funcionarioConsultarDialog = funcionarioConsultarDialog;

	capturerDireito = DPFPGlobal.getCaptureFactory()
				    .createCapture();
	capturerEsquerdo = DPFPGlobal.getCaptureFactory()
				     .createCapture();
	enrollerDireito = DPFPGlobal.getEnrollmentFactory()
				    .createEnrollment();
	enrollerEsquerdo = DPFPGlobal.getEnrollmentFactory()
				     .createEnrollment();

	indiceImagem = 0;
	imagens[0].setIcon(null);
	imagens[1].setIcon(null);
	imagens[2].setIcon(null);
	imagens[3].setIcon(null);

	capturerDireito.addDataListener(new DPFPDataAdapter() {

	    @Override
	    public void dataAcquired(final DPFPDataEvent e) {
		SwingUtilities.invokeLater(new Runnable() {

		    public void run() {
			processarDigitalDireita(e.getSample());
		    }
		});
	    }
	});

	capturerEsquerdo.addDataListener(new DPFPDataAdapter() {

	    @Override
	    public void dataAcquired(final DPFPDataEvent e) {
		SwingUtilities.invokeLater(new Runnable() {

		    public void run() {
				try {
					processarDigitalEsquerda(e.getSample());
				} catch (UpdateDaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
	    }
	});

	capturerDireito.startCapture();
	//capturerEsquerdo.startCapture();

	setVisible(true);
    }

    private void processarDigitalDireita(DPFPSample sample) {
	drawPicture(convertSampleToBitmap(sample));

	DPFPFeatureSet features = extrairCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

	if (features != null)
	    try {
	    	enrollerDireito.addFeatures(features);
	    } catch (DPFPImageQualityException ex) {
	    	ex.printStackTrace();
	    } finally 
	    {
			switch (enrollerDireito.getTemplateStatus()) 
			{
				case TEMPLATE_STATUS_READY:
					funcionarioConsultarDialog.setDigitalDireita(enrollerDireito.getTemplate().serialize());
				    JOptionPane.showMessageDialog(FuncionarioCapturarDigitalDialog.this, "Digital direita capturada com sucesso!", "Captura de Digital Direita", JOptionPane.INFORMATION_MESSAGE);
				    fecharCaptureDireito();
				    indiceImagem = 0;
				    imagens[0].setIcon(null);
				    imagens[1].setIcon(null);
				    imagens[2].setIcon(null);
				    imagens[3].setIcon(null);
				    capturerEsquerdo.startCapture();
				    break;
				case TEMPLATE_STATUS_FAILED:
				    JOptionPane.showMessageDialog(FuncionarioCapturarDigitalDialog.this, "Não foi possível realizar a captura da digital.\r\n\r\nRepita o procedimento.",  "Captura de digital", JOptionPane.ERROR_MESSAGE);
				    funcionarioConsultarDialog.setDigitalDireita(null);
				    enrollerDireito.clear();
				    iniciar(funcionarioConsultarDialog);
				    break;
				default:
					break;
			}
	    }
    }

    private void processarDigitalEsquerda(DPFPSample sample) throws UpdateDaoException, ClassNotFoundException {
	drawPicture(convertSampleToBitmap(sample));

	DPFPFeatureSet features = extrairCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

	if (features != null)
	    try {
		enrollerEsquerdo.addFeatures(features);
	    } catch (DPFPImageQualityException ex) {
		ex.printStackTrace();
	    } finally 
	    {
			switch (enrollerEsquerdo.getTemplateStatus()) 
			{
				case TEMPLATE_STATUS_READY:
				    DPFPFeatureSet featuresverificador = extrairCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
				    DPFPVerification verificator = DPFPGlobal.getVerificationFactory().createVerification();
				    if (verificator.verify(featuresverificador, DPFPGlobal.getTemplateFactory().createTemplate(funcionarioConsultarDialog.getDigitalDireita())).isVerified())
				    {
				    	JOptionPane.showMessageDialog(FuncionarioCapturarDigitalDialog.this, "Você cadastrou dois dedos iguais.\r\n\r\nRepita o procedimento.", "Captura de digital", JOptionPane.ERROR_MESSAGE);
						enrollerEsquerdo.clear();
						indiceImagem = 0;
						imagens[0].setIcon(null);
						imagens[1].setIcon(null);
						imagens[2].setIcon(null);
						imagens[3].setIcon(null);
						break;
				    }
				    funcionarioConsultarDialog.setDigitalEsquerda(enrollerEsquerdo.getTemplate().serialize());
				    JOptionPane.showMessageDialog(FuncionarioCapturarDigitalDialog.this, "Digital esquerda capturada com sucesso!", "Captura de Digital Esquerda", JOptionPane.INFORMATION_MESSAGE);
				    //Implementa a persistencia das digitais no banco após a ultima captura da digital.
				    funcionarioConsultarDialog.atualizar();
				    fecharCaptureEsquerdo();
				    break;
		
				case TEMPLATE_STATUS_FAILED:
				    JOptionPane.showMessageDialog(FuncionarioCapturarDigitalDialog.this, "Não foi possível realizar a captura da digital.\r\n\r\nRepita o procedimento.", "Captura de digital", JOptionPane.ERROR_MESSAGE);
				    funcionarioConsultarDialog.setDigitalEsquerda(null);
				    enrollerEsquerdo.clear();
				    indiceImagem = 0;
				    imagens[0].setIcon(null);
				    imagens[1].setIcon(null);
				    imagens[2].setIcon(null);
				    imagens[3].setIcon(null);
				    break;
				default:
					break;
			}
	    }
    }

    protected void fecharCaptureDireito() {
    	capturerDireito.stopCapture();
    	//setVisible(false);
    }

    protected void fecharCaptureEsquerdo() {
    	capturerEsquerdo.stopCapture();
    	setVisible(false);
    }

    private void drawPicture(Image image) {
    	imagens[indiceImagem].setIcon(new ImageIcon(image.getScaledInstance(imagens[indiceImagem].getWidth(),
		imagens[indiceImagem].getHeight(), Image.SCALE_DEFAULT)));
    	indiceImagem++;
    }

    private Image convertSampleToBitmap(DPFPSample sample) {
	return DPFPGlobal.getSampleConversionFactory()
			 .createImage(sample);
    }

    private DPFPFeatureSet extrairCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose) 
    {
    	DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
		try {
			    return extractor.createFeatureSet(sample, purpose);
			} catch (DPFPImageQualityException e) {
			    return null;
		}
    }

    private DPFPCapture capturerDireito;

    private DPFPCapture capturerEsquerdo;

    private DPFPEnrollment enrollerDireito;

    private DPFPEnrollment enrollerEsquerdo;

    private JButton btnCancelar;

    private JLabel[] imagens;

    private int indiceImagem;

    private FuncionarioConsultarDialog funcionarioConsultarDialog;

}
