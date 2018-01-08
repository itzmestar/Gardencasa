package com.gardencasa.gardencasa.database;

/**
 * Created by TARIQUE on 23-04-2017.
 */

public class Plant {
    private long plant_id;
    private String name;
    private String desc;
    private String type;
    private String image;
    private String bloomTime;
    private String flower ;

    public Plant(){}

    public Plant(long plant_id,String name,String desc,String type, String image, String bloomTime,String flower ){
        this.plant_id=plant_id;
        this.name=name;
        this.desc=desc;
        this.type=type;
        this.image=image;
        this.bloomTime=bloomTime;
        this.flower=flower;
    }

    public long getPlantId() {
        return plant_id;
    }

    public void setPlantId(long id) {
        this.plant_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getDesc() {
        return  desc;
    }

    public void setDesc(String desc) {
        this.desc=desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type=type;
    }

    public String getImage(){ return image;}

    public void setImage(String image){  this.image = image; }

    public String getBloomTime() {
        return bloomTime;
    }

    public void setBloomTime(String bloomTime) {
        this.bloomTime=bloomTime;
    }

    public String getFlowering() {
        return flower;
    }

    public void setFlowering(String flower) {
        this.flower = flower;
    }
}
