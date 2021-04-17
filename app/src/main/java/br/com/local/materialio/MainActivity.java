package br.com.local.materialio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtNome, txtEmail, txtNumero;
    Button btnBusca;

    String url = "http://192.168.15.28/webserviceteste/server.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Consumindo WebService - PHP

        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        txtNumero = findViewById(R.id.txtNumero);

        btnBusca = findViewById(R.id.btnBusca);

        btnBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                        url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            txtNome.setText(response.getString("Nome"));
                            txtEmail.setText(response.getString("Email"));
                            txtNumero.setText(response.getString("Mobile"));
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),
                                    "Nenhuma informação encontrada...",
                                    Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),
                                "Erro ao conectar o servidor WEB",
                                Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });
                MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);
            }
        });

    }
    public void gerarFrase(View view){

        String frases[] = {
                "Tricolor do real parque",
                "NBA sua linda",
                "Tatakae",
                "Mó Paz"
        };
        int numero = new Random().nextInt(4);

        TextView texto = findViewById(R.id.txtResultado);
        texto.setText( frases[numero] );
    }






}