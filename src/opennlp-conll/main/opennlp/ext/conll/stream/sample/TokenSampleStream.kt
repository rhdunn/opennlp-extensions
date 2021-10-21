// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.stream.sample

import opennlp.ext.conll.stream.properties.MultiTokenWords
import opennlp.ext.conll.stream.properties.multiTokenWords
import opennlp.ext.conll.stream.sentence.SentenceStream
import opennlp.ext.conll.treebank.Sentence
import opennlp.ext.conll.treebank.TokenId
import opennlp.tools.tokenize.TokenSample
import opennlp.tools.util.FilterObjectStream
import opennlp.tools.util.InputStreamFactory
import opennlp.tools.util.ObjectStream
import opennlp.tools.util.Span
import java.io.File
import java.util.*

class TokenSampleStream(
    stream: ObjectStream<Sentence>,
    private val multiTokenWords: MultiTokenWords
) : FilterObjectStream<Sentence, TokenSample>(stream) {
    constructor(stream: ObjectStream<Sentence>, properties: Properties) : this(
        stream,
        multiTokenWords(properties)
    )

    override fun read(): TokenSample? {
        val sentence = samples.read() ?: return null

        var needSpace = false
        var currentIndex = 0

        val text = StringBuilder()
        val spans = mutableListOf<Span>()
        sentence.wordLines.forEach { wordLine ->
            when (wordLine.id.type) {
                TokenId.Type.EmptyNodeCounter -> {
                    // Ignore empty node word lines.
                }
                TokenId.Type.Range -> when (multiTokenWords) {
                    MultiTokenWords.Split -> {
                        // Ignore token ranges when splitting multi-token words and spaces between the tokens.
                        currentIndex = wordLine.id.endInclusive
                    }
                    MultiTokenWords.Join -> {
                        if (needSpace) text.append(' ')

                        val spanStart = text.length
                        text.append(wordLine.form)
                        spans.add(Span(spanStart, text.length))

                        needSpace = wordLine.spaceAfter
                        currentIndex = wordLine.id.endInclusive
                    }
                }
                TokenId.Type.Counter -> when {
                    multiTokenWords == MultiTokenWords.Join && wordLine.id.start <= currentIndex -> {
                        // Ignore token counters when not splitting multi-token words.
                    }
                    else -> {
                        if (needSpace) text.append(' ')

                        val spanStart = text.length
                        text.append(wordLine.form)
                        spans.add(Span(spanStart, text.length))

                        needSpace = wordLine.spaceAfter && wordLine.id.start > currentIndex
                        currentIndex = wordLine.id.endInclusive
                    }
                }
            }
        }
        return TokenSample(text.toString(), spans.toTypedArray())
    }

    companion object {
        fun create(stream: ObjectStream<String>, format: String, properties: Properties): ObjectStream<TokenSample> {
            val sentences = SentenceStream.create(stream, format, properties)
            return TokenSampleStream(sentences, properties)
        }

        fun create(stream: InputStreamFactory, format: String, properties: Properties): ObjectStream<TokenSample> {
            val sentences = SentenceStream.create(stream, format, properties)
            return TokenSampleStream(sentences, properties)
        }

        fun create(bytes: ByteArray, format: String, properties: Properties): ObjectStream<TokenSample> {
            val sentences = SentenceStream.create(bytes, format, properties)
            return TokenSampleStream(sentences, properties)
        }

        fun create(string: String, format: String, properties: Properties): ObjectStream<TokenSample> {
            val sentences = SentenceStream.create(string, format, properties)
            return TokenSampleStream(sentences, properties)
        }

        fun load(file: File, properties: Properties): ObjectStream<TokenSample> {
            val sentences = SentenceStream.load(file, properties)
            return TokenSampleStream(sentences, properties)
        }

        fun load(path: String, properties: Properties): ObjectStream<TokenSample> {
            val sentences = SentenceStream.load(path, properties)
            return TokenSampleStream(sentences, properties)
        }
    }
}
