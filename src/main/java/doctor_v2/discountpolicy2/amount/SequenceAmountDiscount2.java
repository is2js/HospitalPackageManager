package doctor_v2.discountpolicy2.amount;

import doctor_v2.domain.Treatment;
import doctor_v2.vo.Money;
import doctor_v2.vo.Sequence;

public class SequenceAmountDiscount2 extends AmountDiscount2 {

    private Sequence eventMaxSequence;

    public SequenceAmountDiscount2(final Money amount, final Sequence eventMaxSequence) {
        super(amount);
        this.eventMaxSequence = eventMaxSequence;
    }

    @Override
    public boolean isSatisfiedBy(final Treatment treatment) {
        return treatment.isSequenceIn(eventMaxSequence);
    }
}
