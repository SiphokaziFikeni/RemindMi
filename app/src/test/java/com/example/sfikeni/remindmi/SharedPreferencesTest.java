package com.example.sfikeni.remindmi;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sfikeni.remindmi.utils.PreferencesHelper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;


/**
 * Created by SFikeni on 2018/02/20.
 */

@RunWith(MockitoJUnitRunner.class)
public class SharedPreferencesTest {

    PreferencesHelper mockPreferencesHelper;

    @Mock
    SharedPreferences mockSharedPreference;

    @Mock
    SharedPreferences.Editor mockPreferenceEditor;

    @Mock
    Context context;


    @Before
    public void initMocks(){
//        mockPreferencesHelper = Mockito.mock(PreferencesHelper.class);
        mockSharedPreference = Mockito.mock(SharedPreferences.class);
        context = Mockito.mock(Context.class);

        mockPreferencesHelper = new PreferencesHelper(context);

        when(context.getSharedPreferences("RemindMiPreferences", Context.MODE_PRIVATE)).thenReturn(mockSharedPreference);
        when(mockPreferenceEditor.commit()).thenReturn(true);
        when(mockSharedPreference.edit()).thenReturn(mockPreferenceEditor);

    }

    @Test
    public void sharedPreferenceSaveHourForReminder(){

        int hour = 13;

        boolean isSaved = mockPreferencesHelper.setHour(hour);

        Assert.assertEquals(true, isSaved);
//        Assert.assertThat("Check that hour save is persisted and returns true", isSaved, is(true));
    }

//    @After


}
