package doctor_v2.vo;

import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Sequence sequence = (Sequence) o;
        return Objects.equals(number, sequence.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
