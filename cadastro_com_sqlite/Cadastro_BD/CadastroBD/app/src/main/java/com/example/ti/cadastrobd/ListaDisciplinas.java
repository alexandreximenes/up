package com.example.ti.cadastrobd;

import java.util.ArrayList;

public class ListaDisciplinas {

    private static ArrayList<Disciplina> listaDisciplinas = new ArrayList<>();

    public static void addDisciplina(Disciplina disciplina){
        listaDisciplinas.add(disciplina);
    }

    public static ArrayList<Disciplina> getListaDisciplinas(){
        return listaDisciplinas;
    }


}
