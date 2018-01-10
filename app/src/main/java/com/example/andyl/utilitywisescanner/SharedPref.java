package com.example.andyl.utilitywisescanner;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by  on 10/01/2018.
 */

public class SharedPref {
    SharedPreferences mySharedPref;
    public SharedPref(Context context){
        mySharedPref = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
    }
    //this method will save the dark mode state: true or false
    public void setDarkModeState(Boolean state){
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("DarkMode",state);
        editor.commit();
    }

    //this method will load the dark mode state
    public Boolean loadDarkModeState(){
        Boolean state = mySharedPref.getBoolean("DarkMode",false);
        return state;
    }
}
