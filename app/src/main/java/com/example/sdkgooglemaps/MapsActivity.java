package com.example.sdkgooglemaps;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.sdkgooglemaps.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    //Metodo principal Maps
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Mudando a exibi????o do Mapa e estilo do mesmo
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);



        // Definindo uma are?? atrav??s da Latitude e Logitude
        LatLng itaperuna = new LatLng(-21.207155, -41.8909067);

        //Desenhando formas no mapa como exemplos: Cirulos
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(itaperuna);
        circleOptions.radius(300); //Sempre em metros
        circleOptions.strokeWidth(0); //Definindo a espessura da borda.
        circleOptions.strokeColor(Color.BLUE); //Definindo a cor da borda
        circleOptions.fillColor(Color.argb(50, 100, 50, 0)); //0 at?? 250 RGB
        mMap.addCircle(circleOptions); //referenciando com o objeto principal mMap.


        //Adicionando Metodo para  Evento de clique no mapa "Adicionando marcadores com clique
        //O metodo onMapClick retorna LatLng
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick( LatLng latLng) {

                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;

                //Exibindo um toats mostrando a Latitude e Longitude atrav??s do Clique no mapa
                Toast.makeText(MapsActivity.this,
                        "onCLick Lat:" +latitude+ "Long:" + longitude, Toast.LENGTH_SHORT).show();

                mMap.addMarker(
                        new MarkerOptions()
                                .position(latLng)
                                .title("Local")
                                .snippet("Descri????o: Localidade EcoPonto") //Definindo uma descri????o com o snippet
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)) // Estilizando Icone do Mapa atraves Default com cor.

                );

            }
        });


        /*?? s?? chamarmos o metodo addMarker
          E depois instanciar "new MarkerOptions" e escolher qual fun????o o mapa vai se comportar.
         */
        mMap.addMarker(
                new MarkerOptions()
                .position(itaperuna) //Passando o bjeto Itaperuna que recebe a Latitude e Long na primeira linha
                .title("Centro de Itaperuna") //Definindo uma title atrav??s da fun????o title
                 .icon(
                         //BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET); Mudando comportamento do icone do Mapa | Definindo uma cor
                         BitmapDescriptorFactory.fromResource(R.drawable.icone_bikeseeker) // Estilizando Icone do Mapa atraves do fromResource.
                 )
        );

        //Chamando metodo para dar zoom no mapa atrav??s do moveCamera e passando o CameraUpdateFactory
        //Os valores do zoom s??o de 2.0 at?? 21.0
        mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(itaperuna, 15)
        );
    } //Fim metodo principal Maps
}