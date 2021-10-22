// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.treebank.features

import opennlp.ext.conll.treebank.Feature
import opennlp.ext.conll.treebank.features.UnknownFeatureValue
import opennlp.ext.conll.treebank.features.nominal.Animacy
import opennlp.ext.conll.treebank.features.nominal.Gender
import opennlp.ext.conll.treebank.features.nominal.NounClass
import opennlp.ext.conll.treebank.features.nominal.Number
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

    @Test
    @DisplayName("NounClass")
    fun nounClass() {
        assertThat(feature("NounClass=Bantu1"), `is`(sameInstance(NounClass.Bantu1)))
        assertThat(feature("NounClass=Bantu2"), `is`(sameInstance(NounClass.Bantu2)))
        assertThat(feature("NounClass=Bantu3"), `is`(sameInstance(NounClass.Bantu3)))
        assertThat(feature("NounClass=Bantu4"), `is`(sameInstance(NounClass.Bantu4)))
        assertThat(feature("NounClass=Bantu5"), `is`(sameInstance(NounClass.Bantu5)))
        assertThat(feature("NounClass=Bantu6"), `is`(sameInstance(NounClass.Bantu6)))
        assertThat(feature("NounClass=Bantu7"), `is`(sameInstance(NounClass.Bantu7)))
        assertThat(feature("NounClass=Bantu8"), `is`(sameInstance(NounClass.Bantu8)))
        assertThat(feature("NounClass=Bantu9"), `is`(sameInstance(NounClass.Bantu9)))
        assertThat(feature("NounClass=Bantu10"), `is`(sameInstance(NounClass.Bantu10)))
        assertThat(feature("NounClass=Bantu11"), `is`(sameInstance(NounClass.Bantu11)))
        assertThat(feature("NounClass=Bantu12"), `is`(sameInstance(NounClass.Bantu12)))
        assertThat(feature("NounClass=Bantu13"), `is`(sameInstance(NounClass.Bantu13)))
        assertThat(feature("NounClass=Bantu14"), `is`(sameInstance(NounClass.Bantu14)))
        assertThat(feature("NounClass=Bantu15"), `is`(sameInstance(NounClass.Bantu15)))
        assertThat(feature("NounClass=Bantu16"), `is`(sameInstance(NounClass.Bantu16)))
        assertThat(feature("NounClass=Bantu17"), `is`(sameInstance(NounClass.Bantu17)))
        assertThat(feature("NounClass=Bantu18"), `is`(sameInstance(NounClass.Bantu18)))
        assertThat(feature("NounClass=Bantu19"), `is`(sameInstance(NounClass.Bantu19)))
        assertThat(feature("NounClass=Bantu20"), `is`(sameInstance(NounClass.Bantu20)))
        assertThat(feature("NounClass=Bantu21"), `is`(sameInstance(NounClass.Bantu21)))
        assertThat(feature("NounClass=Bantu22"), `is`(sameInstance(NounClass.Bantu22)))
        assertThat(feature("NounClass=Bantu23"), `is`(sameInstance(NounClass.Bantu23)))
        assertThat(feature("NounClass=Wol1"), `is`(sameInstance(NounClass.Wol1)))
        assertThat(feature("NounClass=Wol2"), `is`(sameInstance(NounClass.Wol2)))
        assertThat(feature("NounClass=Wol3"), `is`(sameInstance(NounClass.Wol3)))
        assertThat(feature("NounClass=Wol4"), `is`(sameInstance(NounClass.Wol4)))
        assertThat(feature("NounClass=Wol5"), `is`(sameInstance(NounClass.Wol5)))
        assertThat(feature("NounClass=Wol6"), `is`(sameInstance(NounClass.Wol6)))
        assertThat(feature("NounClass=Wol7"), `is`(sameInstance(NounClass.Wol7)))
        assertThat(feature("NounClass=Wol8"), `is`(sameInstance(NounClass.Wol8)))
        assertThat(feature("NounClass=Wol9"), `is`(sameInstance(NounClass.Wol9)))
        assertThat(feature("NounClass=Wol10"), `is`(sameInstance(NounClass.Wol10)))
        assertThat(feature("NounClass=Wol11"), `is`(sameInstance(NounClass.Wol11)))
        assertThat(feature("NounClass=Wol12"), `is`(sameInstance(NounClass.Wol12)))
        assertThat(feature("NounClass=Other"), `is`(UnknownFeatureValue("NounClass", "Other")))
    }

    @Test
    @DisplayName("Number")
    fun number() {
        assertThat(feature("Number=Coll"), `is`(sameInstance(Number.Coll)))
        assertThat(feature("Number=Count"), `is`(sameInstance(Number.Count)))
        assertThat(feature("Number=Dual"), `is`(sameInstance(Number.Dual)))
        assertThat(feature("Number=Grpa"), `is`(sameInstance(Number.Grpa)))
        assertThat(feature("Number=Grpl"), `is`(sameInstance(Number.Grpl)))
        assertThat(feature("Number=Inv"), `is`(sameInstance(Number.Inv)))
        assertThat(feature("Number=Pauc"), `is`(sameInstance(Number.Pauc)))
        assertThat(feature("Number=Plur"), `is`(sameInstance(Number.Plur)))
        assertThat(feature("Number=Ptan"), `is`(sameInstance(Number.Ptan)))
        assertThat(feature("Number=Sing"), `is`(sameInstance(Number.Sing)))
        assertThat(feature("Number=Tri"), `is`(sameInstance(Number.Tri)))
        assertThat(feature("Number=Other"), `is`(UnknownFeatureValue("Number", "Other")))
    }
}
