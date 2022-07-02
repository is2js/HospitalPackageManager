package doctor_v2.discountpolicy;

import doctor_v2.vo.Money;

public class AmountPolicy extends DiscountPolicy{

    private Money amount;

    public AmountPolicy(final Money amount) {
        this.amount = amount;
    }

    @Override
    protected Money applyPolicyTo(final Money fee) {
        if (amount.isGreaterThan(fee)) {
            return Money.ZERO;
        }
        return fee.minus(amount);
    }
}
