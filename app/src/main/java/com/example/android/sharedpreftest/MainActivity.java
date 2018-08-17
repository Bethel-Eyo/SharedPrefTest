package com.example.android.sharedpreftest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText Username;
    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Username= (EditText) findViewById(R.id.username);
        Password= (EditText) findViewById(R.id.password);
    }

    //saving data using SharedPreferences
    public void save (View view){
        SharedPreferences sharedPreferences= getSharedPreferences("myData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name", Username.getText().toString());
        editor.putString("password", Password.getText().toString());
        editor.commit();

        Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_LONG).show();
    }

    //saving data to Internal Storage
    public  void saveToInternalFile(View view) throws IOException {
        String text1=Username.getText().toString();
        String text2=Password.getText().toString();
        File file=null;

        text1=text1+" ";
        file=getFilesDir();
        FileOutputStream fileOutputStream=openFileOutput("candid.txt", Context.MODE_PRIVATE);
        fileOutputStream.write(text1.getBytes());
        fileOutputStream.write(text2.getBytes());
        fileOutputStream.close();
        Toast.makeText(this, "Data Saved Successfully To Internal File Storage" + file+" /candid.txt", Toast.LENGTH_LONG).show();
    }

    public void saveInternalCache(View view) throws IOException {
        String data=Username.getText().toString();
        String data1=Password.getText().toString();
        data=data+" ";
        File folder=getCacheDir();
        File myFile=new File(folder, "mydata.txt");
        writeData(myFile, data, data1);

        Toast.makeText(this, "Data Saved Successfully To Internal Cache" + folder + " mydata.txt", Toast.LENGTH_LONG).show();

    }
    public void saveExternalCache(View view) throws IOException {
        String data=Username.getText().toString();
        String data1=Password.getText().toString();
        data=data+" ";
        File folder=getExternalCacheDir();
        File myFile=new File(folder, "mydata1.txt");
        writeData(myFile, data, data1);

        Toast.makeText(this, "Data Saved Successfully To External Cache" + folder + " /mydata1.txt", Toast.LENGTH_LONG).show();
    }
    public void saveExternalPrivateFile(View view) throws IOException {
        String data=Username.getText().toString();
        String data1=Password.getText().toString();
        data=data+" ";
        File folder=getExternalFilesDir("KinG");
        File myFile=new File(folder, "mydata2.txt");
        writeData(myFile, data, data1);

        Toast.makeText(this, "Data Saved Successfully To External private file" + folder + " /mydata2.txt", Toast.LENGTH_LONG).show();
    }
    public void saveExternalPublicFile(View view) throws IOException {
        String data=Username.getText().toString();
        String data1=Password.getText().toString();
        data=data+" ";
        File folder= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File myFile=new File(folder, "mydata3.txt");
        writeData(myFile, data, data1);

        Toast.makeText(this, "Data Saved Successfully To External public file" + folder + " /mydata3.txt", Toast.LENGTH_LONG).show();
    }

    private void writeData(File myFile, String data, String data1) throws IOException {

        FileOutputStream fileOutputStream=null;
        fileOutputStream=new FileOutputStream(myFile);
        fileOutputStream.write(data.getBytes());
        fileOutputStream.write(data1.getBytes());
        if(fileOutputStream!=null){
            fileOutputStream.close();
        }
    }

    public void next(View view){
        Toast.makeText(this, "Next", Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this,IntentB.class);
        startActivity(intent);
    }
}
