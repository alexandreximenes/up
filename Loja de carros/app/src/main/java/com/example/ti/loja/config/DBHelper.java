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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONTATO);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USUARIO);
        onCreate(db);
    }
}
