package com.example.ti.loja.Empresa;

public class Empresa {

    private Integer id;
    private String foto;
    private String nome;
    private String email;
    private String telefone;
    private String site;
    private String endereco;

    public Empresa() {
    }

    public Empresa(String foto, String nome, String email, String telefone, String site, String endereco) {
        this.foto = foto;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.site = site;
        this.endereco = endereco;
    }
    public Empresa(Integer id, String foto, String nome, String email, String telefone, String site, String endereco) {
        this(foto, nome, email, telefone, site, endereco);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
