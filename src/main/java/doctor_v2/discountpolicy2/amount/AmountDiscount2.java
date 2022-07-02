package doctor_v2.discountpolicy2.amount;

import doctor_v2.discountpolicy2.DiscountCondition2;
import doctor_v2.discountpolicy2.DiscountPolicy2.AMOUNT;
import doctor_v2.vo.Money;

public abstract class AmountDiscount2 implements DiscountCondition2, AMOUNT {
    private final Money amount;

    protected AmountDiscount2(final Money amount) {
        this.amount = amount;
    }

    @Override
    public final Money calculateFee(final Money fee) {
        return fee.minus(amount);
    }
}
