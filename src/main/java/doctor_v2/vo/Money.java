package doctor_v2.vo;

public class Money {

    private final Double money;

    public Money(final Double money) {
        this.money = money;
    }

    public static Money of(final Double money) {
        if (money < 0) {
            throw new IllegalArgumentException("[ERROR] 음수를 입력할 수 없습니다.");
        }

        return new Money(money);
    }
}
