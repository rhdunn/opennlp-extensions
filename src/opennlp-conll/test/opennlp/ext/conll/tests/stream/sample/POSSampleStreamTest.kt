// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.stream.sample

import opennlp.ext.conll.stream.properties.MULTI_TOKEN_WORDS
import opennlp.ext.conll.stream.properties.MultiTokenWords
import opennlp.ext.conll.stream.properties.POS_TAGSET
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
                1	I	I	PRON	PRP	_	_	_	_	_
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

    @Nested
    @DisplayName("part of speech tagsets")
    inner class PosTagSets {
        @Test
        @DisplayName("UPOS")
        fun upos() {
            val properties = Properties()
            properties[POS_TAGSET] = "UPOS"

            val samples = parse(
                """
                1	I	I	PRON	PRP	_	_	_	_	_
                2	like	like	VERB	VBP	_	_	_	_	_
                3	cars	car	NOUN	NNS	_	_	_	_	_
                4	.	.	PUNCT	.	_	_	_	_	_
                """.trimIndent(),
                properties
            )

            assertThat(samples[0].sentence, `is`(arrayOf("I", "like", "cars", ".")))
            assertThat(samples[0].tags, `is`(arrayOf("PRON", "VERB", "NOUN", "PUNCT")))

            assertThat(samples.size, `is`(1))
        }

        @Test
        @DisplayName("CPOSTAG")
        fun cpostag() {
            val properties = Properties()
            properties[POS_TAGSET] = "CPOSTAG"

            val samples = parse(
                """
                1	I	I	PRON	PRP	_	_	_	_	_
                2	like	like	VERB	VBP	_	_	_	_	_
                3	cars	car	NOUN	NNS	_	_	_	_	_
                4	.	.	PUNCT	.	_	_	_	_	_
                """.trimIndent(),
                properties
            )

            assertThat(samples[0].sentence, `is`(arrayOf("I", "like", "cars", ".")))
            assertThat(samples[0].tags, `is`(arrayOf("PRON", "VERB", "NOUN", "PUNCT")))

            assertThat(samples.size, `is`(1))
        }

        @Test
        @DisplayName("XPOS")
        fun xpos() {
            val properties = Properties()
            properties[POS_TAGSET] = "XPOS"

            val samples = parse(
                """
                1	I	I	PRON	PRP	_	_	_	_	_
                2	like	like	VERB	VBP	_	_	_	_	_
                3	cars	car	NOUN	NNS	_	_	_	_	_
                4	.	.	PUNCT	.	_	_	_	_	_
                """.trimIndent(),
                properties
            )

            assertThat(samples[0].sentence, `is`(arrayOf("I", "like", "cars", ".")))
            assertThat(samples[0].tags, `is`(arrayOf("PRP", "VBP", "NNS", ".")))

            assertThat(samples.size, `is`(1))
        }

        @Test
        @DisplayName("POSTAG")
        fun postag() {
            val properties = Properties()
            properties[POS_TAGSET] = "POSTAG"

            val samples = parse(
                """
                1	I	I	PRON	PRP	_	_	_	_	_
                2	like	like	VERB	VBP	_	_	_	_	_
                3	cars	car	NOUN	NNS	_	_	_	_	_
                4	.	.	PUNCT	.	_	_	_	_	_
                """.trimIndent(),
                properties
            )

            assertThat(samples[0].sentence, `is`(arrayOf("I", "like", "cars", ".")))
            assertThat(samples[0].tags, `is`(arrayOf("PRP", "VBP", "NNS", ".")))

            assertThat(samples.size, `is`(1))
        }
    }

    @Nested
    @DisplayName("empty node (inserted) token")
    inner class EmptyNode {
        @Test
        @DisplayName("CopyOf")
        fun copyOf() {
            val samples = parse(
                """
                1	I	I	PRON	PRP	_	_	_	_	_
                2	like	like	VERB	VBP	_	_	_	_	_
                3	cars	car	NOUN	NNS	_	_	_	_	_
                4	and	and	CCONJ	CC	_	_	_	_	_
                4.1	like	like	VERB	VBP	_	_	_	_	CopyOf=2
                5	bikes	bike	NOUN	NNS	_	_	_	_	_
                6	.	.	PUNCT	.	_	_	_	_	_
                """.trimIndent()
            )

            assertThat(samples[0].sentence, `is`(arrayOf("I", "like", "cars", "and", "bikes", ".")))
            assertThat(samples[0].tags, `is`(arrayOf("PRON", "VERB", "NOUN", "CCONJ", "NOUN", "PUNCT")))

            assertThat(samples.size, `is`(1))
        }

        @Test
        @DisplayName("Typo=Yes")
        fun typo() {
            val samples = parse(
                """
                1	As	as	ADV	RB	_	_	_	_	_
                2	far	far	ADV	RB	_	_	_	_	_
                2.1	_	as	SCONJ	IN	Typo=Yes	_	_	_	CorrectForm=as
                3	I	I	PRON	PRP	_	_	_	_	_
                4	know	know	VERB	VBP	_	_	_	_	_
                5	.	.	PUNCT	.	_	_	_	_	_
                """.trimIndent()
            )

            assertThat(samples[0].sentence, `is`(arrayOf("As", "far", "as", "I", "know", ".")))
            assertThat(samples[0].tags, `is`(arrayOf("ADV", "ADV", "SCONJ", "PRON", "VERB", "PUNCT")))

            assertThat(samples.size, `is`(1))
        }
    }

    @Nested
    @DisplayName("multi-token words")
    inner class MultiTokenWordsTest {
        @Test
        @DisplayName("default")
        fun default() {
            val samples = parse(
                """
                1	I	I	PRON	PRP	_	_	_	_	_
                2-3	wanna	_	_	_	_	_	_	_	_
                2	wan	want	VERB	VBP	_	_	_	_	_
                3	na	to	PART	TO	_	_	_	_	_
                4	go	go	VERB	VB	_	_	_	_	_
                5	home	home	NOUN	NN	_	_	_	_	_
                6	.	.	PUNCT	.	_	_	_	_	_
                """.trimIndent()
            )

            assertThat(samples[0].sentence, `is`(arrayOf("I", "wan", "na", "go", "home", ".")))
            assertThat(samples[0].tags, `is`(arrayOf("PRON", "VERB", "PART", "VERB", "NOUN", "PUNCT")))

            assertThat(samples.size, `is`(1))
        }

        @Test
        @DisplayName("split")
        fun split() {
            val properties = Properties()
            properties[MULTI_TOKEN_WORDS] = MultiTokenWords.Split.value

            val samples = parse(
                """
                1	I	I	PRON	PRP	_	_	_	_	_
                2-3	wanna	_	_	_	_	_	_	_	_
                2	wan	want	VERB	VBP	_	_	_	_	_
                3	na	to	PART	TO	_	_	_	_	_
                4	go	go	VERB	VB	_	_	_	_	_
                5	home	home	NOUN	NN	_	_	_	_	_
                6	.	.	PUNCT	.	_	_	_	_	_
                """.trimIndent(),
                properties
            )

            assertThat(samples[0].sentence, `is`(arrayOf("I", "wan", "na", "go", "home", ".")))
            assertThat(samples[0].tags, `is`(arrayOf("PRON", "VERB", "PART", "VERB", "NOUN", "PUNCT")))

            assertThat(samples.size, `is`(1))
        }

        @Test
        @DisplayName("join")
        fun join() {
            val properties = Properties()
            properties[MULTI_TOKEN_WORDS] = MultiTokenWords.Join.value

            val samples = parse(
                """
                1	I	I	PRON	PRP	_	_	_	_	_
                2-3	wanna	_	_	_	_	_	_	_	_
                2	wan	want	VERB	VBP	_	_	_	_	_
                3	na	to	PART	TO	_	_	_	_	_
                4	go	go	VERB	VB	_	_	_	_	_
                5	home	home	NOUN	NN	_	_	_	_	_
                6	.	.	PUNCT	.	_	_	_	_	_
                """.trimIndent(),
                properties
            )

            assertThat(samples[0].sentence, `is`(arrayOf("I", "wanna", "go", "home", ".")))
            assertThat(samples[0].tags, `is`(arrayOf("PRON", "VERB+PART", "VERB", "NOUN", "PUNCT")))

            assertThat(samples.size, `is`(1))
        }
    }
}
