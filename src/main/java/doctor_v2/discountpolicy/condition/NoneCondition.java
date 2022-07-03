package doctor_v2.discountpolicy.condition;

import doctor_v2.domain.Treatment;
import doctor_v2.vo.Count;

public class NoneCondition implements DiscountCondition {

    @Override
    public boolean isSatisfiedBy(final Treatment treatment, final Count count) {
        return true;
    }
}
