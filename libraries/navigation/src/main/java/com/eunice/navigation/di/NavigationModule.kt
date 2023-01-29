package com.eunice.navigation.di

import com.eunice.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by {EUNICE BAKARE T.} on {5/30/22}
 * Email: {eunice@reach.africa}
 */

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {
    
    @get: [Provides Singleton]
    val navigator = Navigator()
}