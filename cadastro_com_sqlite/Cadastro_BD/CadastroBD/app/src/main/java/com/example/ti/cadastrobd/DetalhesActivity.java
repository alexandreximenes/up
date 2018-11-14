package com.example.ti.cadastrobd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetalhesActivity extends AppCompatActivity {

    private TextView txtID;
    private TextView txtNome;
    private TextView txtProfessor;
    private TextView txtDias;
    private TextView txtTurno;
    private Button btnExcluir;
    private Button btnAlterar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);


        txtID        = findViewById(R.id.txtID);
        txtNome      = findViewById(R.id.txtNome);
        txtProfessor = findViewById(R.id.txtProfessor);
        txtDias      = findViewById(R.id.txtDias);
        txtTurno     = findViewById(R.id.txtTurno);
        btnExcluir     = findViewById(R.id.btnExcluir);
        btnAlterar     = findViewById(R.id.btnAlterar);



        Intent detalhesIntent = getIntent();
        int index = detalhesIntent.getIntExtra("index", -1);

        if(index == -1){

            Toast.makeText(this, "Erro ao carregar detalhes da disciplina", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));

        }else{

            final Disciplina disciplina = ListaDisciplinas.getListaDisciplinas().get(index);
            txtID.setText("ID: #" + disciplina.getId());
            txtNome.setText("Nome: " + disciplina.getNome());
            txtProfessor.setText("Professor: " + disciplina.getProfessor());
            txtDias.setText(("Dias na semana: " + disciplina.getDias()));
            txtTurno.setText("Turno: " + disciplina.getTurno());

            btnAlterar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent updateIntent = new Intent(getApplicationContext(), CadastrarActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putInt("id", disciplina.getId());
                    bundle.putString("nome", disciplina.getNome());
                    bundle.putString("professor", disciplina.getProfessor());
                    bundle.putString("dias", disciplina.getDias());
                    bundle.putString("turno", disciplina.getTurno());


                    updateIntent.putExtras(bundle);
                    startActivity(updateIntent);
                }
            });

            btnExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                }
            });


        }


    }
}
