package doctor.discountpolicy.condition;

import doctor.domain.Treatment;
import doctor.domain.vo.Count;

public interface DiscountCondition {

    boolean isSatisfiedBy(Treatment treatment, Count count);
}
