package com.example.shivam.tic_tac_toeonline;

import java.util.ArrayList;

public class Box {


    public Boolean getIstaken() {
        return istaken;
    }

    public void setIstaken(Boolean istaken) {
        this.istaken = istaken;
    }

    Boolean istaken ;


    public ArrayList<Integer> getBoxPosition() {
        return boxPosition;
    }

    public void setBoxPosition(ArrayList<Integer> boxPosition) {
        this.boxPosition = boxPosition;
    }

    ArrayList boxPosition ;




    public Boolean getTakenType() {
        return takenType;
    }

    public void setTakenType(Boolean takenType) {
        this.takenType = takenType;
    }

    Boolean takenType ;

}
