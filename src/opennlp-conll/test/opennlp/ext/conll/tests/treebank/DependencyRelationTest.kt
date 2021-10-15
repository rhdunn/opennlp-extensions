// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.treebank

import opennlp.ext.conll.treebank.DependencyRelation
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("CoNLL DependencyRelation")
class DependencyRelationTest {
    @Nested
    @DisplayName("create")
    inner class Create {
        @Test
        @DisplayName("blank head")
        fun blankHead() {
            val rel = DependencyRelation.create("_", "test")
            assertThat(rel, `is`(nullValue()))
        }

        @Test
        @DisplayName("blank relation")
        fun blankRelation() {
            val rel = DependencyRelation.create("0", "_")
            assertThat(rel, `is`(nullValue()))
        }

        @Test
        @DisplayName("dependency relation")
        fun dependencyRelation() {
            val rel = DependencyRelation.create("1", "test")!!
            assertThat(rel.head, `is`(1))
            assertThat(rel.deprel, `is`("test"))
            assertThat(rel.toString(), `is`("1:test"))
        }

        @Test
        @DisplayName("Invalid head")
        fun invalidHead() {
            val e = assertThrows<DependencyRelation.InvalidDependencyRelationException> {
                DependencyRelation.create("abc", "def")
            }
            assertThat(e.message, `is`("Invalid dependency relation: abc:def"))
        }
    }
}
