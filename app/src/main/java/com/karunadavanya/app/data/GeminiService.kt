package com.karunadavanya.app.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

data class ChatMessage(
    val text: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

object GeminiClient {
    private const val API_KEY = "AIzaSyCVtWaalsDrnkx4a4grcuHpsH6KWLkz100"
    private const val URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash-lite:generateContent?key=$API_KEY"
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    suspend fun chat(prompt: String): String = withContext(Dispatchers.IO) {
        try {
            val json = JSONObject().apply {
                put("contents", JSONArray().apply {
                    put(JSONObject().apply {
                        put("parts", JSONArray().apply {
                            put(JSONObject().apply { put("text", prompt) })
                        })
                    })
                })
            }
            val body = json.toString().toRequestBody("application/json".toMediaType())
            val request = Request.Builder().url(URL).post(body).build()
            val response = client.newCall(request).execute()
            val responseBody = response.body?.string() ?: return@withContext "No response received"
            val result = JSONObject(responseBody)
            if (result.has("error")) {
                return@withContext "Error: ${result.getJSONObject("error").getString("message")}"
            }
            result.getJSONArray("candidates")
                .getJSONObject(0)
                .getJSONObject("content")
                .getJSONArray("parts")
                .getJSONObject(0)
                .getString("text")
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }
}