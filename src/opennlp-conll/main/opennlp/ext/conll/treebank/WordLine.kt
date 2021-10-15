// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

// Reference: [CoNLL-X Format](https://ilk.uvt.nl/~emarsi/download/pubs/14964.pdf)
// Reference: [CoNLL-U Format](https://universaldependencies.org/format.html)
data class WordLine(
    val id: TokenId,
    val form: String,
    val lemma: String,
    val upos: String?,
    val xpos: String?,
    val feats: Map<String, String>,
    val dep: DependencyRelation?,
    val deps: List<DependencyRelation>,
    val misc: List<String>
)
