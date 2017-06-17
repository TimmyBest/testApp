package com.example.forwardgame.myapplication;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.transition.Scene;
import android.support.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;
// Commit June 18  SUCCESSFUL Flipper and back to flipper
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public  static final Random RANDOM = new Random();


    private ImageView img1, img2, img3, img4;
    private Button btnRoll;
    private Button add2;


    private  static  int randDiceVal()
    {
        return RANDOM.nextInt(6) + 1;

    }


    private void addOrRemove() {

        int newHeight = img3.getHeight() == 1 ? img1.getHeight() : 1;

        String st = String.valueOf(newHeight);
         Log.d("aa",st);

        ViewGroup.LayoutParams pr3 = img3.getLayoutParams();
            pr3.height = (int)newHeight;

        ViewGroup.LayoutParams pr4 = img4.getLayoutParams();
        pr4.height = (int)newHeight;

        final ViewGroup layout = (ViewGroup) findViewById(R.id.ccLayout);
        final Scene scene = new Scene(layout);

      //  img3.setLayoutParams(pr3);
       // img4.setLayoutParams(pr4);



        ////


            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) img3.getLayoutParams();
//            params.bottomToBottom = layout.getId();
            img3.setLayoutParams(params);
            // animate changes
            TransitionManager.go(scene);


    }

    private void rollIt()
    {
        final Animation anim1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
        final Animation anim2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
        final Animation anim3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);
        final Animation anim4 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake);

        final  Animation.AnimationListener animListnet = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                int val = randDiceVal();
                int res = getResources().getIdentifier("dice_" + val, "drawable", "com.example.forwardgame.myapplication" );




                if (animation == anim1)
                {
                    img1.setImageResource(res);
                }
                if (animation == anim2)
                {
                    img2.setImageResource(res);
                }
                if (animation == anim3)
                {
                    img3.setImageResource(res);
                }
                if (animation == anim4)
                {
                    img4.setImageResource(res);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        anim1.setAnimationListener(animListnet);
        anim2.setAnimationListener(animListnet);
        anim3.setAnimationListener(animListnet);
        anim4.setAnimationListener(animListnet);

        img1.startAnimation(anim1);
        img2.startAnimation(anim2);
        img3.startAnimation(anim3);
        img4.startAnimation(anim4);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        btnRoll = (Button) findViewById(R.id.btnRoll);
        add2 = (Button) findViewById(R.id.btnAdd2);

        img1 = (ImageView) findViewById(R.id.iv1);
        img2 = (ImageView) findViewById(R.id.iv2);
        img3 = (ImageView) findViewById(R.id.iv3);
        img4 = (ImageView) findViewById(R.id.iv4);

        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollIt();
            }
        });

        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrRemove()   ;         }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
