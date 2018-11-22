package com.example.ti.cadastrobd.Util;

public enum Midia
{
    DIGITAL("DIGITAL"), DVD("DVD"), BLU_RAY("BLU-RAY");

    private String midia;
    Midia(String m) {
        this.midia = m;
    }

    public String getMidia() {
        return midia;
    }
}
