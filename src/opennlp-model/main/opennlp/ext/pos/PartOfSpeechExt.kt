// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.pos

import opennlp.ext.sentence.Sentence
import opennlp.tools.postag.POSTagger
import opennlp.tools.util.Sequence

fun Sentence.tag(tagger: POSTagger, context: Array<Any>? = null): Array<String> {
    return tagger.tag(tokens, context)
}

fun Sentence.tag(tagger: POSTagger, tagset: PosTagset, context: Array<Any>? = null): Array<PosTag> {
    return tagger.tag(tokens, context).map { tagset[it] }.toTypedArray()
}

fun Sentence.topKSequences(tagger: POSTagger, context: Array<Any>? = null): Array<Sequence> {
    return tagger.topKSequences(tokens, context)
}
