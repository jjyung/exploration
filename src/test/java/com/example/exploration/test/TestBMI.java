package com.example.exploration.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestBMI {

  @Test
  void testCalculateBMI() {
    double[][] people = {
      {75, 1.7, 25.95},
      {60, 1.68, 21.26}
    };

    for (double[] person : people) {
      Assertions.assertEquals(BMI.calculateBMI(person[0], person[1]), person[2]);
    }
  }
}
