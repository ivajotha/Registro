package mx.ivajotha.desarrollo;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import mx.ivajotha.desarrollo.fragment.FragmentList;
import mx.ivajotha.desarrollo.fragment.FragmentProfile;
import mx.ivajotha.desarrollo.service.ServiceTimer;

public class ActivityDetail extends AppCompatActivity implements View.OnClickListener {
    private String userName;
    private String userLlogin;
    private TextView txtTimer;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int counter = intent.getExtras().getInt("timer");
            txtTimer.setText(String.format("Session lenght %s seconds",counter));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_detail);
        userName=getIntent().getExtras().getString("key_user");
        userLlogin=getIntent().getExtras().getString("key_llogin");

        String titleInter =  getResources().getString(R.string.title_inter);
        setTitle(titleInter + ", " + userName);

        findViewById(R.id.btnFragmentA).setOnClickListener(this);
        findViewById(R.id.btnFragmentB).setOnClickListener(this);
        txtTimer = (TextView) findViewById(R.id.txtTimer);

        showProfile(userName,userLlogin);
    }

    @Override
    public void onClick(View v) {

        /**  Cachamos el click y lanzamos los Fragments**/
        switch (v.getId())
        {
            case R.id.btnFragmentA:
                showProfile(userName,userLlogin);
                break;
            case R.id.btnFragmentB:
                showList();
                break;
        }
    }

    private void showList() {
        /**  Envia Fragment para la Listas **/
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,new FragmentList()).commit();
    }

    private void showProfile(String userName,String userLlogin) {
        /**  Envia Fragment para Mi perfil **/
        FragmentProfile f = FragmentProfile.newInstance(userName,userLlogin);
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,f).commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ServiceTimer.ACTION_SEND_TIMER);
        registerReceiver(broadcastReceiver,filter);
        Log.d(ServiceTimer.TAG,"OnResume, se reinicia boradcast");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(ServiceTimer.TAG,"onPause quitando broadcast");
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(ServiceTimer.TAG,"OnDestroy, terminando servicio");
        stopService(new Intent(getApplicationContext(),ServiceTimer.class));
    }

}
