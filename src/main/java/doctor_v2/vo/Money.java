package doctor_v2.vo;

import java.util.Objects;

public class Money {

    private final Double money;

    private Money(final Double money) {
        this.money = money;
    }

    public static Money of(final Double money) {
        if (money < 0) {
            throw new IllegalArgumentException("[ERROR] 음수를 입력할 수 없습니다.");
        }

        return new Money(money);
    }

    public boolean isGreaterThan(final Long fee) {
        return money > fee;
    }

    public boolean isEqualTo(final Long fee) {
        return Objects.equals(money, fee);
    }

    public boolean isLessThan(final Long fee) {
        return money < fee;
    }

    public Money minus(final Long fee) {
        return new Money(money - fee);
    }

    public Money multi(Count count) {
        Double sum = 0.0;
        while (count.isPositive()) {
            sum += money;
            count = count.decrease();
        }
        return new Money(sum);
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
