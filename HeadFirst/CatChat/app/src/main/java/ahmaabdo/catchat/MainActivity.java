package ahmaabdo.catchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ahmaabdo.catchat.fragments.DraftsFragment;
import ahmaabdo.catchat.fragments.InboxFragment;
import ahmaabdo.catchat.fragments.SentItemsFragment;
import ahmaabdo.catchat.fragments.TrashFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //This registers the activity as a listener on the navigation view so it will be notified if the user clicks on an item
        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_frame, new InboxFragment())
                .commit();

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer);

        drawerLayout.addDrawerListener(toggle);
        //Method to synchronize the icon on the toolbar with the state of the drawer (opened/closed)
        toggle.syncState();
    }

    //Method that handles drawer items clicks
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_inbox:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, new InboxFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
                break;

            case R.id.nav_drafts:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, new DraftsFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
                break;

            case R.id.nav_sent:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, new SentItemsFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
                break;

            case R.id.nav_trash:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, new TrashFragment())
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
                break;

            case R.id.nav_help:
                startActivity(new Intent(MainActivity.this, HelpActivity.class));
                break;

            case R.id.nav_feedback:
                startActivity(new Intent(MainActivity.this, FeedbackActivity.class));
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
