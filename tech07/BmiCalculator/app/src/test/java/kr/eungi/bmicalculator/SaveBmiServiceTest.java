package kr.eungi.bmicalculator;

import android.content.Context;
import android.content.Intent;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SaveBmiServiceTest {

    @Test
    public void static인start메소드를호출하면startService된다() {
        Context context = mock(Context.class);
        SaveBmiService.start(context, mock(BmiValue.class));
        verify(context, times(1)).startService((Intent) any());
    }

    @Test
    public void onCreate() {
    }

    @Test
    public void onHandleIntent() {
    }

    @Test
    public void saveToRemoteServer() {
    }

    @Test
    public void sendLocalBroadcast() {
    }

    @Test
    public void setLocalBroadcastManager() {
    }

    @Test
    public void start() {
    }
}