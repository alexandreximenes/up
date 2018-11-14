package com.example.ti.cadastrobd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ti.cadastrobd.dao.DisciplinaDAO;


public class CadastrarActivity extends AppCompatActivity {


    private EditText edtNome;
    private EditText edtProfessor;
    private EditText edtTurno;
    private EditText edtDias;
    private Button btnSalvar;
    private TextView txtTitulo2;


    private int id;
    private String nome;
    private String professor;
    private String dias;
    private String turno;
    private boolean atualizar;
    private String msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        edtNome = findViewById(R.id.edtNome);
        edtProfessor = findViewById((R.id.edtProfessor));
        edtTurno = findViewById(R.id.edtTurno);
        edtDias = findViewById(R.id.edtdias);
        btnSalvar = findViewById(R.id.btnSalvar);
        txtTitulo2 = findViewById(R.id.txtTitulo2);

        final DisciplinaDAO dao = new DisciplinaDAO(CadastrarActivity.this);

        // recebe uma intent
        Intent intent = getIntent();
        // recebe o bundle da intent (se existir)
        Bundle bundle = intent.getExtras();
        // nesse momento, não vou atualizar dados.
        atualizar = false;

        // verificar se o bundle não é nulo
        if (bundle != null) {

            // muda-se o titulo da activity
            txtTitulo2.setText("EDITAR DISCIPLINA");

            // se recebemos um bundle, então vamos atualizar
            atualizar = true;

            // recebe os dados do bundle
            id = bundle.getInt("id");
            nome = bundle.getString("nome");
            professor = bundle.getString("professor");
            dias = bundle.getString("dias");
            turno = bundle.getString("turno");

            // seta os campos do form com os valores recebidos:
            edtNome.setText(nome.toString());
            edtProfessor.setText(professor.toString());
            edtDias.setText(dias.toString());
            edtTurno.setText(turno.toString());

        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                nome = edtNome.getText().toString();
                professor = edtProfessor.getText().toString();
                turno = edtTurno.getText().toString();
                dias = edtDias.getText().toString();

                if (atualizar) {


                } else {

                    Disciplina disciplina = new Disciplina(0, nome, professor, turno, dias);

                    try {

                        if(dao.cadastrarDisciplina(disciplina)){
                            msg("Disciplina cadastrada com sucesso!");
                        }else{
                            msg("Não foi possivel salvar disciplina");
                        }

                    }catch(Exception e){
                        msg("Erro ao conectar ...");
                    }

       }

                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });

    }

    private void msg (String msg){
        Toast.makeText(CadastrarActivity.this, msg, Toast.LENGTH_LONG).show();
    }
}
