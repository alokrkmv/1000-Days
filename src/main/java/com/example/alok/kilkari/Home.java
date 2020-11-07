package com.example.alok.kilkari;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Users user = new Users();
    String name = user.getName();
    String email = user.getEmail();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    String user_name;
    String user_email;
    ImageView article1;
    ImageView article2;
    ImageView article3;
    ImageView article4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        Bundle bundle = getIntent().getExtras();
//        String  message = bundle.getString("Email");
        article1=(ImageView)findViewById(R.id.article1);
        article2=(ImageView)findViewById(R.id.article2);
        article3=(ImageView)findViewById(R.id.article3);
        article4=(ImageView)findViewById(R.id.article4);
        article1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent intent=new Intent(Home.this,Article2.class);
                startActivity(intent);
            }
        });
        article2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent intent=new Intent(Home.this,Article1.class);
                startActivity(intent);
            }
        });
        article3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent intent=new Intent(Home.this,Article3.class);
                startActivity(intent);
            }
        });
        article4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent intent=new Intent(Home.this,Article4.class);
                startActivity(intent);
            }
        });
        System.out.println(name);
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_name = sharedPreferences.getString("username", "");
        user_email = sharedPreferences.getString("email", "");
        System.out.println(user_email);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    Intent loginIntent = new Intent(Home.this, Login.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginIntent);
                }

            }
        };
//        user_id=mAuth.getCurrentUser().getUid();
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                user_name=dataSnapshot.child(user_id).child("Name").getValue(String.class);
//                user_email=dataSnapshot.child(user_id).child("Email").getValue(String.class);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//        NavigationView nav = (NavigationView) findViewById(R.id.nav_view);
//        View headerView = nav.getHeaderView(0);
//        TextView txtView = (TextView) headerView.findViewById(R.id.textView1);
//        txtView.setText(message);
    }

    public Location getLocation() {

        Location location = null;
        final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
        final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            try {
                LocationManager locationManager = (LocationManager) getApplicationContext()
                        .getSystemService(Context.LOCATION_SERVICE);

                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
//updating when location is changed
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                        }
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                };

                boolean isNetworkEnabled = locationManager
                        .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                boolean isGPSEnabled = locationManager
                        .isProviderEnabled(LocationManager.GPS_PROVIDER);

                if (!isNetworkEnabled && !isGPSEnabled) {
//call getLocation again in onResume method in this case
                    startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));

                } else {

                    if (isNetworkEnabled) {

                        locationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        }

                    } else {

                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
                        Log.d("GPS", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);

                        }

                    }


                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

//Request for the ACCESS FINE LOCATION
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//call the getLocation funtion again on permission granted callback
        }
        return location;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
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
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       if (id == R.id.addIcon) {
           mAuth.signOut();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent = new Intent(Home.this, BabyProfile.class);
            startActivity(intent);
        }
         else if (id == R.id.nav_vaccination) {
            Intent intent = new Intent(Home.this, Vaccination.class);
            startActivity(intent);

        } else if (id == R.id.nav_childbirth) {
            Intent intent = new Intent(Home.this, ChildClasses.class);
            startActivity(intent);

        } else if (id == R.id.nav_preg) {
            Intent intent = new Intent(Home.this, Pregnency.class);
            startActivity(intent);

        } else if (id == R.id.nav_new) {
            Intent intent = new Intent(Home.this, NewBorn.class);
            startActivity(intent);

        } else if (id == R.id.nav_growth) {
            Intent intent = new Intent(Home.this, TrackBaby.class);
            startActivity(intent);

        } else if (id == R.id.nav_exercise) {
            Intent intent = new Intent(Home.this, Exercise.class);
            startActivity(intent);

        } else if (id == R.id.nav_doctor) {
            Intent intent = new Intent(Home.this, ConsultDoctor.class);
            startActivity(intent);

        } else if (id == R.id.nav_fdoctor) {
            Location location = getLocation();
                if(location!=null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    String uri = String.format("geo:%f,%f?q=Child Specialist", latitude, longitude);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }

            }else if (id == R.id.nav_babysitter) {
            Location location = getLocation();
            if(location!=null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String uri = String.format("geo:%f,%f?q=Baby Sitters", latitude, longitude);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
