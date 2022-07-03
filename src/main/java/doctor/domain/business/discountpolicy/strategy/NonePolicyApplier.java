package doctor.domain.business.discountpolicy.strategy;

import doctor.domain.vo.Money;

public class NonePolicyApplier implements PolicyApplier {

    @Override
    public Money apply(final Money fee) {
        return fee;
    }
}
