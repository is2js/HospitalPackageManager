package doctor_v2.domain;

import doctor_v2.Specialty;
import doctor_v2.vo.Count;
import doctor_v2.vo.Money;
import java.util.List;

public class Coordinator {
    private Reception reception;

    public void setReception(final Reception reception) {
        this.reception = reception;
    }

    public Reception getReception() {
        return reception;
    }

    public Package sellPackage(final Patient patient,
                               final Doctor doctor,
                               final Specialty specialty,
                               final Treatment treatment,
                               final Count count) {
        // 1. if + 중간로직 + if 성공 -> 그외 많은 경우가 실패이므로 early return없이  default NULL객체가 반환되도록  미리 반환변수를 NULL객체로 선언하고, 성공의 경우 재할당해주도록 짠다.
        Package packageItem = Package.EMPTY;

        // 2. 상대방이 쿠폰을 가지고 있는지 확인한다.
        // -> 2가지 경우의 수를 [if-else] or [if-if] or [if-earlyReturn + NoIf]
        // -> if1개로만 2가지 경우를 나누려면 early return해줘야 아랫부분이 반대 경우이나, 위에 default 반환변수를 각각 맨끝에 적용해줘야한다.
//        final Coupon coupon = (Coupon) coupons;
        final List<Coupon> coupons = patient.getCoupons();

        // A: 쿠폰 있는 경우
//        if (coupon != Coupon.EMPTY) {
        if (!coupons.isEmpty()) {
            // A-0 돈검증 대신, 쿠폰을 해당 갯수만큼 들고 있는지 검증한다.
            if (!patient.hasCoupons(count)) {
                throw new RuntimeException("[ERROR] 쿠폰의 갯수가 모자랍니다.");
            }
            // A-1) 돈계산, 돈검증 없이 reception에게 물건을 받아온다 sellPackage -> getPackageWithCoupon
            //      구매정보는 그대로 건네준다.
            packageItem = reception.getPackageWithCoupon(doctor, specialty, treatment, count);
            // A-2 ) 물건 검증후 양호시 돈차감 대신 쿠폰차감을 한다.
            if (packageItem != Package.EMPTY) {
                // A-3) 쿠폰 차감
                patient.minusCoupon(count);
            }
            return packageItem; // early return for 아래의 쿠폰 없는 경우에 else 안쓰려고
        }

        // B: 쿠폰 없는 경우
        // B-1) 돈검증에 필요한 돈 계산을 reception에게 위임해서 한다. 내부에서는 결국 specialty가 한다.
        //      treatment는 정책적용시 trigger(정책조건)을 확인하기 위해 같이 넘겨준다.(현재 미적용)
        final Money sales = reception.calculateFee(specialty, treatment, count);
        // B-2) 상대방 돈검증
        if (patient.hasAmount(sales)) {
            // B-3) 물건 떼오기(if와 if사이 추가로직 -> A && B 형태가 아니게 됨.
            packageItem = reception.sellPackage(doctor, specialty, treatment, count);
            // B-4) 물건 검증
            if (packageItem != Package.EMPTY) {
                // B-5) 상대방 돈차감
                patient.minusAmount(sales);
            }
        }

        return packageItem;
    }
}
