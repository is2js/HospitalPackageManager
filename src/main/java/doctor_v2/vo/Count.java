package doctor_v2.vo;

public class Count {


    private final Long count;

    private Count(final Long count) {
        this.count = count;
    }

    public static Count of(final Long count) {
        if (count < 0) {
            throw new IllegalArgumentException("[ERROR] 음수를 입력할 수 없습니다.");
        }
        return new Count(count);
    }
}
