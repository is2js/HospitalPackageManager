package doctor.domain.business.discountpolicy.condition;

import doctor.domain.business.Treatment;
import doctor.domain.vo.Count;

public interface DiscountCondition {

    boolean isSatisfiedBy(Treatment treatment, Count count);
}
