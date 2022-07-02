package doctor_v2.discountpolicy.percent;

import doctor_v2.discountpolicy.DiscountCondition;
import doctor_v2.discountpolicy.DiscountPolicy.PERCENT;
import doctor_v2.vo.Count;
import doctor_v2.vo.Money;

public abstract class PercentDiscount implements DiscountCondition, PERCENT {

    private Count percent;

    protected PercentDiscount(final Count percent) {
        this.percent = percent;
    }

    @Override
    public Money calculateFee(final Money fee) {
        return fee.minus(fee.multi(percent));
    }
}
