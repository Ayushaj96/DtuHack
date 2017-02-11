package com.codebusters.medlife;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button simpleNotificationButton, bigStyleNotificationButton;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bigStyleNotificationButton = (Button) findViewById(R.id.big);

        bigStyleNotificationButton.setOnClickListener(this);
        lst=(ListView)findViewById(R.id.list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton FAB1 = (FloatingActionButton) findViewById(R.id.fab1);
        FloatingActionButton FAB2 = (FloatingActionButton) findViewById(R.id.fab2);
        /*final Button button1=(Button) findViewById(R.id.settings);
        button1.setOnClickListener(new View.OnClickListener(){
                               @Override
                               public void onClick(View v) {
                                   Intent intent1=new Intent(MainActivity.this,Setting.class);
                                   startActivity(intent1);

                               }
                           });

*/

        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatingActionButton FAB1 = (FloatingActionButton) findViewById(R.id.fab1);
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                if (FAB1.getVisibility() == View.INVISIBLE)
                    fab1();
                else
                    fab2();
            }
        });
        FAB2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Record.class);
                startActivity(intent);

            }
        });
        FAB1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);

            }
        });
       // String medicine[];
          //  Intent intent=getIntent();

        //medicine[0]=intent.getStringExtra("key");
       // ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_main, Integer.parseInt(medicine[0]));
//        ArrayAdapter<String> itemsAdapter =
  //              new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, (List<String>) lst);
    //        lst.setAdapter(itemsAdapter);
String[] StringArray= new String[]{"crocin", "Paracetamol"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.content_main,StringArray);
       lst.setAdapter(adapter);
    }





    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
    void fab1()
    {FloatingActionButton FAB1=(FloatingActionButton)findViewById(R.id.fab1);
        FloatingActionButton FAB2=(FloatingActionButton)findViewById(R.id.fab2);

        FAB1.setVisibility(View.VISIBLE);
        FAB2.setVisibility(View.VISIBLE);
    }
    void fab2(){
        FloatingActionButton FAB1=(FloatingActionButton)findViewById(R.id.fab1);
        FloatingActionButton FAB2=(FloatingActionButton)findViewById(R.id.fab2);

        FAB1.setVisibility(View.INVISIBLE);
        FAB2.setVisibility(View.INVISIBLE);

    }
    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
// Show menu icon
        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator();
        ab.setDisplayHomeAsUpEnabled(true);

    }


            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.big:
                        displayBigStyleNotification();
                        break;
                }
            }
    private void displayBigStyleNotification()  {
        String message = "You are reminded to take your medicines on time..";

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this);
        builder.setContentTitle("Notification!");
        builder.setContentText("Ping");
        builder.setDefaults(Notification.DEFAULT_ALL); // requires VIBRATE permission
        builder.setStyle(new NotificationCompat.BigTextStyle()
                .bigText(message));
        builder.addAction(android.R.drawable.ic_menu_add,

                "Add", null);
        builder.addAction(android.R.drawable.ic_menu_close_clear_cancel,
                "Close", null);
        NotificationManager manager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(2, builder.build());
    }


}
