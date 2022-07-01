package doctor_v2.vo;

import java.util.Objects;

public class Money {

    private final Double money;

    private Money(final Double money) {
        validatePositive(money);
        this.money = money;
    }

    public static Money of(final Double money) {
        return new Money(money);
    }

    private void validatePositive(final Double money) {
        if (money < 0) {
            throw new IllegalArgumentException("[ERROR] 음수를 입력할 수 없습니다.");
        }
    }

    public boolean isGreaterThanOrEqualTo(final Money fee) {
        return money >= fee.money;
    }

    public boolean isLessThan(final Money fee) {
        return money < fee.money;
    }

    public Money minus(final Money fee) {
        return new Money(money - fee.money);
    }

    public Money multi(Count count) {
        Double sum = 0.0;
        while (count.isPositive()) {
            sum += money;
            count = count.decrease();
        }
        return new Money(sum);
    }

    public Money multiRate(final CommissionRate commissionRate) {
        return new Money(commissionRate.calculateCommission(money));
    }

    public Money plus(final Money money) {
        return new Money(this.money + money.money);
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
        return Objects.equals(money, money1.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
