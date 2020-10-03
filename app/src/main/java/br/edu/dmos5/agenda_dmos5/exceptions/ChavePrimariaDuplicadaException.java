package br.edu.dmos5.agenda_dmos5.exceptions;

import android.database.sqlite.SQLiteException;

public class ChavePrimariaDuplicadaException extends SQLiteException {
    public ChavePrimariaDuplicadaException (String error) {
        super(error);
    }
}
