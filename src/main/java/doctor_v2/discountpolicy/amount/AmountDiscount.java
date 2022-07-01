package doctor_v2.discountpolicy.amount;

import doctor_v2.discountpolicy.DiscountCondition;
import doctor_v2.discountpolicy.DiscountPolicy.AMOUNT;
import doctor_v2.vo.Money;

public abstract class AmountDiscount implements DiscountCondition, AMOUNT {
    private final Money amount;

    protected AmountDiscount(final Money amount) {
        this.amount = amount;
    }

    @Override
    public final Money calculateFee(final Money fee) {
        return fee.minus(amount);
    }
}
