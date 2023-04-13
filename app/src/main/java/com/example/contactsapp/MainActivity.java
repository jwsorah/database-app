package com.example.contactsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    EditText nameEdit;
    TextView resultView;
    Spinner spinner;
    Spinner spinner2;
    Button buttonAdd;
    Button buttonGet;
    Button buttonDelete;
    DatabaseControl control;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        control = new DatabaseControl(this);

        nameEdit = findViewById(R.id.nameEdit);
        resultView = findViewById(R.id.resultView);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonGet = findViewById(R.id.buttonGet);
        buttonDelete = findViewById(R.id.buttonDelete);
        recyclerView = findViewById(R.id.recyclerView);

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                control.open();
                String developer = control.getDeveloper(nameEdit.getText().toString());
                String year = control.getYear(nameEdit.getText().toString());
                control.close();
                resultView.setText(developer + "  " + year);


            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameEdit.getText().toString();
                control.open();
                control.delete(name);
                control.close();
                onResume();

            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdit.getText().toString();
                String developer = ((TextView) spinner.getSelectedView()).getText().toString();
                String year = ((TextView) spinner2.getSelectedView()).getText().toString();
                control.open();
                boolean itWorked = control.insert(name, developer, year);
                control.close();
                if (itWorked)
                    Toast.makeText(getApplicationContext(), "Added " + name + " " + developer + " " + year, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "FAILED " + name + " " + developer + " " + year, Toast.LENGTH_SHORT).show();
                onResume();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.developers, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);

    }

    @Override
    public void onResume() {

        super.onResume();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        control.open();
        String[] nameArray = control.getAllNamesArray();
        String[] developerArray = control.getAllDevelopersArray();
        String[] yearArray = control.getAllYearsArray();
        control.close();
        recyclerView.setAdapter(new DatabaseAdapter(nameArray, developerArray, yearArray));

        DatabaseAdapter adapter = new DatabaseAdapter(nameArray, developerArray, yearArray);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseAdapter.ViewHolder viewHolder = (DatabaseAdapter.ViewHolder) view.getTag();
                TextView textView = viewHolder.getTextView();
                //TextView textView2 = viewHolder.getTextView2();
                //TextView textView3 = viewHolder.getTextView3();
                resultView.setText(textView.getText().toString());

            }
        });

        recyclerView.setAdapter(adapter);

    }

}