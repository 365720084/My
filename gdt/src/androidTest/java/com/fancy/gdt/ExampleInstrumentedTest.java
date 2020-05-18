package com.ptg.gdt;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented PtgAdGdt, which will execute on an Android device.
 *
 * @see <v href="http://d.android.com/tools/testing">Testing documentation</v>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under PtgAdGdt.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.ptg.gdt.PtgAdGdt", appContext.getPackageName());
    }
}
