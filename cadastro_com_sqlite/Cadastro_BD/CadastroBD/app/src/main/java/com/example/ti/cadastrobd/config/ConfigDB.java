package com.example.ti.cadastrobd.config;

public enum  ConfigDB {
    DB("curso"),
    TABLE("disciplinas"),
    ID("id"),
    NOME("nome"),
    PROFESSOR("professor"),
    TURNO("turno"),
    DIAS("dias"),
    VERSAO("1");

    private String config;
    ConfigDB(String config) {
        this.config = config;
    }

    public String getConfig() {
        return config;
    }
}
