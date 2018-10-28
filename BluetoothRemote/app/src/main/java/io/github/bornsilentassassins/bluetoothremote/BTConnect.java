package io.github.bornsilentassassins.bluetoothremote;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class BTConnect extends AppCompatActivity {


    private FloatingActionButton Connect;
    private ListView devicelist;
    private Intent i = null;
    ArrayList list;
    private ArrayAdapter adapter;
    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
    public static String EXTRA_ADDRESS = "searching";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btconnect);

        Connect = (FloatingActionButton) findViewById(R.id.BTconnect);
        devicelist = (ListView) findViewById(R.id.BTlist);
        myBluetooth = BluetoothAdapter.getDefaultAdapter();

        if(myBluetooth == null)
        {
            Toast.makeText(getApplicationContext(), "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();
            finish();
        }
        else if(!myBluetooth.isEnabled())
        {
            Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnBTon,1);
        }
        else{
            pairDevices();
        }

        devicelist.setOnItemClickListener(myListClickListener);

        Connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(i == null)
               {
                   Toast.makeText(getApplicationContext(),"Select a Device",Toast.LENGTH_SHORT).show();
               }
               else startActivity(i);
            }
        });

    }

    private void pairDevices()
    {
        pairedDevices = myBluetooth.getBondedDevices();
        list = new ArrayList();
        i = null;

        if (pairedDevices.size()>0)
        {
            for(BluetoothDevice bt : pairedDevices)
            {
                list.add(bt.getName() + "\n" + bt.getAddress());
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_SHORT).show();
        }

        try {
            adapter.notifyDataSetChanged();
        }
        catch (NullPointerException e){
            adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
            devicelist.setAdapter(adapter);
        }
    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView<?> av, View v, int arg2, long arg3)
        {
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);

            i = new Intent(BTConnect.this,Controller.class);
            i.putExtra(EXTRA_ADDRESS, address);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bt_refresh,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh:
                pairDevices();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
