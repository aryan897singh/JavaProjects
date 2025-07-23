package com.pluralsight.calcengine;

public class BC_PracticeQuiz {
    public static void main(String[] args) {
       int num1 = 3;
       int num2 = 6;
       sum(num1, num2);


    }

    private static void sum(int a, int b){
        int calculatedSum = 0;
        for (int i = a; i <=b ; i++ ){
            calculatedSum += i;
            System.out.println("The sum from "+ a + " to " + i + " is: " + calculatedSum);
        }

    }




}
