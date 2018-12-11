package com.example.ti.loja.Usuario;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ti.loja.Mensagem;
import com.example.ti.loja.R;
import com.example.ti.loja.Util.FakeSenha;
import com.example.ti.loja.dao.UsuarioDAO;

public class DetalhesUsuarioActivity extends Activity {

    private TextView txtID;
    private TextView txtNomeUsuario;
    private TextView txtEmailUsuario;
    private TextView txtSenhaUsuario;
    private ImageView fotoUsuario, imageDetalheUsuario;
    private Button btnExcluir;
    private Button btnEditar;
    private Context applicationContext;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_usuario);

        applicationContext = getApplicationContext();

        txtID = findViewById(R.id.txtIdUsuarioDetalhe);
        txtNomeUsuario = findViewById(R.id.txtNomeUsuarioDetalhe);
        txtEmailUsuario = findViewById(R.id.txtEMailUsuarioDetalhe);
        txtSenhaUsuario = findViewById(R.id.txtSenhaUsuarioDetalhe);
        fotoUsuario = findViewById(R.id.imageUsuario);
        imageDetalheUsuario = findViewById(R.id.imageDetalheUsuario);
        btnExcluir = findViewById(R.id.btnExcluirUsuarioDetalhe);
        btnEditar = findViewById(R.id.btnEditarUsuarioDetalhe);


        Intent detalhesIntent = getIntent();
        int id = detalhesIntent.getIntExtra("id", -1);

        if (id == -1) {
            Mensagem.show(getApplicationContext(), "Erro ao carregar detalhes do usuario", Toast.LENGTH_LONG);
            startActivity(new Intent(applicationContext, ListaUsuariosActivity.class));

        } else {

            UsuarioDAO dao = new UsuarioDAO(applicationContext);
            usuario = dao.get(id);

            txtID.setText("ID: #" + usuario.getId());
          if(usuario.getFoto() != null) imageDetalheUsuario.setImageBitmap(setImage(usuario.getFoto()));
            txtNomeUsuario.setText("NOME: " + usuario.getNome());
            txtEmailUsuario.setText("EMAIL: " + usuario.getEmail());
            txtSenhaUsuario.setText("SENHA: " + new FakeSenha().get(usuario.getSenha()));


            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent vaiParaCadastroActivity = new Intent(getApplicationContext(), CadastrarUsuario.class);
                    Bundle bundle = new Bundle();

                    bundle.putInt("id", usuario.getId());
                    bundle.putString("foto", usuario.getFoto());
                    bundle.putString("nome", usuario.getNome());
                    bundle.putString("email", usuario.getEmail());
                    bundle.putString("senha", usuario.getSenha());

                    vaiParaCadastroActivity.putExtras(bundle);
                    startActivity(vaiParaCadastroActivity);
                }
            });

            btnExcluir.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {
                        UsuarioDAO dao = new UsuarioDAO(applicationContext);

                        if (dao.excluir(usuario.getId())) {
                            Mensagem.show(applicationContext, "Contato excluida com sucesso!", Toast.LENGTH_LONG);
                        } else {
                            Mensagem.show(applicationContext, "Erro ao excluir usuario", Toast.LENGTH_LONG);
                        }
                        startActivity(new Intent(applicationContext, ListaUsuariosActivity.class));

                    } catch (Exception e) {
                        Mensagem.show(applicationContext, e.getMessage().toString(), Toast.LENGTH_LONG);
                    }
                }
            });

        }

    }
    private Bitmap setImage(String foto) {
        //fotoUsuario, imageDetalheUsuario;
        Bitmap bitmap;
        fotoUsuario.setImageBitmap(null);
        bitmap = BitmapFactory.decodeFile(foto);
        imageDetalheUsuario.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 360, 230, true));
        imageDetalheUsuario.setScaleType(ImageView.ScaleType.FIT_XY);
        return bitmap;
    }
}
