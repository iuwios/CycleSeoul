package com.example.djeon.cycleseoul;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import android.widget.Toast;

import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPointBounds;
import net.daum.mf.map.api.MapPolyline;
import net.daum.mf.map.api.MapView;

public class MainActivity extends AppCompatActivity implements MapView.CurrentLocationEventListener, MapView.MapViewEventListener,MapView.POIItemEventListener, LocationListener {
    private MapPoint wonStart= MapPoint.mapPointWithGeoCoord(37.577074, 126.996864);
    private MapPoint jong= MapPoint.mapPointWithGeoCoord(37.576404, 126.993608);
    private MapPoint wonEnd= MapPoint.mapPointWithGeoCoord(37.579522, 126.980508);
    private MapPoint changgyeonggung= MapPoint.mapPointWithGeoCoord(37.577788, 126.994820);
    private MapPoint changdeokgung= MapPoint.mapPointWithGeoCoord(37.578298, 126.989907);
    private MapPoint national= MapPoint.mapPointWithGeoCoord(37.579267, 126.981302);


    //map view 를 위한 variables
    MapView mapView;
    ViewGroup mapViewContainer;
    MapPOIItem marker,wonMarker,wonMarker2; //중심점 마커

    Button woncourse;
    Button button2;
    Button button3;
    Button button4;
    Button button5;

    MapPOIItem mCustomMarker;
    MapPOIItem mCustomMarker2;
    MapPOIItem mCustomMarker3;
    MapPOIItem mCustomMarker4;

    FragList txt;

    int l;
    int fragc;
    int mapShow;

    // Acquire a reference to the system Location Manager
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButton();

        //code to get hashkey of application
      //String tt = getKeyHash(getApplicationContext());
        // Toast.makeText(getApplicationContext(), "hashkey:"+tt, Toast.LENGTH_SHORT).show();

       // MapView mapView = new MapView(this);
       // ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        //mapViewContainer.addView(mapView);

        //current location
        //mapView.setCurrentLocationEventListener(this);
        //mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);


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

                woncourse.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                button4.setVisibility(View.INVISIBLE);
                button5.setVisibility(View.INVISIBLE);
                l=0;
                mapShow = 1;

                txt = new FragList();
                getSupportFragmentManager().beginTransaction().add(R.id.scroll, txt).commit();

                createCustomMarker(mapView, jong);
                createCustomMarker2();
                createCustomMarker3();
                createCustomMarker4();
                wonMarker = new MapPOIItem();
                wonMarker2 = new MapPOIItem();

                wonMarker.setItemName("Start");
                wonMarker.setMapPoint(wonStart);
                wonMarker.setTag(2);

                //wonMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
                //wonMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
                //wonMarker.setCustomImageAutoscale(true);

                wonMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                wonMarker.setCustomImageResourceId(R.drawable.custom_marker_red);
                wonMarker.setCustomImageAutoscale(false);
                wonMarker.setCustomImageAnchor(0.5f, 1.0f);
                mapView.addPOIItem(wonMarker);

                //MapPOIItem wonMarker2 = new MapPOIItem();
                wonMarker2.setItemName("Finish");
                wonMarker2.setMapPoint(wonEnd);
                wonMarker2.setTag(2);

                wonMarker2.setMarkerType(MapPOIItem.MarkerType.CustomImage);
                wonMarker2.setCustomImageResourceId(R.drawable.custom_marker_red);
                wonMarker2.setCustomImageAutoscale(false);
                wonMarker2.setCustomImageAnchor(0.5f, 1.0f);

                //wonMarker2.setMarkerType(MapPOIItem.MarkerType.BluePin);
                //wonMarker2.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
                //wonMarker2.setCustomImageAutoscale(true);
                mapView.addPOIItem(wonMarker2);

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
                int padding = 100; // px
                mapView.moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds, padding));
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

        //Intent i = getIntent();
        //String route = i.getStringExtra("theRoute");


        /*if(route!=null){
            //Toast.makeText(getApplicationContext(), "Found" , Toast.LENGTH_SHORT).show();
            receiveData(route);
        }
        else
            Toast.makeText(getApplicationContext(), "NULL DATA" , Toast.LENGTH_SHORT).show();*/


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

        mapView.setCurrentLocationEventListener(this);
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving);

        //이벤트리스너 등록
       // mapView.setMapViewEventListener(this); // this 에 MapView.MapViewEventListener 구현.
       // mapView.setPOIItemEventListener(this);


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
                case R.id.map_find:

                    break;
                default:

                    break;
            }

            return true;
        }
    };


    public void openFragment()
    {
        FragList listFragment = new FragList();
        getSupportFragmentManager().beginTransaction().replace(R.id.scroll, listFragment).commit();



    }
//data received from FragList
    public void receiveData(String rname){

        switch(rname) {

            case "WonReady":
                Toast.makeText(getApplicationContext(), "Hello:"+rname, Toast.LENGTH_SHORT).show();

                break;

            default:
                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_SHORT).show();

                break;

        }




    }
    public void setMapCenter(double latitude, double longitude) {
        // 중심점 변경
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true); // 대구광역시
        // 줌 레벨 변경
        mapView.setZoomLevel(1, true); // level 낮을 수록 확대

        // 줌 인
        mapView.zoomIn(false);

        // 줌 아웃
        mapView.zoomOut(false);

        // 중심점에 Marker 로 표시해줍니다
        addCenterMarker(latitude, longitude);
    }

    public void addCenterMarker(double latitude, double longitude) {

        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("현재위치");
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker);


    }



//Current Location Listener
    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {
        //MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {

    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {

    }



    @Override
    public void onMapViewInitialized(MapView mapView) {
        //setMapCenter(37.577074, 126.996864);

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {
        if(mapShow==1){
            mapView.deselectPOIItem(mCustomMarker);
            mapView.deselectPOIItem(mCustomMarker2);
            mapView.deselectPOIItem(mCustomMarker3);
            mapView.deselectPOIItem(mCustomMarker4);
            mapShow=0;
        }

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {



    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {
        if(mapShow==1){
            mapView.deselectPOIItem(mCustomMarker);
            mapView.deselectPOIItem(mCustomMarker2);
            mapView.deselectPOIItem(mCustomMarker3);
            mapView.deselectPOIItem(mCustomMarker4);
            mapShow=0;
        }


    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
        mapView.onSurfaceDestroyed();
    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private void createCustomMarker(MapView mapView, MapPoint pnt) {
        mCustomMarker = new MapPOIItem();
        String name = "Jongmyo Shrine";
        mCustomMarker.setItemName(name);
        mCustomMarker.setTag(1);
        mCustomMarker.setMapPoint(pnt);
        //mCustomMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);

        mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);

        mCustomMarker.setCustomImageResourceId(R.drawable.jongmyo);
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
        //mCustomMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);

        mCustomMarker2.setMarkerType(MapPOIItem.MarkerType.CustomImage);

        mCustomMarker2.setCustomImageResourceId(R.drawable.c);
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
        //mCustomMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);

        mCustomMarker3.setMarkerType(MapPOIItem.MarkerType.CustomImage);

        mCustomMarker3.setCustomImageResourceId(R.drawable.changd);
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
        //mCustomMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);

        mCustomMarker4.setMarkerType(MapPOIItem.MarkerType.CustomImage);

        mCustomMarker4.setCustomImageResourceId(R.drawable.museum);
        mCustomMarker4.setCustomImageAutoscale(false);
        mCustomMarker4.setCustomImageAnchor(0.5f, 1.0f);

        mapView.addPOIItem(mCustomMarker4);
        mapView.selectPOIItem(mCustomMarker4, true);
    }
}
