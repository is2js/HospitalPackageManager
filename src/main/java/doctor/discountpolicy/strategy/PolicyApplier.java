package doctor.discountpolicy.strategy;

import doctor.domain.vo.Money;

public interface PolicyApplier {

    Money apply(Money currentFee);
}
