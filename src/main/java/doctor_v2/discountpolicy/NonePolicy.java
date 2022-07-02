package doctor_v2.discountpolicy;

import doctor_v2.vo.Money;

public class NonePolicy extends DiscountPolicy {

    @Override
    protected Money applyPolicyTo(final Money fee) {
        return fee;
    }
}
