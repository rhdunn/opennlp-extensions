// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

// Reference: [CoNLL-X Format](https://ilk.uvt.nl/~emarsi/download/pubs/14964.pdf)
// Reference: [CoNLL-U Format](https://universaldependencies.org/format.html)
enum class POSTagset(val conllxField: String, val conlluField: String) {
    Universal("CPOSTAG", "UPOS"),
    LanguageSpecific("POSTAG", "XPOS"),
    UniversalAndPTB("CPOSTAG-PTB", "UPOS-PTB")
}
