package br.edu.dmos5.agenda_dmos5.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.edu.dmos5.agenda_dmos5.R;
import br.edu.dmos5.agenda_dmos5.dao.ContatoDAO;
import br.edu.dmos5.agenda_dmos5.model.Contato;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int REQUESTCODE_NOVO_CONTATO = 99;
    public static final int MOSTRAR_CONTATO = 98;

    protected static final String KEY_NOME = "nome";
    protected static final String KEY_CELULAR = "celular";
    protected static final String KEY_TELEFONE = "telefone";

    private Context ctxt = this;

    private ListView contatosListView;
    private ImageView mImageView;
    private FloatingActionButton adicionarButton;

    private List<Contato> mContatoList;
    private ContatoDAO mContatoDao;

    private ArrayAdapter<Contato> contatoArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recupero a referencia a lista de contatos no layout
        contatosListView = findViewById(R.id.list_contatos);
        mImageView = findViewById(R.id.image_vazia);
        adicionarButton = findViewById(R.id.fab_adicionar);
        adicionarButton.setOnClickListener(this);

        //Carrega a fonte de dados
        mContatoDao = new ContatoDAO(this);
        mContatoList = mContatoDao.recuperaTodos();

        //Instancia do adapter, aqui configura-se como os dados serão apresentados e também
        //qual a fonte de dados será utilizada.
        contatoArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mContatoList);

        //Com o adapter pronto, vinculamos ele ao nosso ListView. Após esse comando o
        //ListView já consegue apresentar os dados na tela.
        contatosListView.setAdapter(contatoArrayAdapter);

        contatosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle argumentos = new Bundle();

                argumentos.putString(KEY_NOME, mContatoList.get(i).getNome());
                argumentos.putString(KEY_CELULAR, mContatoList.get(i).getTel_cel());
                argumentos.putString(KEY_TELEFONE, mContatoList.get(i).getTel_fix());

                Intent in = new Intent(ctxt, MostrarContatoActivity.class);
                in.putExtras(argumentos);

                startActivityForResult(in, MOSTRAR_CONTATO);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab_adicionar:
                Intent in = new Intent(ctxt, NovoContatoActivity.class);
                startActivityForResult(in, REQUESTCODE_NOVO_CONTATO);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case REQUESTCODE_NOVO_CONTATO:
                if(resultCode == RESULT_OK){
                    updateDataSet();
                    contatoArrayAdapter.notifyDataSetChanged();
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateUI(){
        if(contatosListView.getAdapter().getCount() == 0){
            mImageView.setVisibility(View.VISIBLE);
            contatosListView.setVisibility(View.GONE);
        }else{
            mImageView.setVisibility(View.GONE);
            contatosListView.setVisibility(View.VISIBLE);
        }
    }

    private void updateDataSet(){
        mContatoList.clear();
        mContatoList.addAll(mContatoDao.recuperaTodos());
    }
}