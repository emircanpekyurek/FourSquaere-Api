package com.pekyurek.emircan.presentation.core.extensions

import org.junit.Test

internal class LongTest {


    @Test
    fun toVersionFormat() {
        //Given
        val time = 1594147500000L

        //When
        val result = time.toVersionFormat()

        //Then
        assert(result.length == 8)
        assert(result == "20200707")
        assert(result != "2020.07.07")
        assert(result != "2020/07/07")
        assert(result != "2020707")
        assert(result != "2020077")
        assert(result != "2020707")
        assert(result != "20277")
        assert(result != "202707")
    }

}