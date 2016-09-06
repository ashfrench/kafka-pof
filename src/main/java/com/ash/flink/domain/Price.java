package com.ash.flink.domain;

import java.util.Objects;

public class Price {
  public final String isin;
  public final Double bid;
  public final Double ask;
  public final Double mid;

  public Price(String isin, Double bid, Double ask, Double mid) {
    this.isin = isin;
    this.bid = bid;
    this.ask = ask;
    this.mid = mid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Price price = (Price) o;
    return Objects.equals(isin, price.isin) &&
            Objects.equals(bid, price.bid) &&
            Objects.equals(ask, price.ask) &&
            Objects.equals(mid, price.mid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isin, bid, ask, mid);
  }

  @Override
  public String toString() {
    return "Price['" + isin + '\'' + ", b=" + bid + ", a=" + ask +
      ", m=" + mid + ']';
  }
}
