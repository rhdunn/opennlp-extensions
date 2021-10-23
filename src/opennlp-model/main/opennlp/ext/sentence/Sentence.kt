// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.sentence

import opennlp.tools.sentdetect.SentenceDetector
import opennlp.tools.sentdetect.SentenceDetectorME
import opennlp.tools.sentdetect.SentenceModel
import opennlp.tools.util.Span
import java.io.File
import java.io.InputStream
import java.net.URL
import java.nio.file.Path

@Suppress("MemberVisibilityCanBePrivate")
data class Sentence(val textBefore: String?, val text: String, val textAfter: String?) {
    companion object {
        fun detector(model: SentenceModel): SentenceDetector = SentenceDetectorME(model)

        fun detector(stream: InputStream): SentenceDetector = detector(SentenceModel(stream))

        fun detector(file: File): SentenceDetector = detector(SentenceModel(file))

        fun detector(path: Path): SentenceDetector = detector(SentenceModel(path))

        fun detector(url: URL): SentenceDetector = detector(SentenceModel(url))

        fun detector(path: String): SentenceDetector = when {
            path.contains("://") -> detector(URL(path))
            else -> detector(File(path))
        }

        fun split(text: String, spans: Array<Span>): Array<Sentence> {
            val sentences = mutableListOf<Sentence>()
            spans.forEach { span ->
                val sentence = text.substring(span.start, span.end)
                sentences.add(Sentence(null, sentence, null))
            }
            return sentences.toTypedArray()
        }

        fun split(text: String, detector: SentenceDetector): Array<Sentence> {
            return split(text, detector.sentPosDetect(text))
        }
    }
}
