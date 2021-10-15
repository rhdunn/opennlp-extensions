// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.treebank

import opennlp.ext.conll.treebank.Feature
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("CoNLL Feature")
class FeatureTest {
    @Test
    @DisplayName("unnamed feature")
    fun unnamed() {
        val feat = Feature(null, "lorem-ipsum")
        assertThat(feat.toString(), `is`("lorem-ipsum"))
    }

    @Test
    @DisplayName("named feature")
    fun named() {
        val feat = Feature("lorem", "ipsum")
        assertThat(feat.toString(), `is`("lorem=ipsum"))
    }
}
