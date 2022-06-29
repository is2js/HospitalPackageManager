package doctor_v2.domain;

import doctor_v2.vo.Count;
import doctor_v2.vo.Description;
import doctor_v2.vo.Sequence;

public class Treatment {
    private final Sequence sequence;
    private final Description description;
    private final Count count;

    public Treatment(final Sequence sequence,
                     final Description description,
                     final Count count) {
        this.sequence = sequence;
        this.description = description;
        this.count = count;
    }
}
