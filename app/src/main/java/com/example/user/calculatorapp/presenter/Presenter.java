package com.example.user.calculatorapp.presenter;

import com.example.user.calculatorapp.service.CalculatorService;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class Presenter {
    private CalculatorService calculatorService;

    private ICalcView ICalcView;
    private List<String> listSimbols = new ArrayList<>();
    private List<String> changeListSimbols = new ArrayList<>();
    private String result = "";

    public Presenter(ICalcView ICalcView) {
        this.ICalcView = ICalcView;
        this.calculatorService = new CalculatorService();
    }

    public void clearTextView() {
        clearLists();
        ICalcView.clearTextView();

    }

    public void appendSimbol(String simbol) {

        // заменяем простое число на составное число
        String newSimbol = "";
        if (isNumeric(simbol) && listSimbols.size() >= 1 && isNumeric(listSimbols.get(listSimbols.size() - 1))) {

            newSimbol = listSimbols.get(listSimbols.size() - 1) + simbol;
            listSimbols.set(listSimbols.size() - 1, newSimbol);
            ICalcView.setNumberResult(simbol);

            // заменяем число на число с плавующей точкой
        } else if (simbol.equals(".") && listSimbols.size() >= 1) {

            newSimbol = listSimbols.get(listSimbols.size() - 1) + simbol;
            listSimbols.set(listSimbols.size() - 1, newSimbol);
            ICalcView.setNumberResult(simbol);

        } else {

            listSimbols.add(simbol);
            ICalcView.setNumberResult(simbol);
        }

        System.out.println("Список символов " + listSimbols);
    }

    public String computeResult(String compute) {

        try {
            // преобразование listSimbols -1+2 >>> ^0-1+2^
            // получение обратной польской записи (1+2)*4 >>> 1 2 + 4 *
            calculatorService.setReversePolandEntry(setChangeListSimbols());
            // считаем результат
            result = calculatorService.findResult();
            // выводим резудьтат на экран
            ICalcView.setNumberResult(compute + result);
            // очищаем листы данных
            clearLists();

            listSimbols.add(result);

        } catch (EmptyStackException e) {
            ICalcView.setError();
        }
        return result;
    }

    private List<String> setChangeListSimbols() {

        changeListSimbols.add("^");

        for (int i = 0; i < listSimbols.size(); i++) {

            if (isNumeric(listSimbols.get(i))) {
                changeListSimbols.add(listSimbols.get(i));
            }
            //если первый эл-т уравнения равен - ,то перед ним ставим ноль
            else if (!isNumeric(listSimbols.get(i)) &&
                    listSimbols.get(i).equals("-") && changeListSimbols.get(0).equals("^") && i == 0) {

                changeListSimbols.add("0"); //добавляем ноль в начало выражения
                changeListSimbols.add(listSimbols.get(i));  //добавляем сам минус -1+2 >>> 0-1+2

            } else if (!isNumeric(listSimbols.get(i)) &&
                    listSimbols.get(i).equals("-") && i > 0) {
                changeListSimbols.add(listSimbols.get(i));
            }
            //если после открывающейся скобки стоит минус, то перед минусом  ставится ноль
            else if (!isNumeric(listSimbols.get(i)) &&
                    listSimbols.get(i).equals("(") && listSimbols.get(i + 1).equals("-")) {
                changeListSimbols.add(listSimbols.get(i));
                changeListSimbols.add("0");
                //если после открывающейся скобки НЕ стоит минус, то просто записываем скобку
            } else if (!isNumeric(listSimbols.get(i)) &&
                    listSimbols.get(i).equals("(") && !listSimbols.get(i + 1).equals("-")) {
                changeListSimbols.add(listSimbols.get(i));
            } else if (!isNumeric(listSimbols.get(i)) && listSimbols.get(i).equals("+")) {
                changeListSimbols.add(listSimbols.get(i));
            } else if (!isNumeric(listSimbols.get(i)) && listSimbols.get(i).equals("*")) {
                changeListSimbols.add(listSimbols.get(i));
            } else if (!isNumeric(listSimbols.get(i)) && listSimbols.get(i).equals("/")) {
                changeListSimbols.add(listSimbols.get(i));
            } else if (!isNumeric(listSimbols.get(i)) && listSimbols.get(i).equals(")")) {
                changeListSimbols.add(listSimbols.get(i));
            }

        }
        changeListSimbols.add("^");
        System.out.println(changeListSimbols);
        return changeListSimbols;
    }

    private boolean isNumeric(String simbol) {
        try {
            double num = Double.parseDouble(simbol);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private void clearLists() {
        listSimbols.clear();
        changeListSimbols.clear();
        calculatorService.clearList();
    }

}

