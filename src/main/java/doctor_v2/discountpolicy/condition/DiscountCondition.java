package doctor_v2.discountpolicy.condition;

import doctor_v2.domain.Treatment;
import doctor_v2.vo.Count;

public interface DiscountCondition {

    boolean isSatisfiedBy(Treatment treatment, Count count);
}
