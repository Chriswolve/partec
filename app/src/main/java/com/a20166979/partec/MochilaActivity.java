package com.a20166979.partec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MochilaActivity extends AppCompatActivity {


    ArrayList<String> ImageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mochila);

        ImageList = new ArrayList<>();

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Toast.makeText(getApplicationContext(), ImageList.get(position), Toast.LENGTH_LONG).show();
            }
        });


        ImageList.add("Item :Agua \n" + "Equipar minimo con 3 botellas");
        ImageList.add("Item : Mochila\n" +"Llevar mochila para poder cargar los otros objetos");
        ImageList.add("Item : Linterna\n" +"Llevar 01 linterna en casos de sismo podria cortarse la electricidad");
        ImageList.add("Item : soga\n" +"Llevar 01 Soga para cualquien imprevisto necesario");



    }
}
