package com.example.ti.cadastrobd;

import java.util.ArrayList;

/**
 * Crie uma classe ListaFilmes para armazenar os filmes cadastrados em um Array List.
 * Crie , nesta mesma classe, métodos para: armazenar filme na lista; retornar o tamanho da lista; retornar todos os elementos da lista.
 */
public class ListaFilmes {

    private static ArrayList<Filme> listaFilmes = new ArrayList<>();

    public static void add(Filme filme){
        listaFilmes.add(filme);
    }

    public static ArrayList<Filme> get(){
        return listaFilmes;
    }

    public static Integer size(){
        return listaFilmes.size();
    }


}
