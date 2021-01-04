package kr.eungi.bmicalculator;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainActivityTest {

    @Test
    public void onCreate() {
    }

    @Test
    public void initViews_호출시_ButtonListener_설정() {
        // initViews 로직에 필요한 목 오브젝트 준비
        EditText weightText = mock(EditText.class);
        EditText heightText = mock(EditText.class);
        TextView resultText = mock(TextView.class);
        Button resultButton = mock(Button.class);
        View.OnClickListener buttonListener = mock(View.OnClickListener.class);

        // initViews 호출시 준비된 목오브젝트 반환, 메서드 반환값 설정
        MainActivity activity = spy(MainActivity.class);
        doReturn(weightText).when(activity).findViewById(R.id.text_weight);
        doReturn(heightText).when(activity).findViewById(R.id.text_height);
        doReturn(resultText).when(activity).findViewById(R.id.text_result);
        doReturn(resultButton).when(activity).findViewById(R.id.button_calculate);
        when(activity.createButtonListener(weightText, heightText, resultText)).thenReturn(buttonListener);

        // 테스트와 검즘
        activity.initViews();
        verify(activity, times(1)).createButtonListener(weightText, heightText, resultText);
        verify(resultButton, times(1)).setOnClickListener(buttonListener);
    }

    @Test
    public void 버튼_click_실행시_각종_처리_호출() {
        // 버튼 동작시 필요한 목 오브잭트 준비
        TextView weightText = mock(TextView.class);
        TextView heightText = mock(TextView.class);
        TextView resultText = mock(TextView.class);
        BmiValue bmiValue = mock(BmiValue.class);

        // button 의 click listener 호출시 준비된 목오브젝트 반환, 메서드 반환값 설정
        MainActivity activity = spy(MainActivity.class);
        doReturn(bmiValue).when(activity).calculateBmiValue(weightText, heightText);
        doNothing().when(activity).showCalcResult(resultText, bmiValue);
        doNothing().when(activity).startResultSaveService(bmiValue);
        doNothing().when(activity).prepareReceiveResultSaveServiceAction();

        // 테스트와 검증
        View.OnClickListener buttonListener = activity.createButtonListener(weightText, heightText, resultText);
        buttonListener.onClick(mock(View.class));
        verify(activity, times(1)).calculateBmiValue(weightText, heightText);
        verify(activity, times(1)).showCalcResult(resultText, bmiValue);
        verify(activity, times(1)).startResultSaveService(bmiValue);
        verify(activity, times(1)).prepareReceiveResultSaveServiceAction();
    }

    @Test
    public void calculateBmiValue() {
        // BmiCalculatorTest 와 중복
        TextView weightText = mock(TextView.class);
        TextView heightText = mock(TextView.class);

        MainActivity activity = spy(MainActivity.class);
        doReturn("1").when(weightText).getText();
        doReturn("1").when(heightText).getText();

        BmiValue bmi = activity.calculateBmiValue(weightText, heightText);
        Assert.assertEquals(1f, bmi.toFloat(), 0);
    }

    /**
     * Cannot mock/spy class androidx.localbroadcastmanager.content.LocalBroadcastManager
     */
    @Test
    public void prepareReceiveResultSaveServiceAction() {
//        MainActivity activity = spy(MainActivity.class);
//        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(activity.getApplicationContext());
//
//        activity.prepareReceiveResultSaveServiceAction();
//        verify(localBroadcastManager, times(1)).registerReceiver(any(MainActivity.BmiSaveResultReceiver.class), any());
    }

    @Test
    public void onReceive_바르게값이전달_안된경우_버튼에대한변경() {
        Button button = mock(Button.class);
        Intent intent = mock(Intent.class);

        MainActivity.BmiSaveResultReceiver receiver = new MainActivity.BmiSaveResultReceiver(button);
        receiver.onReceive(mock(Context.class), null);
        verify(button, times(0)).setText(any());
        verify(button, times(0)).setEnabled(true);

        receiver.onReceive(mock(Context.class), intent);
        verify(button, times(0)).setText(any());
        verify(button, times(0)).setEnabled(true);
    }

    @Test
    public void onReceive_바르게값이전달된경우_버튼에대한변경() {
        Button button = mock(Button.class);
        Intent intent = mock(Intent.class);

        when(intent.hasExtra(SaveBmiService.PARAM_RESULT)).thenReturn(true);
        when(intent.getBooleanExtra(SaveBmiService.PARAM_RESULT, false)).thenReturn(true);

        MainActivity.BmiSaveResultReceiver receiver = new MainActivity.BmiSaveResultReceiver(button);
        receiver.onReceive(mock(Context.class), intent);
        verify(button, times(1)).setText(any());
        verify(button, times(1)).setEnabled(true);
    }

}