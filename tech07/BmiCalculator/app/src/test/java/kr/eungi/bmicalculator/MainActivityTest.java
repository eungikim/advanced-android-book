package kr.eungi.bmicalculator;

import android.widget.EditText;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MainActivityTest {

    @Test
    public void onCreate() {
    }

    @Test
    public void initViews() {
    }

    @Test
    public void createButtonListener() {
    }

    @Test
    public void calculateBmiValue() {
        MainActivity activity = spy(MainActivity.class);
        TextView weightText = mock(TextView.class);
        TextView heightText = mock(TextView.class);
        doReturn("1").when(weightText).getText();
        doReturn("1").when(heightText).getText();
        when(weightText.getText()).thenReturn("1");
        when(heightText.getText()).thenReturn("1");
        // https://stackoverflow.com/a/29394497 README 에 쓰시오

        BmiValue bmi = activity.calculateBmiValue(weightText, heightText);
        Assert.assertEquals(1f, bmi.toFloat(), 0);
    }

    @Test
    public void showCalcResult() {
    }

    @Test
    public void startResultSaveService() {
    }

    @Test
    public void prepareReceiveResultSaveServiceAction() {
    }

    @Test
    public void onDestroy() {
    }
}