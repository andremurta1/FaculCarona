package com.example.vinicius.faculcarona;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistreActivity extends AppCompatActivity {

    private EditText textoSenha;
    private EditText textoEmail;
    private TextView cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

        cadastrar = (TextView) findViewById(R.id.botaoConcluir);
        textoEmail = (EditText) findViewById(R.id.textoEmailId);
        textoSenha = (EditText) findViewById(R.id.textoSenhaId);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cadastro();
            }
        });
    }

   private void cadastro() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (!TextUtils.isEmpty(textoEmail.getText()) && !TextUtils.isEmpty(textoSenha.getText())) {
            Task<AuthResult> processo = auth.createUserWithEmailAndPassword(textoEmail.getText().toString(), textoSenha.getText().toString());
            processo.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent it = new Intent(RegistreActivity.this, AccountActivity.class);
                        Toast.makeText(RegistreActivity.this, "Sucesso ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
                        startActivity(it);
                    } else {
                        Toast.makeText(RegistreActivity.this, "Falha ao cadastrar usuário", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            Toast.makeText(RegistreActivity.this, "Campos nulos", Toast.LENGTH_SHORT).show();
        }
    }
}