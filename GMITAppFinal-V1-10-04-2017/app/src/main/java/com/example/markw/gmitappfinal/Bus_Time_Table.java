package com.example.markw.gmitappfinal;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by markw on 28/02/2017.
 */

public class Bus_Time_Table extends AppCompatActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    /**http://www.buseireann.ie/timetables/1475580323-409.pdf */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus__time__table);

        WebView myWebView = (WebView) findViewById(R.id.webview1);
        String pdf = "http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf";
        myWebView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setDisplayZoomControls(false);

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });

        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void addDrawerItems() {
        String[] osArray = { "360 Tour", "GMIT Maps", "VR View of Theater 1000", "GMIT Home", "Learn Online", "GMIT Library", "Student Portal", "Time Table", "Bus Time Table" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {

                    Intent myIntent = new Intent(Bus_Time_Table.this, GMIT_tour.class);
                    startActivityForResult(myIntent, 0);
                }
                if(position == 1)
                {

                    Intent myIntent = new Intent(Bus_Time_Table.this, MapsOverlayActivity.class);
                    startActivityForResult(myIntent, 0);
                }

                if(position == 2)
                {

                    Intent myIntent =  new Intent(Bus_Time_Table.this, v_tour.class);
                    startActivityForResult(myIntent, 0);
                }
                if(position == 3)
                {
                    Intent myIntent =  new Intent(Bus_Time_Table.this, MainActivity.class);
                    startActivityForResult(myIntent, 0);
                }
                if(position == 4)
                {
                    Intent myIntent =  new Intent(Bus_Time_Table.this, moodle.class);
                    startActivityForResult(myIntent, 0);
                }
                if(position == 5)
                {
                    Intent myIntent =  new Intent(Bus_Time_Table.this, GMIT_lIBRARY.class);
                    startActivityForResult(myIntent, 0);
                }
                if(position == 6)
                {
                    Intent myIntent =  new Intent(Bus_Time_Table.this, Student_Portal.class);
                    startActivityForResult(myIntent, 0);
                }
                if(position == 7)
                {

                    Intent myIntent =  new Intent(Bus_Time_Table.this, Time_Table.class);
                    startActivityForResult(myIntent, 0);
                }
                if(position == 8)
                {

                    Intent myIntent =  new Intent(Bus_Time_Table.this, Bus_Time_Table.class);
                    startActivityForResult(myIntent, 0);
                }
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("THE GMIT APP");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    public class Main extends Activity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            final TextView content = new TextView(this);
            content.setText(Html.fromHtml("first<br><b>second</b>"));
            setContentView(content);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
