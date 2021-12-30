package com.developerdepository.scout.HelperClasses;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;

import com.developerdepository.scout.User.UserDashboardActivity;

import java.util.Locale;

public class LocaleHelper extends UserDashboardActivity {

    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";
    public static Context onAttach(Context context) {
        String lang = getPersistedData(context, Locale.getDefault().getLanguage());
        return setLocale(context, lang);
    }
    public static Context onAttach(Context context, String defaultLanguage) {
        String lang = getPersistedData(context, defaultLanguage);
        return setLocale(context, lang);
    }
    public static String getLanguage(Context context) {
        return getPersistedData(context, Locale.getDefault().getLanguage());
    }
    public static Context setLocale(Context context, String language) {
        persist(context, language);
        return setApplicationLanguage(context, language);
    }


    private static String getPersistedData(Context context, String defaultLanguage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage);
    }
    private static void persist(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SELECTED_LANGUAGE, language);
        editor.apply();
    }


    @SuppressLint("NewApi")
    private static Context setApplicationLanguage(Context context, String newLanguage) {
        Resources activityRes = context.getResources();
        Configuration activityConf = activityRes.getConfiguration();
        Locale newLocale = new Locale(newLanguage);
        activityConf.setLocale(newLocale);
        activityRes.updateConfiguration(activityConf, activityRes.getDisplayMetrics());

        Resources applicationRes = context.getResources();
        Configuration applicationConf = applicationRes.getConfiguration();
        applicationConf.setLocale(newLocale);
        applicationRes.updateConfiguration(applicationConf,
                applicationRes.getDisplayMetrics());
        return context;
    }

}