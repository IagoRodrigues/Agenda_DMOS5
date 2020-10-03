package br.edu.dmos5.agenda_dmos5.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import br.edu.dmos5.agenda_dmos5.R;
import br.edu.dmos5.agenda_dmos5.model.Contato;

public class MostrarContatoActivity extends AppCompatActivity {

    private TextView textNome;
    private TextView textCel;
    private TextView textFix;

    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_contato);

        //Recupero elementos do layout
        textNome = findViewById(R.id.textview_nome);
        textCel = findViewById(R.id.textview_telCel);
        textFix = findViewById(R.id.textview_telFix);

        //Recupero dados trazidos do intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            String nome     = bundle.getString(MainActivity.KEY_NOME);
            String celular  = bundle.getString(MainActivity.KEY_CELULAR);
            String fixo = bundle.getString(MainActivity.KEY_TELEFONE);

            contato = new Contato(nome, celular, fixo);
        }

        //Atribuo a caixa
        textNome.setText(contato.getNome());
        textCel.setText(contato.getTel_cel());
        textFix.setText(contato.getTel_fix());
    }
}