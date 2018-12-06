package com.example.ti.loja;

import android.content.Context;
import android.widget.Toast;

public class Mensagem {
    public static void show(Context baseContext, String s, int lengthLong) {
        Toast.makeText(baseContext, s, lengthLong).show();
    }
}
