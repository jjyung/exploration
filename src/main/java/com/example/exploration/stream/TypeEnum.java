package com.example.exploration.stream;

public enum TypeEnum {
  ONE(1),
  TWO(2);

  int value;

  TypeEnum(int value) {
    this.value = value;
  }

  public static TypeEnum fromValue(Integer value) {
    for (TypeEnum b : TypeEnum.values()) {
      if (b.value == value) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}
