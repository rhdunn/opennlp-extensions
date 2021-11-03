// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.stream.sample

import opennlp.ext.conll.stream.properties.sentencesPerSample
import opennlp.ext.conll.stream.sentence.SentenceStream
import opennlp.ext.conll.treebank.Sentence
import opennlp.ext.conll.treebank.features.misc.SpaceAfter
import opennlp.tools.sentdetect.SentenceSample
import opennlp.tools.util.FilterObjectStream
import opennlp.tools.util.InputStreamFactory
import opennlp.tools.util.ObjectStream
import opennlp.tools.util.Span
import java.io.File
import java.util.*

class SentenceSampleStream(
    stream: ObjectStream<Sentence>,
    private val sentencesPerSample: Int
) : FilterObjectStream<Sentence, SentenceSample>(stream) {
    constructor(stream: ObjectStream<Sentence>, properties: Properties) : this(
        stream,
        sentencesPerSample(properties)
    )

    override fun read(): SentenceSample? {
        val text = StringBuilder()
        val spans = mutableListOf<Span>()
        var spaceAfter = true

        (1..sentencesPerSample).forEach { i ->
            val sentence = samples.read() ?: return@forEach
            if (i != 1 && spaceAfter) {
                text.append(' ')
            }

            val spanStart = text.length
            text.append(sentence.text ?: "")
            spans.add(Span(spanStart, text.length))

            val token = sentence.wordLines.lastOrNull()
            spaceAfter = token == null || !token.hasFeature(SpaceAfter.No)
        }

        return when (text.length) {
            0 -> null
            else -> SentenceSample(text.toString(), *spans.toTypedArray())
        }
    }

    companion object {
        fun create(stream: ObjectStream<String>, format: String, properties: Properties): ObjectStream<SentenceSample> {
            val sentences = SentenceStream.create(stream, format, properties)
            return SentenceSampleStream(sentences, properties)
        }

        fun create(stream: InputStreamFactory, format: String, properties: Properties): ObjectStream<SentenceSample> {
            val sentences = SentenceStream.create(stream, format, properties)
            return SentenceSampleStream(sentences, properties)
        }

        fun create(bytes: ByteArray, format: String, properties: Properties): ObjectStream<SentenceSample> {
            val sentences = SentenceStream.create(bytes, format, properties)
            return SentenceSampleStream(sentences, properties)
        }

        fun create(string: String, format: String, properties: Properties): ObjectStream<SentenceSample> {
            val sentences = SentenceStream.create(string, format, properties)
            return SentenceSampleStream(sentences, properties)
        }

        fun load(file: File, properties: Properties): ObjectStream<SentenceSample> {
            val sentences = SentenceStream.load(file, properties)
            return SentenceSampleStream(sentences, properties)
        }

        fun load(path: String, properties: Properties): ObjectStream<SentenceSample> {
            val sentences = SentenceStream.load(path, properties)
            return SentenceSampleStream(sentences, properties)
        }
    }
}
