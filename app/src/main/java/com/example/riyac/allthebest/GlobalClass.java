package com.example.riyac.allthebest;

import android.app.Application;

/**
 * Created by riyac on 30-12-2019.
 */

public class GlobalClass extends Application{

    private String username="7985868489";

    public String getName() {

        return username;
    }

    public void setName(String aName) {

        username = aName;

    }



}
