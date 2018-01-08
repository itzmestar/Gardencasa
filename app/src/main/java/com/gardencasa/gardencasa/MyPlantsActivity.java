package com.gardencasa.gardencasa;

/**
 * Created by TARIQUE on 23-04-2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.gardencasa.gardencasa.alarm.AlarmDialogFragment;
import com.gardencasa.gardencasa.alarm.AlarmReceiver;
import com.gardencasa.gardencasa.database.Alarm;
import com.gardencasa.gardencasa.database.AlarmDataSource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//public class MyPlantsActivity extends AppCompatActivity {
//public class MyPlantsActivity extends FragmentActivity {
    public class MyPlantsActivity extends AppCompatActivity {
  //      implements NavigationView.OnNavigationItemSelectedListener{

    public static final String TAG = "MyPlantsActivity" ;
    private AlarmDataSource datasource;
    private AlarmArrayAdapter adapter;
    private ListView listView;
    AlarmReceiver alarm = new AlarmReceiver();
    public static int timeHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    public static int timeMinute = Calendar.getInstance().get(Calendar.MINUTE);

    private ArrayList<Alarm> alarmProperties = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myplants);
        //this.getListView().setDividerHeight(2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d(TAG, "onCreate" );

        //fillData();

        datasource = new AlarmDataSource(this);
        datasource.open();

        alarmProperties=datasource.getAllAlarm();

        //create our new array adapter
        adapter = new AlarmArrayAdapter(this, 0, alarmProperties);

        //Find list view and bind it with the custom adapter
        listView = (ListView) findViewById(R.id.customListView);
        listView.setAdapter(adapter);
        registerForContextMenu( listView );

        //add event listener so we can handle clicks
        AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener() {

            //on click
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Alarm alarm = alarmProperties.get(position);

                Intent intent = new Intent(MyPlantsActivity.this, AlarmDetailsActivity.class);
                intent.putExtra("plantName", alarm.getName());
                intent.putExtra("desc", alarm.getDesc());
                intent.putExtra("type", alarm.getType());
                intent.putExtra("flower", alarm.getFlowering());
                intent.putExtra("image", alarm.getImage());
                intent.putExtra("time", alarm.getTime());
                intent.putExtra("bloomTime", alarm.getBloomTime());

                startActivity(intent);
            }
        };
        //set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);
    }

    private void fillData1() {

        // Fields from the database (projection)
        // Must include the _id column for the adapter to work
        //String[] from = new String[] { TodoTable.COLUMN_SUMMARY };
        // Fields on the UI to which we map
        int[] to = new int[] { R.id.label };

        datasource = new AlarmDataSource(this);
        datasource.open();

        List<Alarm> values = datasource.getAllAlarm();

        String[] from= new String[values.size()];

        for (int i = 0; i < values.size(); i++) {
            //System.out.println(crunchifyList.get(i));
            from[i]=values.get(i).toString();
            System.out.println(values.get(i).toString());
        }
        //getLoaderManager().initLoader(0, null, this);
     //   adapter = new SimpleCursorAdapter(this, R.layout.alarm_row, null, from,
       //         to, 0);
       // setListAdapter(adapter);
    }

    private void fillData() {
        datasource = new AlarmDataSource(this);
        datasource.open();

        alarmProperties=datasource.getAllAlarm();

        //create our new array adapter
        ArrayAdapter<Alarm> adapter = new AlarmArrayAdapter(this, 0, alarmProperties);

        //Find list view and bind it with the custom adapter
        ListView listView = (ListView) findViewById(R.id.customListView);
        listView.setAdapter(adapter);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.alarm_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        long index =info.id;
        Alarm alarm = alarmProperties.get((int) index);
        switch (item.getItemId()) {
            case R.id.setalarm:
               // editNote(info.id);
                //item.remove( items.get((int) index) );
                //setAdapter();
                Log.d(TAG, "onContextItemSelected: "+Long.toString(index)+ " AlarmId:" + alarm.getAlarmId() );

                Bundle bundle = new Bundle();
                bundle.putInt("time_hour", timeHour);
                bundle.putInt("time_minute", timeMinute);
                AlarmDialogFragment fragment = new AlarmDialogFragment(new MyHandler());
                fragment.setArguments(bundle);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(fragment, "time_picker");
                transaction.commit();
                //alarm.setAlarm(this);

                return true;
            case R.id.delete:
                //deleteNote(info.id);

                //Alarm alarm = alarmProperties.get((int) index);
                datasource.deleteAlarm(alarm.getAlarmId());
                alarmProperties.remove(index);
                //listView.setAdapter(adapter);
                adapter.remove(alarm);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Plant Deleted", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.cancelalarm:
                Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
        } else if (id == R.id.nav_login){
            Intent i = new Intent(MyPlantsActivity.this, LoginActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

 /*   @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

*//*        if (id == R.id.nav_login) {
            // Handle the Login action
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        } else *//*if (id == R.id.nav_plants) {
            //Intent i = new Intent(MainActivity.this, MyPlantsActivity.class);
            //startActivity(i);
            return true;
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(MyPlantsActivity.this, PlantGalleryActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_levi) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Intent i = new Intent(MyPlantsActivity.this, SendEmailActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_aboutus) {
            Intent i = new Intent(MyPlantsActivity.this, AboutUsActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
 class MyHandler extends Handler {
     @Override
     public void handleMessage (Message msg){
         Bundle bundle = msg.getData();
         timeHour = bundle.getInt("time_hour");
         timeMinute = bundle.getInt("time_minute");
         //textView1.setText(timeHour + ":" + timeMinute);
         //setAlarm();
         alarm.setAlarm(MyPlantsActivity.this);
         Toast.makeText(MyPlantsActivity.this, "Alarm On", Toast.LENGTH_SHORT).show();
     }
 }
}
