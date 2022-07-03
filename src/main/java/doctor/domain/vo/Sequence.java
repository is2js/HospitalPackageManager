package doctor.domain.vo;

import java.util.Objects;

public class Sequence {

    private final Long value;

    private Sequence(final Long value) {
        validateNotNegative(value);
        this.value = value;
    }

    public static Sequence of(final Long number) {
        return new Sequence(number);
    }

    private void validateNotNegative(final Long number) {
        if (number < 0) {
            throw new IllegalArgumentException("[ERROR] 음수를 입력할 수 없습니다.");
        }
    }

    public boolean isIn(final Sequence sequence) {
        return this.value <= sequence.value;
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
        return Objects.equals(value, sequence.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Sequence{" +
            "number=" + value +
            '}';
    }
}
