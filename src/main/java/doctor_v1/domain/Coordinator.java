package doctor_v1.domain;

public class Coordinator {
    private Reception reception;

    public void setReception(final Reception reception) {
        this.reception = reception;
    }

    public Reception getReception() {
        return reception;
    }

    public Package sellPackage(final Patient patient) {
        // 이중if를 모두 만족시켜야 not empty 반환이고, 그외 default/else/2depth else까지 emtpy반환이면
        // 이중if성공시, return객체를 받아와 재할당하는 로직이 있다면
        // -> 1) 미리 default empty로 반환변수(not final)를 만든다.
        // -> 2) 이중if 통과시만 반환변수에 not empty를 재할당한다.
        // + 마지막 2번재 return객체 재할당 후 if가 not empty검사라면, 직전에 알아서 재할당한다.
        // --> 성공시 알아서 재할당된 것이 덮어쓴다 && earlyreturn없이 맨 마지막에 return된다.
        Package packageItem = Package.EMPTY;

        // 쿠폰을 가지고 있는 경우(!= null) -> 돈 검증 생략
        final Coupon coupon = patient.getCoupon();
        if (coupon != Coupon.EMPTY) {
            // 물건 검증전 물건받기 from reception - list의 첫번째 것을 remove(0)
            // -> 쿠폰은 무료(WithNoFee)로 물건을 가져온다고 따로 기능 명시
            packageItem = reception.getPackageWithNoFee();
            if (packageItem != Package.EMPTY) {
                patient.removeCoupon();
            }
        }
        // (쿠폰이 없는 경우)-> early return이 없으므로, 자동 경우의 수가 안생기므로
        //  -> 매번 직접 위쪽if에 대한 else자리에 if문을 써줘야한다. (안해주면, 내려오면서 계속 탄다)
        if (coupon == Coupon.EMPTY) {
            // 돈 검증
            final Long packageFee = reception.calculatePackageFee();
            if (patient.hasAmount(packageFee)) {
                packageItem = reception.getPackageWithFee();
                // 물건 검증
                if (packageItem != Package.EMPTY) {
                    // 돈 차감
                    patient.minusAmount(packageFee);
                }
            }
        }

        // (쿠폰도 X 돈도X) -> early return 제거해서 경우의 수 사라지고 모두 다 내려옴.
//        return Package.EMPTY; // empty
        return packageItem;
    }
}
