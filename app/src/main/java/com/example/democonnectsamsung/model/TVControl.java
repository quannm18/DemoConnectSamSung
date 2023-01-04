package com.example.democonnectsamsung.model;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.net.InetAddress;


public class TVControl
{
    String paramStr = "a parameter";
    Runnable myRunnable = createRunnable(paramStr);

    private Runnable createRunnable(final String paramStr){

        Runnable aRunnable = new Runnable(){
            public void run(){
                try {
                    ExecuteCommand(paramStr);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        return aRunnable;

    }



    TextView mytext;
    static String IP = "";
    public static void ExecuteCommand(String message) throws IOException, InterruptedException
    {
        try
        {
            InetAddress address = InetAddress.getByName(IP); // 192 168 0 18
            SamsungRemote remote = new SamsungRemote(address);
            TVReply reply = remote.authenticate("Samsung Tablet");
            if(reply == TVReply.ALLOWED)
            {
                remote.keycode(message);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    private boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}
