package com.example.djeon.cycleseoul;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPointBounds;
import net.daum.mf.map.api.MapPolyline;
import net.daum.mf.map.api.MapView;

public class MainActivity extends AppCompatActivity implements MapView.CurrentLocationEventListener, MapView.MapViewEventListener,MapView.POIItemEventListener, LocationListener {
    private MapPoint wonStart= MapPoint.mapPointWithGeoCoord(37.577074, 126.996864);
    private MapPoint wonCenter= MapPoint.mapPointWithGeoCoord(37.569470, 126.987700);
    private MapPoint jong= MapPoint.mapPointWithGeoCoord(37.576404, 126.993608);
    private MapPoint wonEnd= MapPoint.mapPointWithGeoCoord(37.579522, 126.980508);
    private MapPoint changgyeonggung= MapPoint.mapPointWithGeoCoord(37.577788, 126.994820);
    private MapPoint changdeokgung= MapPoint.mapPointWithGeoCoord(37.578298, 126.989907);
    private MapPoint national= MapPoint.mapPointWithGeoCoord(37.579267, 126.981302);




    private MapPoint gwangStart = MapPoint.mapPointWithGeoCoord(37.575922, 126.976766);
    private MapPoint gwangEnd = MapPoint.mapPointWithGeoCoord(37.575937, 126.977348);

    private MapPoint worldStart = MapPoint.mapPointWithGeoCoord(37.566460, 126.898292);
    private MapPoint worldEnd = MapPoint.mapPointWithGeoCoord(37.567133, 126.877636);

    private MapPoint insadongStart = MapPoint.mapPointWithGeoCoord(37.565912, 126.977405);
    private MapPoint insadongEnd = MapPoint.mapPointWithGeoCoord(37.576203, 126.986269);

    private MapPoint chongaeStart = MapPoint.mapPointWithGeoCoord(37.571165, 127.009160);
    private MapPoint chongaeEnd = MapPoint.mapPointWithGeoCoord(37.569314, 126.977458);
    private MapPoint hong= MapPoint.mapPointWithGeoCoord(37.570734, 127.009681);
    private MapPoint chong= MapPoint.mapPointWithGeoCoord(37.569732, 127.008687);
    private MapPoint fish= MapPoint.mapPointWithGeoCoord(37.569689, 127.005924);
    private MapPoint tourist= MapPoint.mapPointWithGeoCoord(37.569178, 126.981529);
    private MapPoint gwangj= MapPoint.mapPointWithGeoCoord(37.569718, 127.001502);

    double userLat;
    double userLong;
    double pointLat;
    double pointLong;

    // bycicle stops
    double [] lat =  {37.586893, 37.585187, 37.573977, 37.576089, 37.575586, 37.576057, 37.5762365, 37.576192, 37.575736,
            37.576049, 37.569654, 37.572217, 37.5878587, 37.599777, 37.602213, 37.603653, 37.603562, 37.606208, 37.572908,
            37.574985, 37.571994, 37.578835, 37.570698, 37.572907, 37.570008, 37.576329, 37.576054, 37.5757217, 37.570987,
            37.5707075, 37.572175, 37.569744, 37.568461, 37.571193, 37.571265, 37.583065, 37.576486, 37.570643, 37.572979,
            37.576556, 37.5826604, 37.583855, 37.585133, 37.584871, 37.585799, 37.573541, 37.571259, 37.570084, 37.571732,
            37.572496, 37.571806, 37.578949, 37.579594, 37.573325, 37.573353, 37.579841, 37.579389, 37.572893, 37.574355,
            37.575023, 37.575748, 37.575975, 37.569952, 37.571057, 37.590614, 37.579806};

    double [] longt = {126.9717701, 126.969941, 126.973078, 126.967988, 126.978965, 126.972114, 126.972454, 126.9728285,
            126.972026, 126.973867, 126.976131, 126.974289, 126.982617, 126.957615, 126.9609726, 126.961269, 126.964442,
            126.968381, 126.961123, 126.957783, 126.997021, 126.996368, 126.988945, 126.979121, 126.988918, 126.984359,
            126.983811, 126.985563, 126.982395, 126.983577, 126.991832, 126.982158, 126.987944, 127.001239, 127.002288,
            127.0049235, 127.005563, 126.991532, 126.991629, 127.002546, 127.001492, 127.001329, 127.001502, 127.001563,
            127.000597, 127.014358, 127.010682, 127.010362, 127.015244, 127.0152734, 127.010161, 127.015032, 127.014994,
            127.016646, 127.016223, 127.015439, 127.015467, 127.016031, 127.019093, 127.022815, 127.022662, 127.022849,
            127.015837, 127.018728, 126.998783, 126.985514};

    //Neighbor Places
    double [] lat_neighbor =  {37.5829296, 37.5808485, 37.580804, 37.5778587, 37.5817921, 37.5775225, 37.5836876, 37.5827349,
            37.5821333, 37.5831289, 37.5800058, 37.5808302};
    double [] longt_neighbor = {126.9849052, 126.98572, 126.9841689, 126.9883535, 126.9856211, 126.9860057, 126.9837718,
            126.9856578, 126.985625, 126.9889494, 126.9846114, 126.988909};

    String [] name = {"Bycicle Station1", "Bycicle Station2", "Bycicle Station3", "Bycicle Station4",
            "Bycicle Station5","Bycicle Station6","Bycicle Station7","Bycicle Station8","Bycicle Station9",
            "Bycicle Station10","Bycicle Station11","Bycicle Station12","Bycicle Station13","Bycicle Station14",
            "Bycicle Station15","Bycicle Station16","Bycicle Station17","Bycicle Station18","Bycicle Station19",
            "Bycicle Station20","Bycicle Station21","Bycicle Station22","Bycicle Station23","Bycicle Station24",
            "Bycicle Station25","Bycicle Station26","Bycicle Station27","Bycicle Station28","Bycicle Station29",
            "Bycicle Station30","Bycicle Station31","Bycicle Station32","Bycicle Station33","Bycicle Station34",
            "Bycicle Station35","Bycicle Station36","Bycicle Station37","Bycicle Station38","Bycicle Station39",
            "Bycicle Station40","Bycicle Station41","Bycicle Station42","Bycicle Station43","Bycicle Station44",
            "Bycicle Station45","Bycicle Station46","Bycicle Station47","Bycicle Station48","Bycicle Station49",
            "Bycicle Station50","Bycicle Station51","Bycicle Station52","Bycicle Station53","Bycicle Station54",
            "Bycicle Station55","Bycicle Station56","Bycicle Station57","Bycicle Station58","Bycicle Station59",
            "Bycicle Station60","Bycicle Station61","Bycicle Station62","Bycicle Station63","Bycicle Station64",
            "Bycicle Station65","Bycicle Station66","Bycicle Station67","Bycicle Station68","Bycicle Station69",
            "Bycicle Station70"};

    String [] name_neighbor = {"House of Kim Hyeongtae in Gahoe-dong", "House of Han Clan in Gahoe-dong",
            "House of Baek Inje in Gahoe-dong", "Arario Museum In Space",
            "Donglim Knot Workshop", "Myeongin Museum",
            "House of Lee Ju-ku in Gahoe-dong", "Han Sang Soo Embroidery Museum",
            "gahoemuseum", "House of Go Hui-dong", "Bukchon Museum", "Korea Art Museum"};





    int change =0;
    int countRoute = 0;
    int countbike =0;
    //map view 를 위한 variables
    MapView mapView;
    ViewGroup mapViewContainer;
    MapPOIItem wonMarker,wonMarker2; //중심점 마커
    MapPOIItem gwangMarker,gwangMarker2;
    MapPOIItem worldMarker,worldMarker2;
    MapPOIItem insadongMarker,insadongMarker2;
    MapPOIItem chongaeMarker,chongaeMarker2;

    Button woncourse;
    Button button2;
    Button button3;
    Button button4;
    Button button5;

    Button share;
    Button neighbor;
    Button bike;

    MapPOIItem mCustomMarker;
    MapPOIItem mCustomMarker2;
    MapPOIItem mCustomMarker3;
    MapPOIItem mCustomMarker4;

    MapPOIItem mChongMarker1;
    MapPOIItem mChongMarker2;
    MapPOIItem mChongMarker3;
    MapPOIItem mChongMarker4;
    MapPOIItem mChongMarker5;

    MapPOIItem [] points;


    MapPOIItem drawBycicle;
    MapPOIItem drawNeighbor;
    MapPolyline drawRoute;


    FragList txt;

    int l;
    int fragc;
    int mapShow;
    int turndraw = 0;

    private ImageView iv;
    private String sharePath="no";

    // Acquire a reference to the system Location Manager
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButton();
        share = findViewById(R.id.share);
        neighbor = findViewById(R.id.neighbor);
        bike = findViewById(R.id.bycicle);


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE); // 위도 경도를 받아올 준비
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
        }
        Log.d("MainActivity:onCreate()","위치정보 업데이트를 요청합니다. ");
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

        createSmallMapView();

        woncourse.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){

               plotWonMarker();

              }
            });


        button2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                plotGanghwa();

            }
        });

        button3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                plotInsadong();

            }
        });

        button4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                plotChongae();

            }
        });

        button5.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                plotWorldCup();

            }
        });

        share.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Toast.makeText(getApplicationContext(), "Hello" + userLat + "  ", Toast.LENGTH_SHORT).show();
                    String url = "daummaps://route?sp=" + userLat + "," + userLong + "&ep=" + pointLat + "," + pointLong + "&by=FOOT";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
                catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                }




            }
        });

        neighbor.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(countRoute ==0) {
                    drawNeighbor = new MapPOIItem();

                    for (int n = 0; n < lat_neighbor.length; n++) {

                        drawNeighbor.setItemName(name_neighbor[n]);
                        drawNeighbor.setTag(1);
                        drawNeighbor.setMapPoint(MapPoint.mapPointWithGeoCoord(lat_neighbor[n], longt_neighbor[n]));
                        drawNeighbor.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                        drawNeighbor.setCustomImageResourceId(R.mipmap.ic_launcher3);
                        drawNeighbor.setCustomImageAutoscale(false);
                        drawNeighbor.setCustomImageAnchor(0.5f, 1.0f);
                        mapView.addPOIItem(drawNeighbor);
                        mapView.selectPOIItem(drawNeighbor, true);
                        countRoute = 1;
                    }
                }
                    else{
                        countRoute =0;
                        mapView.removeAllPOIItems();
                    }



            }
        });

        bike.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(countbike == 0) {
                    drawBycicle = new MapPOIItem();
                    for (int n = 0; n < lat.length; n++) {
                        drawBycicle.setItemName(name[n]);
                        drawBycicle.setTag(1);
                        drawBycicle.setMapPoint(MapPoint.mapPointWithGeoCoord(lat[n], longt[n]));
                        drawBycicle.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                        drawBycicle.setCustomImageResourceId(R.mipmap.ic_2);
                        drawBycicle.setCustomImageAutoscale(false);
                        drawBycicle.setCustomImageAnchor(0.5f, 1.0f);
                        mapView.addPOIItem(drawBycicle);
                        mapView.selectPOIItem(drawBycicle, true);
                        countbike = 1;
                    }
                }
                else
                {
                    countbike = 0;
                    mapView.removeAllPOIItems();
                }


            }
        });










    };

    public void setButton() {
        BottomNavigationView navBot = findViewById(R.id.bottomf);
        navBot.setOnNavigationItemSelectedListener(naListener);

        l=0;
        fragc=1;
        woncourse = findViewById(R.id.wonnam);
        button2 = findViewById(R.id.button3);
        button3 = findViewById(R.id.button4);
        button4 = findViewById(R.id.button5);
        button5 = findViewById(R.id.button6);


        woncourse.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);
        button5.setVisibility(View.INVISIBLE);

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
        }
        Log.d("MainActivity:onResume()","위치정보 업데이트를 요청합니다. ");
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);


    }


    @Override
    public void onPause() {
        super.onPause();
        mapViewContainer.removeAllViews();

    }

    public void createSmallMapView() {

        mapView = new MapView(this);

        mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        drawRoute = new MapPolyline();

        mapView.setCurrentLocationEventListener(this);
        mapView.setPOIItemEventListener(this);
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving);



        //이벤트리스너 등록
        mapView.setMapViewEventListener(this); // this 에 MapView.MapViewEventListener 구현.
       //mapView.setPOIItemEventListener(this);


    }


    //Navigation button on the bottom
    private BottomNavigationView.OnNavigationItemSelectedListener naListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch(item.getItemId()){
                case R.id.map_route:

                    if(l==0) {

                          woncourse.setVisibility(View.VISIBLE);
                          button2.setVisibility(View.VISIBLE);
                          button3.setVisibility(View.VISIBLE);
                          button4.setVisibility(View.VISIBLE);
                          button5.setVisibility(View.VISIBLE);
                        //openFragment();
                        l=1;
                    }
                    else
                        {
                            woncourse.setVisibility(View.INVISIBLE);
                            button2.setVisibility(View.INVISIBLE);
                            button3.setVisibility(View.INVISIBLE);
                            button4.setVisibility(View.INVISIBLE);
                            button5.setVisibility(View.INVISIBLE);
                            l=0;

                        }

                    break;
                case R.id.map_draw:

                    if(turndraw == 0) {
                        Toast.makeText(getApplicationContext(), "Drawing Started" , Toast.LENGTH_SHORT).show();
                        turndraw = 1;
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Drawing Ended", Toast.LENGTH_SHORT).show();
                        turndraw = 0;
                        drawRoute = new MapPolyline();
                    }


                    break;

                case R.id.map_find:
                    //moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds, padding))

                    //mapView.moveCamera();
                    if(change == 0){
                        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
                        change =1;
                    }
                    else {
                        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
                        change =0;
                    }
                    woncourse.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.INVISIBLE);
                    button5.setVisibility(View.INVISIBLE);
                    l=0;


                    break;
                default:

                    break;
            }

            return true;
        }
    };




//Current Location Listener
    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {
        //MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
        //countRoute = countRoute + 1;
        userLat = mapPoint.getMapPointGeoCoord().latitude;
        userLong = mapPoint.getMapPointGeoCoord().longitude;
       //Toast.makeText(getApplicationContext(), "Location Updated:" + mapPoint.getMapPointGeoCoord().latitude + " and " + mapPoint.getMapPointGeoCoord().longitude , Toast.LENGTH_SHORT).show();

        if(turndraw==1)
        {
            drawRoute.addPoint(MapPoint.mapPointWithGeoCoord(mapPoint.getMapPointGeoCoord().latitude, mapPoint.getMapPointGeoCoord().longitude));
            //Toast.makeText(getApplicationContext(), "Draw Start" , Toast.LENGTH_SHORT).show();
            mapView.addPolyline(drawRoute);
        }


    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {
        //Toast.makeText(getApplicationContext(), "Location Device Heading Updated" , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {
        //Toast.makeText(getApplicationContext(), "Location Update Failed" , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {
        //Toast.makeText(getApplicationContext(), "Update Cancelled" , Toast.LENGTH_SHORT).show();

    }



    @Override
    public void onMapViewInitialized(MapView mapView) {
        //setMapCenter(37.577074, 126.996864);
        //Toast.makeText(getApplicationContext(), "Start Map" , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
       //Toast.makeText(getApplicationContext(), "Moved to Center" , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {


    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
        //Toast.makeText(getApplicationContext(), "Single Tap" , Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {
        //Toast.makeText(getApplicationContext(), "Double Tap" , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {
       // Toast.makeText(getApplicationContext(), "Long Press" , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
        //Toast.makeText(getApplicationContext(), "Drag Start" , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {
        //Toast.makeText(getApplicationContext(), "Drag End" , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
        //mapView.onSurfaceDestroyed();

    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        pointLat = mapPOIItem.getMapPoint().getMapPointGeoCoord().latitude;
        pointLong = mapPOIItem.getMapPoint().getMapPointGeoCoord().longitude;
        switch(mapPOIItem.getItemName()) {
            case "Jongmyo ShrineI":
                mapView.removePOIItem(mapPOIItem);
                break;
            case "Changgyeonggung PalaceI":
                mapView.removePOIItem(mapPOIItem);
                break;
            case "Changdeokgung PalaceI":
                mapView.removePOIItem(mapPOIItem);
                break;
            case "HeunginjimunI":
                mapView.removePOIItem(mapPOIItem);
                break;
            case "CheonggyecheonI":
                mapView.removePOIItem(mapPOIItem);
                break;
            case "Fish AlleyI":
                mapView.removePOIItem(mapPOIItem);
                break;
            case "Tourist InformationI":
                mapView.removePOIItem(mapPOIItem);
                break;
            case "Gwang Jang MarketI":
                mapView.removePOIItem(mapPOIItem);
                break;
            case "National Museum of Modern and Contemporary ArtI":
                mapView.removePOIItem(mapPOIItem);
                break;
            default:
                break;
        }

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
        //Toast.makeText(getApplicationContext(), "Balloon Selected" , Toast.LENGTH_SHORT).show();
        pointLat = mapPOIItem.getMapPoint().getMapPointGeoCoord().latitude;
        pointLong = mapPOIItem.getMapPoint().getMapPointGeoCoord().longitude;

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
        //Toast.makeText(getApplicationContext(), "Balloon Touched" , Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), mapPOIItem.getItemName() , Toast.LENGTH_SHORT).show();
        pointLat = mapPOIItem.getMapPoint().getMapPointGeoCoord().latitude;
        pointLong = mapPOIItem.getMapPoint().getMapPointGeoCoord().longitude;
        switch(mapPOIItem.getItemName())
        {
            case "Jongmyo Shrine":
                mCustomMarker = new MapPOIItem();
                String name = "Jongmyo ShrineI";
                mCustomMarker.setItemName(name);
                mCustomMarker.setTag(1);
                mCustomMarker.setMapPoint(jong);
                mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                mCustomMarker.setCustomImageResourceId(R.drawable.jong);
                mCustomMarker.setCustomImageAutoscale(false);
                mCustomMarker.setCustomImageAnchor(0.5f, 1.0f);
                mapView.addPOIItem(mCustomMarker);
                mapView.selectPOIItem(mCustomMarker, true);
                break;

            case "Changgyeonggung Palace":
                //Toast.makeText(getApplicationContext(), "Hello" , Toast.LENGTH_SHORT).show();
                mCustomMarker = new MapPOIItem();
                String name1 = "Changgyeonggung PalaceI";
                mCustomMarker.setItemName(name1);
                mCustomMarker.setTag(1);
                mCustomMarker.setMapPoint(changgyeonggung);
                mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                mCustomMarker.setCustomImageResourceId(R.drawable.chang);
                mCustomMarker.setCustomImageAutoscale(false);
                mCustomMarker.setCustomImageAnchor(0.5f, 1.0f);
                mapView.addPOIItem(mCustomMarker);
                mapView.selectPOIItem(mCustomMarker, true);
                break;

            case "Changdeokgung Palace":
                //Toast.makeText(getApplicationContext(), "Hello" , Toast.LENGTH_SHORT).show();
                mCustomMarker = new MapPOIItem();
                String name2 = "Changdeokgung PalaceI";
                mCustomMarker.setItemName(name2);
                mCustomMarker.setTag(1);
                mCustomMarker.setMapPoint(changdeokgung);
                mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                mCustomMarker.setCustomImageResourceId(R.drawable.changd);
                mCustomMarker.setCustomImageAutoscale(false);
                mCustomMarker.setCustomImageAnchor(0.5f, 1.0f);
                mapView.addPOIItem(mCustomMarker);
                mapView.selectPOIItem(mCustomMarker, true);
                break;

            case "National Museum of Modern and Contemporary Art":
                mCustomMarker = new MapPOIItem();
                String name3 = "National Museum of Modern and Contemporary ArtI";
                mCustomMarker.setItemName(name3);
                mCustomMarker.setTag(1);
                mCustomMarker.setMapPoint(national);
                mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                mCustomMarker.setCustomImageResourceId(R.drawable.muse);
                mCustomMarker.setCustomImageAutoscale(false);
                mCustomMarker.setCustomImageAnchor(0.5f, 1.0f);
                mapView.addPOIItem(mCustomMarker);
                mapView.selectPOIItem(mCustomMarker, true);
                break;

            case "Tourist Information":
                mCustomMarker = new MapPOIItem();
                String name4 = "Tourist InformationI";
                mCustomMarker.setItemName(name4);
                mCustomMarker.setTag(1);
                mCustomMarker.setMapPoint(tourist);
                mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                mCustomMarker.setCustomImageResourceId(R.drawable.tourist);
                mCustomMarker.setCustomImageAutoscale(false);
                mCustomMarker.setCustomImageAnchor(0.5f, 1.0f);
                mapView.addPOIItem(mCustomMarker);
                mapView.selectPOIItem(mCustomMarker, true);
                break;

            case "Heunginjimun":
                mCustomMarker = new MapPOIItem();
                String name5 = "HeunginjimunI";
                mCustomMarker.setItemName(name5);
                mCustomMarker.setTag(1);
                mCustomMarker.setMapPoint(hong);
                mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                mCustomMarker.setCustomImageResourceId(R.drawable.hong);
                mCustomMarker.setCustomImageAutoscale(false);
                mCustomMarker.setCustomImageAnchor(0.5f, 1.0f);
                mapView.addPOIItem(mCustomMarker);
                mapView.selectPOIItem(mCustomMarker, true);
                break;
            case "Cheonggyecheon":
                mCustomMarker = new MapPOIItem();
                String name6 = "CheonggyecheonI";
                mCustomMarker.setItemName(name6);
                mCustomMarker.setTag(1);
                mCustomMarker.setMapPoint(chong);
                mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                mCustomMarker.setCustomImageResourceId(R.drawable.chong);
                mCustomMarker.setCustomImageAutoscale(false);
                mCustomMarker.setCustomImageAnchor(0.5f, 1.0f);
                mapView.addPOIItem(mCustomMarker);
                mapView.selectPOIItem(mCustomMarker, true);
                break;
            case "Fish Alley":
                mCustomMarker = new MapPOIItem();
                String name7 = "Fish AlleyI";
                mCustomMarker.setItemName(name7);
                mCustomMarker.setTag(1);
                mCustomMarker.setMapPoint(fish);
                mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                mCustomMarker.setCustomImageResourceId(R.drawable.fish);
                mCustomMarker.setCustomImageAutoscale(false);
                mCustomMarker.setCustomImageAnchor(0.5f, 1.0f);
                mapView.addPOIItem(mCustomMarker);
                mapView.selectPOIItem(mCustomMarker, true);
                break;

            case "Gwang Jang Market":
                mCustomMarker = new MapPOIItem();
                String name8 = "Gwang Jang MarketI";
                mCustomMarker.setItemName(name8);
                mCustomMarker.setTag(1);
                mCustomMarker.setMapPoint(gwangj);
                mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                mCustomMarker.setCustomImageResourceId(R.drawable.gwang);
                mCustomMarker.setCustomImageAutoscale(false);
                mCustomMarker.setCustomImageAnchor(0.5f, 1.0f);
                mapView.addPOIItem(mCustomMarker);
                mapView.selectPOIItem(mCustomMarker, true);
                break;


            default:
                break;

        }



    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {
        //Toast.makeText(getApplicationContext(), "POI Moved" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        //Toast.makeText(getApplicationContext(), "Location Changed" , Toast.LENGTH_SHORT).show();
        userLat = location.getLatitude();
        userLong = location.getLongitude();

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
       // Toast.makeText(getApplicationContext(), "Status Changed" , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderEnabled(String s) {
      //  Toast.makeText(getApplicationContext(), "Enabled" , Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String s) {
       // Toast.makeText(getApplicationContext(), "Disabled" , Toast.LENGTH_SHORT).show();

    }

    private void createCustomMarker(MapView mapView, MapPoint pnt) {
        mCustomMarker = new MapPOIItem();
        String name = "Jongmyo Shrine";
        mCustomMarker.setItemName(name);
        mCustomMarker.setTag(1);
        mCustomMarker.setMapPoint(pnt);
        mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        mCustomMarker.setCustomImageResourceId(R.mipmap.ic_1);
        mCustomMarker.setCustomImageAutoscale(false);
        mCustomMarker.setCustomImageAnchor(0.5f, 1.0f);
        mapView.addPOIItem(mCustomMarker);
        mapView.selectPOIItem(mCustomMarker, true);
    }

    private void createCustomMarker2() {
        mCustomMarker2 = new MapPOIItem();
        String name = "Changgyeonggung Palace";
        mCustomMarker2.setItemName(name);
        mCustomMarker2.setTag(1);
        mCustomMarker2.setMapPoint(changgyeonggung);
        mCustomMarker2.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        mCustomMarker2.setCustomImageResourceId(R.mipmap.ic_1);
        mCustomMarker2.setCustomImageAutoscale(false);
        mCustomMarker2.setCustomImageAnchor(0.5f, 1.0f);
        mapView.addPOIItem(mCustomMarker2);
        mapView.selectPOIItem(mCustomMarker2, true);
    }

    private void createCustomMarker3() {
        mCustomMarker3 = new MapPOIItem();
        String name = "Changdeokgung Palace";
        mCustomMarker3.setItemName(name);
        mCustomMarker3.setTag(1);
        mCustomMarker3.setMapPoint(changdeokgung);
        mCustomMarker3.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        mCustomMarker3.setCustomImageResourceId(R.mipmap.ic_1);
        mCustomMarker3.setCustomImageAutoscale(false);
        mCustomMarker3.setCustomImageAnchor(0.5f, 1.0f);

        mapView.addPOIItem(mCustomMarker3);
        mapView.selectPOIItem(mCustomMarker3, true);
    }


    private void createCustomMarker4() {
        mCustomMarker4 = new MapPOIItem();
        String name = "National Museum of Modern and Contemporary Art";
        mCustomMarker4.setItemName(name);
        mCustomMarker4.setTag(1);
        mCustomMarker4.setMapPoint(national);
        mCustomMarker4.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        mCustomMarker4.setCustomImageResourceId(R.mipmap.ic_1);
        mCustomMarker4.setCustomImageAutoscale(false);
        mCustomMarker4.setCustomImageAnchor(0.5f, 1.0f);
        mapView.addPOIItem(mCustomMarker4);
        mapView.selectPOIItem(mCustomMarker4, true);
    }

    private void plotWonMarker(){
        mapView.removeAllPOIItems();
        mapView.removeAllPolylines();
        woncourse.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);
        button5.setVisibility(View.INVISIBLE);
        l=0;
        mapShow = 1;

        txt = new FragList();
        getSupportFragmentManager().beginTransaction().add(R.id.scroll, txt).commit();

        Bundle bundle = new Bundle();
        bundle.putString("edttext", "Course1");
        // set Fragmentclass Arguments
        txt.setArguments(bundle);

        createCustomMarker(mapView, jong);
        createCustomMarker2();
        createCustomMarker3();
        createCustomMarker4();

        //plot points
        wonMarker = new MapPOIItem();
        wonMarker2 = new MapPOIItem();

        wonMarker.setItemName("Start");
        wonMarker.setMapPoint(wonStart);
        wonMarker.setTag(0);
        wonMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        wonMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        wonMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem(wonMarker);
        mapView.selectPOIItem(wonMarker, true);

        //MapPOIItem wonMarker2 = new MapPOIItem();
        wonMarker2.setItemName("Finish");
        wonMarker2.setMapPoint(wonEnd);
        wonMarker2.setTag(0);
        wonMarker2.setMarkerType(MapPOIItem.MarkerType.BluePin);
        wonMarker2.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        wonMarker2.setCustomImageAutoscale(true);

        mapView.addPOIItem(wonMarker2);
        mapView.selectPOIItem(wonMarker2, true);


        MapPolyline wonLine = new MapPolyline();
        wonLine.setTag(500);
        wonLine.setLineColor(Color.argb(200, 171, 71, 188));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.577074, 126.996864));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.576316, 126.996202));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.577616, 126.992939));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.577327, 126.989323));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.579282, 126.989225));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.579305, 126.986695));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.579092, 126.985129));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.579245, 126.984990));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.579543, 126.982211));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.579849, 126.980280));
        mapView.addPolyline(wonLine);
        MapPointBounds mapPointBounds = new MapPointBounds(wonLine.getMapPoints());

        MapPolyline testLine = new MapPolyline();
        testLine.setTag(500);
        testLine.setLineColor(Color.argb(200, 171, 71, 188));



        testLine.addPoint(MapPoint.mapPointWithGeoCoord(37.575855, 126.976943));
        testLine.addPoint(MapPoint.mapPointWithGeoCoord(37.575976, 126.978857));
        testLine.addPoint(MapPoint.mapPointWithGeoCoord(37.57645, 126.979371));
        testLine.addPoint(MapPoint.mapPointWithGeoCoord(37.57645, 126.979584));
        testLine.addPoint(MapPoint.mapPointWithGeoCoord(37.57602, 126.979572));
        testLine.addPoint(MapPoint.mapPointWithGeoCoord(37.575922, 126.97966));
        testLine.addPoint(MapPoint.mapPointWithGeoCoord(37.575874, 126.980506));

        /*ArrayList<Double> Lat = new ArrayList<Double>(Arrays.asList(37.575855,
                37.575976 ,37.57645 ,37.57645 ,37.57602 ,37.575922, 37.575874,
                37.575645,
                37.575547,
                37.575596,
                37.575751,
                37.576083,
                37.576815,
                37.577173,
                37.577284,
                37.577334,
                37.577365,
                37.577361,
                37.577375,
                37.577478,
                37.577615,
                37.577653,
                37.5776,
                37.576236,
                37.576241,
                37.576316,
                37.57644,
                37.576382,
                37.575976,
                37.575824,
                37.575592,
                37.571274,
                37.570675,
                37.570613,
                37.570526,
                37.570383,
                37.570371,
                37.57016,
                37.570113,
                37.570096,
                37.569066,
                37.569097,
                37.569811,
                37.569951,
                37.570074,
                37.570122,
                37.570144,
                37.570072,
                37.570078,
                37.570264,
                37.570692,
                37.570985,
                37.571617,
                37.572707,
                37.574924,
                37.57519,
                37.575398,
                37.575527,
                37.575855,
                37.575855));
        ArrayList<Double> Long = new ArrayList<Double>(Arrays.asList(126.976943,
                126.978857,
                126.979371,
                126.979584,
                126.979572,
                126.97966,
                126.980506,
                126.982139,
                126.982905,
                126.983181,
                126.983531,
                126.984361,
                126.986081,
                126.986824,
                126.987181,
                126.987521,
                126.987986,
                126.989233,
                126.98975,
                126.990587,
                126.991856,
                126.992738,
                126.99308,
                126.996349,
                126.996507,
                126.996796,
                126.99701,
                126.997107,
                126.99714,
                126.997085,
                126.997302,
                126.997645,
                126.996816,
                126.996823,
                126.995276,
                126.992197,
                126.992038,
                126.987978,
                126.983529,
                126.983021,
                126.982814,
                126.982734,
                126.982825,
                126.982687,
                126.982469,
                126.982257,
                126.977699,
                126.977437,
                126.97728,
                126.977282,
                126.977175,
                126.977167,
                126.977161,
                126.9771,
                126.977001,
                126.977092,
                126.977291,
                126.977128,
                126.97714,
                126.976943));


        for(int i=0; i<Long.size(); i++){
            testLine.addPoint(MapPeoint.mapPointWithCONGCoord(Lat.get(i), Long.get(i)));

        }*/

        mapView.addPolyline(testLine);






        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.569470, 126.987700), true);
        // zoom level
        mapView.setZoomLevel(4, true);

       // mapView.moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds, padding));
    }

    private void plotGanghwa(){

        mapView.removeAllPOIItems();
        mapView.removeAllPolylines();

        woncourse.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);
        button5.setVisibility(View.INVISIBLE);
        l = 1;


        txt = new FragList();
        getSupportFragmentManager().beginTransaction().add(R.id.scroll, txt).commit();

        //plot points
        gwangMarker = new MapPOIItem();
        gwangMarker2 = new MapPOIItem();

        gwangMarker.setItemName("Start");
        gwangMarker.setMapPoint(gwangStart);
        gwangMarker.setTag(0);
        gwangMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        gwangMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        gwangMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem( gwangMarker);
        mapView.selectPOIItem( gwangMarker, true);

        //2nd marker end point
        gwangMarker2.setItemName("Finish");
        gwangMarker2.setMapPoint(gwangEnd);
        gwangMarker2.setTag(0);
        gwangMarker2.setMarkerType(MapPOIItem.MarkerType.BluePin);
        gwangMarker2.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        gwangMarker2.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem( gwangMarker2);
        mapView.selectPOIItem( gwangMarker2, true);
        mapView.addPOIItem(gwangMarker2);


        MapPolyline wonLine = new MapPolyline();
        wonLine.setTag(500);
        wonLine.setLineColor(Color.argb(200, 171, 71, 188));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.575922, 126.976766));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.575931, 126.974333));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.578292, 126.973947));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.578292, 126.973947));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.583130, 126.973711));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.583487, 126.973958));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.583776, 126.976941));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.583410, 126.979183));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.582781, 126.979977));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.582237, 126.980116));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.581115, 126.980127));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.579117, 126.979515));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.576439, 126.979429));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.575954, 126.978957));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.575937, 126.977348));
        mapView.addPolyline(wonLine);
        MapPointBounds mapPointBounds = new MapPointBounds(wonLine.getMapPoints());
        int padding = 100; // px
        mapView.moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds, padding));



        Bundle bundle = new Bundle();
        bundle.putString("edttext", "Course2");
        // set Fragmentclass Arguments
        txt.setArguments(bundle);
    }

    private void plotWorldCup(){

        mapView.removeAllPOIItems();
        mapView.removeAllPolylines();

        woncourse.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);
        button5.setVisibility(View.INVISIBLE);
        l = 1;


        txt = new FragList();
        getSupportFragmentManager().beginTransaction().add(R.id.scroll, txt).commit();

        //plot points
        worldMarker = new MapPOIItem();
        worldMarker2 = new MapPOIItem();

        worldMarker.setItemName("Start");
        worldMarker.setMapPoint(worldStart);
        worldMarker.setTag(2);
        worldMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        worldMarker.setCustomImageResourceId(R.drawable.custom_marker_red);
        worldMarker.setCustomImageAutoscale(false);
        worldMarker.setCustomImageAnchor(0.5f, 1.0f);
        mapView.addPOIItem(worldMarker);

        //2nd marker end point
        worldMarker2.setItemName("Finish");
        worldMarker2.setMapPoint(worldEnd);
        worldMarker2.setTag(2);

        worldMarker2.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        worldMarker2.setCustomImageResourceId(R.drawable.custom_marker_red);
        worldMarker2.setCustomImageAutoscale(false);
        worldMarker2.setCustomImageAnchor(0.5f, 1.0f);


        mapView.addPOIItem(worldMarker2);


        MapPolyline wonLine = new MapPolyline();
        wonLine.setTag(500);
        wonLine.setLineColor(Color.argb(200, 171, 71, 188));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.566460, 126.898292));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.566460, 126.898292));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.566615, 126.896679));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.566798, 126.895949));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.566892, 126.895563));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.567015, 126.895332));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.566628, 126.895096));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.566576, 126.895284));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.566397, 126.895215));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.564591, 126.895160));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.564531, 126.894209));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.565654, 126.892149));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.565382, 126.891988));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.565637, 126.891484));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.565849, 126.891126));

        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.564344, 126.889517));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.563553, 126.889045));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.562805, 126.888337));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.562728, 126.887951));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.562779, 126.887715));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.567711, 126.879261));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.568077, 126.878505));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.567133, 126.877636));
        mapView.addPolyline(wonLine);
        MapPointBounds mapPointBounds = new MapPointBounds(wonLine.getMapPoints());
        int padding = 100; // px
        mapView.moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds, padding));



        Bundle bundle = new Bundle();
        bundle.putString("edttext", "Course5");
        // set Fragmentclass Arguments
        txt.setArguments(bundle);
    }


    private void plotInsadong(){

        mapView.removeAllPOIItems();
        mapView.removeAllPolylines();

        woncourse.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);
        button5.setVisibility(View.INVISIBLE);
        l = 1;


        txt = new FragList();
        getSupportFragmentManager().beginTransaction().add(R.id.scroll, txt).commit();

        //plot points
        insadongMarker = new MapPOIItem();
        insadongMarker2 = new MapPOIItem();

        insadongMarker.setItemName("Start");
        insadongMarker.setMapPoint(insadongStart);
        insadongMarker.setTag(2);
        insadongMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        insadongMarker.setCustomImageResourceId(R.drawable.custom_marker_red);
        insadongMarker.setCustomImageAutoscale(false);
        insadongMarker.setCustomImageAnchor(0.5f, 1.0f);
        mapView.addPOIItem(insadongMarker);

        //2nd marker end point
        insadongMarker2.setItemName("Finish");
        insadongMarker2.setMapPoint(insadongEnd);
        insadongMarker2.setTag(2);

        insadongMarker2.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        insadongMarker2.setCustomImageResourceId(R.drawable.custom_marker_red);
        insadongMarker2.setCustomImageAutoscale(false);
        insadongMarker2.setCustomImageAnchor(0.5f, 1.0f);


        mapView.addPOIItem(insadongMarker2);


        MapPolyline wonLine = new MapPolyline();
        wonLine.setTag(500);
        wonLine.setLineColor(Color.argb(200, 171, 71, 188));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.565912, 126.977405));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.569348, 126.977239));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.569019, 126.982754));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.568738, 126.984428));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.568457, 126.985866));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.568253, 126.986950));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.568278, 126.987465));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.571067, 126.987519));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.572988, 126.987707));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.573456, 126.987546));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.576203, 126.986269));

        mapView.addPolyline(wonLine);
        MapPointBounds mapPointBounds = new MapPointBounds(wonLine.getMapPoints());
        int padding = 100; // px
        mapView.moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds, padding));



        Bundle bundle = new Bundle();
        bundle.putString("edttext", "Course3");
        // set Fragmentclass Arguments
        txt.setArguments(bundle);
    }

    private void plotChongae(){

        mapView.removeAllPOIItems();
        mapView.removeAllPolylines();

        woncourse.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);
        button5.setVisibility(View.INVISIBLE);
        l = 1;

        customMarkerChong1();
        customMarkerChong2();
        customMarkerChong3();
        customMarkerChong4();
        customMarkerChong5();
        txt = new FragList();
        getSupportFragmentManager().beginTransaction().add(R.id.scroll, txt).commit();

        //plot points
        chongaeMarker = new MapPOIItem();
        chongaeMarker2 = new MapPOIItem();

        chongaeMarker.setItemName("Start");
        chongaeMarker.setMapPoint(chongaeStart);
        chongaeMarker.setTag(0);
        chongaeMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        chongaeMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        chongaeMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem( chongaeMarker);
        mapView.selectPOIItem( chongaeMarker, true);

        //2nd marker end point
        chongaeMarker2.setItemName("Finish");
        chongaeMarker2.setMapPoint(chongaeEnd);
        chongaeMarker2.setTag(0);
        chongaeMarker2.setMarkerType(MapPOIItem.MarkerType.BluePin);
        chongaeMarker2.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        chongaeMarker2.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
        mapView.addPOIItem( chongaeMarker2);
        mapView.selectPOIItem( chongaeMarker2, true);


        MapPolyline wonLine = new MapPolyline();
        wonLine.setTag(500);
        wonLine.setLineColor(Color.argb(200, 171, 71, 188));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.571165, 127.009160));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.570451, 127.009595));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.570032, 127.010016));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.569877, 127.010008));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.569749, 127.006812));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.569723, 127.002048));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.568966, 126.997971));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.568685, 126.995525));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.568464, 126.992339));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.568251, 126.989571));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.568208, 126.987779));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.568225, 126.986910));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.568965, 126.982769));
        wonLine.addPoint(MapPoint.mapPointWithGeoCoord(37.569314, 126.977458));

        mapView.addPolyline(wonLine);
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.560931, 126.993330), true);
        // zoom level
        mapView.setZoomLevel(5, true);

        Bundle bundle = new Bundle();
        bundle.putString("edttext", "Course4");
        // set Fragmentclass Arguments
        txt.setArguments(bundle);
    }
    private void customMarkerChong1() {
        mChongMarker1 = new MapPOIItem();
        String name = "Heunginjimun";
        mChongMarker1.setItemName(name);
        mChongMarker1.setTag(1);
        mChongMarker1.setMapPoint(hong);
        mChongMarker1.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        mChongMarker1.setCustomImageResourceId(R.drawable.ic_stat_name);
        mChongMarker1.setCustomImageAutoscale(false);
        mChongMarker1.setCustomImageAnchor(0.5f, 1.0f);
        mapView.addPOIItem(mChongMarker1);
        mapView.selectPOIItem(mChongMarker1, true);
    }
    private void customMarkerChong2() {
        mChongMarker2 = new MapPOIItem();
        String name = "Cheonggyecheon";
        mChongMarker2.setItemName(name);
        mChongMarker2.setTag(1);
        mChongMarker2.setMapPoint(chong);
        mChongMarker2.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        mChongMarker2.setCustomImageResourceId(R.drawable.ic_stat_name);
        mChongMarker2.setCustomImageAutoscale(false);
        mChongMarker2.setCustomImageAnchor(0.5f, 1.0f);
        mapView.addPOIItem(mChongMarker2);
        mapView.selectPOIItem(mChongMarker2, true);
    }
    private void customMarkerChong3() {
        mChongMarker3 = new MapPOIItem();
        String name = "Fish Alley";
        mChongMarker3.setItemName(name);
        mChongMarker3.setTag(1);
        mChongMarker3.setMapPoint(fish);
        mChongMarker3.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        mChongMarker3.setCustomImageResourceId(R.drawable.ic_stat_name);
        mChongMarker3.setCustomImageAutoscale(false);
        mChongMarker3.setCustomImageAnchor(0.5f, 1.0f);
        mapView.addPOIItem(mChongMarker3);
        mapView.selectPOIItem(mChongMarker3, true);
    }
    private void customMarkerChong4() {
        mChongMarker4 = new MapPOIItem();
        String name = "Tourist Information";
        mChongMarker4.setItemName(name);
        mChongMarker4.setTag(1);
        mChongMarker4.setMapPoint(tourist);
        mChongMarker4.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        mChongMarker4.setCustomImageResourceId(R.drawable.ic_stat_name);
        mChongMarker4.setCustomImageAutoscale(false);
        mChongMarker4.setCustomImageAnchor(0.5f, 1.0f);
        mapView.addPOIItem(mChongMarker4);
        mapView.selectPOIItem(mChongMarker4, true);
    }

    private void customMarkerChong5() {
        mChongMarker5 = new MapPOIItem();
        String name = "Gwang Jang Market";
        mChongMarker5.setItemName(name);
        mChongMarker5.setTag(1);
        mChongMarker5.setMapPoint(gwangj);
        mChongMarker5.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        mChongMarker5.setCustomImageResourceId(R.drawable.ic_stat_name);
        mChongMarker5.setCustomImageAutoscale(false);
        mChongMarker5.setCustomImageAnchor(0.5f, 1.0f);
        mapView.addPOIItem(mChongMarker5);
        mapView.selectPOIItem(mChongMarker5, true);
    }
}
