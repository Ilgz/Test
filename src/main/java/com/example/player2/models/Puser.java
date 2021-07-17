package com.example.player2.models;

import java.io.Serializable;

public class Puser  implements Serializable {
    private int CurrentPuser;

    public Puser(int currentPuser) {
        CurrentPuser = currentPuser;
    }

    public int getCurrentPuser() {
        return CurrentPuser;
    }

    public void setCurrentPuser(int currentPuser) {
        CurrentPuser = currentPuser;
    }
}
