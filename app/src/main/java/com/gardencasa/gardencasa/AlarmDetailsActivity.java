package com.gardencasa.gardencasa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AlarmDetailsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmdetails);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.edalarm);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
               // Intent i = new Intent(MainActivity.this, SendEmailActivity.class);
                //startActivity(i);
            }
        });

        //set the back (up) button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        //find all our view components
        ImageView imageView = (ImageView) findViewById(R.id.image);
        TextView nameTV = (TextView) findViewById(R.id.name);
        TextView descriptionTV = (TextView) findViewById(R.id.description);
        TextView typeTV = (TextView) findViewById(R.id.type);
        TextView bloomtimeTV = (TextView) findViewById(R.id.bloomtime);
        TextView floweringTV = (TextView) findViewById(R.id.flowering);
        TextView alarmtimeTV = (TextView) findViewById(R.id.alarmtime);


        //collect our intent and populate our layout
        Intent intent = getIntent();

        //Integer streetNumber = intent.getIntExtra("streetNumber", 0);
        String plantName = intent.getStringExtra("plantName");
        String description = intent.getStringExtra("desc");
        String type = intent.getStringExtra("type");
        String flowering = intent.getStringExtra("flower");
        String alarmTime = intent.getStringExtra("time");
        String bloomTime = intent.getStringExtra("bloomTime");

        String image = intent.getStringExtra("image");
        Integer imageID = this.getResources().getIdentifier(image, "drawable", this.getPackageName());

        //set elements
        imageView.setImageResource(imageID);
        nameTV.setText(plantName);
        descriptionTV.setText("About: "+ description);
        bloomtimeTV.setText("Bloom Time: " + bloomTime);
        alarmtimeTV.setText("Alarm Time: " + alarmTime);
        floweringTV.setText("Flowering: " + flowering);
        typeTV.setText("Type: " + type);

        //set the title of this activity to be the street name
        getSupportActionBar().setTitle(plantName);
    }
}
