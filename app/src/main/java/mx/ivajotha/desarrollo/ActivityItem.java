package mx.ivajotha.desarrollo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jonathan on 20/06/16.
 */
public class ActivityItem extends AppCompatActivity{
    private String nameItem;
    private ImageView myImage;
    private TextView myName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        setTitle("Detalles");

        /**  Mostramos los del Item Seleccionado **/
        Pattern pat = Pattern.compile("^[a-m|A-M].*");
        nameItem = getIntent().getExtras().getString("key_user");

        Matcher mat = pat.matcher(nameItem);
        myName= (TextView) findViewById(R.id.titleItemInter);
        myImage= (ImageView) findViewById(R.id.imgItem);
        myImage.setBackgroundResource(mat.matches()?R.drawable.ic_thumb_down_black_24dp: R.drawable.ic_thumb_up_black_24dp);
        myName.setText(nameItem);

    }


}
