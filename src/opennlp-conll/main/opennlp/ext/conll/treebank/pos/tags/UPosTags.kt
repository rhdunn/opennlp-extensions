// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank.pos.tags

import opennlp.ext.conll.treebank.pos.WordClass

// Reference: [Universal POS tags](https://universaldependencies.org/u/pos/index.html)
enum class UPosTags(val tag: String, val label: String, val wordClass: WordClass) {
    ADJ("ADJ", "adjective", WordClass.Open),
    ADP("ADP", "adposition", WordClass.Closed),
    ADV("ADV", "adverb", WordClass.Open),
    AUX("AUX", "auxiliary", WordClass.Closed),
    CCONJ("CCONJ", "coordinating conjunction", WordClass.Closed),
    DET("DET", "determiner", WordClass.Closed),
    INTJ("INTJ", "interjection", WordClass.Open),
    NOUN("NOUN", "noun", WordClass.Open),
    NUM("NUM", "numeral", WordClass.Closed),
    PART("PART", "particle", WordClass.Closed),
    PRON("PRON", "pronoun", WordClass.Closed),
    PROPN("PROPN", "proper noun", WordClass.Open),
    PUNCT("PUNCT", "punctuation", WordClass.Other),
    SCONJ("SCONJ", "subordinating conjunction", WordClass.Closed),
    SYM("SYM", "symbol", WordClass.Other),
    VERB("VERB", "verb", WordClass.Open),
    X("X", "other", WordClass.Other)
}
