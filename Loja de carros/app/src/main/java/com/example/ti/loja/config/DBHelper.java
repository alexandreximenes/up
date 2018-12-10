package com.example.ti.loja.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    //Contato
    public static final String DB_CONTATO = "contatos";
    public static final String TABLE_CONTATO = "contato";
    public static final String ID_CONTATO = "id";
    public static final String NOME_CONTATO = "nome";
    public static final String TIPO_CONTATO = "tipo";
    public static final String EMAIL_CONTATO = "email";
    public static final String TELEFONE_CONTATO = "telefone";
    public static final int VERSAO = 1;

    // Usuario
    public static final String DB_USUARIO = "usuarios";
    public static final String TABLE_USUARIO = "usuario";
    public static final String ID_USUARIO = "id";
    public static final String FOTO_USUARIO = "foto";
    public static final String NOME_USUARIO = "nome";
    public static final String EMAIL_USUARIO = "email";
    public static final String SENHA_USUARIO = "senha";

    // Empresa
    public static final String DB_EMPRESA = "empresas";
    public static final String TABLE_EMPRESA = "empresa";
    public static final String ID_EMPRESA = "id";
    public static final String FOTO_EMPRESA = "foto";
    public static final String NOME_EMPRESA = "nome";
    public static final String EMAIL_EMPRESA = "email";
    public static final String TELEFONE_EMPRESA = "telefone";
    public static final String SITE_EMPRESA = "site";
    public static final String ENDERECO_EMPRESA = "endereco";

    // Produto
    public static final String DB_PRODUTO = "produtos";
    public static final String TABLE_PRODUTO = "produto";
    public static final String ID_PRODUTO = "id";
    public static final String FOTO_PRODUTO = "foto";
    public static final String TITULO_PRODUTO = "titulo";
    public static final String DESCRICAO_PRODUTO = "descricao";
    public static final String QUANTIDADE_PRODUTO = "quantidade";
    public static final String PRECO_PRODUTO = "preco";
    public static final String MARCA_PRODUTO = "marca";
    public static final String TAMANHO_PRODUTO = "tamanho";



    public DBHelper(@Nullable Context context) {
        super(context, DB_CONTATO, null, VERSAO);
    }

    public DBHelper(@Nullable Context context, String DB) {
        super(context, DB, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_CONTATO
                        + " (" + ID_CONTATO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        NOME_CONTATO + " VARCHAR, " + TIPO_CONTATO + " VARCHAR, " +
                        EMAIL_CONTATO + " VARCHAR, " + TELEFONE_CONTATO + " VARCHAR );"
                  );

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_USUARIO
                        + " (" + ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        FOTO_USUARIO + " VARCHAR, " + NOME_USUARIO + " VARCHAR, " +
                        EMAIL_USUARIO + " VARCHAR, " + SENHA_USUARIO + " VARCHAR );"
        );

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_EMPRESA
                        + " (" + ID_EMPRESA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        FOTO_EMPRESA+ " VARCHAR, " + NOME_EMPRESA+ " VARCHAR, " +
                        EMAIL_EMPRESA + " VARCHAR, " + TELEFONE_EMPRESA + " VARCHAR, " +
                        SITE_EMPRESA + " VARCHAR, " + ENDERECO_EMPRESA + " VARCHAR );"
        );

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUTO
                        + " (" + ID_PRODUTO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        FOTO_PRODUTO+ " VARCHAR, " + TITULO_PRODUTO + " VARCHAR, " +
                        DESCRICAO_PRODUTO + " TEXT, " + QUANTIDADE_PRODUTO + " INTEGER, " +
                        PRECO_PRODUTO + " DECIMAL, " + MARCA_PRODUTO + " VARCHAR, " +
                        TAMANHO_PRODUTO + " VARCHAR );"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONTATO);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_EMPRESA);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PRODUTO);
        onCreate(db);
    }
}
