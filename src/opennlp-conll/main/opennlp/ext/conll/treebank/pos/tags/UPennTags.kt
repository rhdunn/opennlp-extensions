// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank.pos.tags

import opennlp.ext.conll.treebank.pos.PosTag
import opennlp.ext.conll.treebank.pos.PosTagset
import opennlp.ext.conll.treebank.pos.WordClass

// Reference: [Penn Treebank P.O.S. Tags](https://www.ling.upenn.edu/courses/Fall_2003/ling001/penn_treebank_pos.html)
// Reference: [The Penn Treebank POS Tagset](https://ufal.mff.cuni.cz/pdt/Morphology_and_Tagging/Doc/PTTags.pdf)
enum class UPennTags(
    override val tag: String,
    override val label: String,
    override val wordClass: WordClass
) : PosTag {
    CC("CC", "coordinating conjunction", WordClass.Closed),
    CD("CD", "cardinal number", WordClass.Closed),
    DT("DT", "determiner", WordClass.Closed),
    EX("EX", "existential there", WordClass.Closed),
    FW("FW", "foreign word", WordClass.Other),
    IN("IN", "preposition or subordinating conjunction", WordClass.Closed),
    JJ("JJ", "adjective", WordClass.Open),
    JJR("JJR", "comparative adjective", WordClass.Open),
    JJS("JJS", "superlative adjective", WordClass.Open),
    LS("LS", "list item marker", WordClass.Closed),
    MD("MD", "modal", WordClass.Closed),
    NN("NN", "singular or mass noun", WordClass.Open),
    NNS("NNS", "plural noun", WordClass.Open),
    NNP("NNP", "singular proper noun", WordClass.Open),
    NNPS("NNPS", "plural proper noun", WordClass.Open),
    PDT("PDT", "predeterminer", WordClass.Closed),
    POS("POS", "possessive ending", WordClass.Closed),
    PRP("PRP", "personal pronoun", WordClass.Closed),
    PRPS("PRP$", "possessive pronoun", WordClass.Closed),
    RB("RB", "adverb", WordClass.Open),
    RBR("RBR", "comparative adverb", WordClass.Open),
    RBS("RBS", "superlative adverb", WordClass.Open),
    RP("RP", "particle", WordClass.Closed),
    SYM("SYM", "symbol", WordClass.Other),
    TO("TO", "to", WordClass.Closed),
    UH("UH", "interjection", WordClass.Open),
    VB("VB", "verb base form", WordClass.Open),
    VBD("VBD", "past tense verb", WordClass.Open),
    VBG("VBG", "gerund or present participle verb", WordClass.Open),
    VBN("VBN", "past participle verb", WordClass.Open),
    VBP("VBP", "non-third person singular present verb", WordClass.Open),
    VBZ("VBZ", "third person singular present verb", WordClass.Open),
    WDT("WDT", "wh-determiner", WordClass.Closed),
    WP("WP", "wh-pronoun", WordClass.Closed),
    WPS("WP$", "possessive wh-pronoun", WordClass.Closed),
    WRB("WRB", "wh-adverb", WordClass.Open),
    POUND("#", "pound symbol", WordClass.Other),
    DOLLAR("$", "dollar symbol", WordClass.Other),
    SENT(".", "sentence final punctuation", WordClass.Other),
    COMMA(",", "comma", WordClass.Other),
    COLON(":", "other punctuation", WordClass.Other),
    PARO("(", "open parenthesis", WordClass.Other),
    PARC(")", "close parenthesis", WordClass.Other),
    DQUOO("``", "open double quotes", WordClass.Other),
    DQUOC("''", "close double quotes", WordClass.Other),
    SQUOO("`", "open single quotes", WordClass.Other),
    SQUOC("'", "close single quotes", WordClass.Other);

    companion object : PosTagset {
        override fun get(tag: String): PosTag? = when (tag) {
            "_" -> null
            else -> values().find { it.tag == tag } ?: PosTag.Unknown(tag)
        }
    }
}