package doctor_v2.discountpolicy;

import doctor_v2.discountpolicy.condition.DiscountCondition;
import doctor_v2.discountpolicy.strategy.PolicyApplier;
import doctor_v2.domain.Treatment;
import doctor_v2.vo.Count;
import doctor_v2.vo.Money;
import java.util.HashSet;
import java.util.Set;

public class DiscountPolicy {

    private Set<DiscountCondition> conditions = new HashSet<>();
    private PolicyApplier policyApplier;

    public DiscountPolicy(final PolicyApplier policyApplier) {
        this.policyApplier = policyApplier;
    }

    public final void addCondition(final DiscountCondition discountCondition){
        conditions.add(discountCondition);
    }

    public final Money calculateFee(final Treatment treatment, final Count count, final Money fee) {
        Money currentFee = fee;
        for (final DiscountCondition condition : conditions) {
            if (condition.isSatisfiedBy(treatment, count)) {
                currentFee = policyApplier.apply(currentFee);
            }
        }

        return currentFee;
    }
}
