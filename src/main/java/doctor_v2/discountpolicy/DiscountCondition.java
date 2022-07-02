package doctor_v2.discountpolicy;

import doctor_v2.domain.Treatment;
import doctor_v2.vo.Money;

public interface DiscountCondition {

    boolean isSatisfiedBy(Treatment treatment);

    Money calculateFee(Money fee);
}
