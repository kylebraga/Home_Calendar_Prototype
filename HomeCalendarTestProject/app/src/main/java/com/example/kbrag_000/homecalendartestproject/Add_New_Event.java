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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Add_New_Event extends AppCompatActivity {

    //Display Variables
    Toolbar myToolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    EventLog_Db_Manager mDbManager;
    public int TIME_BLOCK_SIZE = 15;

    //Get Entered Details Variables
    public static String mCurrentEventName;
    public static String mCurrentEventOwner;
    public static String mCurrentEventTimeStart;
    public static String mCurrentEventTimeEnd;
    public static String mCurrentEventDate;
    public static String mCurrentEventColorName;

    //Event Duration Variables
    public int mDurationId;
    public int mDurationEventId;
    public Date mDurationDate;
    public Time mDurationStartTime;
    public Time mDurationEndTime;
    public int mDurationNumCalBlocks;

    //Event Color Variables
    public  int mColorId;
    public  String mColorName;
    public  String mColorHex;

    //Event User  Variables
    public int UserId;
    public String UserFirstName;
    public String UserLastName;
    public String UserEmail;
    public String UserPassword;
    public String UserZipcode;

    //Event Info  Variables
    public int mEventId;
    public String mEventName;
    public int mEventOwnerId;
    public int mEventDurationId;
    public int mEventColorId;



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

        ConnectToDb();
        //mDbManager = new EventLog_Db_Manager();
    }

    public void goToHome(View view)
    {
        Intent intent = new Intent(this, Home_Schedule.class);
        startActivity(intent);
    }

    public void ConnectToDb()
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("# - driver loaded");

        String server = "BASEDKYLE\\SQLEXPRESS";

        int port = 64038;

        String database = "Data";

        String jdbcUrl = "jdbc:sqlserver://"+server+":"+port+" ;databaseName = "+database+";integratedSecurity=true";

        try {
            Connection con = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("# - Connection obtained");
    }

    private void SaveNewEvent(View view)
    {
        setAllEventValues(view);
        insertEventIntoDb();
    }
    private void insertEventIntoDb()
    {

    }

    private void setAllEventValues(View view)
    {
        int tempEventId = getEventId();
        setEnteredInformation(view);
        SetDurationValues(tempEventId);
        setEventInfoValues(tempEventId);
    }

    public void setEnteredInformation(View view)
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

        mCurrentEventOwner = "Lil B";
    }


    public void SetDurationValues(int tempEventId)
    {
        mDurationEventId = tempEventId;
        setDurationId();
        setDurationTimeValues();
        setNumCalBlocks();
        insertDurationInfoIntoDB();
    }

    private void setDurationId()
    {
        //int tempDurationId = getLastDurationId();
        int tempDurationId = 1;
        tempDurationId += 1;

        mDurationId = tempDurationId;
    }

    private void setDurationTimeValues()
    {
        DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date tempDate = null;
        try {
            tempDate = dateFormat.parse(mCurrentEventDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        Time tempStartTime = null;
        try {
            tempStartTime = new Time(hourFormat.parse(mCurrentEventTimeStart).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Time tempEndTime = null;
        try {
            tempEndTime = new Time(hourFormat.parse(mCurrentEventTimeEnd).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mDurationDate = tempDate;
        mDurationStartTime = tempStartTime;
        mDurationEndTime = tempEndTime;
    }

    private void setNumCalBlocks()
    {
        Time tempStartTime = mDurationStartTime;
        Time tempEndTime = mDurationEndTime;
        int tempNumCalBlocks = 0;
        long tempDuration = mDurationEndTime.getTime() - mDurationStartTime.getTime();

        //convert millaseconds difference into minutes
        long durationMinutes = tempDuration / (60 * 1000) % 60;
        long durationHolder = durationMinutes;

        //Subtract time block size from the total minute duration minutes in order to see how many 15 minute blocks the event requires
        while(durationHolder > 0)
        {
            tempNumCalBlocks += 1;
            durationHolder -= TIME_BLOCK_SIZE;
        }

        mDurationNumCalBlocks = tempNumCalBlocks;
    }

    private void insertDurationInfoIntoDB() {

        /*do database work here */

    }

    private void setEventInfoValues(int tempEventId)
    {
        mEventId = getEventId();
        mEventName = mCurrentEventName;
        mEventColorId = getColorId();
        //mEventOwnerId = getOwnerId();
        mEventOwnerId = 1;
        mEventDurationId = mDurationId;
        mEventColorId = getColorId();
    }

    private int getOwnerId()
    {
        int ownerId = 1;
        //ownerId = getOwnerId(mCurrentEventOwner);

        return ownerId;
    }

    private int getEventId()
    {
        int eventId = 1;
        //eventId = getEventId();

        return eventId;
    }

    private int getColorId()
    {
        int tempId;

        switch (mColorName)
        {
            case "Red":
                tempId = 1;
                break;
            case "Orange":
                tempId = 2;
                break;
            case "Yellow":
                tempId = 3;
                break;
            case "Green":
                tempId = 4;
                break;
            case "Blue":
                tempId = 5;
                break;
            default:
                tempId = 1;
                break;
        }

        return tempId;
    }


}
