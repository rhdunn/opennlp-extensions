// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank.features

import opennlp.ext.conll.treebank.Feature

// Reference: [CoNLL-X Format](https://ilk.uvt.nl/~emarsi/download/pubs/14964.pdf)
// Reference: [CoNLL-U Format](https://universaldependencies.org/format.html)
data class UnknownFeatureValue(
    override val type: String?,
    override val value: String
) : Feature {
    override fun toString(): String = when (type) {
        null -> value
        else -> "$type=$value"
    }
}
