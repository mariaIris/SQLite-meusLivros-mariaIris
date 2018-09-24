package tads.eaj.com.exemplobancodados;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class Lista extends AppCompatActivity {
    TextView titulo;
    TextView autor;
    TextView ano;
    TextView nota;
    BancoHelper db;

    List<Livro> livros;
    Livro livro;
    int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        db.save(livro);

        final Button buttonAnterior = findViewById(R.id.buttonAnterior);
        final Button buttonProximo = findViewById(R.id.buttonProximo);
        titulo = findViewById(R.id.titulo);
        autor = findViewById(R.id.autor);
        ano = findViewById(R.id.autor);
        nota = findViewById(R.id.autor);

        livros = db.findAll();

        if(livros.isEmpty()){
            finish();
            Toast.makeText(this,"Não há registro de livros:", LENGTH_LONG).show();
            return;
        }

        buttonAnterior.setEnabled(true);

        cont=0;

        titulo.setText(livros.get(cont).getTitulo());
        autor.setText(livros.get(cont).getAutor());
        ano.setText(livros.get(cont).getAno());
        nota.setText(livro.getNota());

        buttonProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cont >= livros.size()-1){
                    Toast.makeText(Lista.this,"Ultimo livro na lista!", Toast.LENGTH_SHORT).show();
                    buttonProximo.setEnabled(false);
                    return;

                }else{
                    buttonAnterior.setEnabled(true);
                    buttonProximo.setEnabled(true);
                    cont--;
                }

                titulo.setText(livros.get(cont).getTitulo());
                autor.setText(livros.get(cont).getAutor());
                ano.setText(livros.get(cont).getAno());
                nota.setText(livros.get(cont).getNota());
            }
        });
    }


}
