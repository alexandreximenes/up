package com.example.ti.cadastrobd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.ti.cadastrobd.Filme;
import com.example.ti.cadastrobd.ListaFilmes;
import com.example.ti.cadastrobd.config.DBGateway;
import com.example.ti.cadastrobd.config.DBHelper;

import java.util.ArrayList;

public class FilmeDAO {

    private DBHelper helper;
    private DBGateway gateway;
    private final String SELECT_ALL = "SELECT * FROM " + helper.TABLE;

    public FilmeDAO(Context context) {
        gateway = DBGateway.getInstance(context);
    }

    public boolean cadastrar(Filme filme) {

        return gateway.getDataBase().insert(helper.TABLE, null, putValues(filme)) > 0 ? true : false;
    }

    public ArrayList<Filme> get() {
        Cursor cursor = gateway.getDataBase().rawQuery(SELECT_ALL, null);

        try {
            cursor.moveToFirst();

            while (cursor != null) {
                int id              = cursor.getInt(cursor.getColumnIndex(helper.ID));
                String nome         = cursor.getString(cursor.getColumnIndex(helper.NOME));
                String ano    = cursor.getString(cursor.getColumnIndex(helper.ANO));
                String midia        = cursor.getString(cursor.getColumnIndex(helper.MIDIA));

                ListaFilmes.add(new Filme(id, nome, ano, midia));

                cursor.moveToNext();
            }
            return ListaFilmes.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean excluir(int identifier){
        String where = helper.ID + " = ?";
        String [] id = {String.valueOf(identifier)};
        return gateway.getDataBase().delete(helper.TABLE, where,  id) > 0 ? true : false;
    }

    public boolean atualizar(Filme filme){
        if(filme != null){
            String where = helper.ID + " = ?";
            String [] id = {String.valueOf( filme.getId() )};
            return gateway.getDataBase().update(helper.TABLE, putValues(filme), where,  id) > 0 ? true : false;
        }
        return false;
    }

    private ContentValues putValues(Filme filme) {
        ContentValues values = new ContentValues();

        values.put(helper.NOME, filme.getNome());
        values.put(helper.ANO, filme.getAno());
        values.put(helper.MIDIA, filme.getMidia());

        return values;
    }



}
