package doctor_v2.discountpolicy.percent;

import doctor_v2.discountpolicy.DiscountCondition;
import doctor_v2.discountpolicy.DiscountPolicy.PERCENT;
import doctor_v2.domain.DiscountPercent;
import doctor_v2.vo.Money;

public abstract class PercentDiscount implements DiscountCondition, PERCENT {

    private DiscountPercent percent;

    protected PercentDiscount(final DiscountPercent percent) {
        this.percent = percent;
    }

    @Override
    public Money calculateFee(final Money fee) {
        return fee.minus(fee.multiPercent(percent));
    }
}
