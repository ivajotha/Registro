package mx.ivajotha.desarrollo;

import android.app.Fragment;
import android.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import mx.ivajotha.desarrollo.fragment.FragmentList;
import mx.ivajotha.desarrollo.fragment.FragmentProfile;

public class ActivityDetail extends AppCompatActivity implements View.OnClickListener {
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_detail);
        userName=getIntent().getExtras().getString("key_user");
        setTitle("Mi perfil " + userName);
        findViewById(R.id.btnFragmentA).setOnClickListener(this);
        findViewById(R.id.btnFragmentB).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        /**  Cachamos el click y lanzamos los Fragments**/
        switch (v.getId())
        {
            case R.id.btnFragmentA:
                showProfile(userName);
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

    private void showProfile(String userName) {
        /**  Envia Fragment para Mi perfil **/
        FragmentProfile f = FragmentProfile.newInstance(userName);
        getFragmentManager().beginTransaction().replace(R.id.fragmentHolder,f).commit();

    }

}
