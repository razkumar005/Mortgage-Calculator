package com.rajkumar.calculator;

import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int principle;
        float interest;
        float rate;
        long duration;
        double balanceAmount;
        final String USER_NAME;
        int tenure;
        //double[] emipayments = new double[tenure];
        double emi;
        boolean isAllValuesAreNonNegative;
        boolean hasHighIncome = false;
        boolean hasGoodCredit = false;
        boolean hasCriminalRecord;
        int creditScore;
        int income;
        boolean isEligible;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your name :");
        USER_NAME = scanner.next();
        System.out.print("Enter your income :");
        income = scanner.nextInt();
        if (income > 0) {
            hasHighIncome = income >= 10_00_000;

            System.out.print("Enter the credit score : ");
            creditScore = scanner.nextInt();
            if (creditScore > 0) {
                hasGoodCredit = (creditScore >= 600);

                System.out.print("Has any criminal record :");
                hasCriminalRecord = scanner.nextBoolean();
                isEligible = (!hasCriminalRecord && (hasGoodCredit || hasHighIncome));
                if (isEligible) {

                    System.out.print("Principle : ");
                    principle = scanner.nextInt();

                    System.out.print("Annual Rate of interest :");
                    rate = scanner.nextFloat();
                    rate = (float) rate / 1200;

                    System.out.print("Enter the Tenure in months :");
                    tenure = scanner.nextInt();

                    System.out.print("Enter disbursement date (yyyy-MM-dd): ");
                    String date = scanner.next();
                    LocalDate disburseDate = LocalDate.parse(date);
                    scanner.close();

                    LocalDate today = LocalDate.now();
                    duration = ChronoUnit.DAYS.between(disburseDate, today);
                    long elapsedMonths = ChronoUnit.MONTHS.between(disburseDate, today);
                    long pendingEmis = tenure - elapsedMonths;
                    isAllValuesAreNonNegative = principle > 0 && rate > 0 && (tenure > 0);

                    if (isAllValuesAreNonNegative) {
                        if(!(pendingEmis<=0)) {
                            emi = principle * rate * Math.pow(1 + rate, tenure) / (Math.pow(1 + rate, tenure) - 1);
                            emi = Math.round(emi * 100.0) / 100.0;

                            balanceAmount = principle * Math.pow(1 + rate, elapsedMonths) - emi * (Math.pow(1 + rate, elapsedMonths) - 1) / rate;
                            System.out.println();
                            System.out.println(USER_NAME.concat(" ") + "Your total balanceAmount to be paid is "
                                    + NumberFormat.getCurrencyInstance().format(balanceAmount)
                                    + "\nEMI's pending " + pendingEmis
                                    + "\nYour monthly emi is " + NumberFormat.getCurrencyInstance().format(emi));
                        }else
                            System.out.println("Your loan is completed");
                    } else
                        System.out.println("Please enter positive values");
                } else
                    System.out.println("Thank you Better luck next time");
            } else
                System.out.println("Credit score should be greater than zero");
        } else
            System.out.println("Income should be greater than zero");
    }
}
