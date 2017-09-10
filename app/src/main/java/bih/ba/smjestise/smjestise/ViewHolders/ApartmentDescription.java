package bih.ba.smjestise.smjestise.ViewHolders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import bih.ba.smjestise.smjestise.R;

public class ApartmentDescription extends AppCompatActivity {

    private TextView opis;
    private TextView naziv_apartmana;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_description);

        /*to have back button*/
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        opis=(TextView)findViewById(R.id.opis);
        naziv_apartmana=(TextView)findViewById(R.id.naziv_apartmana);
        Bundle bundle = getIntent().getExtras();

        String opis_apartmana=bundle.getString("opis");
        String naziv_apartmana_value=bundle.getString("naziv_apartmana");
        opis.setText(opis_apartmana);
        naziv_apartmana.setText(naziv_apartmana_value);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


}
