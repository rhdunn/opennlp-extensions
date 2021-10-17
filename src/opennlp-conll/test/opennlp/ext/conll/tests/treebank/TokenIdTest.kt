// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.treebank

import opennlp.ext.conll.treebank.TokenId
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("RedundantInnerClassModifier")
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
            assertThat(id.emptyNodeIndex, `is`(nullValue()))
            assertThat(id.type, `is`(TokenId.Type.Counter))
            assertThat(id.toString(), `is`("1"))

            assertThat(id.isId(1), `is`(true))
            assertThat(id.isId(2), `is`(false))
        }

        @Test
        @DisplayName("invalid token ID")
        fun invalidTokenId() {
            val e = assertThrows<TokenId.InvalidTokenIdException> { TokenId.parse("abc") }
            assertThat(e.message, `is`("Invalid token ID: abc"))
        }

        @Test
        @DisplayName("Missing token ID")
        fun missingTokenId() {
            val e = assertThrows<TokenId.MissingTokenIdException> { TokenId.parse("") }
            assertThat(e.message, `is`("Missing token ID"))
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
            assertThat(id.emptyNodeIndex, `is`(nullValue()))
            assertThat(id.type, `is`(TokenId.Type.Range))
            assertThat(id.toString(), `is`("1-4"))

            assertThat(id.isId(1), `is`(false))
            assertThat(id.isId(2), `is`(false))
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

    @Nested
    @DisplayName("CoNLL-U empty node token ID counter")
    inner class EmptyNodeToken {
        @Test
        @DisplayName("empty node token ID counter")
        fun emptyNodeTokenCounter() {
            val id = TokenId.parse("1.4")
            assertThat(id.start, `is`(1))
            assertThat(id.endInclusive, `is`(1))
            assertThat(id.emptyNodeIndex, `is`(4))
            assertThat(id.type, `is`(TokenId.Type.EmptyNodeCounter))
            assertThat(id.toString(), `is`("1.4"))

            assertThat(id.isId(1), `is`(false))
            assertThat(id.isId(2), `is`(false))
        }

        @Test
        @DisplayName("Invalid start")
        fun invalidStart() {
            val e = assertThrows<TokenId.InvalidTokenIdException> { TokenId.parse("ab.4") }
            assertThat(e.message, `is`("Invalid token ID: ab.4"))
        }

        @Test
        @DisplayName("Invalid end")
        fun invalidEnd() {
            val e = assertThrows<TokenId.InvalidTokenIdException> { TokenId.parse("1.bc") }
            assertThat(e.message, `is`("Invalid token ID: 1.bc"))
        }

        @Test
        @DisplayName("Invalid with multiple parts")
        fun invalidMultipleParts() {
            val e = assertThrows<TokenId.InvalidTokenIdException> { TokenId.parse("1.4.8") }
            assertThat(e.message, `is`("Invalid token ID: 1.4.8"))
        }
    }
}
