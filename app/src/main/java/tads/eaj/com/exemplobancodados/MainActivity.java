package tads.eaj.com.exemplobancodados;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "sql";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BancoHelper db = new BancoHelper(this);

        Livro l1 = new Livro("titulo","autor", 0, 0);

        db.save(l1);

       List<Livro> lista = db.findAll();

       for (Livro l: lista) {
           Log.i(TAG, l.toString());
       }




    }
}
