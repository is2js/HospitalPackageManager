package doctor.domain.business.discountpolicy.strategy;

import doctor.domain.vo.Money;

public class AmountPolicyApplier implements PolicyApplier {

    private Money amount;

    public AmountPolicyApplier(final Money amount) {
        this.amount = amount;
    }

    @Override
    public Money apply(final Money fee) {
        if (amount.isGreaterThan(fee)) {
            return Money.ZERO;
        }
        return fee.minus(amount);
    }
}
