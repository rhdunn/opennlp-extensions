// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.pos

import opennlp.ext.sentence.Sentence
import opennlp.tools.postag.POSTagger
import opennlp.tools.util.Sequence

fun Sentence.tag(tagger: POSTagger, context: Array<Any>? = null): Array<String> {
    return tagger.tag(tokens, context)
}

inline fun <reified T> Sentence.tag(tagger: POSTagger, context: Array<Any>?, transform: (String) -> T): Array<T> {
    return tagger.tag(tokens, context).map(transform).toTypedArray()
}

inline fun <reified T> Sentence.tag(tagger: POSTagger, transform: (String) -> T): Array<T> {
    return tag(tagger, null, transform)
}

fun Sentence.tag(tagger: POSTagger, context: Array<Any>?, tagset: PosTagset): Array<PosTag> {
    return tag(tagger, context) { tagset[it] }
}

fun Sentence.tag(tagger: POSTagger, tagset: PosTagset): Array<PosTag> {
    return tag(tagger, null) { tagset[it] }
}

fun Sentence.topKSequences(tagger: POSTagger, context: Array<Any>? = null): Array<Sequence> {
    return tagger.topKSequences(tokens, context)
}
