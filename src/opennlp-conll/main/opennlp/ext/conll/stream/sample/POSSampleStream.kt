// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.stream.sample

import opennlp.ext.conll.stream.sentence.SentenceStream
import opennlp.ext.conll.treebank.Sentence
import opennlp.tools.postag.POSSample
import opennlp.tools.util.FilterObjectStream
import opennlp.tools.util.InputStreamFactory
import opennlp.tools.util.ObjectStream
import java.io.File
import java.util.*

class POSSampleStream(stream: ObjectStream<Sentence>) : FilterObjectStream<Sentence, POSSample>(stream) {
    constructor(stream: ObjectStream<Sentence>, properties: Properties) : this(stream)

    override fun read(): POSSample? {
        val sentence = samples.read() ?: return null

        val tokens = mutableListOf<String>()
        val tags = mutableListOf<String>()
        sentence.wordLines.forEach { wordLine ->
            val pos = wordLine.upos ?: return@forEach

            tokens.add(wordLine.form)
            tags.add(pos)
        }
        return POSSample(tokens, tags)
    }

    companion object {
        fun create(stream: ObjectStream<String>, format: String, properties: Properties): ObjectStream<POSSample> {
            val sentences = SentenceStream.create(stream, format)
            return POSSampleStream(sentences, properties)
        }

        fun create(stream: InputStreamFactory, format: String, properties: Properties): ObjectStream<POSSample> {
            val sentences = SentenceStream.create(stream, format)
            return POSSampleStream(sentences, properties)
        }

        fun create(bytes: ByteArray, format: String, properties: Properties): ObjectStream<POSSample> {
            val sentences = SentenceStream.create(bytes, format)
            return POSSampleStream(sentences, properties)
        }

        fun create(string: String, format: String, properties: Properties): ObjectStream<POSSample> {
            val sentences = SentenceStream.create(string, format)
            return POSSampleStream(sentences, properties)
        }

        fun load(file: File, properties: Properties): ObjectStream<POSSample> {
            val sentences = SentenceStream.load(file)
            return POSSampleStream(sentences, properties)
        }

        fun load(path: String, properties: Properties): ObjectStream<POSSample> {
            val sentences = SentenceStream.load(path)
            return POSSampleStream(sentences, properties)
        }
    }
}
