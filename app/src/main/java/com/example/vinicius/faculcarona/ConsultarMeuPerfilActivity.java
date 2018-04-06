package com.example.vinicius.faculcarona;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ConsultarMeuPerfilActivity extends AppCompatActivity {

    private ImageButton getLocalizacao;
    public static TextView LocalizacaoEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_meu_perfil);


        getLocalizacao = findViewById(R.id.botaoGetLocalizacao);
        LocalizacaoEdt = findViewById(R.id.localizacaoEdt);


        getLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultarMeuPerfilActivity.this, MapaActivity.class);
                startActivity(intent);

                }
        });

    }
}
