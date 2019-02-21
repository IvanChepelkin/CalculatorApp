package com.example.user.calculatorapp;

import com.example.user.calculatorapp.presenter.ICalcView;
import com.example.user.calculatorapp.presenter.Presenter;
import com.example.user.calculatorapp.service.CalculatorService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {

    private Presenter presenter;
    @Mock
    private ICalcView calcView;

    @Mock
    private CalculatorService calculatorService;

    private static final String VALUE_A = "2";
    private static final String VALUE_B = "3";
    private static final String addition = "+";
    private static final String subtraction = "-";
    private static final String multiplication = "*";
    private static final String division = "/";
    private static final String compute = "=";
    private static final String dot = ".";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new Presenter(calcView, calculatorService);
    }

    @Test
    public void testAddition() {
        presenter.appendSimbol(VALUE_A);
        presenter.appendSimbol(addition);
        presenter.appendSimbol(VALUE_B);

        String result = Double.toString(Double.parseDouble(VALUE_A) + Double.parseDouble(VALUE_B));

        assertThat("Addition was executed not correctly", presenter.computeResult(compute), is(equalTo(result)));

    }

    @Test
    public void testSubtraction() {
        presenter.appendSimbol(VALUE_A);
        presenter.appendSimbol(subtraction);
        presenter.appendSimbol(VALUE_B);

        String result = Double.toString(Double.parseDouble(VALUE_A) - Double.parseDouble(VALUE_B));

        assertThat("Subtraction was executed not correctly", presenter.computeResult(compute), is(equalTo(result)));

    }

    @Test
    public void testMultiplication() {
        presenter.appendSimbol(VALUE_A);
        presenter.appendSimbol(multiplication);
        presenter.appendSimbol(VALUE_B);

        String result = Double.toString(Double.parseDouble(VALUE_A) * Double.parseDouble(VALUE_B));

        assertThat("Multiplication was executed not correctly", presenter.computeResult(compute), is(equalTo(result)));

    }

    @Test
    public void testDivision() {
        presenter.appendSimbol(VALUE_A);
        presenter.appendSimbol(division);
        presenter.appendSimbol(VALUE_B);

        String result = Double.toString(Double.parseDouble(VALUE_A) / Double.parseDouble(VALUE_B));

        assertThat("Division was executed not correctly", presenter.computeResult(compute), is(equalTo(result)));

    }

    @Test
    public void testDot() {
        presenter.appendSimbol(VALUE_A);
        presenter.appendSimbol(dot);
        presenter.appendSimbol(VALUE_A);
        presenter.appendSimbol(VALUE_A);
        presenter.appendSimbol(division);
        presenter.appendSimbol(VALUE_B);

        String result = Double.toString(Double.parseDouble(VALUE_A + dot + VALUE_A + VALUE_A) / Double.parseDouble(VALUE_B));

        assertThat("Division was executed not correctly", presenter.computeResult(compute), is(equalTo(result)));

    }

    @Test
    public void testSetNumverResutCall() {
        presenter.appendSimbol(VALUE_A);
        verify(calcView).setNumberResult(VALUE_A);

    }

    @Test
    public void testClearTextCall() {
        presenter.clearTextView();
        verify(calcView).clearTextView();
    }

    @Test
    public void testSetErrorCall() {
        presenter.appendSimbol(VALUE_A);
        presenter.appendSimbol(subtraction);
        presenter.appendSimbol(subtraction);
        presenter.appendSimbol(VALUE_B);
        presenter.computeResult(compute);
        verify(calcView).setError();

    }

}

