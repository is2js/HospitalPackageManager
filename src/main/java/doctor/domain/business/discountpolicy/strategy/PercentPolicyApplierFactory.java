package doctor.domain.business.discountpolicy.strategy;

import doctor.domain.business.DiscountPercent;
import doctor.domain.vo.Money;

public class PercentPolicyApplierFactory implements PolicyApplier {

    private PolicyApplier cache;
    private DiscountPercent percent;

    public PercentPolicyApplierFactory(final DiscountPercent percent) {
        this.percent = percent;
    }

    @Override
    public Money apply(final Money currentFee) {
        return create().apply(currentFee);
    }

    private synchronized PolicyApplier create() {
        if (cache == null) {
            cache = new PercentPolicyApplier(percent);
        }

        return cache;
    }
}
