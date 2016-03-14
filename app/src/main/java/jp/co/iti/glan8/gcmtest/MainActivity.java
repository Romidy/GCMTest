package jp.co.iti.glan8.gcmtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

/**
 */
public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private GCMReceiver upGCMReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button button = (Button) findViewById(R.id.registerBtn);
        // ボタンに OnClickListener インターフェースを実装する
        button.setOnClickListener(new View.OnClickListener() {
            // クリック時に呼ばれるメソッド
            @Override
            public void onClick(View view) {
                final String regId = GCMRegister.getRegistrationId(MainActivity.this);
                if (regId == null || regId.isEmpty()) {
                    // GCMへ端末登録
                    Intent intent = new Intent(MainActivity.this, GCMRegisterService.class);
                    startService(intent);
                }
            }
        });

        TextView text = (TextView) findViewById(R.id.registerText);
        text.setText(GCMRegister.getRegistrationId(this));

        upGCMReceiver = new GCMReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(GCMReceiver.BROADCAST_ACTION_ID);
        registerReceiver(upGCMReceiver, intentFilter);

        upGCMReceiver.registerHandler(updateHandler);

    }

    private Handler updateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            String regId = msg.getData().getString(GCMReceiver.BROADCAST_MESSAGE_ID);
            TextView text = (TextView) findViewById(R.id.registerText);
            text.setText(regId);
        }
    };

    @Override
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
