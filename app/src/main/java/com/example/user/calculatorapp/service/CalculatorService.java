package com.example.user.calculatorapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CalculatorService {
    private List<String> reversePolandEntryList = new ArrayList<>();

    public CalculatorService() {
    }

    public void setReversePolandEntry(List<String> changeListSimbols) {

        Stack<String> st = new Stack<>();
        int i = 0;

        while (i < changeListSimbols.size()) {

            if (isNumeric(changeListSimbols.get(i))) {  //если i тое значение - число, то отправляем его в стек
                reversePolandEntryList.add(changeListSimbols.get(i));
                i++;
            }

            if (changeListSimbols.get(i).equals("^") & !st.empty()) { //если i тое значение равно + и в стеке что то есть

                if (st.peek().equals("+") || st.peek().equals("-") || st.peek().equals("*") || st.peek().equals("/")) { //если верхний элемент стека равен + или - или * или /

                    reversePolandEntryList.add(st.pop()); //отправляем верхушку стека в польскую запись
                } else if (st.peek().equals("^")) {
                    break;
                }
            } else if (changeListSimbols.get(i).equals("^") & st.empty()) { //если i тое значение равно + и в стеке ничего нет

                st.push(changeListSimbols.get(i)); //записываем + в стек
                i++;
            }


            if (changeListSimbols.get(i).equals("+") & !st.empty()) { //если i тое значение равно + и в стеке что то есть

                if (st.peek().equals("+") || st.peek().equals("-") || st.peek().equals("*") || st.peek().equals("/")) { //если верхний элемент стека равен + или - или * или /

                    reversePolandEntryList.add(st.pop());

                } else if (st.peek().equals("(") || st.peek().equals("^")) { //еще если элемент равен (

                    st.push(changeListSimbols.get(i)); //добавляем элемент в стек
                    i++;
                }
            }


            if (changeListSimbols.get(i).equals("-") & !st.empty()) { //если i тое значение равно - и в стеке что то есть

                if (st.peek().equals("+") || st.peek().equals("-") || st.peek().equals("*") || st.peek().equals("/")) { //если верхний элемент стека равен + или -  или * или /

                    reversePolandEntryList.add(st.pop()); //отправляем верхушку стека в польскую запись
                } else if (st.peek().equals("(") || st.peek().equals("^")) { //еще если элемент равен (

                    st.push(changeListSimbols.get(i));//добавляем в стек
                    i++;
                }
            }

            if (changeListSimbols.get(i).equals("*") & !st.empty()) { //если i тое значение равно * и в стеке что то есть

                if (st.peek().equals("^") || st.peek().equals("+") || st.peek().equals("-") || st.peek().equals("(")) { //если верхний элемент стека равен ^ или + или -  или (

                    st.push(changeListSimbols.get(i)); //записываем в стек
                    i++;
                } else if (st.peek().equals("*") || st.peek().equals("/")) {

                    reversePolandEntryList.add(st.pop());
                }
            }


            if (changeListSimbols.get(i).equals("/") & !st.empty()) { //если i тое значение равно / и в стеке что то есть

                if (st.peek().equals("^") || st.peek().equals("+") || st.peek().equals("-") || st.peek().equals("(")) { //если верхний элемент стека равен + или - или ^ или (

                    st.push(changeListSimbols.get(i)); //записываем / в стек
                    i++;
                } else if (st.peek().equals("*") || st.peek().equals("/")) {

                    reversePolandEntryList.add(st.pop());//отправляем верхушку стека в польскую запись
                }
            }


            if (changeListSimbols.get(i).equals("(") & !st.empty()) { //если i тое значение равно ( и в стеке что то есть

                if (st.peek().equals("^") || st.peek().equals("+") || st.peek().equals("-") || st.peek().equals("*") || st.peek().equals("/") || st.peek().equals("(")) { //если верхний элемент стека равен + или - или * или /

                    st.push(changeListSimbols.get(i)); //записываем ) в стек
                    i++;
                }
            }


            if (changeListSimbols.get(i).equals(")") & !st.empty()) { //если i тое значение равно + и в стеке что то есть

                if (st.peek().equals("+") || st.peek().equals("-") || st.peek().equals("*") || st.peek().equals("/")) { //если верхний элемент стека равен + или - или * или /

                    reversePolandEntryList.add(st.pop()); //отправляем верхушку стека в польскую запись

                    if (st.peek().equals("(")) { //если верхушка стала (
                        st.pop(); //удаляем ее
                        i++;
                    }
                }
            }

        }
        System.out.println("Обратная польская запись " + reversePolandEntryList);

    }

    public String findResult() {

        Stack<Double> stack = new Stack<>();

        double num1; //первое значение стека
        double num2; //второе значение стека
        double answer; //решение
        //(8+2*5)/(1+3*2-4)
        for (int i = 0; i < reversePolandEntryList.size(); i++) {

            if (isNumeric(reversePolandEntryList.get(i))) {//определяем, является ли значение числом
                Double x = Double.parseDouble(reversePolandEntryList.get(i));
                stack.add(x); //Добавляем значение в стек
                System.out.println("Stack " + stack);

            } else if (reversePolandEntryList.get(i).equals("+")) { // если i тое значение +
                num1 = stack.pop();  //верхушку стека присваиваем переменной а
                num2 = stack.pop();  //следущее значение стека присваиваем переменной b
                answer = num1 + num2;        // выполняем действие с этими переменными
                stack.push(answer);    //записываем обратно в стек
                System.out.println("Решение " + stack);

            } else if (reversePolandEntryList.get(i).equals("-")) {
                num1 = stack.pop();
                num2 = stack.pop();
                answer = num2 - num1;
                stack.push(answer);
                System.out.println("Решение " + stack);

            } else if (reversePolandEntryList.get(i).equals("*")) {
                num1 = stack.pop();
                num2 = stack.pop();
                answer = num1 * num2;
                stack.push(answer);
                System.out.println("Решение " + stack);

            } else if (reversePolandEntryList.get(i).equals("/")) {
                num1 = stack.pop();
                num2 = stack.pop();
                answer = num2 / num1;
                stack.push(answer);
                System.out.println("Решение " + stack);
            }
        }

        Double d = stack.pop();//записываем решение уравнения в переменную

        String result = String.valueOf(d);//решаем польскую запись и выводим значение
        System.out.println("ответ " + result);

        return result;
    }

    public void clearList() {
        reversePolandEntryList.clear();
    }

    private boolean isNumeric(String simbol) {
        try {
            double num = Double.parseDouble(simbol);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}