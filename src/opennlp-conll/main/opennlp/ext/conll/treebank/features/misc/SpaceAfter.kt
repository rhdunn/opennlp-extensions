// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank.features.misc

import opennlp.ext.conll.treebank.Feature
import opennlp.ext.conll.treebank.features.UnknownFeatureValue

// Reference: [CoNLL-U Format](https://universaldependencies.org/format.html)
enum class SpaceAfter : Feature {
    No;

    override val type: String = "SpaceAfter"
    override val value: String = name

    override fun toString(): String = "$type=$value"

    companion object {
        fun create(value: String): Feature {
            return values().find { it.value == value } ?: UnknownFeatureValue("SpaceAfter", value)
        }
    }
}
