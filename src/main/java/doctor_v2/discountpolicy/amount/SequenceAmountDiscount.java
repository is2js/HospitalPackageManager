package doctor_v2.discountpolicy.amount;

import doctor_v2.domain.Treatment;
import doctor_v2.vo.Money;
import doctor_v2.vo.Sequence;

public class SequenceAmountDiscount extends AmountDiscount{

    private Sequence eventMaxSequence;

    public SequenceAmountDiscount(final Money amount, final Sequence eventMaxSequence) {
        super(amount);
        this.eventMaxSequence = eventMaxSequence;
    }

    @Override
    public boolean isSatisfiedBy(final Treatment treatment) {
        return treatment.isSequenceIn(eventMaxSequence);
    }
}
