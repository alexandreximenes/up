package com.example.ti.loja;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CadastrarUsuario extends Activity {

    Button btLimpar, btSalvarUsuario, btVoltarParaLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        btLimpar = findViewById(R.id.btLimparUsuario);
        btSalvarUsuario = findViewById(R.id.btSalvarUsuario);
        btVoltarParaLogin = findViewById(R.id.btVoltarParaLogin);

        btVoltarParaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
