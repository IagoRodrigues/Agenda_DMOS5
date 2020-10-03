package br.edu.dmos5.agenda_dmos5.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.dmos5.agenda_dmos5.exceptions.ChavePrimariaDuplicadaException;
import br.edu.dmos5.agenda_dmos5.model.Contato;

public class ContatoDAO {

    private SQLiteDatabase mSqLiteDatabase;
    private SQLiteHelper mHelper;

    public ContatoDAO(Context context) {
        mHelper = new SQLiteHelper(context);
    }

    public void adicionar(Contato contato) throws NullPointerException, ChavePrimariaDuplicadaException {
        if(contato == null){
            throw new NullPointerException();
        }

        ContentValues valores = new ContentValues();
        valores.put(SQLiteHelper.COLUMN_NOME, contato.getNome());
        valores.put(SQLiteHelper.COLUMN_TELCEL, contato.getTel_cel());
        valores.put(SQLiteHelper.COLUMN_TELFIX, contato.getTel_fix());

        mSqLiteDatabase = mHelper.getWritableDatabase();
        if(mSqLiteDatabase.insert(SQLiteHelper.TABLE_NAME_CONTATOS, null, valores) == -1){
            throw new ChavePrimariaDuplicadaException("Erro ao adicionar contato");
        }

        mSqLiteDatabase.close();
    }

    public List<Contato> recuperaTodos(){
        List<Contato> mContatoList;
        Contato mContato;
        Cursor mCursor;

        mContatoList = new ArrayList<>();

        String colunas[] = new String[]{
                SQLiteHelper.COLUMN_NOME,
                SQLiteHelper.COLUMN_TELCEL,
                SQLiteHelper.COLUMN_TELFIX
        };

        mSqLiteDatabase = mHelper.getReadableDatabase();

        mCursor = mSqLiteDatabase.query(
                SQLiteHelper.TABLE_NAME_CONTATOS,
                colunas,
                null,
                null,
                null,
                null,
                SQLiteHelper.COLUMN_NOME
        );

        while (mCursor.moveToNext()){
            mContato = new Contato(
                    mCursor.getString(0),
                    mCursor.getString(1),
                    mCursor.getString(2)
            );
            mContatoList.add(mContato);
        }

        mCursor.close();
        mSqLiteDatabase.close();
        return mContatoList;
    }
}
