package com.example.ti.cadastrobd;

import java.util.ArrayList;

/**
 * Crie uma classe ListaContatos para armazenar os filmes cadastrados em um Array List.
 * Crie , nesta mesma classe, métodos para: armazenar filme na lista; retornar o tamanho da lista; retornar todos os elementos da lista.
 */
public class ListaContatos {

    private static ArrayList<Contato> listaContatoes = new ArrayList<>();

    public static void add(Contato contato){
        listaContatoes.add(contato);
    }

    public static ArrayList<Contato> get(){
        return listaContatoes;
    }

    public static Integer size(){
        return listaContatoes.size();
    }


}
