// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

import opennlp.ext.conll.treebank.features.lexical.*
import opennlp.ext.conll.treebank.features.misc.SpaceAfter
import opennlp.ext.conll.treebank.features.nominal.*
import opennlp.ext.conll.treebank.features.nominal.Number
import opennlp.ext.conll.treebank.features.verbal.Mood
import opennlp.ext.conll.treebank.features.verbal.Tense
import opennlp.ext.conll.treebank.features.verbal.VerbForm

interface FeatureSet {
    val type: String

    fun create(value: String): Feature

    companion object {
        operator fun get(type: String): FeatureSet? = when (type) {
            Abbr.type -> Abbr
            Animacy.type -> Animacy
            Case.type -> Case
            Definite.type -> Definite
            Degree.type -> Degree
            Foreign.type -> Foreign
            Gender.type -> Gender
            Mood.type -> Mood
            NounClass.type -> NounClass
            Number.type -> Number
            NumType.type -> NumType
            Poss.type -> Poss
            PronType.type -> PronType
            Reflex.type -> Reflex
            SpaceAfter.type -> SpaceAfter
            Tense.type -> Tense
            Typo.type -> Typo
            VerbForm.type -> VerbForm
            else -> null
        }
    }
}
