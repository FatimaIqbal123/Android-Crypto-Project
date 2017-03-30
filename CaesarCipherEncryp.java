package com.example.fatima.vdc_authentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Insha Siddiqui on 3/27/2017.
 */

public class CaesarCipherEncryp extends Activity {
    public static Button buttonenc;
    public static TextView encryptedtext;
    public static EditText plain;
    public static EditText key;
    public static String text="";
    public static Button sharingButton;
    public static Button buttonvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caesarcipherencryp);
        plain=(EditText)findViewById(R.id.editText1);
        key=(EditText)findViewById(R.id.editText2);
        encryptedtext=(EditText)findViewById(R.id.editText3);
        buttonenc = (Button)findViewById(R.id.button2);
        buttonvc = (Button)findViewById(R.id.button1);
        sharingButton = (Button)findViewById(R.id.button3);
        //ImageButton sharingButton = new ImageButton(this);
        // sharingButton.setLayoutParams(new ViewGroup.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
        //sharingButton.setImageResource(R.mipmap.ic_share);




        buttonenc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the file chooser dialog
                String text="";
                text=Ceaserencrypt();
                encryptedtext.setText(text);
            }
        });
        buttonvc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the file chooser dialog
                Intent i = new Intent(CaesarCipherEncryp.this, VigenereEncryp.class);
                startActivity(i);
            }
        });

       /* buttondec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the file chooser dialog
                String text1="";
                text1=CeaserDecrypt();
                decryptedtext.setText(text1);
            }
        });*/

        sharingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display the file chooser dialog
                Intent sendIntent = new Intent(android.content.Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,encryptedtext.getText().toString()

                );
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share using"));

                //startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(share, menu);
        getMenuInflater().inflate(R.menu.share, menu);
        MenuItem item = menu.findItem(R.id.menu_item_share);
        ShareActionProvider mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        //ShareActionProvider mShareActionProvider;
        //mShareActionProvider.setShareHistoryFileName("share.xml");
        /*Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, String.valueOf(encryptedtext));
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));*/

        return true;

    }



    public static String  CeaserDecrypt() {
        //String Decrypted="";
        String decrypted = "";
        String s1 = encryptedtext.getText().toString();
        s1=s1.toLowerCase();
        char replace = 0;
        final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i <  s1.length(); i++)
        {

            int c = ALPHABET.indexOf(s1.charAt(i));
            //26 letters of the alphabet so mod by 26
            int val = Integer.parseInt( key.getText().toString() );
            int cey = (c-val) % 26;
            if (cey < 0)
            {
                cey = ALPHABET.length() + cey;
            }
            char replaceVal = ALPHABET.charAt(cey);
            replace = ALPHABET.charAt(cey);
            decrypted+=replace;
        }
        return decrypted;



        //decryptedtext.setText(Decrypted);
    }
    public static String  Ceaserencrypt()
    {
        String encrypted = "";

        String s1 = plain.getText().toString();
        s1=s1.toLowerCase();
        char replace = 0;
        final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
        for(int i = 0; i <  s1.length(); i++)
        {
            String s = plain.getText().toString();
            int c = ALPHABET.indexOf(s1.charAt(i));
            //26 letters of the alphabet so mod by 26
            int val = Integer.parseInt( key.getText().toString() );
            int cey = (val + c) % 26;
            replace = ALPHABET.charAt(cey);
            encrypted+=replace;
        }
        return encrypted;




    }

}

