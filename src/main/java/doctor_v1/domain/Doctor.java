package doctor_v1.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Doctor {
    private final Long fee;
    private final Set<Reception> receptions = new HashSet<>();

    public Doctor(final Long fee) {
        this.fee = fee;
    }

    public void setReception(final Reception reception) {
        this.receptions.add(reception);
    }

    public void setReceptions(final Reception... receptions) {
        this.receptions.addAll(Arrays.asList(receptions));
    }

    public Set<Reception> getReceptions() {
        return receptions;
    }
}
