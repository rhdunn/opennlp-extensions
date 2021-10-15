// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.treebank

import opennlp.ext.conll.treebank.TokenId
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("CoNLL-X/U TokenId")
class TokenIdTest {
    @Nested
    @DisplayName("CoNLL-X token ID counter")
    inner class TokenCounter {
        @Test
        @DisplayName("token ID counter")
        fun tokenCounter() {
            val id = TokenId.parse("1")
            assertThat(id.start, `is`(1))
            assertThat(id.toString(), `is`("1"))
        }

        @Test
        @DisplayName("invalid token ID")
        fun invalidTokenId() {
            val e = assertThrows<TokenId.InvalidTokenIdException> { TokenId.parse("abc") }
            assertThat(e.message, `is`("Invalid token ID: abc"))
        }
    }
}
