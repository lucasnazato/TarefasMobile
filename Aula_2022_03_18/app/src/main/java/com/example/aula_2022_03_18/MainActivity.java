package com.example.aula_2022_03_18;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Criar variaveis para imagem e botão
    private ImageView imageViewFoto;
    private Button btnGeo;

    // Sobrescrever o OnCreate para criar a visualização do app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Atribuir botao do xml a variavel e pegar permissão de localização
        btnGeo = (Button) findViewById(R.id.btn_gps);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        // Pegar o botão e ao clicar realizar a ação
        btnGeo.setOnClickListener(new View.OnClickListener() {
            // Sobrescrever o OnClick e criar a classe GPStracker e pegar a localização
            @Override
            public void onClick(View view) {
                GPStracker g = new GPStracker(getApplication());
                Location l = g.getLocation();
                // Se localização não for nula, pegar a latitude e longitude da localização, concatenar e escrever na tela
                if(l != null) {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LATITUDE: " + lat +"\n LONGITUDE: " + lon, Toast.LENGTH_LONG).show();
                }
            }
        });

        // Checar permissão de uso da camera no Manifest
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 0);
        }

        // Declarar a variavel de imagem e atribuir a imagem no xml
        imageViewFoto = (ImageView) findViewById(R.id.image_foto);
        findViewById(R.id.btn_pic).setOnClickListener(new View.OnClickListener() {

            // Sobrescrever OnClickView para chamar o metodo de tirar a foto
            @Override
            public void onClick(View view) {
                tirarFoto();
            }
        });
    }

    // Usar permissão da camera para capturar a foto e carregar
    private void tirarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    // Sobrescrever o onActivityResult e passar os dados do intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        // Se for código certo e o resultado for OK, usar o bundle para pegar a imagem que foi capturada e passar para a var ImageViewFoto
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imagem);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}