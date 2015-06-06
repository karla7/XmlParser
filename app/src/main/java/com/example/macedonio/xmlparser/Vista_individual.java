package com.example.macedonio.xmlparser;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

//Para crear la clase
public class Vista_individual extends ActionBarActivity {

    static final String KEY_ID_ACCESORIO= "id_accesorio";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_COLOR = "color";
    static final String KEY_TIPO = "tipo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_individual);

        Intent in = getIntent();

        String id_accesorio = in.getStringExtra(KEY_ID_ACCESORIO);
        String nombre = in.getStringExtra(KEY_NOMBRE);
        String color= in.getStringExtra(KEY_COLOR);
        String tipo = in.getStringExtra(KEY_TIPO);


        TextView lbl_id_accesorio = (TextView) findViewById(R.id.txt_id_accesorio);
        TextView lblnombre = (TextView) findViewById(R.id.txt_nombre);
        TextView lblcolor = (TextView) findViewById(R.id.txt_color);
        TextView lbltipo = (TextView) findViewById(R.id.txt_tipo);


        lbl_id_accesorio.setText(id_accesorio);
        lblnombre.setText(nombre);
        lblcolor.setText(color);
        lbltipo.setText(tipo);


    }



}
