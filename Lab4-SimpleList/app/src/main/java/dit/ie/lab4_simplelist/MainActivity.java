package dit.ie.lab4_simplelist;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] countries = new String[6];
        countries[0] = "Ireland";
        countries[1] = "Sri Lanka";
        countries[2] = "Saudi Arabia";
        countries[3] = "China";
        countries[4] = "Colombia";
        countries[5] = "Sealand";

        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries));



    }
}
