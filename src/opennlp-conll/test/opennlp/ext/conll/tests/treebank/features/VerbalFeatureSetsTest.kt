// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.treebank.features

import opennlp.ext.conll.treebank.Feature
import opennlp.ext.conll.treebank.features.UnknownFeatureValue
import opennlp.ext.conll.treebank.features.verbal.Mood
import opennlp.ext.conll.treebank.features.verbal.Tense
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

    @Test
    @DisplayName("Mood")
    fun mood() {
        assertThat(feature("Mood=Adm"), `is`(sameInstance(Mood.Adm)))
        assertThat(feature("Mood=Cnd"), `is`(sameInstance(Mood.Cnd)))
        assertThat(feature("Mood=Des"), `is`(sameInstance(Mood.Des)))
        assertThat(feature("Mood=Imp"), `is`(sameInstance(Mood.Imp)))
        assertThat(feature("Mood=Ind"), `is`(sameInstance(Mood.Ind)))
        assertThat(feature("Mood=Irr"), `is`(sameInstance(Mood.Irr)))
        assertThat(feature("Mood=Jus"), `is`(sameInstance(Mood.Jus)))
        assertThat(feature("Mood=Nec"), `is`(sameInstance(Mood.Nec)))
        assertThat(feature("Mood=Opt"), `is`(sameInstance(Mood.Opt)))
        assertThat(feature("Mood=Pot"), `is`(sameInstance(Mood.Pot)))
        assertThat(feature("Mood=Prp"), `is`(sameInstance(Mood.Prp)))
        assertThat(feature("Mood=Qot"), `is`(sameInstance(Mood.Qot)))
        assertThat(feature("Mood=Sub"), `is`(sameInstance(Mood.Sub)))
        assertThat(feature("Mood=Other"), `is`(UnknownFeatureValue("Mood", "Other")))
    }

    @Test
    @DisplayName("Tense")
    fun tense() {
        assertThat(feature("Tense=Fut"), `is`(sameInstance(Tense.Fut)))
        assertThat(feature("Tense=Imp"), `is`(sameInstance(Tense.Imp)))
        assertThat(feature("Tense=Past"), `is`(sameInstance(Tense.Past)))
        assertThat(feature("Tense=Pqp"), `is`(sameInstance(Tense.Pqp)))
        assertThat(feature("Tense=Pres"), `is`(sameInstance(Tense.Pres)))
        assertThat(feature("Tense=Other"), `is`(UnknownFeatureValue("Tense", "Other")))
    }
}
