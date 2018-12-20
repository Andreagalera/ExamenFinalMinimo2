package dsa.eetac.upc.edu.finalexamenminimo2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    //Declaar API
    private APIRest myapirest;
    //Declarar/Crear Recycler
    private Recycler recycler;
    private RecyclerView recyclerView;
    //Declarar spinner de cargando donde estamos esperando los datos
    ProgressDialog progressDialog;
    //Declarar token (2 funciones al final)
    private String token;
    //Declarar textview y imageview que aparecen en el layout para pasar valor(solo utilizamos botones)
    TextView nommuni;
    TextView comarcamuni;
    ImageView fotomuni;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Identificamos el recyclerview cerado antes con el nombre que tenga en el xml(LLAMALO recyclerView)
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recycler = new Recycler(this);
        recyclerView.setAdapter(recycler);
        recyclerView.setHasFixedSize(true);
        //Asignamos a cada liena del recyclerview el lienarlayout de itemfollowe
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Asignamos variable de de los diferents textview y image view a sus variables
        nommuni = findViewById(R.id.nommunicipitxt);
        comarcamuni = findViewById(R.id.nomcomarcatxt);
        fotomuni = findViewById(R.id.municipiimage);

        Intent intent = getIntent();

        //Progress loading. Justo al abrir esta actividad ponemos el spinner de cargando
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Waiting for the server");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        //Creamos API( vigilar que API este bien)
        myapirest = APIRest.createAPIRest();

        getAllCities();

    }

    //Función llamr lista de municipios

    public void getAllCities() {
        Call<Municipi> municipiCall = myapirest.getAllMunicipi();
        municipiCall.enqueue(new Callback<Municipi>() {
            @Override
            public void onResponse(Call<Municipi> call, Response<Municipi> response) {
                if (response.isSuccessful()) {
                    Municipi municipis = response.body();
                    List<Element> newList = municipis.getElements();

                    if (newList.size() != 0) {
                        recycler.addElements(newList);
                    }
                    progressDialog.hide();
                } else {
                    Log.e("No api connection", response.message());

                    progressDialog.hide();

                    //Show the alert dialog
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                    alertDialogBuilder
                            .setTitle("Error")
                            .setMessage(response.message())
                            .setCancelable(false)
                            .setPositiveButton("De acuerdo", (dialog, which) -> finish());

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }

            }

            @Override
            public void onFailure(Call<Municipi> call, Throwable t) {

                Log.e("No api connection: ", t.getMessage());

                progressDialog.hide();

                //Show the alert dialog
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                alertDialogBuilder
                        .setTitle("Error")
                        .setMessage(t.getMessage())
                        .setCancelable(false)
                        .setPositiveButton("De acuerdo", (dialog, which) -> finish());

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

}



