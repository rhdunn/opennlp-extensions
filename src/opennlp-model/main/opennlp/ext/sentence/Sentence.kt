// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.sentence

import opennlp.tools.sentdetect.SentenceDetector
import opennlp.tools.sentdetect.SentenceDetectorME
import opennlp.tools.sentdetect.SentenceModel
import java.io.File
import java.io.InputStream
import java.net.URL
import java.nio.file.Path

@Suppress("MemberVisibilityCanBePrivate")
class Sentence {
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
    }
}
