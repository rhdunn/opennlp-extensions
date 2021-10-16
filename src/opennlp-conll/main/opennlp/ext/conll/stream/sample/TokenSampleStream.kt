// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.stream.sample

import opennlp.ext.conll.treebank.Sentence
import opennlp.tools.tokenize.TokenSample
import opennlp.tools.util.FilterObjectStream
import opennlp.tools.util.ObjectStream
import opennlp.tools.util.Span

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
}
