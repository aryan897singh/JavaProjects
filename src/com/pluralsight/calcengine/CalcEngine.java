package com.pluralsight.calcengine;

import java.util.Scanner;

public class CalcEngine{

    //MAIN METHOD, CONTAINS DEFAULT VALUES, CONTAINS CODE TO RUN CMD LINE TERMINAL BASED ON LENGTH OF INPUT
    public static void main(String[] args) {



   double Lvalues[] = {100.0, 25.0, 225.0, 11.0};
   double Rvalues[] = {50.0, 92.0, 17.0, 3.0};
   char opcodes[] = {'a','s','m','d'};
   double results[] = new double[opcodes.length];
   //ABOVE USED FOR DEFAULT




        double VAL1 = 0;
        double VAL2 = 0;


        // WHEN NO. OF INPUTS IS EXACTLY 3
        if (args.length == 3){
            /* Working with the first argument, i.e. opCode */
            char OPCODE = opCodeFromString(args[0]); //OPCODE = 'a'/'s'/'m'/'d'


            // Working with value 1
            if(args[1].matches("\\d+")){
                VAL1 = Double.parseDouble(args[1]); // type(VAL1) = double - if entered value
            }
            else{
                VAL1 = valueFromWord(args[1]); //Here, the number type is double as well, we just took the word, matched with number value and returned it
            }


            // Working with value 2
            if(args[2].matches("\\d+")){
                VAL2 = Double.parseDouble(args[2]); // type(VAL2) = double - if entered value
            }
            else {
                VAL2 = valueFromWord(args[2]); //Here, the number type is double as well, we just took the word, matched with number value and returned it
            }


            //NOW, HERE OBTAINED VALUES FROM CMD LINE, NEED TO PREFORM OPERATION
            double fresult = execute(OPCODE, VAL1, VAL2 ); //execute method returns the result and stores in fresult variable
            System.out.println(fresult);

        }


   else if (args.length == 0) {
           for(int i = 0 ; i < Lvalues.length ; i++) {
               double fresult = execute(opcodes[i], Lvalues[i], Rvalues[i]);
               System.out.println(fresult);
           }
   }

        else if(args.length == 1 && args[0].equals("interactive") ) {       // ALWAYS USE .equals() for comparison of 2 strings
            System.out.println("Please enter 3 values: opCode, Lval and Rval");
            executeInteractively();
        }
    }

    // EXECUTE METHOD:
    static double execute(char opCode, double LVal, double RVal){
        double result = 0;
        switch (opCode) {
            case 'a':
                result = LVal + RVal;
                break;
            case 's':
                result = LVal - RVal;
                break;
            case 'm':
                result =LVal * RVal;
                break;
            case 'd':
                result = RVal != 0 ? LVal/ RVal : 0.0;
        }
        return result;
    }

    // WHEN CMD LINE EXECUTED, THEN IT TAKES THE THREE VALUES, GOES TO EXECUTE METHOD AND FETCHES RESULT
    static double handlecmdline(String[] array){
        char opCode = array[0].charAt(0);
        double LVal = Double.parseDouble(array[1]);
        double RVal = Double.parseDouble(array[2]);
        double fresult__ = execute(opCode, LVal, RVal);
        return fresult__;


    }

    //METHOD TO TAKE THE FIRST LETTER FROM FIRST INPUT WHEN TERMINAL USED
    static char opCodeFromString(String opName){
        char opCode = opName.charAt(0);
        opCode = Character.toLowerCase(opCode);
        return opCode;
    }


    //METHOD TO TAKE THE NUMBER IN ITS WORD FORM AND CONVERT TO NUMERICAL VALUE IN DOUBLE (0-9)
    static double valueFromWord(String Word){


        String[] numberWords = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        double[] numberValue = {0,1,2,3,4,5,6,7,8,9};


        int obtainedindex = 0;
        for(int i = 0 ; i < numberWords.length ; i++){
            if(numberWords[i].toLowerCase().equals(Word)){
                obtainedindex = i; }
        }


        return numberValue[obtainedindex];






       /*Basically, I need to figure out a way to map the parameter Word to its corresponding value in numberWords array, obtain the index, and return
       the value of the same index of numberValue */


       /* 1. map the parameter Word to its corresponding value in numberWords array
       SOLUTIONS - ok, so in my first array, I can write for( String j : numberWords) { if Word ==  j { indexobtained = ???


       New Solution -  if a variable is out of scope, then just declare it outside instead of inside the for block


       Next problem, how to find index:
       PYTHON CODE:
        for i in range 10:
           if numberWords[i] == Word:
               obtainedindex = i


       JAVA CODE:
       for(int i = 0 ; i < numberWords.length ; i++){
           if(numberWords[i].equals(Word)){
               obtainedindex = i }}






        */




    }

    //METHOD TO EXECUTE WHEN "interactive" ARG IS PASSED IN CMD LINE PROMPT AND USER INPUTS DATA IN INTELLIJ
    static void executeInteractively() {
        Scanner scanner = new Scanner(System.in); //declared Scanner var and named it scanner
        String userInput = scanner.nextLine();
        String[] userInputArray = userInput.split(" ");//The split method for string returns back a string array
        //obtaining the operation
        char opcode = opCodeFromString(userInputArray[0]);

        //working with val1
        double val1;
        if (userInputArray[1].matches("\\d+")) {
            val1 = Double.parseDouble(userInputArray[1]);
        } else val1 = valueFromWord(userInputArray[1]);
        
        //working with val2
        double val2;
        if (userInputArray[2].matches("\\d+"))
            val2 = Double.parseDouble(userInputArray[2]);
        else
            val2 = valueFromWord(userInputArray[2]);

        double result = execute(opcode, val1, val2);
        char symbol = symbolFromOpcode(opcode);

        displayResult(symbol, val1, val2, result);

        }

    //METHOD TO OBTAIN THE SYMBOL FOR A SPECIFIED OPERATION, TO PRINT OUT DURING THE PRINTING OF WHOLE EXPRESSION
    static char symbolFromOpcode(char opcode) {

        char[] opcodes =  {'a', 'm', 's', 'd'};
        char[] opsymbols = {'+', 'x', '-', '/'};

        char symbol ='v';

        for (int i =0; i < opcodes.length ; i++){
            if(opcode == opcodes[i]){
                symbol = opsymbols[i];
                break;
            }

        }

        return symbol;

        //NOTE - Check the valueFromWord() method, I used a slightly different method, I obtained the index
        //and then returned the desired output by simply calling the array and mentioning the index that was obtained

        //IMP LEARNING HERE: we can directly use the == operator for char, but when using String, we should use .equals() because
        // Use .equals() to compare string contents, not memory addresses.
        // "==" checks if both references point to the same object,
        // while .equals() checks if the actual text is the same.

        }

    //METHOD USED TO DISPLAY THE ENTIRE EXPRESSION USING THE STRING BUILDER METHOD
    private static void displayResult(char symbol, double val1, double val2, double result) {
        //USING STRINGBUILDER HERE!
        StringBuilder builder = new StringBuilder(20);
        builder.append(val1);
        builder.append(" ");
        builder.append(symbol);
        builder.append(" ");
        builder.append(val2);
        builder.append(" ");
        builder.append(" = ");
        builder.append(result);

        String println = builder.toString();
        System.out.println(println);



        //IMP LEARNING, USED STRING BUILDER ABOVE, AND NOTE, ALL DATA TYPES DO   NOT   HAVE TO BE A STRING :)
        //Here is what I used before learning this:
        //System.out.println(val1 + " " + symbol + " " + val2 + " = " + result);
        /* Why use StringBuilder?
        - Efficiency: Strings are immutable in Java. Every + creates a new object—StringBuilder avoids that overhead.
        - Best for loops: If you're repeatedly adding to a string (like in a loop or massive output), StringBuilder reduces memory churn.
        - Cleaner for conditional building: When constructing a string with conditions, StringBuilder helps keep the logic tidy.
        */


    }

    //Another learning is to write the function when needed, and pass the rqrd args even before creating it, click Alt + enter
    //and let Intellij create the function and it will automatically specify the rqrd arguments, and you dont need to bother
    //writing the rqrd args again


}
















