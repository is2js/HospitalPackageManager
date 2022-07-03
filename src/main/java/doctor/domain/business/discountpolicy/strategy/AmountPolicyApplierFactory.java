package doctor.domain.business.discountpolicy.strategy;

import doctor.domain.vo.Money;

public class AmountPolicyApplierFactory implements PolicyApplier {

    private PolicyApplier cache;
    private Money amount;

    public AmountPolicyApplierFactory(final Money amount) {
        this.amount = amount;
    }

    @Override
    public Money apply(final Money currentFee) {
        return create().apply(currentFee);
    }

    private synchronized PolicyApplier create() {
        if (cache == null) {
            cache = new AmountPolicyApplier(amount);
        }
        return cache;
    }
}
