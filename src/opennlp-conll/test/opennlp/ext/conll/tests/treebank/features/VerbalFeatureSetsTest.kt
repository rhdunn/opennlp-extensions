// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.treebank.features

import opennlp.ext.conll.treebank.Feature
import opennlp.ext.conll.treebank.features.UnknownFeatureValue
import opennlp.ext.conll.treebank.features.verbal.VerbForm
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.sameInstance
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Universal Dependencies Verbal Feature Sets")
class VerbalFeatureSetsTest {
    private fun feature(value: String): Feature {
        val feats = Feature.parse(value)
        assertThat(feats.size, `is`(1))
        return feats.first()
    }

    @Test
    @DisplayName("VerbForm")
    fun verbForm() {
        assertThat(feature("VerbForm=Conv"), `is`(sameInstance(VerbForm.Conv)))
        assertThat(feature("VerbForm=Fin"), `is`(sameInstance(VerbForm.Fin)))
        assertThat(feature("VerbForm=Gdv"), `is`(sameInstance(VerbForm.Gdv)))
        assertThat(feature("VerbForm=Ger"), `is`(sameInstance(VerbForm.Ger)))
        assertThat(feature("VerbForm=Inf"), `is`(sameInstance(VerbForm.Inf)))
        assertThat(feature("VerbForm=Part"), `is`(sameInstance(VerbForm.Part)))
        assertThat(feature("VerbForm=Sup"), `is`(sameInstance(VerbForm.Sup)))
        assertThat(feature("VerbForm=Vnoun"), `is`(sameInstance(VerbForm.Vnoun)))
        assertThat(feature("VerbForm=Other"), `is`(UnknownFeatureValue("VerbForm", "Other")))
    }
}
