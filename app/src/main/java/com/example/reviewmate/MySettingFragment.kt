package com.example.reviewmate

import android.os.Bundle
import android.text.TextUtils
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat


class MySettingFragment : PreferenceFragmentCompat() {
    companion object{
        var idPreference:EditTextPreference? = null
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        idPreference = findPreference("id")
        // idPreference?.summaryProvider = EditTextPreference.SimpleSummaryProvider.getInstance()
        idPreference?.summaryProvider = Preference.SummaryProvider<EditTextPreference> {
            preference ->
                val text:String? = preference.text
                if(TextUtils.isEmpty(text)){
                    "ID 설정이 되지 않았습니다."
                }
                else{
                    "설정된 ID는 $text 입니다."
                }
        }

        val bgColorPreference: ListPreference? = findPreference("bg_color")
        bgColorPreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()

        val txColorPreference: ListPreference? = findPreference("tx_color")
        txColorPreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
    }
}