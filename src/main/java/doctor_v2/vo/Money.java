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
}
