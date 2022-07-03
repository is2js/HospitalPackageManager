package doctor.discountpolicy.condition;

import doctor.domain.Treatment;
import doctor.domain.vo.Count;

public class NoneCondition implements DiscountCondition {

    @Override
    public boolean isSatisfiedBy(final Treatment treatment, final Count count) {
        return true;
    }
}
