package com.example.ti.cadastrobd;

public class Disciplina {

    private int id;
    private String nome;
    private String professor;
    private String turno;
    private String dias;

    public Disciplina(int id, String nome, String professor, String turno, String dias) {
        this.id = id;
        this.nome = nome;
        this.professor = professor;
        this.turno = turno;
        this.dias = dias;
    }

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

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }
}
