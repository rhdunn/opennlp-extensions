// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.stream.sample

import opennlp.ext.conll.stream.properties.MultiTokenWords
import opennlp.ext.conll.stream.properties.multiTokenWords
import opennlp.ext.conll.stream.properties.posTagset
import opennlp.ext.conll.stream.sentence.SentenceStream
import opennlp.ext.conll.treebank.POSTagset
import opennlp.ext.conll.treebank.Sentence
import opennlp.ext.conll.treebank.TokenId
import opennlp.tools.postag.POSSample
import opennlp.tools.util.FilterObjectStream
import opennlp.tools.util.InputStreamFactory
import opennlp.tools.util.ObjectStream
import java.io.File
import java.util.*

class POSSampleStream(
    stream: ObjectStream<Sentence>,
    private val posTagset: POSTagset,
    private val multiTokenWords: MultiTokenWords
) : FilterObjectStream<Sentence, POSSample>(stream) {
    constructor(stream: ObjectStream<Sentence>, properties: Properties) : this(
        stream,
        posTagset(properties),
        multiTokenWords(properties)
    )

    @Suppress("ReplaceNotNullAssertionWithElvisReturn")
    override fun read(): POSSample? {
        val sentence = samples.read() ?: return null

        val tokens = mutableListOf<String>()
        val tags = mutableListOf<String>()
        var currentIndex = 0

        var mwToken: String? = null
        var mwTag: String? = null
        sentence.wordLines.forEach { wordLine ->
            when (wordLine.id.type) {
                TokenId.Type.EmptyNodeCounter -> when {
                    wordLine["Typo"] == "Yes" && wordLine["CorrectForm"] != null -> {
                        // Keep tokens that are correcting typos using the corrected form.
                        val pos = wordLine.postag(posTagset) ?: return@forEach
                        tokens.add(wordLine["CorrectForm"]!!)
                        tags.add(pos.tag)
                    }
                    else -> {
                        // Ignore nodes that are inserted as copies of other nodes.
                    }
                }
                TokenId.Type.Range -> currentIndex = when (multiTokenWords) {
                    MultiTokenWords.Split -> {
                        // Ignore token ranges when splitting multi-token words and spaces between the tokens.
                        wordLine.id.endInclusive
                    }
                    MultiTokenWords.Join -> {
                        mwToken = wordLine.form
                        wordLine.id.endInclusive
                    }
                }
                TokenId.Type.Counter -> when {
                    multiTokenWords == MultiTokenWords.Join && wordLine.id.start <= currentIndex -> {
                        // Combine part of speech tags when joining multi-token words.
                        val pos = wordLine.postag(posTagset) ?: return@forEach
                        mwTag = mwTag?.let { "$mwTag+${pos.tag}" } ?: pos.tag
                    }
                    else -> {
                        if (mwToken != null) {
                            tokens.add(mwToken!!)
                            tags.add(mwTag!!)

                            mwToken = null
                            mwTag = null
                        }

                        val pos = wordLine.postag(posTagset) ?: return@forEach
                        tokens.add(wordLine.form)
                        tags.add(pos.tag)
                        currentIndex = wordLine.id.endInclusive
                    }
                }
            }
        }
        return POSSample(tokens, tags)
    }

    companion object {
        fun create(stream: ObjectStream<String>, format: String, properties: Properties): ObjectStream<POSSample> {
            val sentences = SentenceStream.create(stream, format, properties)
            return POSSampleStream(sentences, properties)
        }

        fun create(stream: InputStreamFactory, format: String, properties: Properties): ObjectStream<POSSample> {
            val sentences = SentenceStream.create(stream, format, properties)
            return POSSampleStream(sentences, properties)
        }

        fun create(bytes: ByteArray, format: String, properties: Properties): ObjectStream<POSSample> {
            val sentences = SentenceStream.create(bytes, format, properties)
            return POSSampleStream(sentences, properties)
        }

        fun create(string: String, format: String, properties: Properties): ObjectStream<POSSample> {
            val sentences = SentenceStream.create(string, format, properties)
            return POSSampleStream(sentences, properties)
        }

        fun load(file: File, properties: Properties): ObjectStream<POSSample> {
            val sentences = SentenceStream.load(file, properties)
            return POSSampleStream(sentences, properties)
        }

        fun load(path: String, properties: Properties): ObjectStream<POSSample> {
            val sentences = SentenceStream.load(path, properties)
            return POSSampleStream(sentences, properties)
        }
    }
}
