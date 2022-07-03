package doctor_v2.discountpolicy.strategy;

import doctor_v2.vo.Money;

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
