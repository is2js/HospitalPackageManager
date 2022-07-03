package doctor_v2.discountpolicy.strategy;

import doctor_v2.vo.Money;

public class NonePolicyApplier implements PolicyApplier {

    @Override
    public Money apply(final Money fee) {
        return fee;
    }
}
