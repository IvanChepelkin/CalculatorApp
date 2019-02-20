package com.example.user.calculatorapp.presenter;

import com.example.user.calculatorapp.service.CalculatorService;

public class Presenter {
    ICalcView calcView;
    CalculatorService calculatorService;

    public Presenter(ICalcView calcView){
        this.calcView = calcView;
        this.calculatorService = new CalculatorService();

    }
}
