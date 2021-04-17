package br.com.local.materialio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class WebServiceActivity extends AppCompatActivity {

    TextView txtResposta;
    Button btnResposta;

    String url = "http://192.168.15.28/webserviceteste/teste.php";

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_service_layout);

        txtResposta = findViewById(R.id.txtResposta);
        btnResposta = findViewById(R.id.btnResposta);

        // Habilitando cache para melhorar performance
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);

        // Habilitando a rede
        Network network = new BasicNetwork(new HurlStack());

        // Criando o cache e a rede
        requestQueue = new RequestQueue(cache, network);
        // Iniciando o cache e a rede
        requestQueue.start();

        btnResposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                txtResposta.setText(response);
                                requestQueue.stop();
                            }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtResposta.setText("Erro ao conectar!!!");
                        error.printStackTrace();
                    }
                });
                //Adicionando o request a string do objeto
                requestQueue.add(stringRequest);
            }
        });


    }
}