package com.example.ti.loja.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.ti.loja.Empresa.Empresa;
import com.example.ti.loja.Usuario.Usuario;
import com.example.ti.loja.config.DBGateway;
import com.example.ti.loja.config.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {

    private DBHelper helper;
    private DBGateway gateway;
    private final String SELECT = "SELECT * FROM " + helper.TABLE_EMPRESA;
    private List<Empresa> empresas = null;

    public EmpresaDAO(Context context) {
        gateway = DBGateway.getInstance(context, helper.TABLE_EMPRESA);
    }

    public boolean cadastrar(Empresa empresa) {

        return gateway.getDataBase().insert(helper.TABLE_EMPRESA, null, putValues(empresa)) > 0 ? true : false;
    }

    public List<Empresa> lista() {
        Cursor cursor = gateway.getDataBase().rawQuery(SELECT, null);
        empresas = new ArrayList<>();

        try {

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(helper.ID_EMPRESA));
                        String foto = cursor.getString(cursor.getColumnIndex(helper.FOTO_EMPRESA));
                        String nome = cursor.getString(cursor.getColumnIndex(helper.NOME_EMPRESA));
                        String email = cursor.getString(cursor.getColumnIndex(helper.EMAIL_EMPRESA));
                        String telefone = cursor.getString(cursor.getColumnIndex(helper.TELEFONE_EMPRESA));
                        String site = cursor.getString(cursor.getColumnIndex(helper.SITE_EMPRESA));
                        String endereco = cursor.getString(cursor.getColumnIndex(helper.ENDERECO_EMPRESA));

                        empresas.add(new Empresa(id, foto, nome, email, telefone, site, endereco));

                    } while (cursor.moveToNext());
                }
            }
            return empresas;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empresas;
    }

    public boolean excluir(int identifier) {
        String where = helper.ID_EMPRESA + " = ?";
        String[] id = {String.valueOf(identifier)};
        return gateway.getDataBase().delete(helper.TABLE_EMPRESA, where, id) > 0 ? true : false;
    }

    public boolean atualizar(Empresa empresa) {
        if (empresa != null) {
            String where = helper.ID_EMPRESA + " = ?";
            String[] id = {String.valueOf(empresa.getId())};
            return gateway.getDataBase().update(helper.TABLE_EMPRESA, putValues(empresa), where, id) > 0 ? true : false;
        }
        return false;
    }

    private ContentValues putValues(Empresa empresa) {
        ContentValues values = new ContentValues();

        values.put(helper.FOTO_EMPRESA, empresa.getFoto());
        values.put(helper.NOME_EMPRESA, empresa.getNome());
        values.put(helper.EMAIL_EMPRESA, empresa.getEmail());
        values.put(helper.SITE_EMPRESA, empresa.getSite());
        values.put(helper.ENDERECO_EMPRESA, empresa.getEndereco());
        values.put(helper.TELEFONE_EMPRESA, empresa.getTelefone());

        return values;
    }

    public Empresa get(int codigo) {
        Cursor cursor = gateway.getDataBase().rawQuery(SELECT + " WHERE id =" + codigo, null);
        Empresa empresa = null;

        try {

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(helper.ID_EMPRESA));
                        String foto = cursor.getString(cursor.getColumnIndex(helper.FOTO_EMPRESA));
                        String nome = cursor.getString(cursor.getColumnIndex(helper.NOME_EMPRESA));
                        String email = cursor.getString(cursor.getColumnIndex(helper.EMAIL_EMPRESA));
                        String telefone = cursor.getString(cursor.getColumnIndex(helper.TELEFONE_EMPRESA));
                        String site = cursor.getString(cursor.getColumnIndex(helper.SITE_EMPRESA));
                        String endereco = cursor.getString(cursor.getColumnIndex(helper.ENDERECO_EMPRESA));

                        empresa = new Empresa(id, foto, nome, email, telefone, site, endereco);

                    } while (cursor.moveToNext());
                }
            }
            return empresa;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empresa;
    }
    public boolean ehCadastrado(String telefone){
        Cursor cursor = gateway.getDataBase().rawQuery(SELECT + " WHERE " + helper.TELEFONE_EMPRESA + " =? ", new String[]{telefone});
        boolean b = cursor.getCount() > 0 ? true : false;
        cursor.close();
        return b;

    }
}
