package doctor_v2.discountpolicy.strategy;

import doctor_v2.vo.Money;

public interface PolicyApplier {

    Money apply(Money currentFee);
}
