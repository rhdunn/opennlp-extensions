// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.treebank

import opennlp.ext.conll.treebank.Comment
import opennlp.ext.conll.treebank.Feature
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("CoNLL-U Comment")
class CommentTest {
    @Test
    @DisplayName("comment")
    fun comment() {
        val comment = Comment.parse("#  Lorem  ipsum dolor. ")
        assertThat(comment.value, `is`("Lorem  ipsum dolor."))
    }
}
