package doctor_v2.discountpolicy2;

import doctor_v2.domain.Treatment;
import doctor_v2.vo.Money;

public interface DiscountCondition2 {

    boolean isSatisfiedBy(Treatment treatment);

    Money calculateFee(Money fee);
}
