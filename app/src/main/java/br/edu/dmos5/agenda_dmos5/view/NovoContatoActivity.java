package br.edu.dmos5.agenda_dmos5.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.dmos5.agenda_dmos5.R;
import br.edu.dmos5.agenda_dmos5.dao.ContatoDAO;
import br.edu.dmos5.agenda_dmos5.model.Contato;

public class NovoContatoActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText nomeEditText;
    private EditText celEditText;
    private EditText fixEditText;
    private Button salvarButton;

    private ContatoDAO contatoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_contato);

        contatoDao = new ContatoDAO(this);

        nomeEditText = findViewById(R.id.edittext_nome);
        celEditText = findViewById(R.id.edittext_telCel);
        fixEditText = findViewById(R.id.edittext_telFix);

        salvarButton = findViewById(R.id.button_save);
        salvarButton.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_save:
                salvarContato();
                break;
        }
    }

    private void salvarContato() {
        String nome, celular, telFixo;
        nome = nomeEditText.getText().toString();
        celular = celEditText.getText().toString();
        telFixo = fixEditText.getText().toString();

        if(!checkNomeNum(nome, celular, telFixo)){
            showToast("Campos com erros!");
        }else {
            contatoDao = new ContatoDAO(this);
            try {
                contatoDao.adicionar(new Contato(nome, celular, telFixo));
                finalizar(true);
            } catch (NullPointerException e) {
                showToast(getString(R.string.erro_null_contato));
            }
        }
    }

    private void finalizar(boolean sucesso){
        if(sucesso){
            setResult(Activity.RESULT_OK);
        }else{
            setResult(Activity.RESULT_CANCELED);
        }
        finish();
    }

    private void showToast(String mensagem){
        Context context = getApplicationContext();
        CharSequence text = mensagem;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private boolean checkNomeNum(String nome, String celular, String telFixo) {
        boolean valido = true;

        if((celular.isEmpty() && telFixo.isEmpty()) || nome.isEmpty()){
            valido = false;
        }

        return valido;
    }
}