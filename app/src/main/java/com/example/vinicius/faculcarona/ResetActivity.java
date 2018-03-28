package com.example.vinicius.faculcarona;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText resetEmail;
    private TextView redefinirSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        resetEmail = findViewById(R.id.emailReset);
        redefinirSenha = findViewById(R.id.botaoEnviarEmail);

        firebaseAuth = FirebaseAuth.getInstance();

        redefinirSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = resetEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(ResetActivity.this, "Digite seu Email.", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ResetActivity.this, "Link enviado com Sucesso!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ResetActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(ResetActivity.this, "Email não encontrado!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

}
