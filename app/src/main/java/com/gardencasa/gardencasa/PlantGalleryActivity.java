package com.gardencasa.gardencasa;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


import com.gardencasa.gardencasa.database.AlarmDataSource;
import com.gardencasa.gardencasa.database.Plant;
import com.gardencasa.gardencasa.database.PlantDataSource;

import java.util.ArrayList;

public class PlantGalleryActivity extends AppCompatActivity {

    public static final String TAG = "PlantGalleryActivity" ;
    private PlantDataSource datasource;
    private AlarmDataSource alarmdatasource;
    private SimpleCursorAdapter adapter;
    private ListView listView;

    private ArrayList<Plant> plantProperties = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_plant_gallery);
    //this.getListView().setDividerHeight(2);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d(TAG, "onCreate" );

    //fillData();

    datasource = new PlantDataSource(this);
        datasource.open();

        plantProperties=datasource.getAllPlant();

    //create our new array adapter
    ArrayAdapter<Plant> adapter = new PlantArrayAdapter(this, 0, plantProperties);

    //Find list view and bind it with the custom adapter
    listView = (ListView) findViewById(R.id.customListView);
        listView.setAdapter(adapter);
    registerForContextMenu( listView );

    //add event listener so we can handle clicks
    AdapterView.OnItemClickListener adapterViewListener = new AdapterView.OnItemClickListener() {

        //on click
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Plant alarm = plantProperties.get(position);

            Intent intent = new Intent(PlantGalleryActivity.this, PlantDetailsActivity.class);
            intent.putExtra("plantName", alarm.getName());
            intent.putExtra("desc", alarm.getDesc());
            intent.putExtra("type", alarm.getType());
            intent.putExtra("flower", alarm.getFlowering());
            intent.putExtra("image", alarm.getImage());
            //intent.putExtra("time", alarm.getTime());
            intent.putExtra("bloomTime", alarm.getBloomTime());
            startActivity(intent);
        }
    };
    //set the listener to the list view
        listView.setOnItemClickListener(adapterViewListener);
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
        inflater.inflate(R.menu.plant_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        long index =info.id;
        switch (item.getItemId()) {
            case R.id.addplant:
                Plant plant = plantProperties.get((int) index);
                ContentValues values = new ContentValues();
                values.put("name", plant.getName());
                values.put("desc", plant.getDesc());
                values.put("type", plant.getType());
                values.put("image", plant.getImage());
                values.put("bloomTime", plant.getBloomTime());
                values.put("flower", plant.getFlowering());

                alarmdatasource = new AlarmDataSource(this);
                alarmdatasource.open();
                alarmdatasource.createAlarm(values);
                Toast.makeText(this, "Plant Added", Toast.LENGTH_SHORT).show();
                alarmdatasource.close();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}
