package com.example.ti.loja.Util;

public enum Tipo
{
    PESSOAL("Pessoal"), COMERCIAL("Comercial");

    private String tipo;
    Tipo(String t) {
        this.tipo = t;
    }

    public String getTipo() {
        return tipo;
    }
}
