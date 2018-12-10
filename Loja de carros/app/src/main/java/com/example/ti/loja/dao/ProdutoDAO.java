package com.example.ti.loja.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.ti.loja.Empresa.Empresa;
import com.example.ti.loja.Produto.Produto;
import com.example.ti.loja.config.DBGateway;
import com.example.ti.loja.config.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private DBHelper helper;
    private DBGateway gateway;
    private final String SELECT = "SELECT * FROM " + helper.TABLE_PRODUTO;
    private List<Produto> produtos = null;

    public ProdutoDAO(Context context) {
        gateway = DBGateway.getInstance(context, helper.TABLE_PRODUTO);
    }

    public boolean cadastrar(Produto produto) {

        return gateway.getDataBase().insert(helper.TABLE_PRODUTO, null, putValues(produto)) > 0 ? true : false;
    }

    public List<Produto> lista() {
        Cursor cursor = gateway.getDataBase().rawQuery(SELECT, null);
        produtos = new ArrayList<>();

        try {

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(helper.ID_PRODUTO));
                        String foto = cursor.getString(cursor.getColumnIndex(helper.FOTO_PRODUTO));
                        String titulo = cursor.getString(cursor.getColumnIndex(helper.TITULO_PRODUTO));
                        String descricao = cursor.getString(cursor.getColumnIndex(helper.DESCRICAO_PRODUTO));
                        Integer quantidade = Integer.parseInt(cursor.getString(cursor.getColumnIndex(helper.QUANTIDADE_PRODUTO)));
                        Double preco = Double.parseDouble(cursor.getString(cursor.getColumnIndex(helper.PRECO_PRODUTO)));
                        String tamanho = cursor.getString(cursor.getColumnIndex(helper.TAMANHO_PRODUTO));
                        String marca = cursor.getString(cursor.getColumnIndex(helper.MARCA_PRODUTO));

                        produtos.add(new Produto(id, foto, titulo, descricao, quantidade, preco, tamanho, marca));

                    } while (cursor.moveToNext());
                }
            }
            return produtos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public List<Produto> listaPorNome(String produto) {
        if(produto == null) return null;
        //SELECT colunas FROM tabela WHERE campo LIKE 'valor'
        String sql = SELECT + " WHERE " + helper.TITULO_PRODUTO + " LIKE " + "'%" + produto + "%'" + " OR " + helper.DESCRICAO_PRODUTO + " LIKE " + "'%" + produto + "%'";
        Log.d("sql_listaPorNome", sql);
        Cursor cursor = gateway.getDataBase().rawQuery(sql, null);
        produtos = new ArrayList<>();

        try {

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(helper.ID_PRODUTO));
                        String foto = cursor.getString(cursor.getColumnIndex(helper.FOTO_PRODUTO));
                        String titulo = cursor.getString(cursor.getColumnIndex(helper.TITULO_PRODUTO));
                        String descricao = cursor.getString(cursor.getColumnIndex(helper.DESCRICAO_PRODUTO));
                        Integer quantidade = Integer.parseInt(cursor.getString(cursor.getColumnIndex(helper.QUANTIDADE_PRODUTO)));
                        Double preco = Double.parseDouble(cursor.getString(cursor.getColumnIndex(helper.PRECO_PRODUTO)));
                        String tamanho = cursor.getString(cursor.getColumnIndex(helper.TAMANHO_PRODUTO));
                        String marca = cursor.getString(cursor.getColumnIndex(helper.MARCA_PRODUTO));

                        produtos.add(new Produto(id, foto, titulo, descricao, quantidade, preco, tamanho, marca));

                    } while (cursor.moveToNext());
                }
            }
            return produtos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public boolean excluir(int identifier) {
        String where = helper.ID_PRODUTO + " = ?";
        String[] id = {String.valueOf(identifier)};
        return gateway.getDataBase().delete(helper.TABLE_PRODUTO, where, id) > 0 ? true : false;
    }

    public boolean atualizar(Produto produto) {
        if (produto != null) {
            String where = helper.ID_PRODUTO + " = ?";
            String[] id = {String.valueOf(produto.getId())};
            return gateway.getDataBase().update(helper.TABLE_PRODUTO, putValues(produto), where, id) > 0 ? true : false;
        }
        return false;
    }

    private ContentValues putValues(Produto produto) {
        ContentValues values = new ContentValues();

        values.put(helper.FOTO_PRODUTO, produto.getFoto());
        values.put(helper.TITULO_PRODUTO, produto.getTitulo());
        values.put(helper.DESCRICAO_PRODUTO, produto.getDescricao());
        values.put(helper.QUANTIDADE_PRODUTO, produto.getQuantidade());
        values.put(helper.PRECO_PRODUTO, produto.getPreco());
        values.put(helper.TAMANHO_PRODUTO, produto.getTamanho());
        values.put(helper.MARCA_PRODUTO, produto.getMarca());

        return values;
    }

    public Produto get(int codigo) {
        Cursor cursor = gateway.getDataBase().rawQuery(SELECT + " WHERE id =" + codigo, null);
        Produto produto = null;

        try {

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex(helper.ID_PRODUTO));
                        String foto = cursor.getString(cursor.getColumnIndex(helper.FOTO_PRODUTO));
                        String titulo = cursor.getString(cursor.getColumnIndex(helper.TITULO_PRODUTO));
                        String descricao = cursor.getString(cursor.getColumnIndex(helper.DESCRICAO_PRODUTO));
                        Integer quantidade = Integer.parseInt(cursor.getString(cursor.getColumnIndex(helper.QUANTIDADE_PRODUTO)));
                        Double preco = Double.parseDouble(cursor.getString(cursor.getColumnIndex(helper.PRECO_PRODUTO)));
                        String tamanho = cursor.getString(cursor.getColumnIndex(helper.TAMANHO_PRODUTO));
                        String marca = cursor.getString(cursor.getColumnIndex(helper.MARCA_PRODUTO));

                        produto = new Produto(id, foto, titulo, descricao, quantidade, preco, tamanho, marca);

                    } while (cursor.moveToNext());
                }
            }
            return produto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produto;
    }
}
