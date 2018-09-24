package tads.eaj.com.exemplobancodados;


import android.provider.BaseColumns;

/**
 * Created by Taniro on 9/09/2018.
 */

public final class LivroContrato {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private LivroContrato(){

    }
    public static class LivroEntry implements BaseColumns{
        public static final String TABLE_TITULO = "titulo";
        public static final String AUTOR = "autor";
        public static final int ANO = Integer.parseInt("ano");
        public static final int NOTA = Integer.parseInt("nota");
    }


}
