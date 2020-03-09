package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    private String currency;
    private BigDecimal denomination;

    public Money(String currency, BigDecimal denomination) {
        this.currency = currency;
        this.denomination = denomination;
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Money))
            return false;
        Money money = (Money) o;
        return Objects.equals(currency, money.currency) && Objects.equals(denomination, money.denomination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, denomination);
    }
}
