package doctor.domain.vo;

import java.util.Objects;

public class CommissionRate {

    private final Double value;

    private CommissionRate(final Double value) {
        validateRange(value);
        this.value = value;
    }

    public static CommissionRate of(final Double commissionRate) {
        return new CommissionRate(commissionRate);
    }

    private void validateRange(final Double commissionRate) {
        if (!(0.0 <= commissionRate && commissionRate <= 100.0)) {
            throw new IllegalArgumentException("[ERROR] 0~100 사이 값을 입력하세요.");
        }
    }

    public double applyTo(final Double money) {
        return money * (value / 100);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CommissionRate that = (CommissionRate) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "CommissionRate{" +
            "commissionRate=" + value +
            '}';
    }
}
