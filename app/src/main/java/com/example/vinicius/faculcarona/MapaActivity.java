package com.example.vinicius.faculcarona;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final int MAP_PERMISSION_ACCESS_COURSE_LOCATION = 9999;
    private LatLng minhaLocalizacao;
    private String localizacaoEdt;


    private GoogleMap mMap;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MAP_PERMISSION_ACCESS_COURSE_LOCATION);
        } else {
            getLocation();
            if(minhaLocalizacao == null){
                getLastLocation();
            }

        }

        if(minhaLocalizacao != null) {

            LatLng MeuLocal = new LatLng(minhaLocalizacao.latitude, minhaLocalizacao.longitude);
            Double latitude = minhaLocalizacao.latitude;
            Double longitude = minhaLocalizacao.longitude;
            localizacaoEdt = latitude.toString()+','+longitude.toString();
            ConsultarMeuPerfilActivity.LocalizacaoEdt.setText(localizacaoEdt);
            mMap.addMarker(new MarkerOptions().position(MeuLocal).title("Meu Local de Origem"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MeuLocal, 15));
        }
    }

    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            minhaLocalizacao = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
            }
    }

    private void getLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    minhaLocalizacao = new LatLng(location.getLatitude(), location.getLongitude());
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {
                }
            };
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MAP_PERMISSION_ACCESS_COURSE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastLocation();
                    getLocation();
                } else {
                    //Permissão negada
                }
                return;
            }
        }
    }
}
