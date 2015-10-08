package dit.ie.lab4_flashlight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GreenFlashlightActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.green_layout);

        final Button green_button = (Button) findViewById(R.id.press_red);
        green_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(GreenFlashlightActivity.this, RedFlashlightActivity.class);
                startActivity(intent);
            }
        });
    }
}
