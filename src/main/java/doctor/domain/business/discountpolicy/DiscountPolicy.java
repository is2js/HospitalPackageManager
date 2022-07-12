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
    private Set<PolicyApplier> factories = new HashSet<>();

    public DiscountPolicy(final PolicyApplier factory) {
        this.factories.add(factory);
    }

    public final DiscountPolicy addCondition(final DiscountCondition discountCondition) {
        conditions.add(discountCondition);
        return this;
    }

    public DiscountPolicy addPolicy(final PolicyApplier factory) {
        this.factories.add(factory);
        return this;
    }

    public final Money calculateFee(final Treatment treatment, final Count count, Money fee) {
        for (final DiscountCondition condition : conditions) {
            fee = applyDiscountPolicy(treatment, count, fee, condition);
        }

        return fee;
    }

    private Money applyDiscountPolicy(final Treatment treatment, final Count count, Money fee, final DiscountCondition condition) {
        if (!condition.isSatisfiedBy(treatment, count)) {
            return fee;
        }

        for (final PolicyApplier factory : factories) {
            fee = factory.apply(fee);
        }
        return fee;
    }
}
