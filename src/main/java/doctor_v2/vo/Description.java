package doctor_v2.vo;

public class Description {

    private final String description;

    private Description(final String description) {

        this.description = description;
    }

    public static Description of(final String description) {
        if (description.length() < 5) {
            throw new IllegalArgumentException("[ERROR] 제목은 5글자 이상이어야합니다.");
        }
        return new Description(description);    }
}
