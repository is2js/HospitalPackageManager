package doctor_v2.vo;

public class Sequence {
    private final Long number;

    private Sequence(final Long number) {
        this.number = number;
    }

    public static Sequence of(final Long number) {
        if (number < 0) {
            throw new IllegalArgumentException("[ERROR] 음수를 입력할 수 없습니다.");
        }
        return new Sequence(number);
    }
}
