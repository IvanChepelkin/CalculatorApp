package com.example.user.calculatorapp.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.calculatorapp.R;
import com.example.user.calculatorapp.presenter.ICalcView;
import com.example.user.calculatorapp.presenter.Presenter;

public class MainActivity extends AppCompatActivity implements ICalcView, View.OnClickListener {
    Presenter presenter;

    private TextView resultText;
    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button leftBrecket;
    private Button rightBrecket;
    private Button division;
    private Button multiplication;
    private Button subtraction;
    private Button addition;
    private Button compute;
    private Button delete;
    private Button dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter(this);
        initViews();
        setButtonListener();
    }

    private void initViews() {
        zero = findViewById(R.id.zero);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        leftBrecket = findViewById(R.id.leftBrecket);
        rightBrecket = findViewById(R.id.rightBrecket);
        division = findViewById(R.id.division);
        multiplication = findViewById(R.id.multiplication);
        subtraction = findViewById(R.id.subtraction);
        addition = findViewById(R.id.addition);
        compute = findViewById(R.id.compute);
        delete = findViewById(R.id.del);
        dot = findViewById(R.id.dot);
        resultText = findViewById(R.id.resultText);
    }

    private void setButtonListener() {

        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        leftBrecket.setOnClickListener(this);
        rightBrecket.setOnClickListener(this);
        division.setOnClickListener(this);
        multiplication.setOnClickListener(this);
        subtraction.setOnClickListener(this);
        addition.setOnClickListener(this);
        compute.setOnClickListener(this);
        delete.setOnClickListener(this);
        dot.setOnClickListener(this);

    }


    @Override
    public void setNumberResult(String resultNumber) {
        resultText.append(resultNumber);

    }

    @Override
    public void clearTextView() {
        resultText.setText("");
    }

    @SuppressLint("ShowToast")
    @Override
    public void setError() {
        Toast.makeText(getApplicationContext(), "Некорректное выражение", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.zero):
                presenter.appendSimbol("0");
                break;
            case (R.id.one):
                presenter.appendSimbol("1");
                break;
            case (R.id.two):
                presenter.appendSimbol("2");
                break;
            case (R.id.three):
                presenter.appendSimbol("3");
                break;
            case (R.id.four):
                presenter.appendSimbol("4");
                break;
            case (R.id.five):
                presenter.appendSimbol("5");
                break;
            case (R.id.six):
                presenter.appendSimbol("6");
                break;
            case (R.id.seven):
                presenter.appendSimbol("7");
                break;
            case (R.id.eight):
                presenter.appendSimbol("8");
                break;
            case (R.id.nine):
                presenter.appendSimbol("9");
                break;
            case (R.id.division):
                presenter.appendSimbol("/");
                break;
            case (R.id.multiplication):
                presenter.appendSimbol("*");
                break;
            case (R.id.subtraction):
                presenter.appendSimbol("-");
                break;
            case (R.id.addition):
                presenter.appendSimbol("+");
                break;
            case (R.id.leftBrecket):
                presenter.appendSimbol("(");
                break;
            case (R.id.rightBrecket):
                presenter.appendSimbol(")");
                break;
            case (R.id.compute):
                presenter.computeResult("=");
                break;
            case (R.id.del):
                presenter.clearTextView();
                break;
            case (R.id.dot):
                presenter.appendSimbol(".");
                break;
        }
    }
}
