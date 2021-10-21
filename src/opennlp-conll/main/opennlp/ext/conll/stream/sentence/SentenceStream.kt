// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.stream.sentence

import opennlp.ext.conll.stream.io.PlainTextLineStream
import opennlp.ext.conll.treebank.Sentence
import opennlp.tools.util.InputStreamFactory
import opennlp.tools.util.ObjectStream
import java.io.File
import java.util.*

@Suppress("MemberVisibilityCanBePrivate")
object SentenceStream {
    const val CONLLX: String = "conllx"
    const val CONLLU: String = "conllu"

    class UnsupportedSentenceFormatException(format: String) :
        RuntimeException("Unsupported sentence treebank format: $format")

    fun create(
        stream: ObjectStream<String>,
        format: String,
        properties: Properties
    ): ObjectStream<Sentence> = when (format) {
        CONLLX -> ConllxSentenceStream(stream, properties)
        CONLLU -> ConlluSentenceStream(stream, properties)
        else -> throw UnsupportedSentenceFormatException(format)
    }

    fun create(stream: InputStreamFactory, format: String, properties: Properties): ObjectStream<Sentence> {
        val lines = PlainTextLineStream.create(stream)
        return create(lines, format, properties)
    }

    fun create(bytes: ByteArray, format: String, properties: Properties): ObjectStream<Sentence> {
        val lines = PlainTextLineStream.create(bytes)
        return create(lines, format, properties)
    }

    fun create(string: String, format: String, properties: Properties): ObjectStream<Sentence> {
        val lines = PlainTextLineStream.create(string)
        return create(lines, format, properties)
    }

    fun load(file: File, properties: Properties): ObjectStream<Sentence> {
        val lines = PlainTextLineStream.load(file)
        return create(lines, file.extension, properties)
    }

    fun load(path: String, properties: Properties): ObjectStream<Sentence> {
        return load(File(path), properties)
    }
}
