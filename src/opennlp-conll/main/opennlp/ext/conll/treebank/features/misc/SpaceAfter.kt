// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank.features.misc

import opennlp.ext.conll.treebank.Feature

// Reference: [CoNLL-U Format](https://universaldependencies.org/format.html)
enum class SpaceAfter : Feature {
    No;

    override val type: String = "SpaceAfter"
    override val value: String = name

    override fun toString(): String = "$type=$value"
}
