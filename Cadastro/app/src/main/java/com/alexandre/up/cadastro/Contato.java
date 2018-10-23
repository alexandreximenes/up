package com.alexandre.up.cadastro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Contato {
    private String nome;
    private int idade;
    private String email;
    private List<Contato> contatos = new ArrayList<>();

    public Contato() {
    }

    public Contato(String nome, int idade, String email) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public Contato setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public int getIdade() {
        return idade;
    }

    public Contato setIdade(String idade) {
        try {
            this.idade = Integer.parseInt(idade);
        } catch (NumberFormatException n) {
           throw new RuntimeException("Não conseguimos entender sua idade!");
        }
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Contato setEmail(String email) {
        this.email = email;
        return this;
    }

    public void adiciona(Contato contato){
        contatos.add(contato);
    }

    public List<Contato> getContatos(){
        return Collections.unmodifiableList(this.contatos);
    }

    public Contato getContato(Contato contato){
        if(nome.equals("")) return null;
        int index = contatos.indexOf(contato);
        if(index != -1)
            return contatos.get(index);
        return null;
    }
}
