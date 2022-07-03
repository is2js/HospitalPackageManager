package doctor.discountpolicy.strategy;

import doctor.domain.DiscountPercent;
import doctor.domain.vo.Money;

public class PercentPolicyApplier implements PolicyApplier {

    private DiscountPercent percent;

    public PercentPolicyApplier(final DiscountPercent percent) {
        this.percent = percent;
    }

    @Override
    public Money apply(final Money fee) {
        return fee.minus(fee.multiPercent(percent));
    }
}
