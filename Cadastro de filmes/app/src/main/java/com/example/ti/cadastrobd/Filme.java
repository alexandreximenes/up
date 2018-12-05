package com.example.ti.cadastrobd;

import com.example.ti.cadastrobd.Util.Midia;

/**
 *  Crie uma classe Filme.java para armazenar os dados do filme (nome, gênero, ano, formato (nesse atributo vocês vão informar na hora do cadastro se é Digital ou DVD / Blu-Ray, etc).
 */
public class Filme {


    private int id;
    private String nome;
    private String ano;
    private String midia;

    public Filme(int id, String nome, String ano, String midia) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.midia = midia;
    }

    public Filme() {}

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

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getMidia() {
        return midia;
    }

    public void setMidia(String midia) {
        this.midia = midia;
    }

    @Override
    public String toString() {
        return nome;
    }
}
