package com.example.ti.loja;

public class Contato {


    private int id;
    private String nome;
    private String tipo;
    private String email;
    private String telefone;

    public Contato(int id, String nome, String tipo, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.email = email;
        this.telefone = telefone;
    }

    public Contato() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    @Override
    public String toString() {
        return nome;
    }
}
