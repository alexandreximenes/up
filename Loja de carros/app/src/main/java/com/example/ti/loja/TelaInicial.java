package com.example.ti.loja;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class TelaInicial extends Activity {

    public static final int TRES_SEGUNDOS = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fullScreen();

        irParaActivity(TRES_SEGUNDOS);

        setContentView(R.layout.activity_tela_inicial);


    }

    private void fullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    private void irParaActivity(int segundos) {
        Runnable login = () -> {
            try {
                Thread.sleep(segundos);
                startActivity(new Intent(TelaInicial.this, LoginActivity.class));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(login).start();
    }
}
