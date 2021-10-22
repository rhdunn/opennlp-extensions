// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.treebank.features

import opennlp.ext.conll.treebank.Feature
import opennlp.ext.conll.treebank.features.UnknownFeatureValue
import opennlp.ext.conll.treebank.features.lexical.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.sameInstance
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Universal Dependencies Lexical Feature Sets")
class LexicalFeatureSetsTest {
    private fun feature(value: String): Feature {
        val feats = Feature.parse(value)
        assertThat(feats.size, `is`(1))
        return feats.first()
    }

    @Test
    @DisplayName("PronType")
    fun pronType() {
        assertThat(feature("PronType=Art"), `is`(sameInstance(PronType.Art)))
        assertThat(feature("PronType=Dem"), `is`(sameInstance(PronType.Dem)))
        assertThat(feature("PronType=Emp"), `is`(sameInstance(PronType.Emp)))
        assertThat(feature("PronType=Exc"), `is`(sameInstance(PronType.Exc)))
        assertThat(feature("PronType=Ind"), `is`(sameInstance(PronType.Ind)))
        assertThat(feature("PronType=Int"), `is`(sameInstance(PronType.Int)))
        assertThat(feature("PronType=Neg"), `is`(sameInstance(PronType.Neg)))
        assertThat(feature("PronType=Prs"), `is`(sameInstance(PronType.Prs)))
        assertThat(feature("PronType=Rcp"), `is`(sameInstance(PronType.Rcp)))
        assertThat(feature("PronType=Rel"), `is`(sameInstance(PronType.Rel)))
        assertThat(feature("PronType=Tot"), `is`(sameInstance(PronType.Tot)))
        assertThat(feature("PronType=Other"), `is`(UnknownFeatureValue("PronType", "Other")))
    }

    @Test
    @DisplayName("NumType")
    fun numType() {
        assertThat(feature("NumType=Card"), `is`(sameInstance(NumType.Card)))
        assertThat(feature("NumType=Dist"), `is`(sameInstance(NumType.Dist)))
        assertThat(feature("NumType=Frac"), `is`(sameInstance(NumType.Frac)))
        assertThat(feature("NumType=Mult"), `is`(sameInstance(NumType.Mult)))
        assertThat(feature("NumType=Ord"), `is`(sameInstance(NumType.Ord)))
        assertThat(feature("NumType=Range"), `is`(sameInstance(NumType.Range)))
        assertThat(feature("NumType=Sets"), `is`(sameInstance(NumType.Sets)))
        assertThat(feature("NumType=Other"), `is`(UnknownFeatureValue("NumType", "Other")))
    }

    @Test
    @DisplayName("Poss")
    fun poss() {
        assertThat(feature("Poss=Yes"), `is`(sameInstance(Poss.Yes)))
        assertThat(feature("Poss=No"), `is`(UnknownFeatureValue("Poss", "No")))
    }

    @Test
    @DisplayName("Reflex")
    fun reflex() {
        assertThat(feature("Reflex=Yes"), `is`(sameInstance(Reflex.Yes)))
        assertThat(feature("Reflex=No"), `is`(UnknownFeatureValue("Reflex", "No")))
    }

    @Test
    @DisplayName("Foreign")
    fun foreign() {
        assertThat(feature("Foreign=Yes"), `is`(sameInstance(Foreign.Yes)))
        assertThat(feature("Foreign=No"), `is`(UnknownFeatureValue("Foreign", "No")))
    }

    @Test
    @DisplayName("Abbr")
    fun abbr() {
        assertThat(feature("Abbr=Yes"), `is`(sameInstance(Abbr.Yes)))
        assertThat(feature("Abbr=No"), `is`(UnknownFeatureValue("Abbr", "No")))
    }

    @Test
    @DisplayName("Typo")
    fun typo() {
        assertThat(feature("Typo=Yes"), `is`(sameInstance(Typo.Yes)))
        assertThat(feature("Typo=No"), `is`(UnknownFeatureValue("Typo", "No")))
    }
}
