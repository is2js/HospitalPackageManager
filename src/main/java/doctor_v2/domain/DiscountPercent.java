package doctor_v2.domain;

import java.util.Objects;

public class DiscountPercent {

    private final Integer percent;

    private DiscountPercent(final Integer percent) {
        if (percent < 0 || percent > 100) {
            throw new RuntimeException("[ERROR] Percent는 0~100 사이 값을 입력해주세요");
        }
        this.percent = percent;
    }

    public static DiscountPercent of (final Integer percent) {
        return new DiscountPercent(percent);
    }

    public Double applyTo(final Double money) {
        return money * (percent/100);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DiscountPercent that = (DiscountPercent) o;
        return Objects.equals(percent, that.percent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(percent);
    }
}
