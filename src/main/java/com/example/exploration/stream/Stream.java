package com.example.exploration.stream;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stream {

  private static final List<Tx> txes =
      Arrays.asList(
          new Tx(1, BigDecimal.ONE),
          new Tx(1, BigDecimal.ONE),
          new Tx(2, BigDecimal.ONE),
          new Tx(2, BigDecimal.TEN));

  public static void main(String[] args) {
    //
    System.out.println(sum(2));
    System.out.println(groupByType());
  }

  public static BigDecimal sum(int type) {
    return txes.stream()
        .filter(tx -> tx.getType() == type)
        .map(Tx::getAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public static Map<TypeEnum, BigDecimal> groupByType() {
    return txes.stream()
        .collect(
            Collectors.groupingBy(
                Tx::getType, Collectors.reducing(BigDecimal.ZERO, Tx::getAmount, BigDecimal::add)))
        .entrySet()
        .stream()
        .collect(Collectors.toMap(e -> TypeEnum.fromValue(e.getKey()), Map.Entry::getValue));
  }
}
