// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.stream.sample

import opennlp.ext.conll.stream.sentence.SentenceStream
import opennlp.ext.conll.treebank.Sentence
import opennlp.tools.tokenize.TokenSample
import opennlp.tools.util.FilterObjectStream
import opennlp.tools.util.InputStreamFactory
import opennlp.tools.util.ObjectStream
import opennlp.tools.util.Span
import java.io.File

class TokenSampleStream(stream: ObjectStream<Sentence>) : FilterObjectStream<Sentence, TokenSample>(stream) {
    override fun read(): TokenSample? {
        val sentence = samples.read() ?: return null

        var needSpace = false
        val text = StringBuilder()
        val spans = mutableListOf<Span>()
        sentence.wordLines.forEach { wordLine ->
            if (needSpace) text.append(' ')

            val spanStart = text.length
            text.append(wordLine.form)
            spans.add(Span(spanStart, text.length))

            needSpace = wordLine.spaceAfter
        }
        return TokenSample(text.toString(), spans.toTypedArray())
    }

    companion object {
        fun create(stream: ObjectStream<String>, format: String): ObjectStream<TokenSample> {
            val sentences = SentenceStream.create(stream, format)
            return TokenSampleStream(sentences)
        }

        fun create(stream: InputStreamFactory, format: String): ObjectStream<TokenSample> {
            val sentences = SentenceStream.create(stream, format)
            return TokenSampleStream(sentences)
        }

        fun create(bytes: ByteArray, format: String): ObjectStream<TokenSample> {
            val sentences = SentenceStream.create(bytes, format)
            return TokenSampleStream(sentences)
        }

        fun create(string: String, format: String): ObjectStream<TokenSample> {
            val sentences = SentenceStream.create(string, format)
            return TokenSampleStream(sentences)
        }

        fun load(file: File): ObjectStream<TokenSample> {
            val sentences = SentenceStream.load(file)
            return TokenSampleStream(sentences)
        }

        fun load(path: String): ObjectStream<TokenSample> {
            val sentences = SentenceStream.load(path)
            return TokenSampleStream(sentences)
        }
    }
}
