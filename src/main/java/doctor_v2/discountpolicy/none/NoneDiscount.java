package doctor_v2.discountpolicy.none;

import doctor_v2.discountpolicy.DiscountCondition;
import doctor_v2.discountpolicy.DiscountPolicy;
import doctor_v2.domain.Treatment;
import doctor_v2.vo.Money;

public class NoneDiscount implements DiscountCondition, DiscountPolicy.NONE {

    @Override
    public boolean isSatisfiedBy(final Treatment treatment) {
        return true;
    }

    @Override
    public Money calculateFee(final Money fee) {
        return fee;
    }
}
