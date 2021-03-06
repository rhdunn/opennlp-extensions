// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.treebank

import opennlp.ext.conll.treebank.DependencyRelation
import opennlp.ext.conll.treebank.TokenId
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("RedundantInnerClassModifier", "ReplaceNotNullAssertionWithElvisReturn")
@DisplayName("CoNLL DependencyRelation")
class DependencyRelationTest {
    private val token1 = TokenId(1, 1, null)
    private val token2 = TokenId(2, 2, null)
    private val token3 = TokenId(3, 3, null)

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
            assertThat(rel.head, `is`(token1))
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

    @Nested
    @DisplayName("root")
    inner class Root {
        @Test
        @DisplayName("root")
        fun root() {
            val rel = DependencyRelation.create("0", "root")
            assertThat(rel, `is`(sameInstance(DependencyRelation.ROOT)))
        }

        @Test
        @DisplayName("Invalid root head")
        fun invalidRootHead() {
            val e = assertThrows<DependencyRelation.InvalidRootException> {
                DependencyRelation.create("1", "root")
            }
            assertThat(e.message, `is`("Invalid root node dependency relation: 1:root"))
        }

        @Test
        @DisplayName("Invalid root relation")
        fun invalidRootRelation() {
            val e = assertThrows<DependencyRelation.InvalidRootException> {
                DependencyRelation.create("0", "lorem")
            }
            assertThat(e.message, `is`("Invalid root node dependency relation: 0:lorem"))
        }
    }

    @Nested
    @DisplayName("parse list")
    inner class ParseList {
        @Test
        @DisplayName("empty")
        fun empty() {
            val rel = DependencyRelation.parse("_")
            assertThat(rel.size, `is`(0))
        }

        @Test
        @DisplayName("invalid: missing value")
        fun missingValue() {
            val e = assertThrows<DependencyRelation.MissingDependencyRelationException> {
                DependencyRelation.parse("")
            }
            assertThat(e.message, `is`("Missing dependency relation"))
        }

        @Test
        @DisplayName("one")
        fun one() {
            val rel = DependencyRelation.parse("1:lorem")
            assertThat(rel[0], `is`(DependencyRelation(token1, "lorem")))
            assertThat(rel.size, `is`(1))
        }

        @Test
        @DisplayName("multiple")
        fun multiple() {
            val rel = DependencyRelation.parse("1:lorem|2:ipsum|3:dolor")
            assertThat(rel[0], `is`(DependencyRelation(token1, "lorem")))
            assertThat(rel[1], `is`(DependencyRelation(token2, "ipsum")))
            assertThat(rel[2], `is`(DependencyRelation(token3, "dolor")))
            assertThat(rel.size, `is`(3))
        }

        @Test
        @DisplayName("multiple parts")
        fun multipleParts() {
            val rel = DependencyRelation.parse("1:lorem:ipsum")
            assertThat(rel[0], `is`(DependencyRelation(token1, "lorem:ipsum")))
            assertThat(rel.size, `is`(1))
        }
    }
}
