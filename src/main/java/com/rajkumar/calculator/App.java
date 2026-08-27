package com.rajkumar.calculator;

import javax.swing.*;
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
        double[] emipayments;
        double emi;
        boolean isAllValuesAreNonNegative;
        boolean hasHighIncome = false;
        boolean hasGoodCredit = false;
        boolean hasCriminalRecord;
        int creditScore;
        int income;
        boolean isEligible;
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your name :");
        USER_NAME = scanner.next();

        while (true) {
            System.out.print("Enter your income :");
            income = scanner.nextInt();
            if (income > 0) {
                hasHighIncome = income >= 10_00_000;
                break;
            } else {
                System.out.println("Income should be greater than 0");
            }
        }

        while (true) {
            System.out.print("Enter the credit score : ");
            creditScore = scanner.nextInt();
            if (creditScore >= 600 && creditScore < 800) {
                hasGoodCredit = true;
                break;
            } else {
                System.out.println("Credit score should be between 600 and 800");
            }
        }


        System.out.print("Has any criminal record :");
        hasCriminalRecord = scanner.nextBoolean();
        isEligible = (!hasCriminalRecord && (hasGoodCredit || hasHighIncome));
        if (isEligible) {

            System.out.print("Principle : ");
            principle = scanner.nextInt();

            System.out.print("Annual Rate of interest :");
            rate = scanner.nextFloat();
            int categeory = creditScore / 50;
            switch (categeory) {
                case (15):
                    System.out.println("Excellent credit score ");
                    break;
                case 14:
                    System.out.println("Good credit score ");
                    break;
                case 13:
                    System.out.println("Average credit score");
                    break;
                case 12:
                    System.out.println("needs to improve creditor ");
                    break;
                default:
                    System.out.println("Poor credit score ");

            }
            rate = (float) rate / 1200;

            System.out.print("Enter the Tenure in months :");
            tenure = scanner.nextInt();

            System.out.print("Enter disbursement date (yyyy-MM-dd): ");
            String date = scanner.next();
            LocalDate disburseDate = LocalDate.parse(date);


            LocalDate today = LocalDate.now();
            duration = ChronoUnit.DAYS.between(disburseDate, today);
            long elapsedMonths = ChronoUnit.MONTHS.between(disburseDate, today);
            long pendingEmis = tenure - elapsedMonths;
            isAllValuesAreNonNegative = principle > 0 && rate > 0 && (tenure > 0);

            if (isAllValuesAreNonNegative) {
                if (!(pendingEmis <= 0)) {
                    emi = principle * rate * Math.pow(1 + rate, tenure) / (Math.pow(1 + rate, tenure) - 1);
                    emi = Math.round(emi * 100.0) / 100.0;

                    balanceAmount = principle * Math.pow(1 + rate, elapsedMonths) - emi * (Math.pow(1 + rate, elapsedMonths) - 1) / rate;
                    System.out.println();
                    emipayments = new double[tenure];

                    for (int i = 0; i < elapsedMonths; i++) {
                        emipayments[i] = emi;
                        count++;
                    }

                    System.out.println("\nMortgage Calculator");
                    System.out.println("1. Calculate EMI");
                    System.out.println("2. Pending EMI's");
                    System.out.println("3. Show Balance");
                    System.out.println("4. Exit");

                    System.out.print("Choose an option: ");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.print("\nYour monthly emi is " + NumberFormat.getCurrencyInstance().format(emi));
                            break;
                        case 2:
                            System.out.print("\nEMI's pending " + pendingEmis);
                            break;
                        case 3:
                            System.out.print("Your total balanceAmount to be paid is "
                                    + NumberFormat.getCurrencyInstance().format(balanceAmount));
                            break;
                        case 4:
                            System.out.print("Exiting ");
                            break;
                        default:
                            System.out.println("Invalid input");
                    }

                    scanner.close();
                    System.out.println();
                    System.out.println(USER_NAME.concat(" ") + "You have paid till now " + count + " emi's");
                } else
                    System.out.println("Your loan is completed");
            } else
                System.out.println("Please enter positive values");
        } else
            System.out.println("Thank you Better luck next time");
    }
}
