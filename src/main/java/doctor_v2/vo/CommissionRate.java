package doctor_v2.vo;

import java.util.Objects;

public class CommissionRate {

    private final Double commissionRate;

    private CommissionRate(final Double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public static CommissionRate of(final Double commissionRate) {
        if (!(0.0 <= commissionRate  && commissionRate <= 100.0)) {
            throw new IllegalArgumentException("[ERROR] 0~100 사이 값을 입력하세요.");
        }
        return new CommissionRate(commissionRate);
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
        return Objects.equals(commissionRate, that.commissionRate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commissionRate);
    }
}
