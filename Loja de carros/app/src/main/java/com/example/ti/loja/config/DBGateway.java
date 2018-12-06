package com.example.ti.loja.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBGateway {

    private static DBGateway gateway;
    private SQLiteDatabase db;

    private DBGateway(Context context, String banco) {
        DBHelper helper = new DBHelper(context, banco);
        db = helper.getWritableDatabase();
    }

    public static DBGateway getInstance(Context context, String banco) {
        if (gateway == null) {
            gateway = new DBGateway(context, banco);
        }
        return gateway;
    }

    public SQLiteDatabase getDataBase() {
        return this.db;
    }
}
