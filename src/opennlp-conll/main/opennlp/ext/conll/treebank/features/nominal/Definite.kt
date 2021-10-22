// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank.features.nominal

import opennlp.ext.conll.treebank.Feature
import opennlp.ext.conll.treebank.FeatureSet
import opennlp.ext.conll.treebank.features.UnknownFeatureValue

// Reference: [Definite](https://universaldependencies.org/u/feat/Definite.html)
enum class Definite : Feature {
    Com, Cons, Def, Ind, Spec;

    override val type: String = "Definite"
    override val value: String = name

    override fun toString(): String = "$type=$value"

    companion object : FeatureSet {
        override val type: String = "Definite"

        override fun create(value: String): Feature {
            return values().find { it.value == value } ?: UnknownFeatureValue(type, value)
        }
    }
}
