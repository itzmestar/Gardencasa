package com.gardencasa.gardencasa;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.gardencasa.gardencasa.database.Plant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TARIQUE on 30-04-2017.
 */

public class PlantArrayAdapter extends ArrayAdapter<Plant> {
    private Context context;
    private List<Plant> plantProperties;

    //constructor, call on creation
    public PlantArrayAdapter(Context context, int resource, ArrayList<Plant> objects) {
        super(context, resource, objects);

        this.context = context;
        this.plantProperties = objects;
    }

    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the property we are displaying
        Plant plant = plantProperties.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_plants, null);

        TextView name = (TextView) view.findViewById(R.id.nameP);
        TextView type = (TextView) view.findViewById(R.id.typeP);
        TextView flower = (TextView) view.findViewById(R.id.floweringP);
        ImageView image = (ImageView) view.findViewById(R.id.image);

        name.setText(String.valueOf(plant.getName()));
        type.setText("Type: "+String.valueOf(plant.getType()));
        flower.setText("Flowering: "+String.valueOf(plant.getFlowering()));
       /* String timeStr=String.valueOf(plant.getTime());
        System.out.println(timeStr);
        if(timeStr==null){
            time.setText("");
        }else{
            time.setText(timeStr);
        }*/
       /* TextView bedroom = (TextView) view.findViewById(R.id.bedroom);
        TextView bathroom = (TextView) view.findViewById(R.id.bathroom);
        TextView carspot = (TextView) view.findViewById(R.id.carspot);
        TextView price = (TextView) view.findViewById(R.id.price);*/


/*        //set address and description
        String completeAddress = property.getStreetNumber() + " " + property.getStreetName() + ", " + property.getSuburb() + ", " + property.getState();
        address.setText(completeAddress);

        //display trimmed excerpt for description
        int descriptionLength = property.getDescription().length();
        if(descriptionLength >= 100){
            String descriptionTrim = property.getDescription().substring(0, 100) + "...";
            description.setText(descriptionTrim);
        }else{
            description.setText(property.getDescription());
        }

        //set price and rental attributes
        price.setText("$" + String.valueOf(property.getPrice()));
        bedroom.setText("Bed: " + String.valueOf(property.getBedrooms()));
        bathroom.setText("Bath: " + String.valueOf(property.getBathrooms()));
        carspot.setText("Car: " + String.valueOf(property.getCarspots()));*/

        //get the image associated with this property
        int imageID = context.getResources().getIdentifier(plant.getImage(), "drawable", context.getPackageName());
        image.setImageResource(imageID);

        return view;
    }


}
