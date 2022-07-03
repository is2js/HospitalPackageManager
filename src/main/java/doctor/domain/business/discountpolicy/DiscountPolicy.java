package doctor.domain.business.discountpolicy;

import doctor.domain.business.discountpolicy.condition.DiscountCondition;
import doctor.domain.business.discountpolicy.strategy.PolicyApplier;
import doctor.domain.business.Treatment;
import doctor.domain.vo.Count;
import doctor.domain.vo.Money;
import java.util.HashSet;
import java.util.Set;

public class DiscountPolicy {

    private Set<DiscountCondition> conditions = new HashSet<>();
    private PolicyApplier factory;

    public DiscountPolicy(final PolicyApplier factory) {
        this.factory = factory;
    }

    public final void addCondition(final DiscountCondition discountCondition){
        conditions.add(discountCondition);
    }

    public final Money calculateFee(final Treatment treatment, final Count count, final Money fee) {
        Money currentFee = fee;
        for (final DiscountCondition condition : conditions) {
            if (condition.isSatisfiedBy(treatment, count)) {
                currentFee = factory.apply(currentFee);
            }
        }

        return currentFee;
    }
}
