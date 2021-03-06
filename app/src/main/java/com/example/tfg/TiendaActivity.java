package com.example.tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import apiResult.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TiendaActivity extends AppCompatActivity implements Callback<Usuario> {

    Button btnhome, btnQuiz, btnPerfil;
    TextView tvUsuario, tvPuntos;
    View imgSteam, imgNetflix, imgPlay, imgAmazon;
    Usuario usu;
    Bundle datos;
    int puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        tvUsuario = findViewById(R.id.tvUsuarioBrowse);
        tvPuntos = findViewById(R.id.tvPuntosBrowse);
        datos = getIntent().getExtras();
        puntos = Integer.parseInt(datos.getString("puntos"));
        tvUsuario.setText(datos.getString("nombre"));
        tvPuntos.setText(String.valueOf(puntos));

        btnhome = findViewById(R.id.btnHome);
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TiendaActivity.this,BrowseActivity.class);
                i.putExtra("id",datos.getString("id"));
                i.putExtra("nombre",datos.getString("nombre"));
                i.putExtra("apellidos",datos.getString("apellidos"));
                i.putExtra("email",datos.getString("email"));
                i.putExtra("puntos",String.valueOf(puntos));
                startActivity(i);
            }
        });

        btnQuiz = findViewById(R.id.btnQuiz);
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(TiendaActivity.this, ListadoActivity.class);
                i2.putExtra("id",datos.getString("id"));
                i2.putExtra("nombre",datos.getString("nombre"));
                i2.putExtra("apellidos",datos.getString("apellidos"));
                i2.putExtra("email",datos.getString("email"));
                i2.putExtra("puntos",String.valueOf(puntos));
                startActivity(i2);
            }
        });

        btnPerfil = findViewById(R.id.btnPerfil);
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(TiendaActivity.this,PerfilActivity.class);
                i3.putExtra("id",datos.getString("id"));
                i3.putExtra("nombre",datos.getString("nombre"));
                i3.putExtra("apellidos",datos.getString("apellidos"));
                i3.putExtra("email",datos.getString("email"));
                i3.putExtra("puntos",String.valueOf(puntos));
                startActivity(i3);
            }
        });

        imgSteam = findViewById(R.id.steamImg);
        imgPlay = findViewById(R.id.playImg);
        imgNetflix = findViewById(R.id.netflixImg);
        imgAmazon = findViewById(R.id.amazonImg);

        imgSteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                comprarProducto(Integer.parseInt(datos.getString("id")),
                        puntos,10000);
            }
        });

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                comprarProducto(Integer.parseInt(datos.getString("id")),
                        puntos,20000);
            }
        });

        imgNetflix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprarProducto(Integer.parseInt(datos.getString("id")),
                        puntos,15000);
            }
        });

        imgAmazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprarProducto(Integer.parseInt(datos.getString("id")),
                        puntos,50000);
            }
        });
    }
    public void comprarProducto(int id,int puntos1,int puntosProducto){
        if(puntos1<puntosProducto){
            Toast.makeText(getApplicationContext(), "No tienes los puntos suficientes", Toast.LENGTH_LONG).show();
        }else{
            Call<Usuario> call=UsuarioAdapter.getApiService().comprarTienda(id,puntosProducto);
            call.enqueue(this);
            if(puntos> puntosProducto){
                puntos = puntos - puntosProducto;
                tvPuntos.setText(String.valueOf(puntos));
            }
        }
    }

    @Override
    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
        if (response.isSuccessful()) {
            usu = response.body();
            Toast.makeText(getApplicationContext(), "Puntos actuales" + usu.getPuntos(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "No dispones de las monedas suficientes", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onFailure(Call<Usuario> call, Throwable t) {
        Toast.makeText(getApplicationContext(), "Error de red", Toast.LENGTH_LONG).show();
    }
}