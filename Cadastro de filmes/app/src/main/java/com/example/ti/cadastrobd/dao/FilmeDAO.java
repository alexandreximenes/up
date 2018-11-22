package com.example.ti.cadastrobd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.ti.cadastrobd.Filme;
import com.example.ti.cadastrobd.ListaFilmes;
import com.example.ti.cadastrobd.config.DBGateway;
import com.example.ti.cadastrobd.config.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {

    private DBHelper helper;
    private DBGateway gateway;
    private final String SELECT_ALL = "SELECT * FROM " + helper.TABLE;
    private List<Filme> filmes = null;

    public FilmeDAO(Context context) {
        gateway = DBGateway.getInstance(context);
    }

    public boolean cadastrar(Filme filme) {

        return gateway.getDataBase().insert(helper.TABLE, null, putValues(filme)) > 0 ? true : false;
    }

    public List<Filme> lista() {
        Cursor cursor = gateway.getDataBase().rawQuery(SELECT_ALL, null);
        filmes = new ArrayList<>();

        try {

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(helper.ID));
                        String nome = cursor.getString(cursor.getColumnIndex(helper.NOME));
                        String ano = cursor.getString(cursor.getColumnIndex(helper.ANO));
                        String midia = cursor.getString(cursor.getColumnIndex(helper.MIDIA));

                        filmes.add(new Filme(id, nome, ano, midia));

                    } while (cursor.moveToNext());
                }
            }
            return filmes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filmes;
    }

    public boolean excluir(int identifier) {
        String where = helper.ID + " = ?";
        String[] id = {String.valueOf(identifier)};
        return gateway.getDataBase().delete(helper.TABLE, where, id) > 0 ? true : false;
    }

    public boolean atualizar(Filme filme) {
        if (filme != null) {
            String where = helper.ID + " = ?";
            String[] id = {String.valueOf(filme.getId())};
            return gateway.getDataBase().update(helper.TABLE, putValues(filme), where, id) > 0 ? true : false;
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


    public Filme get(int codigo) {
        Cursor cursor = gateway.getDataBase().rawQuery(SELECT_ALL + " WHERE id =" + String.valueOf(codigo), null);
        Filme filme = null;

        try {

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(helper.ID));
                        String nome = cursor.getString(cursor.getColumnIndex(helper.NOME));
                        String ano = cursor.getString(cursor.getColumnIndex(helper.ANO));
                        String midia = cursor.getString(cursor.getColumnIndex(helper.MIDIA));

                        filme  = new Filme(id, nome, ano, midia);

                    } while (cursor.moveToNext());
                }
            }
            return filme;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filme;
    }
}
