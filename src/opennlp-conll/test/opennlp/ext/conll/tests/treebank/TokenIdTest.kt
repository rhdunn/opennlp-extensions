// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.treebank

import opennlp.ext.conll.treebank.TokenId
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("CoNLL TokenId")
class TokenIdTest {
    @Nested
    @DisplayName("CoNLL-X token ID counter")
    inner class TokenCounter {
        @Test
        @DisplayName("token ID counter")
        fun tokenCounter() {
            val id = TokenId.parse("1")
            assertThat(id.start, `is`(1))
            assertThat(id.endInclusive, `is`(1))
            assertThat(id.toString(), `is`("1"))
        }

        @Test
        @DisplayName("invalid token ID")
        fun invalidTokenId() {
            val e = assertThrows<TokenId.InvalidTokenIdException> { TokenId.parse("abc") }
            assertThat(e.message, `is`("Invalid token ID: abc"))
        }
    }

    @Nested
    @DisplayName("CoNLL-U token ID range")
    inner class TokenRange {
        @Test
        @DisplayName("token ID range")
        fun tokenRange() {
            val id = TokenId.parse("1-4")
            assertThat(id.start, `is`(1))
            assertThat(id.endInclusive, `is`(4))
            assertThat(id.toString(), `is`("1-4"))
        }

        @Test
        @DisplayName("invalid start")
        fun invalidStart() {
            val e = assertThrows<TokenId.InvalidTokenIdException> { TokenId.parse("ab-4") }
            assertThat(e.message, `is`("Invalid token ID: ab-4"))
        }

        @Test
        @DisplayName("invalid end")
        fun invalidEnd() {
            val e = assertThrows<TokenId.InvalidTokenIdException> { TokenId.parse("1-bc") }
            assertThat(e.message, `is`("Invalid token ID: 1-bc"))
        }

        @Test
        @DisplayName("invalid with multiple parts")
        fun invalidMultipleParts() {
            val e = assertThrows<TokenId.InvalidTokenIdException> { TokenId.parse("1-4-8") }
            assertThat(e.message, `is`("Invalid token ID: 1-4-8"))
        }

        @Test
        @DisplayName("invalid with start > end")
        fun invalidRangeOrder() {
            val e = assertThrows<TokenId.InvalidTokenIdException> { TokenId.parse("4-1") }
            assertThat(e.message, `is`("Invalid token ID: 4-1"))
        }
    }
}
