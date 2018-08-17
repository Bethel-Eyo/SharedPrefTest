package com.example.android.sharedpreftest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IntentB extends AppCompatActivity {

    public static final String DEFAULT="N/A";
    TextView usernameTextView, passwordTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_b);
        usernameTextView= (TextView) findViewById(R.id.userText);
        passwordTextView= (TextView) findViewById(R.id.passText);
    }

   public void load(View view){
       SharedPreferences sharedPreferences= getSharedPreferences("myData", Context.MODE_PRIVATE);
       String Name=sharedPreferences.getString("name", DEFAULT);
       String Pass=sharedPreferences.getString("password", DEFAULT);
       if (Name==DEFAULT || Pass==DEFAULT){
           Toast.makeText(this, " Sorry!! Data Was Not Found", Toast.LENGTH_LONG).show();
       }
       else {
           Toast.makeText(this, "Data Loaded Successfully", Toast.LENGTH_LONG).show();
           usernameTextView.setText(Name);
           passwordTextView.setText(Pass);
       }
   }

    public void loadFromInternalStorage(View view) throws IOException {
        /*
        * what you see when you read the file??
        * will be string you inputed right??
        * like for example if you typed "bethel" as username and "12345" as password
        * you would expect to the same output when you click on the load button i.e bethel 12345
        * but what is actually contained in the internal storage are some strings you inputed converted to bytes
        * like for example.. something like
         * 101 118 105 122 32 46*/
        FileInputStream fileInputStream=openFileInput("candid.txt");
        /* reads data byte after byte for example it reads 101, then reads 118 and so on...
        * if it does not find any data in a byte... say 101, in returns -1*/
        int read=-1;
        //the Ordinary String datatype does not allow modification of data directly but StringBuffer allows that
        StringBuffer buffer=new StringBuffer();
        while ((read=fileInputStream.read())!= -1){
            /*adds each byte to each StringBuffer; and then the data in typcasted
            to character"CHAR" so u can get back the data in the format which you saved it*/
            buffer.append((char)read);
        }
        /*split the single buffer which will come out as "bethel 12345"
        to display both in the username and password column respectively*/
        String text1=buffer.substring(0, buffer.indexOf(" "));
        String text2=buffer.substring(buffer.indexOf(" ")+1);

        usernameTextView.setText(text1);
        passwordTextView.setText(text2);
        fileInputStream.close();


    }

    public  void loadFromInternalCache(View view) throws IOException {
        File folder=getCacheDir();
        File myFile= new File(folder, "mydata.txt");
        FileInputStream fileInputStream=null;
        fileInputStream= new FileInputStream(myFile);
        int read=-1;
        StringBuffer stringBuffer=new StringBuffer();
        while ((read=fileInputStream.read())!= -1){
            stringBuffer.append((char)read);
        }
        if (fileInputStream!=null) {
            fileInputStream.close();
        }
        String textA=stringBuffer.substring(0, stringBuffer.indexOf(" "));
        String textZ= stringBuffer.substring(stringBuffer.indexOf(" ")+1);
        usernameTextView.setText(textA);
        passwordTextView.setText(textZ);

    }
    public  void loadFromExternalCache(View view) throws IOException {
        File folder=getExternalCacheDir();
        File myFile= new File(folder, "mydata1.txt");
        FileInputStream fileInputStream=null;
        fileInputStream= new FileInputStream(myFile);
        int read=-1;
        StringBuffer stringBuffer=new StringBuffer();
        while ((read=fileInputStream.read())!= -1){
            stringBuffer.append((char)read);
        }
        if (fileInputStream!=null) {
            fileInputStream.close();
        }
        String textA=stringBuffer.substring(0, stringBuffer.indexOf(" "));
        String textZ= stringBuffer.substring(stringBuffer.indexOf(" ")+1);
        usernameTextView.setText(textA);
        passwordTextView.setText(textZ);

    }
    public  void loadFromExternalPrivate(View view) throws IOException {
        File folder=getExternalFilesDir("KinG");
        File myFile= new File(folder, "mydata2.txt");
        FileInputStream fileInputStream=null;
        fileInputStream= new FileInputStream(myFile);
        int read=-1;
        StringBuffer stringBuffer=new StringBuffer();
        while ((read=fileInputStream.read())!= -1){
            stringBuffer.append((char)read);
        }
        if (fileInputStream!=null) {
            fileInputStream.close();
        }
        String textA=stringBuffer.substring(0, stringBuffer.indexOf(" "));
        String textZ= stringBuffer.substring(stringBuffer.indexOf(" ")+1);
        usernameTextView.setText(textA);
        passwordTextView.setText(textZ);


    }


    public  void loadFromExternalPublic(View view) throws IOException {
        File folder= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File myFile= new File(folder, "mydata3.txt");
        FileInputStream fileInputStream=null;
        fileInputStream= new FileInputStream(myFile);
        int read=-1;
        StringBuffer stringBuffer=new StringBuffer();
        while ((read=fileInputStream.read())!= -1){
            stringBuffer.append((char)read);
        }
        if (fileInputStream!=null) {
            fileInputStream.close();
        }
        String textA=stringBuffer.substring(0, stringBuffer.indexOf(" "));
        String textZ= stringBuffer.substring(stringBuffer.indexOf(" ")+1);
        usernameTextView.setText(textA);
        passwordTextView.setText(textZ);

    }


    public void previous(View view){
        Toast.makeText(this, "Previous", Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
