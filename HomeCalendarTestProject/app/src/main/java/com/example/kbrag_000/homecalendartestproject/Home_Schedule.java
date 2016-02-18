package com.example.kbrag_000.homecalendartestproject;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class Home_Schedule extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ActionBarDrawerToggle mDrawerToggle;
    Toolbar myToolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__schedule);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        String[] myDataset = new String[24];
        myDataset[0] = "1am";
        myDataset[1] = "2am";
        myDataset[2] = "3am";
        myDataset[3] = "4am";
        myDataset[4] = "5am";
        myDataset[5] = "6am";
        myDataset[6] = "7am";
        myDataset[7] = "8am";
        myDataset[8] = "9am";
        myDataset[9] = "10am";
        myDataset[10] = "11am";
        myDataset[11] = "12pm";
        myDataset[12] = "1pm";
        myDataset[13] = "2pm";
        myDataset[14] = "3pm";
        myDataset[15] = "4pm";
        myDataset[16] = "5pm";
        myDataset[17] = "6pm";
        myDataset[18] = "7pm";
        myDataset[19] = "8pm";
        myDataset[20] = "9pm";
        myDataset[21] = "10pm";
        myDataset[22] = "11pm";
        myDataset[23] = "12am";

        mAdapter = new CalendarRecyclerAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        myToolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(myToolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();
                /*
                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()){


                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.inbox:
                        Toast.makeText(getApplicationContext(),"Inbox Selected",Toast.LENGTH_SHORT).show();
                        ContentFragment fragment = new ContentFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame,fragment);
                        fragmentTransaction.commit();
                        return true;

                    // For rest of the options we just show a toast on click

                    case R.id.starred:
                        Toast.makeText(getApplicationContext(),"Stared Selected",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.sent_mail:
                        Toast.makeText(getApplicationContext(), "Send Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.drafts:
                        Toast.makeText(getApplicationContext(),"Drafts Selected",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.allmail:
                        Toast.makeText(getApplicationContext(),"All Mail Selected",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.trash:
                        Toast.makeText(getApplicationContext(),"Trash Selected",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.spam:
                        Toast.makeText(getApplicationContext(),"Spam Selected",Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_SHORT).show();
                        return true;

                }*/
                return true;
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
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




        /*DrawerLayout Drawer;

        Drawer = (DrawerLayout) findViewById(R.id.drawer_layout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,myToolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }



        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home__schedule, menu);
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

        return super.onOptionsItemSelected(item);
    }

    public void executeQuickNav(View view)
    {

        int position = 0;
        Quick_Nave quickFrag;
        RecyclerView recycler = (RecyclerView) findViewById(R.id.my_recycler_view);
        recycler.scrollToPosition(position);
    }
    public void goTo6am(View view)
    {
        int position = 5;
        Quick_Nave quickFrag;
        RecyclerView recycler = (RecyclerView) findViewById(R.id.my_recycler_view);
        recycler.scrollToPosition(position);
    }
    public void goTo9am(View view)
    {
        int position = 8;
        Quick_Nave quickFrag;
        RecyclerView recycler = (RecyclerView) findViewById(R.id.my_recycler_view);
        recycler.scrollToPosition(position);
    }
    public void goTo12pm(View view)
    {
        int position = 11;
        Quick_Nave quickFrag;
        RecyclerView recycler = (RecyclerView) findViewById(R.id.my_recycler_view);
        recycler.scrollToPosition(position);
    }
    public void goTo3pm(View view)
    {
        int position = 14;
        Quick_Nave quickFrag;
        RecyclerView recycler = (RecyclerView) findViewById(R.id.my_recycler_view);
        recycler.scrollToPosition(position);
    }
    public void goTo6pm(View view)
    {
        int position = 17;
        Quick_Nave quickFrag;
        RecyclerView recycler = (RecyclerView) findViewById(R.id.my_recycler_view);
        recycler.scrollToPosition(position);
    }
    public void goTo9pm(View view)
    {
        int position = 20;
        Quick_Nave quickFrag;
        RecyclerView recycler = (RecyclerView) findViewById(R.id.my_recycler_view);
        recycler.scrollToPosition(position);
    }
}
