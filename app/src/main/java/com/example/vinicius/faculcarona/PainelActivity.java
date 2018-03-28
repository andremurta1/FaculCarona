package com.example.vinicius.faculcarona;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.LoginManager;

public class PainelActivity extends AccountActivity {

    private TextView solicitarCarona;
    private TextView oferecerCarona;
    private TextView minhasCaronas;
    private TextView meuCadastro;
    private TextView gerarRota;
    private TextView sair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painel);

        solicitarCarona = findViewById(R.id.botaoSolicitarCarona);
        oferecerCarona = findViewById(R.id.botaoOferecerCarona);
        minhasCaronas = findViewById(R.id.botaoMinhasCaronas);
        meuCadastro = findViewById(R.id.botaoMeuCadastro);
        gerarRota = findViewById(R.id.botaoGerarRota);
        sair = findViewById(R.id.botaoSair);

        solicitarCarona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PainelActivity.this, SolicitarCaronaActivity.class);
                startActivity(intent);
            }
        });

        oferecerCarona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PainelActivity.this, OferecerCaronaActivity.class);
                startActivity(intent);
            }
        });

        minhasCaronas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PainelActivity.this, MinhasCaronasActivity.class);
                startActivity(intent);
            }
        });

        meuCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PainelActivity.this, MeuCadastroActivity.class);
                startActivity(intent);
            }
        });

        gerarRota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.br/maps/dir/-15.8226983,-47.9843072/-15.8135732,-47.9639654/-15.8197578,-47.9085008/@-15.8208808,-48.0002344,13z"));
                startActivity(intent);
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                LoginManager.getInstance().logOut();

                updateUI();
            }
        });

    }
}
