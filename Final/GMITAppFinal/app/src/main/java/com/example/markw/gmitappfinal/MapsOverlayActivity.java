package com.example.markw.gmitappfinal;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;
import com.indooratlas.android.sdk.IARegion;
import com.indooratlas.android.sdk.resources.IAFloorPlan;
import com.indooratlas.android.sdk.resources.IALatLng;
import com.indooratlas.android.sdk.resources.IALocationListenerSupport;
import com.indooratlas.android.sdk.resources.IAResourceManager;
import com.indooratlas.android.sdk.resources.IAResult;
import com.indooratlas.android.sdk.resources.IAResultCallback;
import com.indooratlas.android.sdk.resources.IATask;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import android.widget.AdapterView.OnItemSelectedListener;




import static android.graphics.Color.BLUE;

public class MapsOverlayActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener,
        OnItemSelectedListener {

    private static final String TAG = "IndoorAtlasExample";

    private static final float HUE_IABLUE = 200.0f;

    /* used to decide when bitmap should be downscaled */
    private static final int MAX_DIMENSION = 2048;

    // private IARegion.Listener FloorPlan = new IARegion.Listener();

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Marker mMarker;
    private IARegion mOverlayFloorPlan = null;
    private GroundOverlay mGroundOverlay = null;
    private IALocationManager mIALocationManager;
    private IAResourceManager mResourceManager;
    private IATask<IAFloorPlan> mFetchFloorPlanTask;
    private Target mLoadTarget;
    //Set this as a global variable
    private String floor;


    private boolean mCameraPositionNeedsUpdating = true; // update on first location

    //PollyLines Map One
    private static final LatLng ONE = new LatLng(53.27760409, -9.00980660);
    private static final LatLng TWO = new LatLng(53.27772137, -9.00995394);
    private static final LatLng THREE = new LatLng(53.27774918, -9.01017463);
    private static final LatLng FOUR = new LatLng(53.27777868, -9.01040712);
    private static final LatLng FIVE = new LatLng(53.27777254, -9.01062494);

    //PollyLines Map Two
    private static final LatLng SIX = new LatLng(53.27793764, -9.01134789);
    private static final LatLng SEVEN = new LatLng(53.27794726, -9.01125267);
    private static final LatLng EIGHT = new LatLng(53.27788792, -9.01105821);
    private static final LatLng NINE = new LatLng(53.27784061, -9.01095897);
    private static final LatLng TEN = new LatLng(53.27781655, -9.01073501);
    private static final LatLng ELEVEN = new LatLng(53.27785023, -9.01070684);
    private static final LatLng TWELEVE = new LatLng(53.27815976, -9.01057541);

    //PollyLines Map Three
    private static final LatLng THIRTEEN = new LatLng(53.27816778, -9.01056468);
    private static final LatLng FOURTEEN = new LatLng(53.27843962, -9.01043192);
    private static final LatLng FIFTEEN = new LatLng(53.27882372, -9.01031390);
    private static final LatLng SIXTEEN = new LatLng(53.27893919, -9.01022270);

    //PollyLine Map Four
    private static final LatLng SEVENTEEN = new LatLng(53.27899612, -9.01024148);
    private static final LatLng EIGHTEEN = new LatLng(53.27896298, -9.01027071);
    private static final LatLng NINETEEN = new LatLng(53.27876037, -9.01038229);
    private static final LatLng TWENTY = new LatLng(53.27866893, -9.01063599);


    //PolyLine Style
    private static final int POLYLINE_STROKE_WIDTH_PX = 12;

    //MarkersLGF
    private static final LatLng WHEELCHAIRACCESSLGF = new LatLng(53.277953, -9.010724);
    private static final LatLng ITCenter = new LatLng(53.27774918, -9.01017463);

    //MarkersUF
    private static final LatLng VENDINGMACHINEULF = new LatLng(53.27785424, -9.01104882);
    private static final LatLng ELEVATORULF = new LatLng(53.27783540, -9.01061464);

    //MarkersOUF
    private static final LatLng TOILETS1 = new LatLng(53.27836104, -9.01041582);
    private static final LatLng DANDAOFFICE = new LatLng(53.27916211, -9.01014224);

    //MarkersOGF
    private static final LatLng CANTEENENTRANCE = new LatLng(53.27876037, -9.01038229);
    private static final LatLng TOILETS2 = new LatLng(53.27858797, -9.01034340);
    private static final LatLng CANTEEN = new LatLng(53.27866893, -9.01063599);
    private static final LatLng BOIATM = new LatLng(53.27872594, -9.01054416);
    private static final LatLng AIBATM = new LatLng(53.27862065, -9.01072075);
    private static final LatLng STUDENTSUNION = new LatLng(53.27895949, -9.01007113);
    private static final LatLng AIBBANK = new LatLng(53.27891007, -9.01006205);
    private static final LatLng BANKOFIRELAND = new LatLng(53.27890235, -9.00999912);
    private static final LatLng NURSESOFFICE = new LatLng(53.27894008, -9.00998625);
    private static final LatLng WHEELCHAIRENTRANCE2 = new LatLng(53.27917380, -9.00994749);
    private static final LatLng BARBERISTA = new LatLng(53.27888998, -9.01039259);
    private static final LatLng WHEELCHAIRENTRANCE3 = new LatLng(53.27895853, -9.01194393);
    private static final LatLng JOHNFARELLSOFFICE = new LatLng(53.27882132, -9.01179582);
    private static final LatLng SHOP = new LatLng(53.27914885, -9.01009940);

    //ROOM 156
    private static final LatLng ROOM156_3 = new LatLng(53.27916684, -9.01072971);
    private static final LatLng ROOM156_2 = new LatLng(53.27911480, -9.01021063);
    private static final LatLng ROOM156_1 = new LatLng(53.27876037, -9.01038229);
    private Polyline mPolyline156;
    private Marker mRoom156;


    //ROOM 375
    private static final LatLng ROOM375_2 = new LatLng(53.27885390, -9.01093345);
    private static final LatLng ROOM375_1 = new LatLng(53.27876037, -9.01038229);
    private Polyline mPolyline375;
    private Marker mRoom375;

    //ROOM 556
    private static final LatLng ROOM556_1 = new LatLng(53.27876037, -9.01038229);
    private static final LatLng ROOM556_2 = new LatLng(53.27883735, -9.01097104);
    private static final LatLng ROOM556_3 = new LatLng(53.27866415, -9.01103273);
    private static final LatLng ROOM556_4 = new LatLng(53.27869806, -9.01133898);
    private static final LatLng ROOM556_5 = new LatLng(53.27863529, -9.01134128);
    private Polyline mPolyline556;
    private Marker mRoom556;


    private Marker mNursesOffice;
    private Marker mStudentsUnion;
    private Marker mLecturesJ;
    private Marker mLecturesD;
    private Marker mElevator;
    private Marker mCanteen1;
    private Marker mCanteen2;
    private Marker mBanks1;
    private Marker mBanks2;
    private Marker mBanks3;
    private Marker mBanks4;
    private Marker mToilets1;
    private Marker mToilets2;
    private Marker mWheelChair1;
    private Marker mWheelChair2;
    private Marker mWheelChair3;
    private Marker mITCenter;
    private Marker mVendingMachine;
    private Marker mBarberista;
    private Marker mShop;

    private Marker mLastSelectedMarker;

    private EditText txtText;


    private final Random mRandom = new Random();

    /*
     * Listener that handles location change events.
     */
    private IALocationListener mListener = new IALocationListenerSupport() {

        /**
         * Location changed, move marker and camera position.
         */
        @Override
        public void onLocationChanged(IALocation location) {

            /* double Latitude = location.getLatitude();
            double Longitude = location.getLongitude();
            double Attitude = location.getAltitude(); */

            int FloorLevel = location.getFloorLevel();

            Log.d(TAG, "new location received with coordinates: " + location.getLatitude()
                    + "," + location.getLongitude() + "," + location.getAltitude() + "," + location.getFloorLevel());

            showInfo("You are on floor level " +  FloorLevel);

            /*
            if (Latitude >= 53.27788790 && Latitude <= 53.27788800 && Longitude <= -9.01105820 && Longitude >= -9.01105830 && FloorLevel == 1  ){

                Toast.makeText(getApplicationContext(), "Go through the doors and continue straight", Toast.LENGTH_LONG).show();
            }
            else if (Latitude >= 53.27781650 && Latitude <= 53.27781660 && Longitude <= -9.01073500 && Longitude >= -9.01073510 && FloorLevel == 1  ){

                Toast.makeText(getApplicationContext(), "Turn Left", Toast.LENGTH_LONG).show();
            }
            else if (Latitude >= 53.27816770 && Latitude <= 53.27816780 && Longitude <= -9.01056460 && Longitude >= -9.01056470 && FloorLevel == 1  ){

                Toast.makeText(getApplicationContext(), "Continue forward", Toast.LENGTH_LONG).show();
            }
            else if (Latitude >= 53.27865610 && Latitude <= 53.27865620 && Longitude <= -9.01036620 && Longitude >= -9.01036630 && FloorLevel == 1  ){

                Toast.makeText(getApplicationContext(), "Continue forward", Toast.LENGTH_LONG).show();
            }
            else if (Latitude >= 53.27887900 && Latitude <= 53.2788791 && Longitude <= -9.01026690 && Longitude >= -9.01026700 && FloorLevel == 1  ){

                Toast.makeText(getApplicationContext(), "Go down the stairs", Toast.LENGTH_LONG).show();
            }
            else if (Latitude >= 53.27899610 && Latitude <= 53.2788791 && Longitude <= -9.01024140 && Longitude >= -9.01024150 && FloorLevel == 0  ){

                Toast.makeText(getApplicationContext(), "Go down the stairs", Toast.LENGTH_LONG).show();
            }
            else if (Latitude >= 53.27876030 && Latitude <= 53.2787604 && Longitude <= -9.01038220 && Longitude >= -9.01038240 && FloorLevel == 0  ){

                Toast.makeText(getApplicationContext(), "You have arrived at the canteen", Toast.LENGTH_LONG).show();
            }
                */
            if (mMap == null) {
                // location received before map is initialized, ignoring update here
                return;
            }

            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

            if (mMarker == null) {
                // first location, add marker
                mMarker = mMap.addMarker(new MarkerOptions().position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(HUE_IABLUE)));
            } else {
                // move existing markers position to received location
                mMarker.setPosition(latLng);
            }

            // our camera position needs updating if location has significantly changed
            if (mCameraPositionNeedsUpdating) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.5f));
                mCameraPositionNeedsUpdating = false;
            }
        }
    };



    /**
     * Listener that changes overlay if needed
     */
    private IARegion.Listener mRegionListener = new IARegion.Listener() {

        @Override
        public void onEnterRegion(IARegion region) {

            //public String FloorPlanId = region.getId();

            if (region.getType() == IARegion.TYPE_FLOOR_PLAN) {
                final String newId = region.getId();
                // Are we entering a new floor plan or coming back the floor plan we just left?
                if (mGroundOverlay == null || !region.equals(mOverlayFloorPlan)) {
                    mCameraPositionNeedsUpdating = true; // entering new fp, need to move camera
                    if (mGroundOverlay != null) {
                        mGroundOverlay.remove();
                        mGroundOverlay = null;
                    }
                    mOverlayFloorPlan = region; // overlay will be this (unless error in loading)
                    fetchFloorPlan(newId);
                } else {
                    mGroundOverlay.setTransparency(0.0f);
                }
            }

        }

        @Override
        public void onExitRegion(IARegion region) {
            if (mGroundOverlay != null) {
                // Indicate we left this floor plan but leave it there for reference
                // If we enter another floor plan, this one will be removed and another one loaded
                mGroundOverlay.setTransparency(0.5f);
            }
            showInfo("Enter " + (region.getType() == IARegion.TYPE_VENUE
                    ? "VENUE "
                    : "FLOOR_PLAN ") + region.getId());
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // prevent the screen going to sleep while app is on foreground
        findViewById(android.R.id.content).setKeepScreenOn(true);

        // instantiate IALocationManager and IAResourceManager
        mIALocationManager = IALocationManager.create(this);
        mResourceManager = IAResourceManager.create(this);

        // Spinner element
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);

        // Spinner click listener
        spinner1.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> Selection = new ArrayList<>();
        Selection.add("Select an option:");
        Selection.add("Lectures Offices");
        Selection.add("Bank Services");
        Selection.add("Toilets");
        Selection.add("Entrances(W.C)");
        Selection.add("Show all Markers");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Selection);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);

        // Spinner element 2
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        // Spinner click listener
        spinner2.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> Room = new ArrayList<>();
        Room.add("Select a room:");
        Room.add("Room 156");
        Room.add("Room 375");
        Room.add("Room 556");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Room);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);


        txtText = (EditText) findViewById(R.id.txtText);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // remember to clean up after ourselves
        mIALocationManager.destroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        }

        // start receiving location updates & monitor region changes
        mIALocationManager.requestLocationUpdates(IALocationRequest.create(), mListener);
        mIALocationManager.registerRegionListener(mRegionListener);
    }


    @Override
    protected void onPause() {
        super.onPause();
        // unregister location & region changes
        mIALocationManager.removeLocationUpdates(mListener);
        mIALocationManager.registerRegionListener(mRegionListener);
    }


    /**
     * Sets bitmap of floor plan as ground overlay on Google Maps
     */
    private void setupGroundOverlay(IAFloorPlan floorPlan, Bitmap bitmap) {

        if (mGroundOverlay != null) {
            mGroundOverlay.remove();
        }

        if (mMap != null) {
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
            IALatLng iaLatLng = floorPlan.getCenter();
            LatLng center = new LatLng(iaLatLng.latitude, iaLatLng.longitude);
            GroundOverlayOptions fpOverlay = new GroundOverlayOptions()
                    .image(bitmapDescriptor)
                    .position(center, floorPlan.getWidthMeters(), floorPlan.getHeightMeters())
                    .bearing(floorPlan.getBearing());

            mGroundOverlay = mMap.addGroundOverlay(fpOverlay);
        }
    }

    /**
     * Download floor plan using Picasso library.
     */
    private void fetchFloorPlanBitmap(final IAFloorPlan floorPlan) {

        final String url = floorPlan.getUrl();

        if (mLoadTarget == null) {
            mLoadTarget = new Target() {

                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    Log.d(TAG, "onBitmap loaded with dimensions: " + bitmap.getWidth() + "x"
                            + bitmap.getHeight());
                    setupGroundOverlay(floorPlan, bitmap);
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                    // N/A
                }

                @Override
                public void onBitmapFailed(Drawable placeHolderDraweble) {
                    showInfo("Failed to load bitmap");
                    mOverlayFloorPlan = null;
                }
            };
        }

        RequestCreator request = Picasso.with(this).load(url);

        final int bitmapWidth = floorPlan.getBitmapWidth();
        final int bitmapHeight = floorPlan.getBitmapHeight();

        if (bitmapHeight > MAX_DIMENSION) {
            request.resize(0, MAX_DIMENSION);
        } else if (bitmapWidth > MAX_DIMENSION) {
            request.resize(MAX_DIMENSION, 0);
        }

        request.into(mLoadTarget);
    }

    /**
     * Fetches floor plan data from IndoorAtlas server.
     */
    private void fetchFloorPlan(String id) {

        // if there is already running task, cancel it
        cancelPendingNetworkCalls();

        final IATask<IAFloorPlan> task = mResourceManager.fetchFloorPlanWithId(id);

        this.floor = id;

        //Log.d(TAG, "Check on the Plan id " + this.floor);

        task.setCallback(new IAResultCallback<IAFloorPlan>() {

            @Override
            public void onResult(IAResult<IAFloorPlan> result) {

                if (result.isSuccess() && result.getResult() != null) {
                    // retrieve bitmap for this floor plan metadata
                    fetchFloorPlanBitmap(result.getResult());
                } else {
                    // ignore errors if this task was already canceled
                    if (!task.isCancelled()) {
                        // do something with error
                        showInfo("Loading floor plan failed: " + result.getError());
                        mOverlayFloorPlan = null;
                    }
                }
            }
        }, Looper.getMainLooper()); // deliver callbacks using main looper

        // keep reference to task so that it can be canceled if needed
        mFetchFloorPlanTask = task;

    }

    /**
     * Helper method to cancel current task if any.
     */
    private void cancelPendingNetworkCalls() {
        if (mFetchFloorPlanTask != null && !mFetchFloorPlanTask.isCancelled()) {
            mFetchFloorPlanTask.cancel();
        }
    }

    private void showInfo(String text) {
        final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), text,
                Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.button_close, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }


    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        addMarkersToMapLGF();
        addMarkersToMapULF();
        addMarkersToMapOUF();
        addMarkersToMapOLF();
        addRoomPolylines();
        addRoomMarkers();

        hideMarkers();

    }


    public void addPloyLines(View view)
    {

        Log.d(TAG, "Check on the Plan id " + this.floor);

        if (this.floor.equals("96f2e4ac-c03c-4c62-8be6-f86786be65de")) {
            //Frst Map
            Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
                    .add(ONE, TWO, THREE, FOUR, FIVE));

            // Style the polyline.
            stylePolyline(polyline1);

            // Add lots of markers to the map.
            addMarkersToMapLGF();

            // Set listeners for marker events.  See the bottom of this class for their behavior.
            mMap.setOnMarkerClickListener(this);


            // Override the default content description on the view, for accessibility mode.
            // Ideally this string would be localised.
            mMap.setContentDescription("Map with lots of markers.");
        }

        else if(this.floor.equals("01887f36-e050-4033-9b48-31bbc4e6d418")) {
            //Second Map
            Polyline polyline2 = mMap.addPolyline(new PolylineOptions()
                    .add(SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN, TWELEVE));

            //Second Map
            stylePolyline(polyline2);

            // Add lots of markers to the map.
            addMarkersToMapULF();

            // Set listeners for marker events.  See the bottom of this class for their behavior.
            mMap.setOnMarkerClickListener(this);


            // Override the default content description on the view, for accessibility mode.
            // Ideally this string would be localised.
            mMap.setContentDescription("Map with lots of markers.");


        }
        else if(this.floor.equals("61802306-d2d4-4812-ab3c-7202a471472e")) {
            //Third
            Polyline polyline3 = mMap.addPolyline(new PolylineOptions()
                    .add(THIRTEEN, FOURTEEN, FIFTEEN, SIXTEEN));

            //Third Map
            stylePolyline(polyline3);

            // Add lots of markers to the map.
            addMarkersToMapOUF();

            // Set listeners for marker events.  See the bottom of this class for their behavior.
            mMap.setOnMarkerClickListener(this);


            // Override the default content description on the view, for accessibility mode.
            // Ideally this string would be localised.
            mMap.setContentDescription("Map with lots of markers.");

        }

        else if(this.floor.equals("f049abb7-b057-4cb1-a917-5663ef5bd445")) {
            //Fourth Map
            Polyline polyline4 = mMap.addPolyline(new PolylineOptions()
                    .add(SEVENTEEN, EIGHTEEN, NINETEEN, TWENTY));

            //Fourth Map
            stylePolyline(polyline4);

            // Add lots of markers to the map.
            addMarkersToMapOLF();

            // Set listeners for marker events.  See the bottom of this class for their behavior.
            mMap.setOnMarkerClickListener(this);


            // Override the default content description on the view, for accessibility mode.
            // Ideally this string would be localised.
            mMap.setContentDescription("Map with lots of markers.");

        }
        else
        {
            Toast.makeText(this, "Unable to make polyline", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        if (item.equals("Lectures Offices")){

            mNursesOffice.setVisible(false);
            mStudentsUnion.setVisible(false);
            mElevator.setVisible(false);
            mCanteen1.setVisible(false);
            mCanteen2.setVisible(false);
            mWheelChair1.setVisible(false);
            mWheelChair2.setVisible(false);
            mWheelChair3.setVisible(false);
            mITCenter.setVisible(false);
            mVendingMachine.setVisible(false);
            mBarberista.setVisible(false);
            mShop.setVisible(false);
            mBanks1.setVisible(false);
            mBanks2.setVisible(false);
            mBanks3.setVisible(false);
            mBanks4.setVisible(false);
            mToilets1.setVisible(false);
            mToilets2.setVisible(false);
            mRoom156.setVisible(false);
            mRoom375.setVisible(false);
            mRoom556.setVisible(false);
            mPolyline156.setVisible(false);
            mPolyline375.setVisible(false);
            mPolyline556.setVisible(false);

            // Add Lectures Office to the map.
            //addMarkersLecturesOffice();
            mLecturesJ.setVisible(true);
            mLecturesD.setVisible(true);

        }
        else if (item.equals("Bank Services")){

            mNursesOffice.setVisible(false);
            mStudentsUnion.setVisible(false);
            mElevator.setVisible(false);
            mCanteen1.setVisible(false);
            mCanteen2.setVisible(false);
            mWheelChair1.setVisible(false);
            mWheelChair2.setVisible(false);
            mWheelChair3.setVisible(false);
            mITCenter.setVisible(false);
            mVendingMachine.setVisible(false);
            mBarberista.setVisible(false);
            mShop.setVisible(false);
            mLecturesJ.setVisible(false);
            mLecturesD.setVisible(false);
            mToilets1.setVisible(false);
            mToilets2.setVisible(false);
            mRoom156.setVisible(false);
            mRoom375.setVisible(false);
            mRoom556.setVisible(false);
            mPolyline156.setVisible(false);
            mPolyline375.setVisible(false);
            mPolyline556.setVisible(false);

            // Add lots of markers to the map.
            //addMarkersBanks();
            mBanks1.setVisible(true);
            mBanks2.setVisible(true);
            mBanks3.setVisible(true);
            mBanks4.setVisible(true);
        }
        else if (item.equals("Toilets")){

            mNursesOffice.setVisible(false);
            mStudentsUnion.setVisible(false);
            mElevator.setVisible(false);
            mCanteen1.setVisible(false);
            mCanteen2.setVisible(false);
            mWheelChair1.setVisible(false);
            mWheelChair2.setVisible(false);
            mWheelChair3.setVisible(false);
            mITCenter.setVisible(false);
            mVendingMachine.setVisible(false);
            mBarberista.setVisible(false);
            mShop.setVisible(false);
            mLecturesJ.setVisible(false);
            mLecturesD.setVisible(false);
            mBanks1.setVisible(false);
            mBanks2.setVisible(false);
            mBanks3.setVisible(false);
            mBanks4.setVisible(false);
            mRoom156.setVisible(false);
            mRoom375.setVisible(false);
            mRoom556.setVisible(false);
            mPolyline156.setVisible(false);
            mPolyline375.setVisible(false);
            mPolyline556.setVisible(false);

            // Add Toilet markers to the map.
            //addMarkersToilets();
            mToilets1.setVisible(true);
            mToilets2.setVisible(true);

        }
        else if (item.equals("Entrances(W.C)")){

            mToilets1.setVisible(false);
            mToilets2.setVisible(false);
            mNursesOffice.setVisible(false);
            mStudentsUnion.setVisible(false);
            mElevator.setVisible(false);
            mCanteen1.setVisible(false);
            mCanteen2.setVisible(false);
            mITCenter.setVisible(false);
            mVendingMachine.setVisible(false);
            mBarberista.setVisible(false);
            mShop.setVisible(false);
            mLecturesJ.setVisible(false);
            mLecturesD.setVisible(false);
            mBanks1.setVisible(false);
            mBanks2.setVisible(false);
            mBanks3.setVisible(false);
            mBanks4.setVisible(false);
            mRoom156.setVisible(false);
            mRoom375.setVisible(false);
            mRoom556.setVisible(false);
            mPolyline156.setVisible(false);
            mPolyline375.setVisible(false);
            mPolyline556.setVisible(false);

            // Add lots of markers to the map.
            //addMarkersWheelChairAccess();
            mWheelChair1.setVisible(true);
            mWheelChair2.setVisible(true);
            mWheelChair3.setVisible(true);


        }
        else if (item.equals("Show all Markers")){

            mNursesOffice.setVisible(true);
            mStudentsUnion.setVisible(true);
            mElevator.setVisible(true);
            mCanteen1.setVisible(true);
            mCanteen2.setVisible(false);
            mWheelChair1.setVisible(true);
            mWheelChair2.setVisible(true);
            mWheelChair3.setVisible(true);
            mITCenter.setVisible(true);
            mVendingMachine.setVisible(true);
            mBarberista.setVisible(true);
            mShop.setVisible(true);
            mBanks1.setVisible(true);
            mBanks2.setVisible(true);
            mBanks3.setVisible(true);
            mBanks4.setVisible(true);
            mToilets1.setVisible(true);
            mToilets2.setVisible(true);
            mLecturesD.setVisible(true);
            mLecturesJ.setVisible(true);


        }
        else if (item.equals("Room 156")){

            mPolyline156.setVisible(true);
            mRoom156.setVisible(true);

            mPolyline375.setVisible(false);
            mPolyline556.setVisible(false);
            mRoom375.setVisible(false);
            mRoom556.setVisible(false);
            mNursesOffice.setVisible(false);
            mStudentsUnion.setVisible(false);
            mElevator.setVisible(false);
            mCanteen1.setVisible(false);
            mCanteen2.setVisible(false);
            mWheelChair1.setVisible(false);
            mWheelChair2.setVisible(false);
            mWheelChair3.setVisible(false);
            mITCenter.setVisible(false);
            mVendingMachine.setVisible(false);
            mBarberista.setVisible(false);
            mShop.setVisible(false);
            mBanks1.setVisible(false);
            mBanks2.setVisible(false);
            mBanks3.setVisible(false);
            mBanks4.setVisible(false);
            mToilets1.setVisible(false);
            mToilets2.setVisible(false);
            mLecturesD.setVisible(false);
            mLecturesJ.setVisible(false);


        }
        else if (item.equals("Room 375")){

            mPolyline375.setVisible(true);
            mRoom375.setVisible(true);

            mPolyline156.setVisible(false);
            mPolyline556.setVisible(false);
            mRoom156.setVisible(false);
            mRoom556.setVisible(false);
            mNursesOffice.setVisible(false);
            mStudentsUnion.setVisible(false);
            mElevator.setVisible(false);
            mCanteen1.setVisible(false);
            mCanteen2.setVisible(false);
            mWheelChair1.setVisible(false);
            mWheelChair2.setVisible(false);
            mWheelChair3.setVisible(false);
            mITCenter.setVisible(false);
            mVendingMachine.setVisible(false);
            mBarberista.setVisible(false);
            mShop.setVisible(false);
            mBanks1.setVisible(false);
            mBanks2.setVisible(false);
            mBanks3.setVisible(false);
            mBanks4.setVisible(false);
            mToilets1.setVisible(false);
            mToilets2.setVisible(false);
            mLecturesD.setVisible(false);
            mLecturesJ.setVisible(false);

        }
        else if (item.equals("Room 556")){


            mPolyline556.setVisible(true);
            mRoom556.setVisible(true);

            mPolyline156.setVisible(false);
            mPolyline375.setVisible(false);
            mRoom156.setVisible(false);
            mRoom375.setVisible(false);
            mNursesOffice.setVisible(false);
            mStudentsUnion.setVisible(false);
            mElevator.setVisible(false);
            mCanteen1.setVisible(false);
            mCanteen2.setVisible(false);
            mWheelChair1.setVisible(false);
            mWheelChair2.setVisible(false);
            mWheelChair3.setVisible(false);
            mITCenter.setVisible(false);
            mVendingMachine.setVisible(false);
            mBarberista.setVisible(false);
            mShop.setVisible(false);
            mBanks1.setVisible(false);
            mBanks2.setVisible(false);
            mBanks3.setVisible(false);
            mBanks4.setVisible(false);
            mToilets1.setVisible(false);
            mToilets2.setVisible(false);
            mLecturesD.setVisible(false);
            mLecturesJ.setVisible(false);


        }

    }

    public void addRoomPolylines(){

        //156
        Polyline polylineRoom156 = mMap.addPolyline(new PolylineOptions()
                .add(ROOM156_1, ROOM156_2, ROOM156_3));

        stylePolyline(polylineRoom156);

        this.mPolyline156 = polylineRoom156;


        //375
        Polyline polylineRoom375 = mMap.addPolyline(new PolylineOptions()
                .add(ROOM375_1, ROOM375_2));

        stylePolyline(polylineRoom375);

        this.mPolyline375 = polylineRoom375;


        //556
        Polyline polylineRoom556 = mMap.addPolyline(new PolylineOptions()
                .add(ROOM556_1, ROOM556_2,ROOM556_3,ROOM556_4,ROOM556_5));

        stylePolyline(polylineRoom556);

        this.mPolyline556 = polylineRoom556;

    }

    public void addRoomMarkers(){

        mRoom156 = mMap.addMarker(new MarkerOptions()
                .position(ROOM156_3)
                .title("Room 156")
                .snippet("Floor Level 0")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mRoom375 = mMap.addMarker(new MarkerOptions()
                .position(ROOM375_2)
                .title("Room 375")
                .snippet("Floor Level 0")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mRoom556 = mMap.addMarker(new MarkerOptions()
                .position(ROOM556_5)
                .title("Floor Level 0")
                .snippet("Located Here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
    }

    public void hideMarkers(){

        mNursesOffice.setVisible(false);
        mStudentsUnion.setVisible(false);
        mElevator.setVisible(false);
        mCanteen1.setVisible(false);
        mCanteen2.setVisible(false);
        mWheelChair1.setVisible(false);
        mWheelChair2.setVisible(false);
        mWheelChair3.setVisible(false);
        mITCenter.setVisible(false);
        mVendingMachine.setVisible(false);
        mBarberista.setVisible(false);
        mShop.setVisible(false);
        mLecturesJ.setVisible(false);
        mLecturesD.setVisible(false);
        mBanks1.setVisible(false);
        mBanks2.setVisible(false);
        mBanks3.setVisible(false);
        mBanks4.setVisible(false);
        mToilets1.setVisible(false);
        mToilets2.setVisible(false);
        mRoom156.setVisible(false);
        mRoom375.setVisible(false);
        mRoom556.setVisible(false);
        mPolyline156.setVisible(false);
        mPolyline375.setVisible(false);
        mPolyline556.setVisible(false);

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    private void stylePolyline(Polyline polyline1) {

        polyline1.setStartCap(new RoundCap());
        polyline1.setEndCap(new RoundCap());
        polyline1.setWidth(POLYLINE_STROKE_WIDTH_PX);
        polyline1.setColor(BLUE);
        polyline1.setJointType(JointType.ROUND);
    }

    //MARKERS IT CENTER
    private void addMarkersToMapLGF() {
        // Uses a colored icon.
        //First Map
        mWheelChair1 = mMap.addMarker(new MarkerOptions()
                .position(WHEELCHAIRACCESSLGF)
                .title("Wheel Chair Access")
                .snippet("Floor Level -1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //ITCenter
        mITCenter = mMap.addMarker(new MarkerOptions()
                .position(ITCenter)
                .title("IT Center")
                .snippet("Floor Level -1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

    }
    //MARKERS UPPER LIBRARY FLOOR
    private void addMarkersToMapULF() {
        // Uses a colored icon.
        //LGF
        mVendingMachine = mMap.addMarker(new MarkerOptions()
                .position(VENDINGMACHINEULF)
                .title("Vending Machine")
                .snippet("Floor Level 1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        //ULF
        mElevator = mMap.addMarker(new MarkerOptions()
                .position(ELEVATORULF)
                .title("Elevator")
                .snippet("Located Here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

    }
    //MARKERS OLD BUILDING UPPER
    private void addMarkersToMapOUF() {
        // Uses a colored icon.
        mToilets1 = mMap.addMarker(new MarkerOptions()
                .position(TOILETS1)
                .title("Male Toilet")
                .snippet("Floor Level 1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        mLecturesD = mMap.addMarker(new MarkerOptions()
                .position(DANDAOFFICE)
                .title("Deirdre and Annette's office")
                .snippet("Floor Level 1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));

    }
    //MARKERS OLD BUILDING LOWER FLOOR
    private void addMarkersToMapOLF() {
        // Uses a colored icon.
        mCanteen1 = mMap.addMarker(new MarkerOptions()
                .position(CANTEENENTRANCE)
                .title("Canteen Entrance")
                .snippet("Floor Level 0")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mCanteen2 = mMap.addMarker(new MarkerOptions()
                .position(CANTEEN)
                .title("Canteen")
                .snippet("Floor Level 0")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mToilets2 = mMap.addMarker(new MarkerOptions()
                .position(TOILETS2)
                .title("Males Toilets")
                .snippet("Floor Level 1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        mBanks1 = mMap.addMarker(new MarkerOptions()
                .position(BOIATM)
                .title("BOI ATM")
                .snippet("Floor Level 0")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mBanks2 = mMap.addMarker(new MarkerOptions()
                .position(AIBATM)
                .title("AIB ATM")
                .snippet("Floor Level 0")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mBanks3 = mMap.addMarker(new MarkerOptions()
                .position(BANKOFIRELAND)
                .title("Bank of Ireland")
                .snippet("Floor Level 0")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mBanks4 = mMap.addMarker(new MarkerOptions()
                .position(AIBBANK)
                .title("AIB Bank")
                .snippet("Floor Level 0")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mStudentsUnion = mMap.addMarker(new MarkerOptions()
                .position(STUDENTSUNION)
                .title("Students Union")
                .snippet("Floor Level 0")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

        mNursesOffice = mMap.addMarker(new MarkerOptions()
                .position(NURSESOFFICE)
                .title("Nurses Office")
                .snippet("Floor Level 0")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        mWheelChair2 = mMap.addMarker(new MarkerOptions()
                .position(WHEELCHAIRENTRANCE2)
                .title("Wheel Chair Entrance")
                .snippet("Floor Level 0")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mBarberista = mMap.addMarker(new MarkerOptions()
                .position(BARBERISTA)
                .title("Barberista Entrance")
                .snippet("Floor Level 0")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

        mLecturesJ = mMap.addMarker(new MarkerOptions()
                .position(JOHNFARELLSOFFICE)
                .title("JOHN FARELLS OFFICE")
                .snippet("Floor Level 0")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        mWheelChair3 = mMap.addMarker(new MarkerOptions()
                .position(WHEELCHAIRENTRANCE3)
                .title("Wheel Chair Entrance")
                .snippet("Floor Level 0")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mShop = mMap.addMarker(new MarkerOptions()
                .position(SHOP)
                .title("Shop")
                .snippet("Floor Level 0")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

    }
    /*
    private void addMarkersToilets(){

        mToilets1 = mMap.addMarker(new MarkerOptions()
                .position(TOILETS2)
                .title("Male Toilets")
                .snippet("Located Here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

        mToilets2 = mMap.addMarker(new MarkerOptions()
                .position(TOILETS1)
                .title("Male Toilet")
                .snippet("Located Here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));

    }

    private void addMarkersWheelChairAccess(){

        mWheelChair1 = mMap.addMarker(new MarkerOptions()
                .position(WHEELCHAIRENTRANCE3)
                .title("Wheel Chair Entrance")
                .snippet("Located Here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mWheelChair2 = mMap.addMarker(new MarkerOptions()
                .position(WHEELCHAIRENTRANCE2)
                .title("Wheel Chair Entrance")
                .snippet("Located Here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mWheelChair3 = mMap.addMarker(new MarkerOptions()
                .position(WHEELCHAIRACCESSLGF)
                .title("Wheel Chair Access")
                .snippet("Located Here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

    }

    private void addMarkersLecturesOffice(){

        mLecturesJ = mMap.addMarker(new MarkerOptions()
                .position(JOHNFARELLSOFFICE)
                .title("JOHN FARELLS OFFICE")
                .snippet("Located Here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        mLecturesD = mMap.addMarker(new MarkerOptions()
                .position(DANDAOFFICE)
                .title("Deirdre and Annette's office")
                .snippet("Located Here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
    }

    private void addMarkersBanks(){

        mBanks1 = mMap.addMarker(new MarkerOptions()
                .position(BOIATM)
                .title("BOI ATM")
                .snippet("Located Here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mBanks2 = mMap.addMarker(new MarkerOptions()
                .position(AIBATM)
                .title("AIB ATM")
                .snippet("Located Here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mBanks3 = mMap.addMarker(new MarkerOptions()
                .position(BANKOFIRELAND)
                .title("Bank of Ireland")
                .snippet("Located Here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mBanks4 = mMap.addMarker(new MarkerOptions()
                .position(AIBBANK)
                .title("AIB Bank")
                .snippet("Located Here")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
    }
        */

    @Override
    public boolean onMarkerClick(final Marker marker) {
        /*if (marker.equals(mWheelChair)) {
            // This causes the marker at Perth to bounce into position when it is clicked.
            final Handler handler = new Handler();
            final long start = SystemClock.uptimeMillis();
            final long duration = 1500;

            final Interpolator interpolator = new BounceInterpolator();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    long elapsed = SystemClock.uptimeMillis() - start;
                    float t = Math.max(
                            1 - interpolator.getInterpolation((float) elapsed / duration), 0);
                    marker.setAnchor(0.5f, 1.0f + 2 * t);

                    if (t > 0.0) {
                        // Post again 16ms later.
                        handler.postDelayed(this, 16);
                    }
                }
            });
        } else */

        //Change Marker Color
        if (marker.equals(mWheelChair2)) {
            // This causes the marker at Adelaide to change color and alpha.
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(mRandom.nextFloat() * 360));
            marker.setAlpha(mRandom.nextFloat());
        }

        // Markers have a z-index that is settable and gettable.
        Toast.makeText(this, marker.getTitle(),
                Toast.LENGTH_SHORT).show();

        mLastSelectedMarker = marker;
        // We return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}