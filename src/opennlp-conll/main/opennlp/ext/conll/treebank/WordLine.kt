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
    operator fun get(name: String): String? {
        return feats.find { it.name == name }?.value ?: misc.find { it.name == name }?.value
    }

    fun postag(tagset: POSTagset): PosTag? = when (tagset) {
        POSTagset.Universal -> upos
        POSTagset.LanguageSpecific -> xpos
    }

    val spaceAfter: Boolean
        get() = this[SPACE_AFTER] != NO

    class InvalidWorldLineException(line: String) : RuntimeException("Invalid word line: $line")

    companion object {
        const val SPACE_AFTER: String = "SpaceAfter"
        const val NO: String = "No"
    }
}
