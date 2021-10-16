// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.stream.sentence

import opennlp.ext.conll.treebank.Sentence
import opennlp.tools.util.ObjectStream

object SentenceStream {
    const val CONLLX = "conllx"
    const val CONLLU = "conllu"

    class UnsupportedSentenceFormatException(format: String) :
        RuntimeException("Unsupported sentence treebank format: $format")

    fun create(stream: ObjectStream<String>, format: String): ObjectStream<Sentence> = when (format) {
        CONLLX -> ConllxSentenceStream(stream)
        CONLLU -> ConlluSentenceStream(stream)
        else -> throw UnsupportedSentenceFormatException(format)
    }
}
