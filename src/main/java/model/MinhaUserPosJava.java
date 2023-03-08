package model;

import dao.MinhaDao;

public class MinhaUserPosJava {
	private long id;
	private String nome;
	private String email;
	
	public MinhaUserPosJava(Long id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean salvaCadastro() {
	    boolean enviadoComSucesso = false;
	    try {
	    	MinhaDao dao = new MinhaDao();
	        dao.salvar(this);
	        enviadoComSucesso = true;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return enviadoComSucesso;
	}
}