package doctor.domain.vo;

import doctor.domain.DiscountPercent;
import java.util.Objects;

public class Money {

    public static final Money ZERO = new Money(0.0);

    private final Double value;

    private Money(final Double value) {
        validateNotNegative(value);
        this.value = value;
    }

    public static Money of(final Double money) {
        return new Money(money);
    }

    private void validateNotNegative(final Double money) {
        if (money < 0) {
            throw new RuntimeException("[ERROR] 돈은 음수가 될 수 없습니다.");
        }
    }

    public boolean isGreaterThanOrEqualTo(final Money fee) {
        return value >= fee.value;
    }

    public boolean isLessThan(final Money fee) {
        return value < fee.value;
    }

    public Money minus(final Money fee) {
        return new Money(value - fee.value);
    }

    public Money multi(Count count) {
        Double sum = 0.0;
        while (count.isPositive()) {
            sum += value;
            count = count.decrease();
        }
        return new Money(sum);
    }

    public Money multiRate(final CommissionRate commissionRate) {
        return new Money(commissionRate.applyTo(value));
    }

    public Money plus(final Money money) {
        return new Money(this.value + money.value);
    }

    public Money multiPercent(final DiscountPercent percent) {
        return new Money(percent.applyTo(value));
    }

    public boolean isGreaterThan(final Money money) {
        return this.value > money.value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Money money1 = (Money) o;
        return Objects.equals(value, money1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Money{" +
            "money=" + value +
            '}';
    }
}
