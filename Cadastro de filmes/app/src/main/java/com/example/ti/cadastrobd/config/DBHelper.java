package com.example.ti.cadastrobd.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DB = "filmes";
    public static final String TABLE = "filme";
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String ANO = "ano";
    public static final String MIDIA = "midia";
    public static final int VERSAO = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DB, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE
                        + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        NOME + " VARCHAR, " + ANO + " VARCHAR, " +
                        MIDIA + " VARCHAR );"
                  );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE);
        onCreate(db);
    }
}
