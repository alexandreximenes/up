package br.edu.up.exemplolista;

import java.util.ArrayList;

public class JogoLista {

    private static ArrayList<Jogo> listaJogos = new ArrayList<>();

    public static void addJogo(Jogo j){
        listaJogos.add(j);
    }

    public static Jogo getJogo(int index){
        return listaJogos.get(index);
    }

    public static ArrayList<Jogo> getLista(){
        return listaJogos;
    }

}
