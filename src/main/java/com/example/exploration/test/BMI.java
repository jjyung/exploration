package com.example.exploration.test;

import java.util.Scanner;

public class BMI {
  public static void main(String[] args) {
    //
    Scanner sc = new Scanner(System.in);
    System.out.print("Input weight in kilogram: ");
    double weight = sc.nextDouble();
    System.out.print("Input height in meters: ");
    double height = sc.nextDouble();
    double BMI = calculateBMI(weight, height);
    System.out.print("The Body Mass Index (BMI) is " + BMI + " kg/m2");
  }

  public static double calculateBMI(double weight, double height) {
    return Math.round(weight / (height * height) * 100.0d) / 100.0d;
  }
}
