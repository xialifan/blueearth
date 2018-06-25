package blueearth.opendgwdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends Activity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdPath = Environment.getExternalStorageDirectory().getPath();
                File file = new File(sdPath + "/dwg/dwg.dwg");
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.fromFile(file);
                intent.addCategory("android.intent.category.DEFAULT");

                intent.setDataAndType(uri, "application/dwg");
                startActivity(intent);


            }
        });


    }
}
