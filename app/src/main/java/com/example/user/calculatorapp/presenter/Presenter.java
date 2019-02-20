package com.example.user.calculatorapp.presenter;

public class Presenter {
    ICalcView calcView;
    public Presenter(ICalcView calcView){
        this.calcView = calcView;
    }
}
