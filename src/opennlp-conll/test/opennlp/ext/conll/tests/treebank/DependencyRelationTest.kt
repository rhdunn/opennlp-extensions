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
            assertThat(rel[0], `is`(DependencyRelation(1, "lorem")))
            assertThat(rel.size, `is`(1))
        }

        @Test
        @DisplayName("multiple")
        fun multiple() {
            val rel = DependencyRelation.parse("1:lorem|2:ipsum|3:dolor")
            assertThat(rel[0], `is`(DependencyRelation(1, "lorem")))
            assertThat(rel[1], `is`(DependencyRelation(2, "ipsum")))
            assertThat(rel[2], `is`(DependencyRelation(3, "dolor")))
            assertThat(rel.size, `is`(3))
        }

        @Test
        @DisplayName("invalid: too many parts")
        fun tooManyParts() {
            val e = assertThrows<DependencyRelation.InvalidDependencyRelationException> {
                DependencyRelation.parse("1:lorem:ipsum")
            }
            assertThat(e.message, `is`("Invalid dependency relation: 1:lorem:ipsum"))
        }
    }
}
