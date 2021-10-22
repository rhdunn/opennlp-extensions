// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.treebank.features

import opennlp.ext.conll.treebank.Feature
import opennlp.ext.conll.treebank.features.UnknownFeatureValue
import opennlp.ext.conll.treebank.features.misc.SpaceAfter
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.sameInstance
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@Suppress("RedundantInnerClassModifier")
@DisplayName("Universal Dependencies MISC Feature Sets")
class MiscFeatureSetsTest {
    private fun feature(value: String): Feature {
        val feats = Feature.parse(value)
        assertThat(feats.size, `is`(1))
        return feats.first()
    }

    @Test
    @DisplayName("SpaceAfter")
    fun spaceAfter() {
        assertThat(feature("SpaceAfter=No"), `is`(sameInstance(SpaceAfter.No)))
        assertThat(feature("SpaceAfter=Yes"), `is`(UnknownFeatureValue("SpaceAfter", "Yes")))
    }
}
