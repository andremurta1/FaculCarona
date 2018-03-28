package com.example.vinicius.faculcarona;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MeuCadastroActivity extends AppCompatActivity {

    private TextView queroSerMotorista;
    private TextView consultarCadastro;
    private TextView deixarSerMotorista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meu_cadastro);

        queroSerMotorista = findViewById(R.id.botaoSerMotorista);
        consultarCadastro = findViewById(R.id.botaoConsultarCadastro);
        deixarSerMotorista = findViewById(R.id.botaoNaoMotorista);

        queroSerMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeuCadastroActivity.this, ConfirmarSerMotoristaActivity.class);
                startActivity(intent);
            }
        });

        consultarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeuCadastroActivity.this, ConsultarMeuPerfilActivity.class);
                startActivity(intent);
            }
        });

        deixarSerMotorista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
