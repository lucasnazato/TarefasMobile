package com.example.aula_2022_03_18;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class GPStracker implements LocationListener {

    Context context;

    // Criar metodo publico do GPStracker
    public GPStracker(Context c) {
        context = c;
    }

    // Metodo publico derivado do LocationListener
    public Location getLocation(){

        // Checar a permissão para usar a localização no Manifest, caso sim, pegar se o GPS está ativado
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){

            // Se não for permitido pelo Package Manager, mostrar mensagem ao usuário avisando e retornar null
            Toast.makeText(context, "Não foi permitido", Toast.LENGTH_SHORT).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // Se o GPS estiver ativado, pegar a localização e a retornar
        if (isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        } else {

            // Caso não esteja ativado, pedir ao usuário que ative o GPS
            Toast.makeText(context, "Por favor, habilitar o GPS!", Toast.LENGTH_LONG).show();
        }

        // Após pegar todas as informações retornar nulo
        return null;
    }
    // Precisa sobrescrever para que o metódo funcione
    @Override
    public void onProviderDisabled(@NonNull String provider) {    }
    // Precisa sobrescrever para que o metódo funcione
    @Override
    public void onLocationChanged(@NonNull Location location) {    }
    // Precisa sobrescrever para que o metódo funcione
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {    }
}
