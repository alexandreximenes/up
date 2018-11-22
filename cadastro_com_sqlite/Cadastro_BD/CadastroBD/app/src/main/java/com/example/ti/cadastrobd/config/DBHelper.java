package com.example.ti.cadastrobd.config;

import android.content.Context;
import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DB = "curso";
    public static final String TABLE = "disciplinas";
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String PROFESSOR = "professor";
    public static final String TURNO = "turno";
    public static final String DIAS = "dias";
    public static final int VERSAO = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DB, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE
                        + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        NOME + " VARCHAR, " + PROFESSOR + " VARCHAR, " +
                        TURNO + " VARCHAR, " + DIAS + " VARCHAR );"
                  );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE);
        onCreate(db);
    }
}
