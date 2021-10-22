// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

import opennlp.ext.conll.treebank.features.lexical.*
import opennlp.ext.conll.treebank.features.misc.SpaceAfter

interface FeatureSet {
    val type: String

    fun create(value: String): Feature

    companion object {
        operator fun get(type: String): FeatureSet? = when (type) {
            Foreign.type -> Foreign
            NumType.type -> NumType
            Poss.type -> Poss
            PronType.type -> PronType
            Reflex.type -> Reflex
            SpaceAfter.type -> SpaceAfter
            else -> null
        }
    }
}
