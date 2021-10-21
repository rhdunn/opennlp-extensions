// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.treebank

import opennlp.ext.conll.treebank.Feature
import opennlp.ext.conll.treebank.features.UnknownFeature
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("RedundantInnerClassModifier")
@DisplayName("CoNLL Feature")
class FeatureTest {
    @Test
    @DisplayName("unnamed feature")
    fun unnamed() {
        val feat = UnknownFeature(null, "lorem-ipsum")
        assertThat(feat.toString(), `is`("lorem-ipsum"))
    }

    @Test
    @DisplayName("named feature")
    fun named() {
        val feat = UnknownFeature("lorem", "ipsum")
        assertThat(feat.toString(), `is`("lorem=ipsum"))
    }

    @Nested
    @DisplayName("parse list")
    inner class ParseList {
        @Test
        @DisplayName("empty")
        fun empty() {
            val feat = Feature.parse("_")
            assertThat(feat.size, `is`(0))
        }

        @Test
        @DisplayName("invalid: missing value")
        fun missingValue() {
            val e = assertThrows<Feature.MissingFeatureException> {
                Feature.parse("")
            }
            assertThat(e.message, `is`("Missing feature"))
        }

        @Test
        @DisplayName("one")
        fun one() {
            val feat = Feature.parse("one=lorem")
            assertThat(feat[0], `is`(UnknownFeature("one", "lorem")))
            assertThat(feat.size, `is`(1))
        }

        @Test
        @DisplayName("multiple")
        fun multiple() {
            val feat = Feature.parse("one=lorem|ipsum|three=dolor")
            assertThat(feat[0], `is`(UnknownFeature("one", "lorem")))
            assertThat(feat[1], `is`(UnknownFeature(null, "ipsum")))
            assertThat(feat[2], `is`(UnknownFeature("three", "dolor")))
            assertThat(feat.size, `is`(3))
        }

        @Test
        @DisplayName("invalid: too many parts")
        fun tooManyParts() {
            val e = assertThrows<Feature.InvalidFeatureException> {
                Feature.parse("one=lorem=ipsum")
            }
            assertThat(e.message, `is`("Invalid feature: one=lorem=ipsum"))
        }
    }
}
