// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.tests.sentence

import opennlp.ext.sentence.Sentence
import opennlp.tools.util.Span
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Sentences")
class SentenceTest {
    @Test
    @DisplayName("empty")
    fun empty() {
        val text = ""
        val spans = arrayOf<Span>()
        val sentences = Sentence.split(text, spans)

        assertThat(sentences.size, `is`(0))
    }

    @Test
    @DisplayName("one")
    fun one() {
        val text = "abc"
        val spans = arrayOf(Span(0, 3))
        val sentences = Sentence.split(text, spans)

        assertThat(sentences[0], `is`(Sentence(null, "abc", null)))
        assertThat(sentences.size, `is`(1))
    }

    @Test
    @DisplayName("two")
    fun two() {
        val text = "abcdefg"
        val spans = arrayOf(Span(0, 3), Span(4, 7))
        val sentences = Sentence.split(text, spans)

        assertThat(sentences[0], `is`(Sentence(null, "abc", "d")))
        assertThat(sentences[1], `is`(Sentence("d", "efg", null)))
        assertThat(sentences.size, `is`(2))
    }

    @Test
    @DisplayName("multiple")
    fun multiple() {
        val text = "abcdefghijk"
        val spans = arrayOf(Span(0, 3), Span(4, 7), Span(8, 11))
        val sentences = Sentence.split(text, spans)

        assertThat(sentences[0], `is`(Sentence(null, "abc", "d")))
        assertThat(sentences[1], `is`(Sentence("d", "efg", "h")))
        assertThat(sentences[2], `is`(Sentence("h", "ijk", null)))
        assertThat(sentences.size, `is`(3))
    }

    @Test
    @DisplayName("text before")
    fun textBefore() {
        val text = "abcd"
        val spans = arrayOf(Span(1, 4))
        val sentences = Sentence.split(text, spans)

        assertThat(sentences[0], `is`(Sentence("a", "bcd", null)))
        assertThat(sentences.size, `is`(1))
    }

    @Test
    @DisplayName("text after")
    fun textAfter() {
        val text = "abcd"
        val spans = arrayOf(Span(0, 3))
        val sentences = Sentence.split(text, spans)

        assertThat(sentences[0], `is`(Sentence(null, "abc", "d")))
        assertThat(sentences.size, `is`(1))
    }
}
