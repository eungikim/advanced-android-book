package kr.eungi.bmicalculator;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Mockito에서 제공하는 @Mock, @Spy, @InjectMocks과 같은 annotation을 사용하는
 * field를 초기화 하는 방법 3가지.<br/>
 * https://jongmin92.github.io/2019/02/10/Java/mockito-anotation-field-initialize/
 */
//@RunWith(MockitoJUnitRunner.class)
public class BmiCalculatorTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Spy
    private BmiCalculator mCalculator = new BmiCalculator();

    @Before
    public void setup() {
//        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = RuntimeException.class)
    public void 신장에_음수값을_넘기면_예외발생() {
        mCalculator.calculate(-1f, 60.f);
    }

    @Test(expected = RuntimeException.class)
    public void 체중에_음수값을_넘기면_예외발생() {
        mCalculator.calculate(170.f, -1f);
    }

    @Test
    public void 단순한_값을_넘겨_BMI_계산() {
        BmiValue result = mCalculator.calculate(1, 1);
        assertNotNull(result);
        verify(mCalculator, times(1)).createValueObj(1f);
    }

    @Test
    public void 적절한_값을_넘겨_BMI_계산() {
        BmiValue result = mCalculator.calculate(1.70f, 70f);
        assertNotNull(result);
        verify(mCalculator, times(1)).createValueObj(24.22145328719723f);
    }

}
