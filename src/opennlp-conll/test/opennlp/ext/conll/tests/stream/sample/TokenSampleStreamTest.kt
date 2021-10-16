// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.stream.sample

import opennlp.ext.conll.stream.sample.TokenSampleStream
import opennlp.ext.conll.stream.sentence.SentenceStream
import opennlp.tools.tokenize.TokenSample
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("TokenSample Stream")
class TokenSampleStreamTest {
    private fun parse(text: String): List<TokenSample> {
        val tokens = TokenSampleStream.create(text, SentenceStream.CONLLU)
        return generateSequence { tokens.read() }.toList()
    }

    private fun tokens(sample: TokenSample): List<String> = sample.tokenSpans.map {
        sample.text.substring(it.start, it.end)
    }

    @Nested
    @DisplayName("sentences")
    inner class Sentences {
        @Test
        @DisplayName("none")
        fun none() {
            val tokens = parse("")
            assertThat(tokens.size, `is`(0))
        }

        @Test
        @DisplayName("single")
        fun single() {
            val samples = parse(
                """
                1	one	one	_	_	_	_	_	_	_
                2	+	plus	_	_	_	_	_	_	_
                3	two	two	_	_	_	_	_	_	_
                4	=	equals	_	_	_	_	_	_	_
                5	3	three	_	_	_	_	_	_	_
                """.trimIndent()
            )

            assertThat(samples[0].text, `is`("one + two = 3"))
            assertThat(tokens(samples[0]), `is`(listOf("one", "+", "two", "=", "3")))

            assertThat(samples.size, `is`(1))
        }

        @Test
        @DisplayName("multiple")
        fun multiple() {
            val samples = parse(
                """
                1	one	one	_	_	_	_	_	_	_
                2	+	plus	_	_	_	_	_	_	_
                3	two	two	_	_	_	_	_	_	_

                1	a	a	_	_	_	_	_	_	_
                2	-	minus	_	_	_	_	_	_	_
                3	b	b	_	_	_	_	_	_	_
                """.trimIndent()
            )

            assertThat(samples[0].text, `is`("one + two"))
            assertThat(tokens(samples[0]), `is`(listOf("one", "+", "two")))

            assertThat(samples[1].text, `is`("a - b"))
            assertThat(tokens(samples[1]), `is`(listOf("a", "-", "b")))

            assertThat(samples.size, `is`(2))
        }
    }

    @Test
    @DisplayName("SpaceAfter=No")
    fun spaceAfterNo() {
        val samples = parse(
            """
            1	One	One	_	_	_	_	_	_	SpaceAfter=No
            2	,	,	_	_	_	_	_	_	_
            3	two	two	_	_	_	_	_	_	SpaceAfter=No
            4	,	,	_	_	_	_	_	_	_
            5	and	and	_	_	_	_	_	_	_
            6	three	three	_	_	_	_	_	_	SpaceAfter=No
            7	.	.	_	_	_	_	_	_	_
            """.trimIndent()
        )

        assertThat(samples[0].text, `is`("One, two, and three."))
        assertThat(tokens(samples[0]), `is`(listOf("One", ",", "two", ",", "and", "three", ".")))

        assertThat(samples.size, `is`(1))
    }
}
