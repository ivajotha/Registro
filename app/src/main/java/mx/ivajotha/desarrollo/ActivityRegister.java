package mx.ivajotha.desarrollo;

import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import mx.ivajotha.desarrollo.model.ModelUser;
import mx.ivajotha.desarrollo.util.PreferenceUtil;

/**
 * Created by jonathan on 25/06/16.
 */

public class ActivityRegister extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        String title_layaut = getResources().getString(R.string.title_register);
        setTitle(title_layaut);
        final EditText data_usr = (EditText) findViewById(R.id.mUserRegister);
        final EditText data_pwd = (EditText) findViewById(R.id.mPasswordRegister);
        /** Call myUtil**/
        findViewById(R.id.btnRegisterUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gdata_usr = data_usr.getText().toString();
                String gdata_pwd= data_pwd.getText().toString();

                if(gdata_usr.equals("") || gdata_pwd.equals("")){

                    String fielRequired = getResources().getString(R.string.msg_fiel_required);
                    Toast.makeText(getApplicationContext(),fielRequired,Toast.LENGTH_SHORT).show();

                }else{

                    PreferenceUtil myUtil = new PreferenceUtil(getApplicationContext());
                    myUtil.saveUser(new ModelUser(gdata_usr,gdata_pwd));

                    String fielRequired = getResources().getString(R.string.msg_register_ok);
                    Toast.makeText(getApplicationContext(),fielRequired,Toast.LENGTH_SHORT).show();

                    finish();

                }


            }
        });
    }
}
