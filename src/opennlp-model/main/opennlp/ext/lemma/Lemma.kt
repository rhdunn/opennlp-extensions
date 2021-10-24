// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.lemma

import opennlp.tools.lemmatizer.Lemmatizer
import opennlp.tools.lemmatizer.LemmatizerME
import opennlp.tools.lemmatizer.LemmatizerModel
import java.io.File
import java.io.InputStream
import java.net.URL
import java.nio.file.Path

@Suppress("MemberVisibilityCanBePrivate")
object Lemma {
    fun lemmatizer(model: LemmatizerModel): Lemmatizer = LemmatizerME(model)

    fun lemmatizer(stream: InputStream): Lemmatizer = lemmatizer(LemmatizerModel(stream))

    fun lemmatizer(file: File): Lemmatizer = lemmatizer(LemmatizerModel(file))

    fun lemmatizer(path: Path): Lemmatizer = lemmatizer(LemmatizerModel(path))

    fun lemmatizer(url: URL): Lemmatizer = lemmatizer(LemmatizerModel(url))

    fun lemmatizer(path: String): Lemmatizer = when {
        path.contains("://") -> lemmatizer(URL(path))
        else -> lemmatizer(File(path))
    }
}
