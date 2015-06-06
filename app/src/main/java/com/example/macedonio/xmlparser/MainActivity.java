package com.example.macedonio.xmlparser;


import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;



public class MainActivity extends ListActivity {

    //la direccion del archivo xml
    // All static variables
    static final String URL = "http://resources.260mb.net/bbd-k.xml";
    // XML node keys
    static final String KEY_ACCESORIOS = "accesorios"; // parent node
    static final String KEY_ID_ACCESORIO = "id_accesorio";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_COLOR = "color";
    static final String KEY_TIPO = "tipo";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

        XMLParser parser = new XMLParser();
        String xml = parser.getXmlFromUrl(URL);
        Document doc = parser.getDomElement(xml);

        NodeList nl = doc.getElementsByTagName(KEY_ACCESORIOS);

        for (int i = 0; i < nl.getLength(); i++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Element e = (Element) nl.item(i);
            // adding each child node to HashMap key => value
            map.put(KEY_ID_ACCESORIO, "id_accesorio: " + parser.getValue(e, KEY_ID_ACCESORIO));
            map.put(KEY_NOMBRE, "nombre: " +  parser.getValue(e, KEY_NOMBRE));
            map.put(KEY_COLOR, "color: " + parser.getValue(e, KEY_COLOR));
            map.put(KEY_TIPO, "tipo :" +  parser.getValue(e, KEY_TIPO));


            // adding HashList to ArrayList
            menuItems.add(map);

        }

        ListAdapter adapter = new SimpleAdapter(this, menuItems,
                R.layout.lista_accesorios,
                new String[]{KEY_ID_ACCESORIO, KEY_NOMBRE, KEY_COLOR, KEY_TIPO}, new int[]{
                R.id.txt_id_accesorio, R.id.txt_nombre ,R.id.color, R.id.tipo});

        setListAdapter(adapter);
        ListView lv = getListView();
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                //se declaran textview
                String txt_id_a = ((TextView) view.findViewById(R.id.txt_id_accesorio)).getText().toString();
                String txt_nombre_a = ((TextView) view.findViewById(R.id.txt_nombre)).getText().toString();
                String color_a = ((TextView) view.findViewById(R.id.txt_color)).getText().toString();
                String tipo_a = ((TextView) view.findViewById(R.id.txt_tipo)).getText().toString();


                // Starting new intent
                //para comenzar nuevo intento
                Intent in = new Intent(getApplicationContext(), Vista_individual.class);
                in.putExtra(KEY_ID_ACCESORIO, txt_id_a);
                in.putExtra(KEY_NOMBRE, txt_nombre_a);
                in.putExtra(KEY_COLOR, color_a);
                in.putExtra(KEY_TIPO, tipo_a);

                startActivity(in);

            }
        });
    }
}
