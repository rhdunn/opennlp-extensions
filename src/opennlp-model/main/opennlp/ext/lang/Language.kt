// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.lang

import opennlp.tools.langdetect.LanguageDetector
import opennlp.tools.langdetect.LanguageDetectorME
import opennlp.tools.langdetect.LanguageDetectorModel
import java.io.File
import java.io.InputStream
import java.net.URL
import java.nio.file.FileSystems
import java.nio.file.Path

@Suppress("MemberVisibilityCanBePrivate")
object Language {
    fun detector(model: LanguageDetectorModel): LanguageDetector = LanguageDetectorME(model)

    fun detector(stream: InputStream): LanguageDetector = detector(LanguageDetectorModel(stream))

    fun detector(file: File): LanguageDetector = detector(LanguageDetectorModel(file))

    fun detector(path: Path): LanguageDetector = when (path.fileSystem) {
        FileSystems.getDefault() -> detector(File(path.toString()))
        else -> detector(path.toUri().toURL())
    }

    fun detector(url: URL): LanguageDetector = detector(LanguageDetectorModel(url))

    fun detector(path: String): LanguageDetector = when {
        path.contains("://") -> detector(URL(path))
        else -> detector(File(path))
    }
}
