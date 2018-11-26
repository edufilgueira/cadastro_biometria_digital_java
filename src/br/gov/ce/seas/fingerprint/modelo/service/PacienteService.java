package br.gov.ce.seas.fingerprint.modelo.service;

import br.gov.ce.seas.fingerprint.modelo.dao.ColaboradorDao;


public class PacienteService {
	
	public PacienteService() {
		new ColaboradorDao();
	}

	/*public void salvar ( Paciente paciente ) {

		if (paciente.getId() != 0 ) {
			dao.atualizar(paciente);
		} else {
			dao.inserir(paciente);
		}
	
	}
	
	public List<Paciente> getPacientes() {
		return dao.listaTodosPacientes();
	}*/
}
