/**
 * 
 */
package br.gov.ce.seas.fingerprint.entidade;

/**
 * @author Bisso
 *
 */
public class Colaborador {
	
	private Integer id;
	private String nome;
	private byte[] digitalEsquerda;
    private byte[] digitalDireita;
    private Boolean hasDigital;
	
	public Colaborador() {

	}
	
	public Colaborador(Integer id, String nome) {

		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	
	public Boolean getHasDigital()
	{
		if (getDigitalDireita() != null && getDigitalEsquerda() != null)
			hasDigital = true;
		else
			hasDigital = false;
		return hasDigital;
	}

	
	
}
