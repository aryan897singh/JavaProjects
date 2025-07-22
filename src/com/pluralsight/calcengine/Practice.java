package com.pluralsight.calcengine;


//My brain stopped functioning because I was tired from a long day
//I went to grab a snack and came back and got it in under 5 mins :)
public class Practice {
    public static void main(String[] args) {
        int howMany = 7;
        String s1 = "^";
        String s2 = "*";
        for (int i = 1; i <= howMany; i++) {
            for (int j = i - 1; j >0; j--){
                System.out.print(s1);
            }

            for (int k = howMany - i; k > 0; k--){
                System.out.print(s2);

            }
            System.out.println("\n");

        }
    }
}






















//Immediate course of action
//Ok, here's the plan
//We want to make sure that the user inputs the fist letter to be when
//Then if the user entered when, we're gonna check the second entry which should be a date in the yyyy-mm-dd format
//Just for coding practice and complexity, we will take the input of the year, month and day, then format string it
//Then, we will make a new function thats called plus days, and take the input of the number of days the user wants to add
//Then we print out the final result after the addition of the days

//Later Task:
//Another thing we want to do is add variation to the input in opcode as well as numbers and see how we can implement
//the AI like mistake detector in here


