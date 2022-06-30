package doctor_v2.vo;

import java.util.Objects;

public class Count {

    public static final Count EMPTY = new Count(0L);
    private final Long count;

    private Count(final Long count) {
        this.count = count;
    }

    public static Count of(final Long count) {
        if (count < 0) {
            throw new IllegalArgumentException("[ERROR] 음수를 입력할 수 없습니다.");
        }
        return new Count(count);
    }

    public Long getCount() {
        return count;
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
        return Objects.equals(count, count1.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    public boolean isPositive() {
        if (count <= 0) {
            return false;
        }
        return true;
    }

    public Count decrease() {
        return new Count(count - 1);
    }

    public boolean isGreaterThanOrEqualTo(final Count count) {
        return this.count >= count.count;
    }

    public Count minus(final Count count) {
        return new Count(this.count - count.count);
    }

    public boolean isLessThanOrEqualTo(final int count) {
        return this.count <= count;
    }
}
