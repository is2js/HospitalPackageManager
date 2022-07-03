package doctor_v2.discountpolicy.strategy;

import doctor_v2.domain.DiscountPercent;
import doctor_v2.vo.Money;

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
