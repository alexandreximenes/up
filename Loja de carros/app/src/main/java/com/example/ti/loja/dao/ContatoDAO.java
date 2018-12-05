package com.example.ti.loja.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.ti.loja.Contato;
import com.example.ti.loja.config.DBGateway;
import com.example.ti.loja.config.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private DBHelper helper;
    private DBGateway gateway;
    private final String SELECT_ALL = "SELECT * FROM " + helper.TABLE;
    private List<Contato> contatos = null;

    public ContatoDAO(Context context) {
        gateway = DBGateway.getInstance(context);
    }

    public boolean cadastrar(Contato contato) {

        return gateway.getDataBase().insert(helper.TABLE, null, putValues(contato)) > 0 ? true : false;
    }

    public List<Contato> lista() {
        Cursor cursor = gateway.getDataBase().rawQuery(SELECT_ALL, null);
        contatos = new ArrayList<>();

        try {

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(helper.ID));
                        String nome = cursor.getString(cursor.getColumnIndex(helper.NOME));
                        String tipo = cursor.getString(cursor.getColumnIndex(helper.TIPO));
                        String email = cursor.getString(cursor.getColumnIndex(helper.EMAIL));
                        String telefone = cursor.getString(cursor.getColumnIndex(helper.TELEFONE));

                        contatos.add(new Contato(id, nome, tipo, email, telefone));

                    } while (cursor.moveToNext());
                }
            }
            return contatos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contatos;
    }

    public boolean excluir(int identifier) {
        String where = helper.ID + " = ?";
        String[] id = {String.valueOf(identifier)};
        return gateway.getDataBase().delete(helper.TABLE, where, id) > 0 ? true : false;
    }

    public boolean atualizar(Contato contato) {
        if (contato != null) {
            String where = helper.ID + " = ?";
            String[] id = {String.valueOf(contato.getId())};
            return gateway.getDataBase().update(helper.TABLE, putValues(contato), where, id) > 0 ? true : false;
        }
        return false;
    }

    private ContentValues putValues(Contato contato) {
        ContentValues values = new ContentValues();

        values.put(helper.NOME, contato.getNome());
        values.put(helper.TIPO, contato.getTipo());
        values.put(helper.EMAIL, contato.getEmail());
        values.put(helper.TELEFONE, contato.getTelefone());

        return values;
    }


    public Contato get(int codigo) {
        Cursor cursor = gateway.getDataBase().rawQuery(SELECT_ALL + " WHERE id =" + String.valueOf(codigo), null);
        Contato contato = null;

        try {

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(helper.ID));
                        String nome = cursor.getString(cursor.getColumnIndex(helper.NOME));
                        String tipo = cursor.getString(cursor.getColumnIndex(helper.TIPO));
                        String email = cursor.getString(cursor.getColumnIndex(helper.EMAIL));
                        String telefone = cursor.getString(cursor.getColumnIndex(helper.TELEFONE));

                        contato = new Contato(id, nome, tipo, email, telefone);

                    } while (cursor.moveToNext());
                }
            }
            return contato;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contato;
    }
}
