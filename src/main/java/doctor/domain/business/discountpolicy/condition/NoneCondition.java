package doctor.domain.business.discountpolicy.condition;

import doctor.domain.business.Treatment;
import doctor.domain.vo.Count;

public class NoneCondition implements DiscountCondition {

    @Override
    public boolean isSatisfiedBy(final Treatment treatment, final Count count) {
        return true;
    }
}
