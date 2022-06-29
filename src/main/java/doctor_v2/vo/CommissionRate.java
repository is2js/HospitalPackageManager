package doctor_v2.vo;

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
}
