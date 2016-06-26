package mx.ivajotha.desarrollo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import mx.ivajotha.desarrollo.sql.ItemDataSource;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText m_Usr;
    private EditText m_Password;

    private View m_loading;
    String msjLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //itemDataSource = new ItemDataSource(getApplicationContext());


        /** Obtenemos Valores de campos**/
        m_Usr= (EditText) findViewById(R.id.act_main_user);
        m_Password= (EditText) findViewById(R.id.act_main_pwd);
        findViewById(R.id.act_main_login).setOnClickListener(this);
        findViewById(R.id.act_main_reg).setOnClickListener(this);
        m_loading=findViewById(R.id.progress);

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

        m_loading.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {

                m_loading.setVisibility(View.GONE);

                if(m_user.equals("ivan") && m_pass.equals("entra"))
                {

                    Toast.makeText(getApplicationContext(),"Correcto",Toast.LENGTH_SHORT).show();
                    /**  Comunicación una vez que esta Logueado **/
                    Intent intent= new Intent(getApplicationContext(),ActivityDetail.class);
                    intent.putExtra("key_user",m_user);
                    startActivity(intent);

                } else {
                    String msgFailLogin = getResources().getString(R.string.msg_fail_login);
                    Toast.makeText(getApplicationContext(),msgFailLogin,Toast.LENGTH_SHORT).show();
                }

            }
        }, 1000 * 4);

    }
}
