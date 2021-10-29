// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.tests.token

import opennlp.ext.token.Token
import opennlp.ext.token.tokenize
import opennlp.tools.util.Span
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Tokens")
class TokenTest {
    @Test
    @DisplayName("empty")
    fun empty() {
        val text = ""
        val spans = arrayOf<Span>()
        val tokens = text.tokenize(spans)

        assertThat(tokens.size, `is`(0))
    }

    @Test
    @DisplayName("one")
    fun one() {
        val text = "abc"
        val spans = arrayOf(Span(0, 3))
        val tokens = text.tokenize(spans)

        assertThat(tokens[0], `is`(Token(0, null, "abc", Span(0, 3))))
        assertThat(tokens.size, `is`(1))
    }

    @Test
    @DisplayName("two")
    fun two() {
        val text = "abcdefg"
        val spans = arrayOf(Span(0, 3), Span(4, 7))
        val tokens = text.tokenize(spans)

        assertThat(tokens[0], `is`(Token(0, null, "abc", Span(0, 3))))
        assertThat(tokens[1], `is`(Token(1, "d", "efg", Span(4, 7))))
        assertThat(tokens.size, `is`(2))
    }

    @Test
    @DisplayName("multiple")
    fun multiple() {
        val text = "abcdefghijk"
        val spans = arrayOf(Span(0, 3), Span(4, 7), Span(8, 11))
        val tokens = text.tokenize(spans)

        assertThat(tokens[0], `is`(Token(0, null, "abc", Span(0, 3))))
        assertThat(tokens[1], `is`(Token(1, "d", "efg", Span(4, 7))))
        assertThat(tokens[2], `is`(Token(2, "h", "ijk", Span(8, 11))))
        assertThat(tokens.size, `is`(3))
    }
}
