// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank.features.verbal

import opennlp.ext.conll.treebank.Feature
import opennlp.ext.conll.treebank.FeatureSet
import opennlp.ext.conll.treebank.features.UnknownFeatureValue

// Reference: [Polite](https://universaldependencies.org/u/feat/Polite.html)
enum class Polite : Feature {
    Elev, Form, Humb, Infm;

    override val type: String = "Polite"
    override val value: String = name

    override fun toString(): String = "$type=$value"

    companion object : FeatureSet {
        override val type: String = "Polite"

        override fun create(value: String): Feature {
            return values().find { it.value == value } ?: UnknownFeatureValue(type, value)
        }
    }
}
