package mx.ivajotha.desarrollo;

import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.Date;

import mx.ivajotha.desarrollo.model.ModelItem;
import mx.ivajotha.desarrollo.model.ModelUser;
import mx.ivajotha.desarrollo.sql.ItemDataSource;
import mx.ivajotha.desarrollo.util.PreferenceUtil;

/**
 * Created by jonathan on 25/06/16.
 */

public class ActivityRegister extends AppCompatActivity {

    private ItemDataSource itemDataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        itemDataSource = new ItemDataSource(getApplicationContext());

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

                if( TextUtils.isEmpty(gdata_usr) || TextUtils.isEmpty(gdata_pwd) ){

                    String fielRequired = getResources().getString(R.string.msg_fiel_required);
                    Toast.makeText(getApplicationContext(),fielRequired,Toast.LENGTH_SHORT).show();

                }else{

                    /*SimpleDateFormat now = new SimpleDateFormat("yyyyMMdd_HHmmss");
                    String currentDateandTime = sdf.format(new Date());
                    */

                    /*
                    ModelItem itemDB = new ModelItem();
                    itemDB.data_usr = gdata_usr;
                    itemDB.data_pwd = gdata_pwd;
                    itemDB.data_lllog = ""; //"(DATETIME('now'))";
                     */

                    //PreferenceUtil myUtil = new PreferenceUtil(getApplicationContext());
                    //myUtil.saveUser(new ModelUser(gdata_usr,gdata_pwd));

                    String fielRequired = getResources().getString(R.string.msg_register_ok);
                    Toast.makeText(getApplicationContext(),fielRequired,Toast.LENGTH_SHORT).show();

                    finish();

                }


            }
        });
    }
}
