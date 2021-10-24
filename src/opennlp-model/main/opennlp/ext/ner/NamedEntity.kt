// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.ner

import opennlp.tools.namefind.NameFinderME
import opennlp.tools.namefind.TokenNameFinder
import opennlp.tools.namefind.TokenNameFinderModel
import java.io.File
import java.io.InputStream
import java.net.URL
import java.nio.file.Path

@Suppress("MemberVisibilityCanBePrivate")
object NamedEntity {
    fun ner(model: TokenNameFinderModel): TokenNameFinder = NameFinderME(model)

    fun ner(stream: InputStream): TokenNameFinder = ner(TokenNameFinderModel(stream))

    fun ner(file: File): TokenNameFinder = ner(TokenNameFinderModel(file))

    fun ner(path: Path): TokenNameFinder = ner(TokenNameFinderModel(path))

    fun ner(url: URL): TokenNameFinder = ner(TokenNameFinderModel(url))

    fun ner(path: String): TokenNameFinder = when {
        path.contains("://") -> ner(URL(path))
        else -> ner(File(path))
    }
}
