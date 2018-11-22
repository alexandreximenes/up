package com.example.ti.cadastrobd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.ti.cadastrobd.Disciplina;
import com.example.ti.cadastrobd.ListaDisciplinas;
import com.example.ti.cadastrobd.config.DBGateway;
import com.example.ti.cadastrobd.config.DBHelper;

public class DisciplinaDAO {

    private DBHelper helper;
    private DBGateway gateway;
    private final String SELECT_ALL = "SELECT * FROM " + helper.TABLE;

    public DisciplinaDAO(Context context) {
        gateway = DBGateway.getInstance(context);
    }

    public boolean cadastrarDisciplina(Disciplina disciplina) {

        return gateway.getDataBase().insert(helper.TABLE, null, putValues(disciplina)) > 0 ? true : false;
    }

    public void getDisciplinas() {
        Cursor cursor = gateway.getDataBase().rawQuery(SELECT_ALL, null);

        try {
            cursor.moveToFirst();

            while (cursor != null) {
                int id              = cursor.getInt(cursor.getColumnIndex(helper.ID));
                String nome         = cursor.getString(cursor.getColumnIndex(helper.NOME));
                String professor    = cursor.getString(cursor.getColumnIndex(helper.PROFESSOR));
                String turno        = cursor.getString(cursor.getColumnIndex(helper.TURNO));
                String dias         = cursor.getString(cursor.getColumnIndex(helper.DIAS));

                ListaDisciplinas.addDisciplina(new Disciplina(id, nome, professor, turno, dias));

                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean excluirDisciplina(int identifier){
        String where = helper.ID + " = ?";
        String [] id = {String.valueOf(identifier)};
        return gateway.getDataBase().delete(helper.TABLE, where,  id) > 0 ? true : false;
    }

    public boolean atualizarDisciplina(Disciplina disciplina){
        if(disciplina != null){
            String where = helper.ID + " = ?";
            String [] id = {String.valueOf( disciplina.getId() )};
            return gateway.getDataBase().update(helper.TABLE, putValues(disciplina), where,  id) > 0 ? true : false;
        }
        return false;
    }

    private ContentValues putValues(Disciplina disciplina) {
        ContentValues values = new ContentValues();

        values.put(helper.NOME, disciplina.getNome());
        values.put(helper.PROFESSOR, disciplina.getProfessor());
        values.put(helper.DIAS, disciplina.getDias());
        values.put(helper.TURNO, disciplina.getTurno());

        return values;
    }



}
