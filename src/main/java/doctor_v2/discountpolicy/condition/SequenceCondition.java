package doctor_v2.discountpolicy.condition;

import doctor_v2.domain.Treatment;
import doctor_v2.vo.Count;
import doctor_v2.vo.Sequence;

public class SequenceCondition implements DiscountCondition {

    private Sequence eventMaxSequence;

    public SequenceCondition(final Sequence eventMaxSequence) {
        this.eventMaxSequence = eventMaxSequence;
    }

    @Override
    public boolean isSatisfiedBy(final Treatment treatment, final Count count) {
        return treatment.isSequenceIn(eventMaxSequence);
    }
}
