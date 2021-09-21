package com.example.madapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class HomeActivity extends AppCompatActivity {

    Button buttonCall;
    Button Share;
    Button Blink;
    Button map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        map=findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:4327.3.2005.785"));
                startActivity(in);
            }
        });

        Blink=findViewById(R.id.link);

       Blink.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               gotoUrl("https://github.com/manishkumardubb/SQLiteDB");
           }
       });


        Share=findViewById(R.id.share);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("SHARE TO MORE ");

        Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                String body =" SHare the application"+"\n\nhttps://github.com/manishkumardubb/SQLiteDB";
                String sub="";
                i.putExtra(Intent.EXTRA_TEXT,body);
                i.putExtra(Intent.EXTRA_SUBJECT,sub);
                startActivity(Intent.createChooser(i,"share"));
            }
        });


        buttonCall=findViewById(R.id.btnCall);
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:6005106427"));
                startActivity(intent);
            }
        });


    }

    private void gotoUrl(String s) {
        Uri uri =Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.share){

            ApplicationInfo api= getApplicationContext().getApplicationInfo();
            String apkpath = api.sourceDir;
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("application/vnd.android.package-archive");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(apkpath)));
            startActivity(Intent.createChooser(intent,"share via"));
        }return true;
    }


}
