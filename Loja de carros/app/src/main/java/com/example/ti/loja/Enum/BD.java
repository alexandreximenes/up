package com.example.ti.loja.Enum;

public enum BD {
    CONTATO("contatos"), USUARIO("usuarios"), EMPRESA("empresa");

    private String param;

    BD(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }
}
