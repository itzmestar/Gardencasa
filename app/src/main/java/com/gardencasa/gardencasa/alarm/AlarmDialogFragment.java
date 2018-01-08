package com.gardencasa.gardencasa.alarm;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

/**
 * Created by TARIQUE on 06-05-2017.
 */

public class AlarmDialogFragment extends DialogFragment {

    private int timeHour;
    private int timeMinute;
    private Handler handler;

    public AlarmDialogFragment(Handler handler) {
        this.handler = handler;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        timeHour = bundle.getInt("time_hour");
        timeMinute = bundle.getInt("time_minute");
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeHour = hourOfDay;
                timeMinute = minute;
                Bundle b = new Bundle();
                b.putInt("time_hour", timeHour);
                b.putInt("time_minute", timeMinute);
                Message msg = new Message();
                msg.setData(b);
                handler.sendMessage(msg);
            }
        };
        return new TimePickerDialog(getActivity(), listener, timeHour, timeMinute, false);
    }
}
