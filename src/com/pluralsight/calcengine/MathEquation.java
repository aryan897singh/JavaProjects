package com.pluralsight.calcengine;

public class MathEquation {
    double LVal;
    double RVal;
    char opCode;
    double result;
    //We have defined 4 fields within this class MathEquation

    //Method here below takes no parameters
    void execute() {
        switch (opCode) {
            case 'a':
                result = LVal + RVal;
                break;
            case 's':
                result = LVal - RVal;
                break;
            case 'm':
                result = LVal * RVal;
                break;
            case 'd':
                result = RVal != 0 ? LVal / RVal : 0.0;  //NOTE: When you do a/b it will give only int value if a,b are integers
                //V.V. IMP!!! Mistake when making programs
                break;
            default:
                System.out.println("Invalid opCode " + opCode);
        }


    }
}
