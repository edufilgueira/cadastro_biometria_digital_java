package br.gov.ce.seas.fingerprint.modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.gov.ce.seas.fingerprint.entidade.Colaborador;
import br.gov.ce.seas.fingerprint.framework.dao.CreateDaoException;
import br.gov.ce.seas.fingerprint.framework.dao.Conexao;
import br.gov.ce.seas.fingerprint.framework.dao.QueryMapper;
import br.gov.ce.seas.fingerprint.framework.dao.UpdateDaoException;

/**
 * 
 * @author Bisso
 *
 */
public class ColaboradorDao {
	
	private Conexao conexao;
	
	public ColaboradorDao () {
		conexao = new Conexao();
	}
	
	
	public void inserir (Colaborador colaborador) throws CreateDaoException, ClassNotFoundException {
		
		try {
			conexao.beginTransaction();
			
			Integer id =  (int) conexao.executePreparedUpdateAndReturnGeneratedKeys( conexao.getConnectionFromContext()
					                                      , "insert into adm_ponto.colaboradores (nome, created_at, updated_at) values ( ? , now(), now())"
					                                      , colaborador.getNome());
					                                      //, paciente.getCpf()
					                                      //, paciente.getSexo().toString() 
					                                      //, new Date( paciente.getCriacao().getTime() ) );
			
			colaborador.setId( id );
			conexao.endTransaction();

		} catch (SQLException e) {
			
			conexao.rollbackTransaction();	
			
			throw new CreateDaoException("Nï¿½o foi possivel realizar a transaï¿½ï¿½o", e);
			
		} 
		
	}
	
	
	public void atualizar (Colaborador colaborador) throws UpdateDaoException, ClassNotFoundException {
		
		final String query = "update adm_ponto.colaboradores set digitaldireita = ?, digitalesquerda = ? where id = ? ";
		
		try {
			conexao.beginTransaction();
			conexao.executePreparedUpdate(query,
												colaborador.getDigitalDireita(),
												colaborador.getDigitalEsquerda(),
												colaborador.getId() );
			
			conexao.endTransaction();
		} catch (SQLException e) {
			conexao.rollbackTransaction();	
			throw new UpdateDaoException("Não foi possível atualizar Paciente", e);
		}
		
	}
		
/*
	public void atualizarPacienteEndereco(Colaborador colaborador ) throws SQLException {
		EnderecoDao dao = new EnderecoDao();
		dao.atualizar(paciente.getEndereco());
	}

	public void atualizarPacienteContato (Colaborador colaborador ) throws SQLException {
		ContatoDao dao = new ContatoDao();
		dao.atualizar(paciente.getContato());
	}
	*/
	public List<Colaborador> listaTodosColaboradores() throws ClassNotFoundException {
		
		final List<Colaborador> colaboradores = new ArrayList<Colaborador>();
		
		try {
		
			conexao.executePreparedQuery("select * from adm_ponto.colaboradores", new QueryMapper<Colaborador>() {

				@Override
				public List<Colaborador> mapping(ResultSet rset) throws SQLException {
					while (rset.next()) {
						Colaborador colaborador = new Colaborador();
						colaborador.setId( rset.getInt("id") );
						colaborador.setNome( rset.getString("nome") );
						colaborador.setDigitalDireita( rset.getBytes("digitaldireita") );
						colaborador.setDigitalEsquerda( rset.getBytes("digitalesquerda") );
						//paciente.setRg( rset.getString("rg") );
						//paciente.setSexo( SexoType.valueOf( rset.getString("sexo") ) );
						colaboradores.add(colaborador);
					}
					return colaboradores;
				}
				
			});
		} catch (SQLException e) {
                    e.printStackTrace();
			//ignore exception
		}
		
		return colaboradores;
	}
	
	
	public List<Colaborador> buscarPorNome(String nome) throws ClassNotFoundException {	
		final List<Colaborador> colaboradores = new ArrayList<Colaborador>();
		
		try {
		
			conexao.executePreparedQuery("select * from adm_ponto.colaboradores where nome ilike '%"+nome+"%' ", new QueryMapper<Colaborador>() {

				@Override
				public List<Colaborador> mapping(ResultSet rset) throws SQLException {
					while (rset.next()) {
						Colaborador colaborador = new Colaborador();
						colaborador.setId( rset.getInt("id") );
						colaborador.setNome( rset.getString("nome") );
						colaborador.setDigitalDireita( rset.getBytes("digitaldireita") );
						colaborador.setDigitalEsquerda( rset.getBytes("digitalesquerda") );
						//paciente.setRg( rset.getString("rg") );
						//paciente.setSexo( SexoType.valueOf( rset.getString("sexo") ) );
						colaboradores.add(colaborador);
					}
					return colaboradores;
				}
				
			});
		} catch (SQLException e) {
                    e.printStackTrace();
			//ignore exception
		}
		
		return colaboradores;
	}
	
}
