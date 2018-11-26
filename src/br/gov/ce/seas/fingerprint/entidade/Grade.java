package br.gov.ce.seas.fingerprint.entidade;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Eduardo
 */
public class Grade {
    

	private Integer id;
	private Integer colaboradorId;
    private Integer data;
    private String created_at;
    private String update_at;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getColaboradorId() {
		return colaboradorId;
	}
	public void setColaboradorId(Integer colaboradorId) {
		this.colaboradorId = colaboradorId;
	}
	public Integer getData() {
		return data;
	}
	public void setData(Integer data) {
		this.data = data;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdate_at() {
		return update_at;
	}
	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}
    
    
    
}
