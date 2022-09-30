package by.bstu.bdlab62;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public String fileName = "Contacts.txt";

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyStoragePermissions(this);
    }

    public void SearchButtonClick(View view) {
        DatePicker datePicker = findViewById(R.id.datePicker);


        ArrayList<Person> allPersons = GetList();
        ArrayList<Person> foundPersons = new ArrayList<Person>();
        for (Person p: allPersons
        ) {
            String date = datePicker.getDayOfMonth() + "." + (datePicker.getMonth()+1) + "." + datePicker.getYear();
            if(p.DateOfBirth.equals(date)){
                foundPersons.add(p);
            }
        }

        ListView countriesList = findViewById(R.id.personsView);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, foundPersons);
        countriesList.setAdapter(adapter);

    }

    ArrayList<Person> GetList(){
        ArrayList<Person> persons = new ArrayList<Person>();
        File publicFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), fileName);
        try(BufferedReader br = new BufferedReader(new FileReader(publicFile)))
        {
            String s;
            while((s=br.readLine())!=null){
                String[] parts = s.split("/");
                String surname = parts[0];
                String name = parts[1];
                String phone = parts[2];
                String date = parts[3];
                Person p = new Person(surname, name, phone, date);
                persons.add(p);
            }
        }
        catch(IOException ex){

        }
        return persons;
    }
}