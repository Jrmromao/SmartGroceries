package co.devhut.smartgroceries;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jrmromao on 31/10/2017.
 * class for the menu option drawer
 */

public class OptionsDrawer_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_drawer);
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
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ProductModel pm = new ProductModel();
        ArrayList<ProductModel> list = new ArrayList<>();


//        //test items in the list
//        pm.setmName("batatas");
//        pm.setPrice(4.25);
//        pm.setmBrand("Matutano");
//        list.add(pm);
//
//        pm.setmName("batatas");
//        pm.setPrice(4.25);
//        pm.setmBrand("Matutano");
//        list.add(pm);
//
//        pm.setmName("batatas");
//        pm.setPrice(4.25);
//        pm.setmBrand("Matutano");
//        list.add(pm);
//
//        pm.setmName("batatas");
//        pm.setPrice(4.25);
//        pm.setmBrand("Matutano");
//        list.add(pm);

//        pm.setmName("batatas");
//        pm.setPrice(4.25);
//        pm.setmBrand("Matutano");
//        list.add(pm);

        ProdListAdapter adapter = new ProdListAdapter(this, ProdLists.getBestProdList());
        ListView bestProd = (ListView) findViewById(R.id.best_product);
        bestProd.setAdapter(adapter);


        //if the user is already logged in we will directly start the profile activity
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Second_activity.class));
            return;
        }


//        ListView pList = (ListView) view.findViewById(R.id.product_List_view);
//        ProdLists.setScanProdList(testList);
//        ArrayList<ProductModel> list =  ProdLists.getBestProdList();
//        ProdListAdapter adapter = new ProdListAdapter(getContext(), list);
//        pList.setAdapter(adapter);


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

    @SuppressLint("ResourceType")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_productDetails) {
            //call product fragment
            // to call a fragment
            Product_Fragment product_fragment = new Product_Fragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()

                    .replace(
                            R.id.content_profile,
                            product_fragment,
                            product_fragment.getTag()
                    ).commit();


            //   startActivity(new Intent(getApplicationContext(), Scan_Activity.class));



            // startActivity(new Intent(getApplicationContext(), Scan_Activity.class));
        } else if (id == R.id.nav_profile) {

            // to call a fragment
            Profile_Fragment prof_fragment = new Profile_Fragment();
            FragmentManager manager = getSupportFragmentManager();

            manager.beginTransaction().replace(
                    R.id.content_profile,
                    prof_fragment,
                    prof_fragment.getTag()
            ).commit();

        } else if (id == R.id.nav_signOut) {

            //logout and redirect the user to the login activity
            //
            SharedPrefManager s = new SharedPrefManager();
            s.logout();
            startActivity(new Intent(getApplicationContext(), Second_activity.class));

        } else if (id == R.id.nav_exit) {
            System.exit(0);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}