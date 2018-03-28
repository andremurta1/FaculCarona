package com.example.vinicius.faculcarona;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroActivity extends AppCompatActivity {

    private Button addFPerfil;
    private EditText edtNome;
    private EditText edtSobrenome;
    private EditText edtSexo;
    private EditText edtDataNasc;
    private EditText edtCelular;
    private TextView gravar;

    FirebaseDatabase database;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        addFPerfil = findViewById(R.id.addFoto);
        gravar = findViewById(R.id.gravarId);
        edtNome = findViewById(R.id.edtNome);
        edtSobrenome = findViewById(R.id.edtSobrenome);
        edtSexo = findViewById(R.id.edtSexo);
        edtDataNasc = findViewById(R.id.edtDataNasc);
        edtCelular = findViewById(R.id.edtCelular);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        addFPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroActivity.this, FotoActivity.class);
                startActivity(intent);
            }
        });

        gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gravar();
                Intent it = new Intent(CadastroActivity.this, PainelActivity.class);
                startActivity(it);
                Toast.makeText(CadastroActivity.this, "Cadastro completo!", Toast.LENGTH_SHORT).show();
                Toast.makeText(CadastroActivity.this, "Bem vindo "+ edtNome.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void gravar() {
        String nome = edtNome.getText().toString();
        String sobrenome = edtSobrenome.getText().toString();
        String sexo = edtSexo.getText().toString();
        String dataNasc = edtDataNasc.getText().toString();
        String celular = edtCelular.getText().toString();

        FirebaseUser user = auth.getCurrentUser();
        String uid = user.getUid();

        DatabaseReference usuarios = database.getReference("Usuarios");
        usuarios.child(uid).child("nome").setValue(nome);
        usuarios.child(uid).child("sobrenome").setValue(sobrenome);
        usuarios.child(uid).child("sexo").setValue(sexo);
        usuarios.child(uid).child("dataNasc").setValue(dataNasc);
        usuarios.child(uid).child("celular").setValue(celular);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

