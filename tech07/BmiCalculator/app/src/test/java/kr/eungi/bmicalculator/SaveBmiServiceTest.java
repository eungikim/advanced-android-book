package kr.eungi.bmicalculator;

import android.content.Context;
import android.content.Intent;

import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SaveBmiServiceTest {

    @Test
    public void start() {
        Context context = mock(Context.class);
        SaveBmiService.start(context, mock(BmiValue.class));
        verify(context, times(1)).startService((Intent) any());
    }

    @Test
    public void onHandleIntent_null을닌_전달_아무것도하지않을것이다() {
        SaveBmiService service = spy(SaveBmiService.class);
        service.onHandleIntent(null);
        verify(service, never()).sendLocalBroadcast(anyBoolean());
        verify(service, never()).saveToRemoteServer(any());
    }

    @Test
    public void onHandleIntent_빈_intent를_전달_아무것도하지않을것이다() {
        SaveBmiService service = spy(SaveBmiService.class);
        service.onHandleIntent(mock(Intent.class));
        verify(service, never()).sendLocalBroadcast(anyBoolean());
        verify(service, never()).saveToRemoteServer(any());
    }

    @Test
    public void onHandleIntent_BmiValue가_아닌_데이터를_가진_intent를_전달_아무것도하지않을것이다() {
        Intent intent = mock(Intent.class);
        when(intent.getSerializableExtra(SaveBmiService.PARAM_KEY_BMI_VALUE)).thenReturn("something");

        SaveBmiService service = spy(SaveBmiService.class);
        service.onHandleIntent(intent);
        verify(service, never()).sendLocalBroadcast(anyBoolean());
        verify(service, never()).saveToRemoteServer(any());
    }

    @Test
    public void onHandleIntent_바른데이터가_들어간_intent전달_데이터저장과_sendBroadcast_발생() {
        //준비：SaveBmiService에 전달할 Intent를 준비
        BmiValue bmiValue = new BmiValue(1);
        Intent intent = mock(Intent.class);
        when(intent.getSerializableExtra(SaveBmiService.PARAM_KEY_BMI_VALUE)).thenReturn(bmiValue);

        //준비：SaveBmiService의 각 메소드는 아무것도 하지 않도록 한다
        SaveBmiService service = spy(SaveBmiService.class);
        doReturn(false).when(service).saveToRemoteServer(any());
        doNothing().when(service).sendLocalBroadcast(anyBoolean());

        //테스트와 메소드 호출 확인
        service.onHandleIntent(intent);
        verify(service, times(1)).saveToRemoteServer(any(BmiValue.class));
        verify(service, times(1)).sendLocalBroadcast(anyBoolean());
    }

    /**
     * Cannot mock/spy class androidx.localbroadcastmanager.content.LocalBroadcastManager
     * Mockito cannot mock/spy because :
     * - final class
     */
    @Test
    public void sendLocalBroadcast() {
        /*
        // 테스트를 위해 LocalBroadcastManager를 교체
        LocalBroadcastManager manager = mock(LocalBroadcastManager.class);
        SaveBmiService service = new SaveBmiService();
        service.setLocalBroadcastManager(manager);

        // Broadcast 송신
        service.sendLocalBroadcast(true);

        // LocalBroadcast로 Broadcast가 실행되고 있는지 확인
        verify(manager, times(1)).sendBroadcast((Intent)any());
        */
    }

    @Test
    public void saveToRemoteServer() {
    }


}