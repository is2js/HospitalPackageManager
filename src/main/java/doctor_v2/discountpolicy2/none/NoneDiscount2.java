package doctor_v2.discountpolicy2.none;

import doctor_v2.discountpolicy2.DiscountCondition2;
import doctor_v2.discountpolicy2.DiscountPolicy2;
import doctor_v2.domain.Treatment;
import doctor_v2.vo.Money;

public class NoneDiscount2 implements DiscountCondition2, DiscountPolicy2.NONE {

    @Override
    public boolean isSatisfiedBy(final Treatment treatment) {
        return true;
    }

    @Override
    public Money calculateFee(final Money fee) {
        return fee;
    }
}
