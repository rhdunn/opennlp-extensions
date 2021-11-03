// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.stream.sample

import opennlp.ext.conll.stream.properties.SENTENCES_PER_SAMPLE
import opennlp.ext.conll.stream.sample.SentenceSampleStream
import opennlp.tools.sentdetect.SentenceSample
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*

@DisplayName("OpenNLP SentenceSample Stream")
class SentenceSampleStreamTest {
    private fun parse(text: String, properties: Properties = Properties()): List<SentenceSample> {
        val stream = SentenceSampleStream.create(text, "conllu", properties)
        return generateSequence { stream.read() }.toList()
    }

    private fun sentences(sample: SentenceSample): List<String> = sample.sentences.map {
        sample.document.substring(it.start, it.end)
    }

    @Nested
    @DisplayName("sentences")
    inner class Sentences {
        @Test
        @DisplayName("none")
        fun none() {
            val samples = parse("")
            assertThat(samples.size, `is`(0))
        }

        @Test
        @DisplayName("single")
        fun single() {
            val samples = parse(
                """
                # text = Lorem ipsum.
                """.trimIndent()
            )

            assertThat(samples[0].document, `is`("Lorem ipsum."))
            assertThat(sentences(samples[0]), `is`(listOf("Lorem ipsum.")))

            assertThat(samples.size, `is`(1))
        }

        @Test
        @DisplayName("multiple")
        fun multiple() {
            val samples = parse(
                """
                # text = Lorem ipsum.

                # text = Dolor sed emit.
                """.trimIndent()
            )

            assertThat(samples[0].document, `is`("Lorem ipsum. Dolor sed emit."))
            assertThat(sentences(samples[0]), `is`(listOf("Lorem ipsum.", "Dolor sed emit.")))

            assertThat(samples.size, `is`(1))
        }
    }

    @Nested
    @DisplayName("sentences per sample")
    inner class SentencesPerSample {
        @Test
        @DisplayName("default")
        fun default() {
            val samples = parse(
                """
                # text = A

                # text = B

                # text = C

                # text = D

                # text = E

                # text = F

                # text = G
                """.trimIndent()
            )

            assertThat(samples[0].document, `is`("A B C D E"))
            assertThat(sentences(samples[0]), `is`(listOf("A", "B", "C", "D", "E")))

            assertThat(samples[1].document, `is`("F G"))
            assertThat(sentences(samples[1]), `is`(listOf("F", "G")))

            assertThat(samples.size, `is`(2))
        }

        @Test
        @DisplayName("non-default")
        fun nonDefault() {
            val properties = Properties()
            properties[SENTENCES_PER_SAMPLE] = "4"

            val samples = parse(
                """
                # text = A

                # text = B

                # text = C

                # text = D

                # text = E

                # text = F

                # text = G
                """.trimIndent(),
                properties
            )

            assertThat(samples[0].document, `is`("A B C D"))
            assertThat(sentences(samples[0]), `is`(listOf("A", "B", "C", "D")))

            assertThat(samples[1].document, `is`("E F G"))
            assertThat(sentences(samples[1]), `is`(listOf("E", "F", "G")))

            assertThat(samples.size, `is`(2))
        }
    }

    @Test
    @DisplayName("SpaceAfter=No")
    fun spaceAfterNo() {
        val samples = parse(
            """
                # text = Lorem ipsum.--
                1	Lorem	lorem	X	FW	_	_	_	_	_
                2	ipsum	ipsum	X	FW	_	_	_	_	SpaceAfter=No
                3	.	.	PUNCT	.	_	_	_	_	SpaceAfter=No
                4	--	--	PUNCT	:	_	_	_	_	SpaceAfter=No

                # text = Dolor sed emit.

                # text = Consectetur adipiscing elit.
                """.trimIndent()
        )

        assertThat(samples[0].document, `is`("Lorem ipsum.--Dolor sed emit. Consectetur adipiscing elit."))
        assertThat(
            sentences(samples[0]),
            `is`(listOf("Lorem ipsum.--", "Dolor sed emit.", "Consectetur adipiscing elit."))
        )

        assertThat(samples.size, `is`(1))
    }
}
