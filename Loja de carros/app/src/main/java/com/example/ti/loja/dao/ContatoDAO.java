package com.example.ti.loja.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.ti.loja.Contato;
import com.example.ti.loja.Enum.BD;
import com.example.ti.loja.config.DBGateway;
import com.example.ti.loja.config.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private DBHelper helper;
    private DBGateway gateway;
    private final String SELECT_ALL = "SELECT * FROM " + helper.TABLE_CONTATO;
    private List<Contato> contatos = null;

    public ContatoDAO(Context context) {
        gateway = DBGateway.getInstance(context, helper.DB_USUARIO);
    }

    public boolean cadastrar(Contato contato) {

        return gateway.getDataBase().insert(helper.TABLE_CONTATO, null, putValues(contato)) > 0 ? true : false;
    }

    public List<Contato> lista() {
        Cursor cursor = gateway.getDataBase().rawQuery(SELECT_ALL, null);
        contatos = new ArrayList<>();

        try {

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(helper.ID_CONTATO));
                        String nome = cursor.getString(cursor.getColumnIndex(helper.NOME_CONTATO));
                        String tipo = cursor.getString(cursor.getColumnIndex(helper.TIPO_CONTATO));
                        String email = cursor.getString(cursor.getColumnIndex(helper.EMAIL_CONTATO));
                        String telefone = cursor.getString(cursor.getColumnIndex(helper.TELEFONE_CONTATO));

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
        String where = helper.ID_CONTATO + " = ?";
        String[] id = {String.valueOf(identifier)};
        return gateway.getDataBase().delete(helper.TABLE_CONTATO, where, id) > 0 ? true : false;
    }

    public boolean atualizar(Contato contato) {
        if (contato != null) {
            String where = helper.ID_CONTATO + " = ?";
            String[] id = {String.valueOf(contato.getId())};
            return gateway.getDataBase().update(helper.TABLE_CONTATO, putValues(contato), where, id) > 0 ? true : false;
        }
        return false;
    }

    private ContentValues putValues(Contato contato) {
        ContentValues values = new ContentValues();

        values.put(helper.NOME_CONTATO, contato.getNome());
        values.put(helper.TIPO_CONTATO, contato.getTipo());
        values.put(helper.EMAIL_CONTATO, contato.getEmail());
        values.put(helper.TELEFONE_CONTATO, contato.getTelefone());

        return values;
    }


    public Contato get(int codigo) {
        Cursor cursor = gateway.getDataBase().rawQuery(SELECT_ALL + " WHERE id =" + String.valueOf(codigo), null);
        Contato contato = null;

        try {

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(helper.ID_CONTATO));
                        String nome = cursor.getString(cursor.getColumnIndex(helper.NOME_CONTATO));
                        String tipo = cursor.getString(cursor.getColumnIndex(helper.TIPO_CONTATO));
                        String email = cursor.getString(cursor.getColumnIndex(helper.EMAIL_CONTATO));
                        String telefone = cursor.getString(cursor.getColumnIndex(helper.TELEFONE_CONTATO));

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
