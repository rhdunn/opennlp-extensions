// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.sentence

import opennlp.tools.sentdetect.SentenceDetector
import opennlp.tools.util.Span

fun String.sentences(spans: Array<Span>): Array<Sentence> {
    val sentences = mutableListOf<Sentence>()
    var start = 0

    spans.asSequence().windowed(2, partialWindows = true).forEach { span ->
        val textBefore = when (start) {
            span[0].start -> null
            else -> substring(start, span[0].start)
        }

        val textAfter = when {
            span.size == 1 -> when (span[0].end) {
                length -> null
                else -> substring(span[0].end)
            }
            span[0].end == span[1].start -> null
            else -> substring(span[0].end, span[1].start)
        }

        val sentence = substring(span[0].start, span[0].end)
        sentences.add(Sentence(textBefore, sentence, textAfter))
        start = span[0].end
    }

    return sentences.toTypedArray()
}

fun String.sentences(detector: SentenceDetector): Array<Sentence> = sentences(detector.sentPosDetect(this))
