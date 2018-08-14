package com.tentwenty.movieticket

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.tentwenty.movieticket.feature.shared.model.TheaterLayout
import org.hamcrest.CoreMatchers.instanceOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import com.google.gson.Gson



/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    companion object {
        private const val JSON_STRING = "[\n" +
                "  {\n" +
                "    \"rowName\": \"A\",\n" +
                "    \"values\": [1,1,0,0,1,1]\n" +
                "  },\n" +
                "  {\n" +
                "    \"rowName\": \"B\",\n" +
                "    \"values\": [1,1,0,0,1,1]\n" +
                "  },\n" +
                "  {\n" +
                "    \"rowName\": \"C\",\n" +
                "    \"values\": [1,1,0,0,1,1]\n" +
                "  },\n" +
                "  {\n" +
                "    \"rowName\": \"D\",\n" +
                "    \"values\": [1,1,1,1,1,1]\n" +
                "  }\n" +
                "]"
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.tentwenty.movieticket", appContext.packageName)
    }

    @Test
    fun testGsonDeserializer() {


        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(object : TypeToken<List<TheaterLayoutNew>>() {}.type, NaturalDeserializer())
        val gson = Gson()
        val targetCollection = gson.fromJson(JSON_STRING, JSONResponse::class.java)

//        Log.d("A","${targetCollection.size}  a ${targetCollection[0]}")

        assertThat(targetCollection, instanceOf(ArrayList::class.java))


    }
}
