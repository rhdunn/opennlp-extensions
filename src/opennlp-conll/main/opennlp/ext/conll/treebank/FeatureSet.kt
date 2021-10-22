// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

import opennlp.ext.conll.treebank.features.lexical.NumType
import opennlp.ext.conll.treebank.features.lexical.PronType
import opennlp.ext.conll.treebank.features.misc.SpaceAfter

interface FeatureSet {
    val type: String

    fun create(value: String): Feature

    companion object {
        operator fun get(type: String): FeatureSet? = when (type) {
            NumType.type -> NumType
            PronType.type -> PronType
            SpaceAfter.type -> SpaceAfter
            else -> null
        }
    }
}
