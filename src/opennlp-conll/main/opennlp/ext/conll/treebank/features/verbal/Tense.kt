// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank.features.verbal

import opennlp.ext.conll.treebank.Feature
import opennlp.ext.conll.treebank.FeatureSet
import opennlp.ext.conll.treebank.features.UnknownFeatureValue

// Reference: [Tense](https://universaldependencies.org/u/feat/Tense.html)
enum class Tense : Feature {
    Fut, Imp, Past, Pqp, Pres;

    override val type: String = "Tense"
    override val value: String = name

    override fun toString(): String = "$type=$value"

    companion object : FeatureSet {
        override val type: String = "Tense"

        override fun create(value: String): Feature {
            return values().find { it.value == value } ?: UnknownFeatureValue(type, value)
        }
    }
}
