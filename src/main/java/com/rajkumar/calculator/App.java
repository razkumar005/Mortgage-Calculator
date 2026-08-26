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
        float  interest;
        float rate ;
        long duration;
        double balanceAmount;
        final String USER_NAME = "Rajkumar";
        int tenure;
        //double[] emipayments = new double[tenure];
        double emi;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Principle : ");
        principle = scanner.nextInt();

        System.out.print("Annual Rate of interest :");
        rate = scanner.nextFloat();
        rate = (float)rate/1200;

        System.out.print("Enter the Tenure :");
        tenure = scanner.nextInt();

        System.out.print("Enter disbursement date (yyyy-MM-dd): ");
        String date = scanner.next();
        LocalDate disburseDate = LocalDate.parse(date);
        scanner.close();

        LocalDate today = LocalDate.now();
        duration = ChronoUnit.DAYS.between(disburseDate,today);
        long elapsedMonths = ChronoUnit.MONTHS.between(disburseDate,today);
        long pendingEmis = tenure-elapsedMonths;

        emi = principle * rate* Math.pow(1+rate,tenure)/(Math.pow(1+rate,tenure)-1);
        emi = Math.round(emi*100.0)/100.0;

        balanceAmount = principle * Math.pow(1+rate,elapsedMonths)-emi*(Math.pow(1+rate,elapsedMonths)-1)/rate;
        System.out.println();
        System.out.println(USER_NAME.concat(" ") +"Your total balanceAmount to be paid is "
                +NumberFormat.getCurrencyInstance().format(balanceAmount)
                + "\nEMI's pending "+pendingEmis
                +"\nYour monthly emi is "+NumberFormat.getCurrencyInstance().format(emi));

    }
}
