package com.docmine.mpubattery;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager SM =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        assert SM != null;
        SM.registerListener(this, SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            TextView AxisXText = (TextView)findViewById(R.id.AxisXText);
            TextView AxisYText = (TextView)findViewById(R.id.AxisYText);
            TextView AxisZText = (TextView)findViewById(R.id.AxisZText);
            ImageView GreenLayer = (ImageView) findViewById(R.id.green);
            LinearLayout.LayoutParams laParams=(LinearLayout.LayoutParams)GreenLayer.getLayoutParams();
            float X_lateral = event.values[0];
            float Y_longitudinal = event.values[1];
            float Z_vertical = event.values[2];
            int Yhight = (int)(Y_longitudinal * -120);
            if(Yhight>0){
                laParams.height = Yhight;
            }else {
                laParams.height = 0;
            }
            GreenLayer.setLayoutParams(laParams);
            AxisXText.setText("AxisX: "+X_lateral + "");
            AxisYText.setText("AxisY: "+Y_longitudinal + "");
            AxisZText.setText("AxisZ: "+Z_vertical + "");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
