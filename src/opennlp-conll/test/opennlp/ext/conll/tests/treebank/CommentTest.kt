// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.treebank

import opennlp.ext.conll.treebank.Comment
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("CoNLL-U Comment")
class CommentTest {
    @Test
    @DisplayName("comment")
    fun comment() {
        val comment = Comment.parse("#  Lorem  ipsum dolor. ")
        assertThat(comment.name, `is`(nullValue()))
        assertThat(comment.value, `is`("Lorem  ipsum dolor."))
    }

    @Test
    @DisplayName("metadata comment")
    fun metadataComment() {
        val comment = Comment.parse("# lorem = ipsum")
        assertThat(comment.name, `is`("lorem"))
        assertThat(comment.value, `is`("ipsum"))
    }
}
