package doctor_v2.discountpolicy2.percent;

import doctor_v2.discountpolicy.DiscountPolicy.PERCENT;
import doctor_v2.discountpolicy2.DiscountCondition2;
import doctor_v2.domain.DiscountPercent;
import doctor_v2.vo.Money;

public abstract class PercentDiscount2 implements DiscountCondition2, PERCENT {

    private DiscountPercent percent;

    protected PercentDiscount2(final DiscountPercent percent) {
        this.percent = percent;
    }

    @Override
    public Money calculateFee(final Money fee) {
        return fee.minus(fee.multiPercent(percent));
    }
}
