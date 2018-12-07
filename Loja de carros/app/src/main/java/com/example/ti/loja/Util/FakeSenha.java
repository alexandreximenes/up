package com.example.ti.loja.Util;

public class FakeSenha {
    public StringBuffer get(String senha) {
        StringBuffer newSenha = new StringBuffer();
        for (int i = 0;i<senha.length();i++) {
            if(i>0){
                newSenha.append("*");
            }else{
                newSenha.append(senha.charAt(i));
            }
        }
        return newSenha;
    }
}
