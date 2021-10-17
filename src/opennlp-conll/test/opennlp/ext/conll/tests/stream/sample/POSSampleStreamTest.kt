// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.stream.sample

import opennlp.ext.conll.stream.sample.POSSampleStream
import opennlp.ext.conll.stream.sentence.SentenceStream
import opennlp.tools.postag.POSSample
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*

@DisplayName("POSSample Stream")
class POSSampleStreamTest {
    private fun parse(text: String, properties: Properties = Properties()): List<POSSample> {
        val samples = POSSampleStream.create(text, SentenceStream.CONLLU, properties)
        return generateSequence { samples.read() }.toList()
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
                1	I	I	PRON	PRN	_	_	_	_	_
                2	like	like	VERB	VBP	_	_	_	_	_
                3	cars	car	NOUN	NNS	_	_	_	_	_
                4	.	.	PUNCT	.	_	_	_	_	_
                """.trimIndent()
            )

            assertThat(samples[0].sentence, `is`(arrayOf("I", "like", "cars", ".")))
            assertThat(samples[0].tags, `is`(arrayOf("PRON", "VERB", "NOUN", "PUNCT")))

            assertThat(samples.size, `is`(1))
        }

        @Test
        @DisplayName("multiple")
        fun multiple() {
            val samples = parse(
                """
                1	I	I	PRON	PRP	_	_	_	_	_
                2	like	like	VERB	VBP	_	_	_	_	_
                3	cars	car	NOUN	NNS	_	_	_	_	_
                4	.	.	PUNCT	.	_	_	_	_	_

                1	We	we	PRON	PRP	_	_	_	_	_
                2	like	like	VERB	VBP	_	_	_	_	_
                3	them	they	PRON	PRP	_	_	_	_	_
                4	.	.	PUNCT	.	_	_	_	_	_
                """.trimIndent()
            )

            assertThat(samples[0].sentence, `is`(arrayOf("I", "like", "cars", ".")))
            assertThat(samples[0].tags, `is`(arrayOf("PRON", "VERB", "NOUN", "PUNCT")))

            assertThat(samples[1].sentence, `is`(arrayOf("We", "like", "them", ".")))
            assertThat(samples[1].tags, `is`(arrayOf("PRON", "VERB", "PRON", "PUNCT")))

            assertThat(samples.size, `is`(2))
        }
    }
}
