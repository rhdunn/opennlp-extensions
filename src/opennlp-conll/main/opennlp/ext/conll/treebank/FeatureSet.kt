// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

import opennlp.ext.conll.treebank.features.lexical.*
import opennlp.ext.conll.treebank.features.misc.SpaceAfter
import opennlp.ext.conll.treebank.features.nominal.Gender

interface FeatureSet {
    val type: String

    fun create(value: String): Feature

    companion object {
        operator fun get(type: String): FeatureSet? = when (type) {
            Abbr.type -> Abbr
            Foreign.type -> Foreign
            Gender.type -> Gender
            NumType.type -> NumType
            Poss.type -> Poss
            PronType.type -> PronType
            Reflex.type -> Reflex
            SpaceAfter.type -> SpaceAfter
            Typo.type -> Typo
            else -> null
        }
    }
}
