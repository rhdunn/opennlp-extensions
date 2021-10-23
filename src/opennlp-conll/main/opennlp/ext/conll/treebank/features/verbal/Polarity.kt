// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank.features.verbal

import opennlp.ext.conll.treebank.Feature
import opennlp.ext.conll.treebank.FeatureSet
import opennlp.ext.conll.treebank.features.UnknownFeatureValue

// Reference: [Polarity](https://universaldependencies.org/u/feat/Polarity.html)
enum class Polarity : Feature {
    Neg, Pos;

    override val type: String = "Polarity"
    override val value: String = name

    override fun toString(): String = "$type=$value"

    companion object : FeatureSet {
        override val type: String = "Polarity"

        override fun create(value: String): Feature {
            return values().find { it.value == value } ?: UnknownFeatureValue(type, value)
        }
    }
}
