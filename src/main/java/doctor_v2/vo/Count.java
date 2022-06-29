package doctor_v2.vo;

import java.util.Objects;

public class Count {

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

    public Long getValue() {
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
}
