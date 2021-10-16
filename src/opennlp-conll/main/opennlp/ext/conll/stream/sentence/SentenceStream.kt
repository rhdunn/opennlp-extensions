// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.stream.sentence

import opennlp.ext.conll.stream.io.PlainTextLineStream
import opennlp.ext.conll.treebank.Sentence
import opennlp.tools.util.InputStreamFactory
import opennlp.tools.util.ObjectStream
import java.io.File

@Suppress("MemberVisibilityCanBePrivate")
object SentenceStream {
    const val CONLLX: String = "conllx"
    const val CONLLU: String = "conllu"

    class UnsupportedSentenceFormatException(format: String) :
        RuntimeException("Unsupported sentence treebank format: $format")

    fun create(stream: ObjectStream<String>, format: String): ObjectStream<Sentence> = when (format) {
        CONLLX -> ConllxSentenceStream(stream)
        CONLLU -> ConlluSentenceStream(stream)
        else -> throw UnsupportedSentenceFormatException(format)
    }

    fun create(stream: InputStreamFactory, format: String): ObjectStream<Sentence> {
        val lines = PlainTextLineStream.create(stream)
        return create(lines, format)
    }

    fun create(bytes: ByteArray, format: String): ObjectStream<Sentence> {
        val lines = PlainTextLineStream.create(bytes)
        return create(lines, format)
    }

    fun create(string: String, format: String): ObjectStream<Sentence> {
        val lines = PlainTextLineStream.create(string)
        return create(lines, format)
    }

    fun load(file: File): ObjectStream<Sentence> {
        val lines = PlainTextLineStream.load(file)
        return create(lines, file.extension)
    }

    fun load(path: String): ObjectStream<Sentence> {
        return load(File(path))
    }
}
