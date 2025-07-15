package com.io.tea.android.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore by preferencesDataStore(name = "settings")

object DataStoreHelper {
    private val EXAMPLE_KEY = stringPreferencesKey("example_key")
    private val BOOLEAN_KEY = booleanPreferencesKey("boolean_key")

    suspend fun saveStringData(context: Context, value: String) {
        context.dataStore.edit { preferences ->
            preferences[EXAMPLE_KEY] = value
        }
    }

    suspend fun saveBooleanData(context: Context, value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[BOOLEAN_KEY] = value
        }
    }

    // データを取得するメソッド
    fun getStringData(context: Context): Flow<String> {
        return context.dataStore.data
            .catch { Exception ->
                // エラーハンドリング
                if (Exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw Exception
                }
            }
            .map { preferences ->
                preferences[EXAMPLE_KEY] ?: "Default Value"
            }
    }

    fun getBooleanData(context: Context): Flow<Boolean> {
        return context.dataStore.data
            .catch { Exception ->
                if (Exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw Exception
                }
            }
            .map { preferences ->
                preferences[BOOLEAN_KEY] ?: false // デフォルト値
            }
    }

    suspend fun clearStringData(context: Context) {
        context.dataStore.edit { preferences ->
            preferences.remove(EXAMPLE_KEY)
        }
    }

    suspend fun clearBooleanData(context: Context) {
        context.dataStore.edit { preferences ->
            preferences.remove(BOOLEAN_KEY)
        }
    }
}