package mx.ivajotha.desarrollo;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import mx.ivajotha.desarrollo.model.ModelItem;
import mx.ivajotha.desarrollo.model.ModelUser;
import mx.ivajotha.desarrollo.service.ServiceTimer;
import mx.ivajotha.desarrollo.sql.ItemDataSource;
import mx.ivajotha.desarrollo.util.PreferenceUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText m_Usr;
    private EditText m_Password;
    private ItemDataSource itemDataSource;
    private CheckBox checkBox;
    private View m_loading;

    private PreferenceUtil preferenceUtil;
    Boolean isRememberMe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferenceUtil= new PreferenceUtil(getApplicationContext());

        itemDataSource = new ItemDataSource(getApplicationContext());

        /** Obtenemos Valores de campos**/
        m_Usr= (EditText) findViewById(R.id.act_main_user);
        m_Password= (EditText) findViewById(R.id.act_main_pwd);
        m_loading=findViewById(R.id.progress);
        checkBox = (CheckBox) findViewById(R.id.chkRememberMe);

        /** Si existen datos se agregagan a los campos y se marca la opcion de recordad **/
        ModelUser modelUser = preferenceUtil.getUser_();
        if ( !(modelUser==null) ){
            m_Usr.setText(modelUser.userName);
            m_Password.setText(modelUser.password);
            checkBox.setChecked(true);
        }


        findViewById(R.id.act_main_login).setOnClickListener(this);
        findViewById(R.id.act_main_reg).setOnClickListener(this);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                /** Verificamos si el usuario quiere recordar su datos.**/
                if (isChecked) {
                    isRememberMe = true;
                }else{
                    isRememberMe = false;
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.act_main_login:
                processData();
                break;

            case R.id.act_main_reg:
                launchRegister();
                break;

        }
    }

    /**  Function Register **/
    private void launchRegister() {
        //Toast.makeText(getApplicationContext(),"Click Register",Toast.LENGTH_SHORT).show();
        Intent intentReg = new Intent(getApplicationContext(), ActivityRegister.class);
        startActivity(intentReg);
    }

    /**  Function Login **/
    private void processData() {

        final String m_user = m_Usr.getText().toString();
        final String m_pass = m_Password.getText().toString();
        final PreferenceUtil util = new PreferenceUtil(getApplicationContext());

        m_loading.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {

                m_loading.setVisibility(View.GONE);

                ModelItem itemDB = new ModelItem();
                itemDB.data_usr = m_user;
                itemDB.data_pwd = m_pass;

                //if(m_user.equals("ivan") && m_pass.equals("entra"))
                ModelUser modelUser= itemDataSource.isRegistered(itemDB);
                if(modelUser !=null)
                {

                    String msgOkLogin = getResources().getString(R.string.title_inter);
                    Toast.makeText(getApplicationContext(),msgOkLogin,Toast.LENGTH_SHORT).show();

                    if(isRememberMe){

                        /** Creamos el archivo de Preferencias Guardamos la información **/

                        util.saveUser(new ModelUser(modelUser.userName, modelUser.password, null, null));

                    }else{
                        util.saveUser(new ModelUser("", "", null, null));
                        //Log.d(ServiceTimer.TAG,"NO RECUERDA");
                        m_Usr.setText("");
                        m_Password.setText("");
                    }

                    /**  Comunicación una vez que esta Logueado **/
                    Intent intent= new Intent(getApplicationContext(),ActivityDetail.class);
                    intent.putExtra("key_user",modelUser.userName);
                    intent.putExtra("key_llogin",modelUser.lastLogin);
                    intent.putExtra("key_id",modelUser.userId);
                    startActivity(intent);
                    startService(new Intent(getApplicationContext(), ServiceTimer.class));

                } else {
                    String msgFailLogin = getResources().getString(R.string.msg_fail_login);
                    Toast.makeText(getApplicationContext(),msgFailLogin,Toast.LENGTH_SHORT).show();

                    isRememberMe = false;
                    util.saveUser(new ModelUser("", "", null, null));
                    m_Usr.setText("");
                    m_Password.setText("");
                    checkBox.setChecked(false);

                }

            }
        }, 1000 * 2);

    }
}
