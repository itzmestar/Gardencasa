package com.gardencasa.gardencasa.database;

/**
 * Created by TARIQUE on 23-04-2017.
 */

public class Alarm {
    private long alarm_id;
    private String name;
    private String desc;
    private String type;
    private String image;
    private String bloomTime;
    private String flower ;
    private long timeHour;
    private long timeMinute;
    private String time;
    private long active;

    public Alarm(){
    }

    public Alarm(long alarm_id,String name,String desc,String type, String image, String bloomTime,String flower, long timeHour, long timeMinute ){
        this.alarm_id=alarm_id;
        this.name=name;
        this.desc=desc;
        this.type=type;
        this.image=image;
        this.bloomTime=bloomTime;
        this.flower=flower;
        this.timeHour=timeHour;
        this.timeMinute=timeMinute;
        this.active=1;
        this.time=Long.toString(timeHour)+":"+Long.toString(timeMinute);
    }

    public Alarm(long alarm_id,String name,String desc,String type, String image, String bloomTime,String flower ){
        this.alarm_id=alarm_id;
        this.name=name;
        this.desc=desc;
        this.type=type;
        this.image=image;
        this.bloomTime=bloomTime;
        this.flower=flower;
        this.active=0;
        this.time=null;
    }

    public long getAlarmId() {
        return alarm_id;
    }

    public void setAlarmId(long alarm_id) {
        this.alarm_id = alarm_id;
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

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public long getTimeHour() {
        return timeHour;
    }
    public void setTimeHour(long timeHour) {
        this.timeHour = timeHour;
    }

    public long getTimeMinute() {
        return timeMinute;
    }
    public void setTimeMinute(long timeMinute) {
        this.timeMinute = timeMinute;
    }

    public long getActive() {
        return active;
    }
    public void setActive(long active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return name;
    }
}
