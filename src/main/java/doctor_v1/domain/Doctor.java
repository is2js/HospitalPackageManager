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

    public void setPackage(final Reception reception, Long number) {
        if (!receptions.contains(reception)) {
            throw new RuntimeException("협력관계의 원무과 직원이 아닙니다.");
        }

        while (number-- >0) {
            reception.addPackage(new Package(this));
        }
    }

    public void setCoupon(final Patient patient) {
        //몇번 이상 진료내역있는 회원인지 검증하고 줘도 될 듯?
        patient.setCoupon(new Coupon(this));
    }

    public Long getFee() {
        return fee;
    }
}
