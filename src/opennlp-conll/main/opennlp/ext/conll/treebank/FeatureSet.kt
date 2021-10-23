// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

import opennlp.ext.conll.treebank.features.lexical.*
import opennlp.ext.conll.treebank.features.misc.SpaceAfter
import opennlp.ext.conll.treebank.features.nominal.*
import opennlp.ext.conll.treebank.features.nominal.Number
import opennlp.ext.conll.treebank.features.verbal.*

interface FeatureSet {
    val type: String

    fun create(value: String): Feature

    companion object {
        operator fun get(type: String): FeatureSet? = when (type) {
            Abbr.type -> Abbr
            Animacy.type -> Animacy
            Aspect.type -> Aspect
            Case.type -> Case
            Definite.type -> Definite
            Degree.type -> Degree
            Evident.type -> Evident
            Foreign.type -> Foreign
            Gender.type -> Gender
            Mood.type -> Mood
            NounClass.type -> NounClass
            Number.type -> Number
            NumType.type -> NumType
            Person.type -> Person
            Polarity.type -> Polarity
            Poss.type -> Poss
            PronType.type -> PronType
            Reflex.type -> Reflex
            SpaceAfter.type -> SpaceAfter
            Tense.type -> Tense
            Typo.type -> Typo
            VerbForm.type -> VerbForm
            Voice.type -> Voice
            else -> null
        }
    }
}
