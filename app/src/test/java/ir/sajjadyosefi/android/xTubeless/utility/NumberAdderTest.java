package ir.sajjadyosefi.android.xTubeless.utility;

import org.junit.Test;
import org.mockito.Mock;

import ir.sajjadyosefi.android.xTubeless.activity.MainActivity;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NumberAdderTest {

    @Mock
    MainActivity mMockMainActivity;

    @Test
    public void testIsNumberValid() {

        //setup

        //test
        NumberAdder numberAdder = new NumberAdder(mMockMainActivity);
        assert(numberAdder.isNumberValid(55.0));
    }

    @Test
    public void testIsNumberNotValid() {

        //setup

        //test
        NumberAdder numberAdder = new NumberAdder(mMockMainActivity);
        assertFalse(numberAdder.isNumberValid(-55.0));
    }

    @Test
    public void testPerformAddition() {

        //setup

        when(mMockMainActivity.getFirstNumber())
            .thenReturn(10.0);

        when(mMockMainActivity.getSecondNumber())
            .thenReturn(11.0);

        //test
        NumberAdder numberAdder = new NumberAdder(mMockMainActivity);

        numberAdder.performAddition();

        //verify
        verify(mMockMainActivity).setAdditionResult(21.0);
    }
}
