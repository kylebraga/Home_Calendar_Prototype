package com.example.kbrag_000.homecalendartestproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.kbrag_000.homecalendartestproject.dummy.EventLog_Db_Manager;

public class Add_New_Event extends AppCompatActivity {

    //Display Variables
    Toolbar myToolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    EventLog_Db_Manager mDbManager;

    //Get Entered Details Variables
    public static String mCurrentEventName;
    public static String mCurrentEventOwner;
    public static String mCurrentEventTimeStart;
    public static String mCurrentEventTimeEnd;
    public static String mCurrentEventDate;
    public static String mCurrentEventColorName;

    



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__new__event);
        myToolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(myToolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,myToolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                return true;
            }
        });

        mDbManager = new EventLog_Db_Manager();
    }

    public void goToHome(View view)
    {
        Intent intent = new Intent(this, Home_Schedule.class);
        startActivity(intent);
    }

    public void getEnteredInformation(View view)
    {
        TextView titleText = (TextView) findViewById(R.id.titleInput);
        TextView startText = (TextView) findViewById(R.id.startInput);
        TextView endText = (TextView) findViewById(R.id.endInput);
        TextView dateText = (TextView) findViewById(R.id.dateInput);

        RadioGroup colorRadioGroup = (RadioGroup) findViewById(R.id.colorRadioGroup);
        int selectedRadioId = colorRadioGroup.getCheckedRadioButtonId();
        RadioButton radiocolorButton = (RadioButton) findViewById(selectedRadioId);

        String mCurrentEventName = titleText.getText().toString();
        String mCurrentEventTimeStart = startText.getText().toString();
        String mCurrentEventTimeEnd = endText.getText().toString();
        String mCurrentEventDate = dateText.getText().toString();
        String mCurrentEventColorName = radiocolorButton.getText().toString();
    }

}
