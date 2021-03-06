// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.treebank.features

import opennlp.ext.conll.treebank.Feature
import opennlp.ext.conll.treebank.features.UnknownFeatureValue
import opennlp.ext.conll.treebank.features.verbal.*
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

    @Test
    @DisplayName("Aspect")
    fun aspect() {
        assertThat(feature("Aspect=Hab"), `is`(sameInstance(Aspect.Hab)))
        assertThat(feature("Aspect=Imp"), `is`(sameInstance(Aspect.Imp)))
        assertThat(feature("Aspect=Iter"), `is`(sameInstance(Aspect.Iter)))
        assertThat(feature("Aspect=Perf"), `is`(sameInstance(Aspect.Perf)))
        assertThat(feature("Aspect=Prog"), `is`(sameInstance(Aspect.Prog)))
        assertThat(feature("Aspect=Prosp"), `is`(sameInstance(Aspect.Prosp)))
        assertThat(feature("Aspect=Other"), `is`(UnknownFeatureValue("Aspect", "Other")))
    }

    @Test
    @DisplayName("Voice")
    fun voice() {
        assertThat(feature("Voice=Act"), `is`(sameInstance(Voice.Act)))
        assertThat(feature("Voice=Antip"), `is`(sameInstance(Voice.Antip)))
        assertThat(feature("Voice=Bfoc"), `is`(sameInstance(Voice.Bfoc)))
        assertThat(feature("Voice=Cau"), `is`(sameInstance(Voice.Cau)))
        assertThat(feature("Voice=Dir"), `is`(sameInstance(Voice.Dir)))
        assertThat(feature("Voice=Inv"), `is`(sameInstance(Voice.Inv)))
        assertThat(feature("Voice=Lfoc"), `is`(sameInstance(Voice.Lfoc)))
        assertThat(feature("Voice=Mid"), `is`(sameInstance(Voice.Mid)))
        assertThat(feature("Voice=Pass"), `is`(sameInstance(Voice.Pass)))
        assertThat(feature("Voice=Rcp"), `is`(sameInstance(Voice.Rcp)))
        assertThat(feature("Voice=Other"), `is`(UnknownFeatureValue("Voice", "Other")))
    }

    @Test
    @DisplayName("Evident")
    fun evident() {
        assertThat(feature("Evident=Fh"), `is`(sameInstance(Evident.Fh)))
        assertThat(feature("Evident=Nfh"), `is`(sameInstance(Evident.Nfh)))
        assertThat(feature("Evident=Other"), `is`(UnknownFeatureValue("Evident", "Other")))
    }

    @Test
    @DisplayName("Polarity")
    fun polarity() {
        assertThat(feature("Polarity=Neg"), `is`(sameInstance(Polarity.Neg)))
        assertThat(feature("Polarity=Pos"), `is`(sameInstance(Polarity.Pos)))
        assertThat(feature("Polarity=Other"), `is`(UnknownFeatureValue("Polarity", "Other")))
    }

    @Test
    @DisplayName("Person")
    fun person() {
        assertThat(feature("Person=0"), `is`(sameInstance(Person.Zero)))
        assertThat(feature("Person=1"), `is`(sameInstance(Person.First)))
        assertThat(feature("Person=2"), `is`(sameInstance(Person.Second)))
        assertThat(feature("Person=3"), `is`(sameInstance(Person.Third)))
        assertThat(feature("Person=4"), `is`(sameInstance(Person.Fourth)))
        assertThat(feature("Person=Other"), `is`(UnknownFeatureValue("Person", "Other")))
    }

    @Test
    @DisplayName("Polite")
    fun polite() {
        assertThat(feature("Polite=Elev"), `is`(sameInstance(Polite.Elev)))
        assertThat(feature("Polite=Form"), `is`(sameInstance(Polite.Form)))
        assertThat(feature("Polite=Humb"), `is`(sameInstance(Polite.Humb)))
        assertThat(feature("Polite=Infm"), `is`(sameInstance(Polite.Infm)))
        assertThat(feature("Polite=Other"), `is`(UnknownFeatureValue("Polite", "Other")))
    }

    @Test
    @DisplayName("Clusivity")
    fun clusivity() {
        assertThat(feature("Clusivity=Ex"), `is`(sameInstance(Clusivity.Ex)))
        assertThat(feature("Clusivity=In"), `is`(sameInstance(Clusivity.In)))
        assertThat(feature("Clusivity=Other"), `is`(UnknownFeatureValue("Clusivity", "Other")))
    }
}
