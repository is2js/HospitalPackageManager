package doctor_v1.domain;

import java.util.Objects;

public class Package {
    public static final Package EMPTY = new Package(null);
    private final Doctor doctor;
    private boolean isUsed = false;

    public Package(final Doctor doctor) {

        this.doctor = doctor;
    }

    public Long getFee() {
        return this.doctor.getFee();
    }

    public boolean isValid(final Doctor doctor) {
        // 1회성 객체은 ON/OFF되는 객체처럼, flag변수를 가지며 검증에 포함된다.
        // 검증되는 소모성 객체는 EMPTY도 검사에 포함시킨다.
        if (isUsed || !Objects.equals(this.doctor, doctor) || this == EMPTY) {
            return false;
        }
        // 유효한 티켓일 때, isUsed = true;를 주고, true를 return하기도..
        // -> 검사하는 순간이 사용순간일 때! 사용표시를 해버림.
        isUsed = true;
        // 만약 여기서 안하면, 나가서 해야하는데.. boolean validatePackage라는 메서드라서
        // if isValid -> switch하고나선.. return형이... boolean이 아니게 되기도

        return true;
    }

    public void setUsedState() {
        this.isUsed = true;
    }
}
