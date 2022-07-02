package doctor_v2.discountpolicy2;

public interface DiscountPolicy2 {

    interface AMOUNT extends DiscountPolicy2 {}
    interface PERCENT extends DiscountPolicy2 {}
    interface OVERLAPPED extends DiscountPolicy2 {}
    interface NONE extends DiscountPolicy2 {}
}
