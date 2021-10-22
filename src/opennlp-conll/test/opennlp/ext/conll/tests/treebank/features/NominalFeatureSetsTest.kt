// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.treebank.features

import opennlp.ext.conll.treebank.Feature
import opennlp.ext.conll.treebank.features.UnknownFeatureValue
import opennlp.ext.conll.treebank.features.nominal.Animacy
import opennlp.ext.conll.treebank.features.nominal.Gender
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.sameInstance
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Universal Dependencies Nominal Feature Sets")
class NominalFeatureSetsTest {
    private fun feature(value: String): Feature {
        val feats = Feature.parse(value)
        assertThat(feats.size, `is`(1))
        return feats.first()
    }

    @Test
    @DisplayName("Gender")
    fun gender() {
        assertThat(feature("Gender=Com"), `is`(sameInstance(Gender.Com)))
        assertThat(feature("Gender=Fem"), `is`(sameInstance(Gender.Fem)))
        assertThat(feature("Gender=Masc"), `is`(sameInstance(Gender.Masc)))
        assertThat(feature("Gender=Neut"), `is`(sameInstance(Gender.Neut)))
        assertThat(feature("Gender=Other"), `is`(UnknownFeatureValue("Gender", "Other")))
    }

    @Test
    @DisplayName("Animacy")
    fun animacy() {
        assertThat(feature("Animacy=Anim"), `is`(sameInstance(Animacy.Anim)))
        assertThat(feature("Animacy=Hum"), `is`(sameInstance(Animacy.Hum)))
        assertThat(feature("Animacy=Inan"), `is`(sameInstance(Animacy.Inan)))
        assertThat(feature("Animacy=Nhum"), `is`(sameInstance(Animacy.Nhum)))
        assertThat(feature("Animacy=Other"), `is`(UnknownFeatureValue("Animacy", "Other")))
    }
}
