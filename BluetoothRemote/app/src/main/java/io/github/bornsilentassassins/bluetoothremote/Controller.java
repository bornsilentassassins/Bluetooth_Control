package io.github.bornsilentassassins.bluetoothremote;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class Controller extends AppCompatActivity {

    private int data = 0;
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent newint = getIntent();
        address = newint.getStringExtra(BTConnect.EXTRA_ADDRESS);
        setContentView(R.layout.activity_controller);

        Button leftUp = (Button) findViewById(R.id.left_up);
        Button leftDown = (Button) findViewById(R.id.left_down);
        Button rightUp = (Button) findViewById(R.id.right_up);
        Button rightDown = (Button) findViewById(R.id.right_down);
        Button armUp = (Button) findViewById(R.id.arm_up);
        Button armDown = (Button) findViewById(R.id.arm_down);
        Button open = (Button) findViewById(R.id.open);
        Button close = (Button) findViewById(R.id.close);

        new ConnectBT().execute();

        close.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    data &= 191;
                    data |= 128;
                    sendData();
//                    Log.e("data",Integer.toString(data));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    data &= 63;
                    sendData();
//                    Log.e("data",Integer.toString(data));
                }
                return true;
            }
        });

        open.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    data &= 127;
                    data |= 64;
                    sendData();
//                    Log.e("data",Integer.toString(data));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    data &= 63;
                    sendData();
//                    Log.e("data",Integer.toString(data));
                }
                return true;
            }
        });

        armDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    data &= 239;
                    data |= 32;
                    sendData();
//                    Log.e("data",Integer.toString(data));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    data &= 207;
                    sendData();
//                    Log.e("data",Integer.toString(data));
                }
                return true;
            }
        });

        armUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    data &= 223;
                    data |= 16;
                    sendData();
//                    Log.e("data",Integer.toString(data));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    data &= 207;
                    sendData();
//                    Log.e("data",Integer.toString(data));
                }
                return true;
            }
        });

        rightDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    data &= 251;
                    data |= 8;
                    sendData();
//                    Log.e("data",Integer.toString(data));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    data &= 243;
                    sendData();
//                    Log.e("data",Integer.toString(data));
                }
                return true;
            }
        });

        rightUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    data &= 247;
                    data |= 4;
                    sendData();
//                    Log.e("data",Integer.toString(data));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    data &= 243;
                    sendData();
//                    Log.e("data",Integer.toString(data));
                }
                return true;
            }
        });

        leftDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    data &= 254;
                    data |= 2;
                    sendData();
//                    Log.e("data",Integer.toString(data));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    data &= 252;
                    sendData();
//                    Log.e("data",Integer.toString(data));
                }
                return true;
            }
        });

        leftUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    data &= 253;
                    data |= 1;
                    sendData();
//                    Log.e("data",Integer.toString(data));
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    data &= 252;
                    sendData();
//                    Log.e("data",Integer.toString(data));
                }
                return true;
            }
        });


    }

    private void sendData()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write((Integer.toString(data) + '\n').getBytes());
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void msg(String s)
    {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(Controller.this, "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice HC05 = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = HC05.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            }
            else
            {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }
}

