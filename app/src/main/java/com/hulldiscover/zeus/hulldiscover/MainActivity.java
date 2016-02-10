package com.hulldiscover.zeus.hulldiscover;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName(); //traceback where the error/information is coming from

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseUser currentUser = ParseUser.getCurrentUser();
        // check to see if user is already logged in
        if (currentUser == null) {
            navigateToLogin();

        }
        else { // user is logged in
            Log.i(TAG, currentUser.getUsername());

        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerList);
        // determines how the contents of the list are positioned on the list
        LinearLayoutManager linearLM = new LinearLayoutManager(this);
        linearLM.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLM);

        recyclerView.setAdapter(new MyRecyclerAdapter(generateBooks()));

    }

    private ArrayList<Palette> generateBooks() {
        ArrayList<Palette> palettes = new ArrayList<>();
        palettes.add(new Palette("Yoga Soc: Yoga Session", "10 February, 2016", Color.parseColor("#d32f2f")));
        palettes.add(new Palette("Tower", "10 February, 2016", Color.parseColor("#ff4081")));
        palettes.add(new Palette("Mental Health Awareness Day", "11 February, 2016", Color.parseColor("#7b1fa2")));
        palettes.add(new Palette("Clubbercise", "11 February, 2016", Color.parseColor("#536dfe")));
        palettes.add(new Palette("Friday Night Live", "12 February, 2016", Color.parseColor("#388e3c")));
        palettes.add(new Palette("Brassick Swipe Right Valentines Party", "13 February, 2016", Color.parseColor("#ff5722")));
        palettes.add(new Palette("Squash - GIAG", "18 February, 2016", Color.parseColor("#ffa000")));
        return palettes;
    }

    private void navigateToLogin() {
        // start login activity
        // all activities are started with an Intent
        Intent intent = new Intent(this, LoginActivity.class);
        // A Task - refers to collection of activities in the order in which a user using them to complete a task
        // logging should be a new task, and that the old task (starting th app) should be clear so that the user can't get back to it
        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == R.id.action_logout) {
            // user tapped logout
            ParseUser.logOut();
            navigateToLogin();
        }
        return super.onOptionsItemSelected(item);
    }
}
