package br.edu.dmos5.agenda_dmos5.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    //Constantes do BD
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "meus_contatos.db";

    //Contantes da tablea contatos
    public static final String TABLE_NAME_CONTATOS = "contatos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_TELCEL = "tel_cel";
    public static final String COLUMN_TELFIX = "tel_fix";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql;

        sql = "CREATE TABLE " + TABLE_NAME_CONTATOS + " (";
        sql += COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += COLUMN_NOME + " TEXT NOT NULL, ";
        sql += COLUMN_TELCEL + " TEXT NOT NULL, ";
        sql += COLUMN_TELFIX + " TEXT NOT NULL );";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
