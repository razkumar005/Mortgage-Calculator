package com.rajkumar.calculator;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class App {


    public static void main(String[] args) {

        final String USER_NAME = readStrings("Please enter your name :");
        String criminalRecord = readStrings("Has any criminal record : ");
        int income = (int) readNumber("Enter your income :", 1000, 1000000);
        int creditScore = (int) readNumber("Enter credit score :", 500, 800);
        boolean isEligible = !criminalRecord.equalsIgnoreCase("yes")
                && (income > 100000 || creditScore > 600);
        if (isEligible)
            hasEligible();
        else
            System.out.println(USER_NAME + " You are not eligible \nThank you \nBetter luck next time ");

        typeOfCustomer(creditScore);
    }

    public static void hasEligible() {
        int principle = (int) readNumber("Principle : ", 1000, 10_00_00_000);
        float rate = (float) readNumber("Annual Rate of interest :", 0, 30);
        int tenure = (int) readNumber("Enter the Tenure in months :", 1, 100);
        String date = readStrings("Enter disbursement date (yyyy-MM-dd): ");
        int choice = (int) readNumber("""
                Mortgage Calculator
                1. Calculate EMI\s
                2. Pending EMI's
                3. Show Balance\s
                4. Exit
                """, 1, 4);

        result(choice, principle, rate, tenure, date);
    }

    public static void typeOfCustomer(int creditScore) {
        int category = creditScore / 50;
        switch (category) {
            case (15):
                System.out.print("\nExcellent credit score ");
                break;
            case 14:
                System.out.print("\nGood credit score ");
                break;
            case 13:
                System.out.print("\nAverage credit score");
                break;
            case 12:
                System.out.print("\nneeds to improve creditor ");
                break;
            default:
                System.out.println("\nPoor credit score ");

        }
    }

    public static double calculateMortgage(int principle, float rate, int tenure) {
        rate = rate / 1200;
        double emi = principle * rate * Math.pow(1 + rate, tenure) / (Math.pow(1 + rate, tenure) - 1);
        emi = Math.round(emi * 100.0) / 100.0;
        return emi;
    }

    public static double readNumber(String prompt, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextInt();
            if (value >= min && value <= max) {
                break;
            } else {
                System.out.println(prompt + " should be between " + min + " and " + max);
            }
        }
        return value;
    }

    public static String readStrings(String prompt) {
        Scanner scanner = new Scanner(System.in);
        String value;
        while (true) {
            System.out.print(prompt);
            value = scanner.next();
            if (!value.isEmpty()) {
                break;
            } else {
                System.out.println(prompt + "Please enter valid input");
            }
        }
        return value;
    }

    public static void result(int choice, int principle, float rate, int tenure, String date) {
        double emi = calculateMortgage(principle, rate, tenure);
        long months = elapsedMonths(date);
        int pendingEmis = (int) (tenure - months);
        switch (choice) {
            case 1:
                System.out.print("\nYour monthly emi is " + NumberFormat.getCurrencyInstance().format(emi));
                break;
            case 2:
                if (pendingEmis > 0) {
                    System.out.print("\nEMI's pending " + pendingEmis);
                } else
                    System.out.println("Your loan is completed \nThanks ");
                break;
            case 3:
                System.out.print("Your total balanceAmount to be paid is "
                        + NumberFormat.getCurrencyInstance().format(calculateBalanceAmount(principle, months, rate, emi)));
                break;
            case 4:
                System.out.print("Exiting ");
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    public static double calculateBalanceAmount(int principle, long paidMonths, float rate, double emi) {
        return principle * Math.pow(1 + rate, paidMonths) - emi * (Math.pow(1 + rate, paidMonths) - 1) / rate;

    }

    public static long elapsedMonths(String date) {
        LocalDate disburseDate = LocalDate.parse(date);
        LocalDate today = LocalDate.now();
        return ChronoUnit.MONTHS.between(disburseDate, today);
    }
}
