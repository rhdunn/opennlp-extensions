// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

import opennlp.ext.conll.treebank.pos.PosTag

// Reference: [CoNLL-X Format](https://ilk.uvt.nl/~emarsi/download/pubs/14964.pdf)
// Reference: [CoNLL-U Format](https://universaldependencies.org/format.html)
data class WordLine(
    val id: TokenId,
    val form: String,
    val lemma: String,
    val upos: PosTag?,
    val xpos: PosTag?,
    val feats: List<Feature>,
    val dep: DependencyRelation?,
    val deps: List<DependencyRelation>,
    val misc: List<Feature>
) {
    operator fun get(name: String?): String? = when (name) {
        null -> null
        else -> feats.find { it.type == name }?.value ?: misc.find { it.type == name }?.value
    }

    fun hasFeature(feature: Feature): Boolean = this[feature.type] == feature.value

    fun postag(tagset: POSTagset): PosTag? = when (tagset) {
        POSTagset.Universal -> upos
        POSTagset.LanguageSpecific -> xpos
    }

    class InvalidWorldLineException(line: String) : RuntimeException("Invalid word line: $line")
}
