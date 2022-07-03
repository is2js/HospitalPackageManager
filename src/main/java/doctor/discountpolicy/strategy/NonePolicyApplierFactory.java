package doctor.discountpolicy.strategy;

import doctor.domain.vo.Money;

public class NonePolicyApplierFactory implements PolicyApplier {

    private PolicyApplier cache;

    @Override
    public Money apply(final Money currentFee) {
        return create().apply(currentFee);
    }

    private synchronized PolicyApplier create() {
        if (cache == null) {
            cache = new NonePolicyApplier();
        }

        return cache;
    }
}
