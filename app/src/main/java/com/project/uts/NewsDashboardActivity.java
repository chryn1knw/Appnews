package com.project.uts;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewsDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        ArrayList<String> languages = new ArrayList<String>();
        languages.add("Kotlin");
        languages.add("Swift");
        languages.add("Dart");
        languages.add("C#");
        languages.add("Javascript");
        languages.add("Java");

        ListView lv_languages = (ListView) findViewById(R.id.lv_languages);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, languages);
        lv_languages.setAdapter(arrayAdapter);

        lv_languages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "You choose " + selected, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
