package com.pluralsight.calcengine;

import java.time.LocalDate;
import java.util.Scanner;

public class SwissArmyCalc {

    //MAIN METHOD, CONTAINS DEFAULT VALUES, CONTAINS CODE TO RUN CMD LINE TERMINAL BASED ON LENGTH OF INPUT
    public static void main(String[] args) {

   double Lvalues[] = {100.0, 25.0, 225.0, 11.0};
   double Rvalues[] = {50.0, 92.0, 17.0, 3.0};
   char opcodes[] = {'a','s','m','d'};
   double results[] = new double[opcodes.length];
   //ABOVE USED FOR DEFAULT

   MathEquation[] equations = new MathEquation[Lvalues.length];

   for(int i = 0; i< Lvalues.length ; i++){
       equations[i] = createMathEquationObject(Lvalues[i],Rvalues[i],opcodes[i]);

       //ok so basically what we want to do is create a MathEquation array, and store objects in them
       //SO we're going to run a for loop, and create a new object
   }

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

            System.out.println("\n Please Select one of the options from the following \n " +
                    "\n 1.Basic Arithmetic {Enter B}" +
                    "\n 2.Date Arithmetics {Enter D}" +
                    "\n 3.Tax and Interest Calculator {Enter T}"
                    //"\n 5.Unit Convertor {Enter U}" - TO BE DONE IN FREE TIME
            );

            Scanner userCalcChoice = new Scanner(System.in);
            String userCalcChoiceString = userCalcChoice.nextLine();


            switch (userCalcChoiceString.toUpperCase()){
                case "B":
                    System.out.println("Please enter 3 values: Operation to be preformed (+,-,*,/ (spelled out)), Value1 and Value2");
                    executeInteractively();
                    break;
                case "D":
                    handleWhen();
                    break;
                case "T":
                    handleTaxes();
                    break;
                case "A":
                    break;
                case "U":
                    break;
            }




        }
    }

    private static MathEquation createMathEquationObject(double lvalue, double rvalue, char opcode) {
        MathEquation equation = new MathEquation();
        equation.LVal = lvalue;
        equation.RVal = rvalue;
        equation.opCode = opcode;

        return equation;
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
                result = RVal != 0 ? LVal/ RVal : 0.0;  //NOTE: When you do a/b it will give only int value if a,b are integers
                                                        //V.V. IMP!!! Mistake when making programs
        }
        return result;
    }

    // WHEN CMD LINE EXECUTED, THEN IT TAKES THE THREE VALUES, GOES TO EXECUTE METHOD AND FETCHES RESULT
    static double handleCmdLine(String[] array){
        char opCode = array[0].charAt(0);
        double LVal = Double.parseDouble(array[1]);
        double RVal = Double.parseDouble(array[2]);
        double fresult__ = execute(opCode, LVal, RVal);
        return fresult__;


    }

    //METHOD TO TAKE THE FIRST LETTER (LOWERCASE) FROM FIRST INPUT WHEN TERMINAL USED
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
        String userInput = scanner.nextLine(); //.nextLine(); will take a one line string from user
        String[] userInputArray = userInput.split(" ");//The split method for string returns back a string array
        //obtaining the operation
        char opcode = opCodeFromString(userInputArray[0]);


        if (userInputArray.length == 3) {
            //working with val1
            double val1;
            if (userInputArray[1].matches("\\ d+")) {
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

        /* else if (userInputArray.length == 1 && userInputArray[0].toLowerCase().equals("w")) {
            handleWhen(); */


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
        /*
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

            */

        /* Why use StringBuilder?
        - Efficiency: Strings are immutable in Java. Every + creates a new object—StringBuilder avoids that overhead.
        - Best for loops: If you're repeatedly adding to a string (like in a loop or massive output), StringBuilder reduces memory churn.
        - Cleaner for conditional building: When constructing a string with conditions, StringBuilder helps keep the logic tidy.
        */

        /* Can also use string formatting: */

        //String finalresult = String.format("%f %C %f ", val1, symbol, val2 + " = " + "%f", result); THIS IS WRONG
        //Important: %c will give lowercase char and %C will give uppercase char (if applicable)
        //Important: %f is used for double type, if %d used, it will give an error

        //can specify exactly which index we want to match the format specifier to the argument [Index starts from 1]
        // "%index$d"
        // ex. String.format(" %2$d, %1$d, %3$d ", val1, val2 val3 ==  (O/P): val2 val1 val3

        // String.format(" %2d, %<d, %3d ", val1, val2 val3 ==  (O/P): val2 val2 val3
        //In this example the second format specifier got whatever the first format specifier got

        String finalresult = String.format("%.2f %c %.2f = %.2f", val1, symbol, val2, result);
        System.out.println(finalresult);



    }

    //METHOD TO HANDLE WHEN THE USER TRIES TO DO DATE ARITHMETIC
    private static void handleWhen(){

        int days;
        int months;
        int years;
        System.out.println("This Calculator lets you know the date after a specific number of days, weeks, months or years");
        System.out.println("Enter the initial Date in YYYY-MM-DD format ");
        Scanner scanner1 = new Scanner(System.in);
        //scanner1.nextLine();
        String userInputDate = scanner1.nextLine(); //So we are taking the input date and assigning it to userInputDate
        //System.out.println("DEBUG - InputDate = " + userInputDate);

        //Now checking if the user wants to add days, months or years

        System.out.println("Do you want to add " +
                "\n 1. Years {Y}" +
                "\n 2. Months {M}" +
                "\n 3. Days {D}");

        Scanner dateArithmeticAddnType = new Scanner(System.in);
        String dateArithmeticAddnTypeString = dateArithmeticAddnType.nextLine();

        switch (dateArithmeticAddnTypeString.toLowerCase()){
            case "y":
                System.out.println("Enter the number of Years to be added ");
                Scanner YearCount = new Scanner(System.in);
                String userAddYearCount = YearCount.nextLine(); //We are taking the number of days to be added based on below cases



                if (userAddYearCount.matches("\\d+")){
                    years = Integer.parseInt(userAddYearCount); //If input is digits, we will automatically convert to int
                }
                else{
                    double YearsToBeAdded = valueFromWord(userAddYearCount); //If input is spelling, we use valueFromWord method
                    years = (int) YearsToBeAdded;
                }
                //Years to be added is obtained in the var years

                executeDateArithmetic_YEARS(userInputDate, years);
                break;


            case "m":
                System.out.println("Enter the number of Months to be added ");
                Scanner Monthcount = new Scanner(System.in);
                String userAddMonthCount = Monthcount.nextLine(); //We are taking the number of months to be added based on below cases


                if (userAddMonthCount.matches("\\d+")){
                    months = Integer.parseInt(userAddMonthCount); //If input is digits, we will automatically convert to int
                }
                else{
                    double daysToBeAdded = valueFromWord(userAddMonthCount); //If input is spelling, we use valueFromWord method
                    months = (int) daysToBeAdded;
                }
                //Months to be added is obtained in the var months

                executeDateArithmetic_MONTHS(userInputDate, months);
                break;

            case "d":
                System.out.println("Enter the number of days to be added ");
                Scanner DayCount = new Scanner(System.in);
                String userAddDayCount = DayCount.nextLine(); //We are taking the number of days to be added based on below cases



                if (userAddDayCount.matches("\\d+")){
                    days = Integer.parseInt(userAddDayCount); //If input is digits, we will automatically convert to int
                }
                else{
                    double daysToBeAdded = valueFromWord(userAddDayCount); //If input is spelling, we use valueFromWord method
                    days = (int) daysToBeAdded;
                }
                //Days to be added is obtained in the var days

                executeDateArithmetic_DAYS(userInputDate, days);
                break;

        }

    }

    //METHOD TO ADD THE NUMBER OF MONTHS TO A SEPCIFIED START DATE AND PRINTS OUT THE RESULT DATE
    private static void executeDateArithmetic_MONTHS(String userInputDate, int months) {
        LocalDate startDate = LocalDate.parse(userInputDate);
        LocalDate resultDate = startDate.plusMonths((long) months);
        String finalDate = resultDate.toString();
        String output = String.format("The date after %2$d days from %3$s is %1$s", resultDate, months, startDate);
        System.out.println(output);

    }

    //METHOD TO ADD THE NUMBER OF YEARS TO A SEPCIFIED START DATE AND PRINTS OUT THE RESULT DATE
    private static void executeDateArithmetic_YEARS(String userInputDate, int years) {
        LocalDate startDate = LocalDate.parse(userInputDate);
        LocalDate resultDate = startDate.plusYears((long) years);
        String finalDate = resultDate.toString();
        String output = String.format("The date after %2$d days from %3$s is %1$s", resultDate, years, startDate);
        System.out.println(output);

    }

    //METHOD TO ADD THE NUMBER OF DAYS TO A SEPCIFIED START DATE AND PRINTS OUT THE RESULT DATE
    static void executeDateArithmetic_DAYS(String userInputDate, int days) {

        LocalDate startDate = LocalDate.parse(userInputDate);
        LocalDate resultDate = startDate.plusDays((long) days);
        String finalDate = resultDate.toString();
        String output = String.format("The date after %2$d days from %3$s is %1$s", resultDate, days, startDate);
        System.out.println(output);
        //Learning - you can print out the LocalDate format in the form of a string as well

    }

    private static void handleTaxes() {
        System.out.println("Welcome to the Finance Calculator!");
        System.out.println("Would you like to find \n 1. Income tax calculator {I}" +
                "\n 2. Simple Interest Calc {S}" +
                "\n 3. Compound Interest Calc {C}");
        Scanner whichFianceTool = new Scanner(System.in);
        String whichFinanceToolStr = whichFianceTool.nextLine();

        switch (whichFinanceToolStr.toLowerCase()){
            case "i":
                handleIncometax();
                break;

            case "s":
                System.out.println("Enter the Principal Amount: ");
                double principalSimple = whichFianceTool.nextDouble();
                System.out.println("Enter the annual interest % rate: ");
                double interestPercent = whichFianceTool.nextDouble();
                System.out.println("Enter the total number of years the money is deposited: ");
                int years = whichFianceTool.nextInt();
                handleSimpleInterest(principalSimple, interestPercent, years);
                break;

            case "c":
                System.out.println("Enter the Principal Amount: ");
                double principalCompound = whichFianceTool.nextDouble();
                System.out.println("Enter the annual interest % rate: ");
                double interestPercentCompound = whichFianceTool.nextDouble();

                System.out.println("What kind of compound interest is it: " +
                        "\n 1. Yearly {y}" +
                        "\n 2. Quarterly {q}" +
                        "\n 3. Monthly {m}");
                String compType = whichFianceTool.next();


                int compTypeInt;
                switch (compType.toLowerCase().charAt(0)){
                    case 'y':
                        compTypeInt = 1;
                        break;
                    case 'q':
                        compTypeInt = 4;
                        break;
                    case 'm':
                        compTypeInt = 12;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + compType.toLowerCase().charAt(0));
                }

                System.out.println("Enter the total number of years: ");
                int time = whichFianceTool.nextInt();

                double TotalAmount = handleCompoundInterest(principalCompound, interestPercentCompound, compTypeInt, time);
                double CompInterest = (handleCompoundInterest(principalCompound, interestPercentCompound, compTypeInt, time) - principalCompound);

                String formatTotalAmt = String.format("%.2f", TotalAmount);
                String formatCompIntrest = String.format("%.2f", CompInterest );
                //Above two lines are to limit the value to only 2 digits

                System.out.println("The interest amount is $" + formatTotalAmt );
                System.out.println("So the total amount is $" + formatCompIntrest);
                break;
        }

    }

    private static double handleCompoundInterest(double principalCompound, double interestPercentCompound, int compTypeInt, int time) {
        double bracket = 1 + (interestPercentCompound/(100*compTypeInt));
        double bracket1 = Math.pow(bracket, (compTypeInt*time));
        double finalInterestAmount = principalCompound * bracket1;

        return  finalInterestAmount;
    }

    private static void handleIncometax(){
        Scanner input = new Scanner(System.in);
        System.out.println("Are you married {M} or single {S} ?" );
        String type = input.nextLine();

        switch (type.toUpperCase()){
            case "S":
                System.out.println("What is your annual income amount");
                double singleIncome = input.nextDouble();
                System.out.println("The standard deduction for singles is $15,000, and so your taxable income is $" + (singleIncome-15000));
                System.out.println("The tax you must pay for this year is: $" + handleSingleIncomeTax(singleIncome-15000));
                break;
            case "M":
                System.out.println("What is your combined annual income amount");
                double MarriedIncome = input.nextDouble();
                System.out.println("The standard deduction for married couples is $30,000, and so your taxable income is $" + (MarriedIncome-30000));
                System.out.println("The tax you must pay for this year is: $" + handleMarriedIncomeTax(MarriedIncome-30000));
                break;

            //In both of the cases, I have included the standard deduction so the calculator is as realistic as possible
        }


    }

    private static double handleMarriedIncomeTax(double marriedIncome) {
        double sum = 0;

        double marriedIncomeTaxBracket[] = {0, 23850, 96950, 206700, 394600, 501050, 751600};
        double marriedIncomeTaxRate[] = {0, 0.1,0.12,0.22,0.24,0.32,0.35};

        for(int i = 1; i < marriedIncomeTaxBracket.length ; i++){
            if(marriedIncome > (marriedIncomeTaxBracket[i] - marriedIncomeTaxBracket[i-1])){
                sum += (marriedIncomeTaxBracket[i] - marriedIncomeTaxBracket[i-1]) * marriedIncomeTaxRate[i];
                marriedIncome -= (marriedIncomeTaxBracket[i] - marriedIncomeTaxBracket[i-1]);
            }
            else{
                sum += marriedIncome * marriedIncomeTaxRate[i];
                break;
            }
        }

        return sum;
    }

    private static double handleSingleIncomeTax(double singleIncome) {
        double sum = 0;
        //Handling till $11,925

        double SingleTaxBracket[] = {0, 11925, 48475, 103350, 197300, 250525, 626350};
        double SingleTaxPercent[] = {0,0.1,0.12,0.22,0.24,0.32,0.35,0.37};

        for(int i = 1 ; i < SingleTaxBracket.length ; i++){
            if (singleIncome > (SingleTaxBracket[i] - SingleTaxBracket[i-1])){
                sum += (SingleTaxPercent[i] * (SingleTaxBracket[i]-SingleTaxBracket[i-1]));
                singleIncome -= (SingleTaxBracket[i]-SingleTaxBracket[i-1]);
            }
            else {
                sum += singleIncome * SingleTaxPercent[i];
                break;
            }





        }


         /*Goal - to distribute the income, and to see how much is left
        M1 - To apply if conditions to check if the leftover is greater than the difference or not
        M2 - To make a tax bracket array, and loop through the array - WAY more efficient*/

        return sum;

    }

    private static void handleSimpleInterest(double principal, double interestPercent, int years) {
        double interest = principal * (interestPercent/100) * years;
        System.out.println("The total interest after " + years + " is " + " $" + interest);
        System.out.println("So the total amount now is $" + (principal + interest));
    }


    //Another learning is to write the function when needed, and pass the rqrd args even before creating it, click Alt + enter
    //and let Intellij create the function and it will automatically specify the rqrd arguments, and you dont need to bother
    //writing the rqrd args again

    }


























