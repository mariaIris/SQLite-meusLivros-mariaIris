package tads.eaj.com.exemplobancodados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BancoHelper extends SQLiteOpenHelper {

    //String auxiliares
    private static final String TAG = "sql";
    private static final String TEXT_TYPE = " TEXT";
    private static final String NUMBER_TYPE = " INTEGER";
    private static final String VIRGULA = ",";

    // Nome do banco
    private static final String DATABASE_NAME = "banco_exemplo.sqlite";

    //versão do banco
    private static final int DATABASE_VERSION = 1;

    //SQLs
    private static final String SQL_CREATE_TABLE =
            ("CREATE TABLE " + LivroContrato.LivroEntry.TABLE_TITULO +
                    "("+
                    LivroContrato.LivroEntry._ID + NUMBER_TYPE+  " PRIMARY KEY"+ VIRGULA+
                    LivroContrato.LivroEntry.TABLE_TITULO + TEXT_TYPE + VIRGULA+
                    LivroContrato.LivroEntry.AUTOR + TEXT_TYPE + VIRGULA+
                    LivroContrato.LivroEntry.ANO + TEXT_TYPE + VIRGULA+
                    LivroContrato.LivroEntry.NOTA + NUMBER_TYPE+
                    ");"
            );
    private static final String SQL_DROP_TABLE =
            ("DROP TABLE "+ LivroContrato.LivroEntry.TABLE_TITULO+";"
            );



    public BancoHelper(Context context) {
        // context, nome do banco, factory, versão
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Não foi possível acessar o banco, criando um novo...");
        db.execSQL(SQL_CREATE_TABLE);
        Log.d(TAG, "Banco criado com sucesso.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Caso mude a versãoo do banco de dados, podemos executar um SQL aqui
        if (oldVersion != newVersion) {
            // Execute o script para atualizar a versão...
            Log.d(TAG, "Foi detectada uma nova versão do banco, aqui deverão ser executados os scripts de update.");
            db.execSQL(SQL_DROP_TABLE);
            this.onCreate(db);
        }
    }

    @Override
    public void  onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if (oldVersion != newVersion) {
            // Execute o script para atualizar a versão...
            Log.d(TAG, "Foi detectada uma nova versão do banco, aqui deverão ser executados os scripts de downgrade.");
            db.execSQL(SQL_DROP_TABLE);
            this.onCreate(db);
        }
    }

    // Insere um novo carro, ou atualiza se já existe.
    public long save(Livro livro) {

        long id = livro.getId();
        SQLiteDatabase db = getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(LivroContrato.LivroEntry.TABLE_TITULO , livro.getTitulo());
            values.put(LivroContrato.LivroEntry.AUTOR, livro.getAutor());
            values.put(String.valueOf(LivroContrato.LivroEntry.ANO), livro.getAno());
            values.put(String.valueOf(LivroContrato.LivroEntry.NOTA ), livro.getAno());

            if (id != 0) {

                String selection = LivroContrato.LivroEntry._ID + "= ?";
                String[] whereArgs = new String[]{String.valueOf(id)};

                // update carro set values = ... where _id=?
                int count = db.update(LivroContrato.LivroEntry.TABLE_TITULO, values, selection, whereArgs);
                Log.i(TAG, "Atualizou id [" + id + "] no banco.");
                return count;

            } else {
                // insert into carro values (...)-------------------alterei de "" para null
                id = db.insert(LivroContrato.LivroEntry.TABLE_TITULO, null, values);
                Log.i(TAG, "Inseriu id [" + id + "] no banco.");
                return id;
            }
        } finally {
            db.close();
        }
    }


    // Consulta a lista com todos os carros
    public List<Livro> findAll() {
        SQLiteDatabase db = getReadableDatabase();
        try {
            // select * from carro
            Cursor l = db.query(LivroContrato.LivroEntry.TABLE_TITULO, null, null, null, null, null, null, null);
            Log.i(TAG, "Listou todos os registros");
            return toList(l);
        } finally {
            db.close();
        }
    }

    // Consulta a lista com todos os carros
    public Livro findById(int id) {
        SQLiteDatabase db = getReadableDatabase();

        Log.i(TAG, "Buscou carro com id = "+ id);

        try {
            // select * from carro
            String selection = LivroContrato.LivroEntry._ID + "= ?";
            String[] whereArgs = new String[]{String.valueOf(id)};
            Cursor l = db.query(LivroContrato.LivroEntry.TABLE_TITULO, null, selection, whereArgs, null, null, null, null);

            if (l.moveToFirst()){
                Livro livro = new Livro(editTextTitulo.getText().toString(), editTextAutor.getText().toString(), editTextAno.getText().toString(), ratingBarNota.getRating());

                // recupera os atributos de livro
                livro.setId(l.getInt(l.getColumnIndex(LivroContrato.LivroEntry._ID)));
                livro.setTitulo(l.getString(l.getColumnIndex(LivroContrato.LivroEntry.TABLE_TITULO)));
                livro.setAutor(l.getString(l.getColumnIndex(LivroContrato.LivroEntry.AUTOR)));
                livro.setAno(l.getInt(l.getColumnIndex(String.valueOf(LivroContrato.LivroEntry.ANO))));
                livro.setNota(l.getInt(l.getColumnIndex(String.valueOf(LivroContrato.LivroEntry.NOTA))));

                return livro;
            }else {
                return null;
            }
        } finally {
            db.close();
        }
    }


    // Lê o cursor e cria a lista de carros
    private List<Livro> toList(Cursor l) {

        List<Livro> livros = new ArrayList<>();

        if (l.moveToFirst()) {
            do {
                Livro livro = new Livro(editTextTitulo.getText().toString(), editTextAutor.getText().toString(), editTextAno.getText().toString(), ratingBarNota.getRating());
                livros.add(livro);

                // recupera os atributos de carro
                livro.setId(l.getInt(l.getColumnIndex(LivroContrato.LivroEntry._ID)));
                livro.setTitulo(l.getString(l.getColumnIndex(LivroContrato.LivroEntry.TABLE_TITULO)));
                livro.setAutor(l.getString(l.getColumnIndex(LivroContrato.LivroEntry.AUTOR)));
                livro.setAno(l.getInt(l.getColumnIndex(String.valueOf(LivroContrato.LivroEntry.ANO))));
                livro.setNota(l.getInt(l.getColumnIndex(String.valueOf(LivroContrato.LivroEntry.NOTA))));

            } while (l.moveToNext());
        }

        return livros;
    }

    // Executa um SQL
    public void execSQL(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL(sql);
        } finally {
            db.close();
        }
    }

    // Executa um SQL
    public void execSQL(String sql, Object[] args) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL(sql, args);
        } finally {
            db.close();
        }
    }
}