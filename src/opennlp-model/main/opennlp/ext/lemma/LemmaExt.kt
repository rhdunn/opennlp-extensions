// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.lemma

import opennlp.ext.sentence.Sentence
import opennlp.tools.lemmatizer.Lemmatizer
import opennlp.tools.lemmatizer.LemmatizerME

fun Sentence.lemmatize(lemmatizer: Lemmatizer, tags: Array<String>): Array<String> {
    return lemmatizer.lemmatize(tokens, tags)
}

fun Sentence.topKLemmas(lemmatizer: Lemmatizer, tags: Array<String>, numLemmas: Int): Array<Array<String>> {
    return when (lemmatizer) {
        is LemmatizerME -> lemmatizer.predictLemmas(numLemmas, tokens, tags)
        else -> lemmatizer.lemmatize(tokens.toList(), tags.toList()).map { it.toTypedArray() }.toTypedArray()
    }
}
