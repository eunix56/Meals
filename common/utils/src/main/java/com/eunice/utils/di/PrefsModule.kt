package com.eunice.utils.di

import android.content.Context
import com.eunice.utils.Prefs
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by {EUNICE BAKARE T.} on {6/28/22}
 * Email: {eunice@reach.africa}
 */

@Module
@InstallIn(SingletonComponent::class)
class PrefsModule {
    
    @[Provides Singleton]
    fun getPrefs(@ApplicationContext appContext: Context, moshi: Moshi): Prefs {
        return Prefs(appContext, moshi)
    }
}