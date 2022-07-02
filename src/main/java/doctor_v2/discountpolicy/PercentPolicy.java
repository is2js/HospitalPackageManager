package doctor_v2.discountpolicy;

import doctor_v2.domain.DiscountPercent;
import doctor_v2.vo.Money;

public class PercentPolicy extends DiscountPolicy {

    private DiscountPercent percent;

    public PercentPolicy(final DiscountPercent percent) {
        this.percent = percent;
    }

    @Override
    protected Money applyPolicyTo(final Money fee) {
        return fee.minus(fee.multiPercent(percent));
    }
}
