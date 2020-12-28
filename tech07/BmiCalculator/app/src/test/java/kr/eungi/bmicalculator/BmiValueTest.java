package kr.eungi.bmicalculator;

import org.junit.Assert;
import org.junit.Test;


public class BmiValueTest {

    @Test
    public void 생성자_float_값을_소수점_2자리까지_반올림해_반환() {
        BmiValue bmiValue = new BmiValue(24.225f);
        Assert.assertEquals(24.23f, bmiValue.toFloat(), 0);
        BmiValue bmiValue2 = new BmiValue(24.221f);
        Assert.assertEquals(24.22f, bmiValue2.toFloat(), 0);
        BmiValue bmiValue3 = new BmiValue(24.229f);
        Assert.assertEquals(24.23f, bmiValue3.toFloat(), 0);
    }

    @Test
    public void 마른체형판정_상한값() {
        BmiValue bmiValue = new BmiValue(18.499f);
        Assert.assertEquals(BmiValue.THIN, bmiValue.getMessage());
    }

    @Test
    public void 보통체형판정_하한값() {
        BmiValue bmiValue = new BmiValue(18.500f);
        Assert.assertEquals(BmiValue.NORMAL, bmiValue.getMessage());
    }

    @Test
    public void 보통체형판정_상한값() {
        BmiValue bmiValue = new BmiValue(24.999f);
        Assert.assertEquals(BmiValue.NORMAL, bmiValue.getMessage());
    }

    @Test
    public void 경도비만판정_하한값() {
        BmiValue bmiValue = new BmiValue(25.000f);
        Assert.assertEquals(BmiValue.OBESITY, bmiValue.getMessage());
    }

    @Test
    public void 경도비만판정_상한값() {
        BmiValue bmiValue = new BmiValue(29.999f);
        Assert.assertEquals(BmiValue.OBESITY, bmiValue.getMessage());
    }

    @Test
    public void 중도비만판정_상한값() {
        BmiValue bmiValue = new BmiValue(30.000f);
        Assert.assertEquals(BmiValue.VERY_OBESITY, bmiValue.getMessage());
    }

}