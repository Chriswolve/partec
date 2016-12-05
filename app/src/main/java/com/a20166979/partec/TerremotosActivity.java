package com.a20166979.partec;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TerremotosActivity extends AppCompatActivity {


    List<String> li;
    List<String> descr;

    ListView lista;

    ArrayList<HashMap<String, String>> earquakeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terremotos);



        li = new ArrayList<String>();
        descr = new ArrayList<String>();

        lista = (ListView) findViewById(R.id.list_contacts);


        new GetEarthQuakes().execute();




        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {



            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position,
                                    long id) {

                Toast.makeText(getApplicationContext(), descr.get(position), Toast.LENGTH_LONG).show();

            }
        });


    }







    private class GetEarthQuakes extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response

            String url = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.geojson";
            String jsonStr = sh.makeServiceCall(url);

            // JSONObject feature  = jsonObj.getJSONObject("features");
                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        JSONArray features = jsonObj.getJSONArray("features");


                        for (int i = 0; i < 20; i++) {
                            JSONObject c = features.getJSONObject(i);


                            String id = c.getString("type");

                            JSONObject p = c.getJSONObject("properties");



                            String mag = p.getString("mag");
                            String place = p.getString("place");
                            String type = p.getString("type");
                            String title = p.getString("title");

                            // adding contact to contact list
                            li.add(title  + " \nMagnitud : "+ mag);
                            descr.add("Tipo : " +type  + "\n Magnitud : "+ mag +"\n Lugar :" +place );
                           // earquakeList.add(contact);
                        }



                    } catch (final JSONException e) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(),
                                        "Json parsing error: " + e.getMessage(),
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });

                    }
                } else {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Couldn't get json from server. Check LogCat for possible errors!",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }


            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ArrayAdapter<String> adp = new ArrayAdapter<String>
                    (getBaseContext(), R.layout.list, li);

            lista.setAdapter(adp);
        }
    }



}
