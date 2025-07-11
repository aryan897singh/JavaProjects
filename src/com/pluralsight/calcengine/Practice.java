package com.pluralsight.calcengine;

public class Practice {
    public static void main(String[] args) {
        String location = "Florida";
        int number = 458;
        StringBuilder sb = new StringBuilder();
        sb.append("I have a flight to ");
        sb.append(location);
        sb.append(" on flight number ");
        sb.append(number);

        String message = sb.toString();
        System.out.println(message);

        int posn = sb.indexOf(" on");
        sb.insert(posn, " at 5 am ");
         message = sb.toString();
        System.out.println(message);
    }
}
