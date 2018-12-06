package com.example.ti.loja.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.ti.loja.Usuario.Usuario;
import com.example.ti.loja.config.DBGateway;
import com.example.ti.loja.config.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private DBHelper helper;
    private DBGateway gateway;
    private final String SELECT = "SELECT * FROM " + helper.TABLE_USUARIO;
    private List<Usuario> usuarios = null;

    public UsuarioDAO(Context context) {
        gateway = DBGateway.getInstance(context, helper.DB_USUARIO);
    }

    public boolean cadastrar(Usuario usuario) {

        return gateway.getDataBase().insert(helper.TABLE_USUARIO, null, putValues(usuario)) > 0 ? true : false;
    }

    public List<Usuario> lista() {
        Cursor cursor = gateway.getDataBase().rawQuery(SELECT, null);
        usuarios = new ArrayList<>();

        try {

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(helper.ID_USUARIO));
                        String foto = cursor.getString(cursor.getColumnIndex(helper.FOTO_USUARIO));
                        String nome = cursor.getString(cursor.getColumnIndex(helper.NOME_USUARIO));
                        String email = cursor.getString(cursor.getColumnIndex(helper.EMAIL_USUARIO));
                        String senha = cursor.getString(cursor.getColumnIndex(helper.SENHA_USUARIO));

                        usuarios.add(new Usuario(id, foto, nome, email, senha));

                    } while (cursor.moveToNext());
                }
            }
            return usuarios;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public boolean excluir(int identifier) {
        String where = helper.ID_USUARIO + " = ?";
        String[] id = {String.valueOf(identifier)};
        return gateway.getDataBase().delete(helper.TABLE_USUARIO, where, id) > 0 ? true : false;
    }

    public boolean atualizar(Usuario usuario) {
        if (usuario != null) {
            String where = helper.ID_USUARIO + " = ?";
            String[] id = {String.valueOf(usuario.getId())};
            return gateway.getDataBase().update(helper.TABLE_USUARIO, putValues(usuario), where, id) > 0 ? true : false;
        }
        return false;
    }

    private ContentValues putValues(Usuario usuario) {
        ContentValues values = new ContentValues();

        values.put(helper.FOTO_USUARIO, usuario.getFoto());
        values.put(helper.NOME_USUARIO, usuario.getNome());
        values.put(helper.EMAIL_USUARIO, usuario.getEmail());
        values.put(helper.SENHA_USUARIO, usuario.getSenha());

        return values;
    }

    public Usuario get(int codigo) {
        Cursor cursor = gateway.getDataBase().rawQuery(SELECT + " WHERE id =" + codigo, null);
        Usuario usuario = null;

        try {

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(helper.ID_USUARIO));
                        String foto = cursor.getString(cursor.getColumnIndex(helper.FOTO_USUARIO));
                        String nome = cursor.getString(cursor.getColumnIndex(helper.NOME_USUARIO));
                        String email = cursor.getString(cursor.getColumnIndex(helper.EMAIL_USUARIO));
                        String senha = cursor.getString(cursor.getColumnIndex(helper.SENHA_USUARIO));

                        usuario = new Usuario(id, foto, nome, email, senha);

                    } while (cursor.moveToNext());
                }
            }
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }
    public Usuario getNomeESenha(String nomeRequest, String senhaRequest) {
        Cursor cursor = gateway.getDataBase().rawQuery(SELECT + " WHERE nome =" + "'" +nomeRequest+ "'" + " AND senha =" + "'" + senhaRequest + "'", null);
        Usuario usuario = null;

        try {

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(helper.ID_USUARIO));
                        String foto = cursor.getString(cursor.getColumnIndex(helper.FOTO_USUARIO));
                        String nome = cursor.getString(cursor.getColumnIndex(helper.NOME_USUARIO));
                        String email = cursor.getString(cursor.getColumnIndex(helper.EMAIL_USUARIO));
                        String senha = cursor.getString(cursor.getColumnIndex(helper.SENHA_USUARIO));

                        usuario = new Usuario(id, foto, nome, email, senha);

                    } while (cursor.moveToNext());
                }
            }
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
