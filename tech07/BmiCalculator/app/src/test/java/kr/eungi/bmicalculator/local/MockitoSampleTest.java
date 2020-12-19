package kr.eungi.bmicalculator.local;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MockitoSampleTest {

    @Test
    public void insertTest() {
        // 목 객체 생성
        List mockedList = mock(List.class);
        // mockedList.get(0) 이 반환할 값을 "first" 로 설정
        when(mockedList.get(0)).thenReturn("first");

        assertEquals("first", mockedList.get(0));
        assertNull(mockedList.get(999));
    }


    @Test
    public void methodCallTest() {
        // mock creation
        List mockedList = mock(List.class);

        // using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.clear();

        // selective, explicit, highly readable verification
        // add(), clear() 메서드가 1회씩 호출됐다는 것을 검
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

}
