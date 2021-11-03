// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

import opennlp.tools.util.Span

// Reference: [CoNLL-X Format](https://ilk.uvt.nl/~emarsi/download/pubs/14964.pdf)
// Reference: [CoNLL-U Format](https://universaldependencies.org/format.html)
data class Sentence(
    val lines: Span,
    val comments: List<Comment>,
    val wordLines: List<WordLine>
) {
    operator fun get(name: String): String? = comments.find { it.name == name }?.value

    val sentenceId: String?
        get() = this[SENT_ID]

    val text: String?
        get() = this[TEXT] ?: this[SENTENCE_TEXT]

    companion object {
        const val SENT_ID: String = "sent_id"
        const val SENTENCE_TEXT: String = "sentence-text"
        const val TEXT: String = "text"
    }
}
