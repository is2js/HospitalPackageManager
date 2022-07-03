package doctor.domain.vo;

import java.util.Objects;

public class Count {

    public static final Count EMPTY = new Count(0L);
    private final Long value;

    private Count(final Long value) {
        validateNotNegative(value);
        this.value = value;
    }

    public static Count of(final Long count) {
        return new Count(count);
    }

    private void validateNotNegative(final Long count) {
        if (count < 0) {
            throw new IllegalArgumentException("[ERROR] 음수를 입력할 수 없습니다.");
        }
    }

    public boolean isPositive() {
        if (value <= 0) {
            return false;
        }
        return true;
    }

    public Count decrease() {
        return new Count(value - 1);
    }

    public boolean isGreaterThanOrEqualTo(final Count count) {
        return this.value >= count.value;
    }

    public Count minus(final Count count) {
        return new Count(this.value - count.value);
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Count count1 = (Count) o;
        return Objects.equals(value, count1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Count{" +
            "count=" + value +
            '}';
    }
}
