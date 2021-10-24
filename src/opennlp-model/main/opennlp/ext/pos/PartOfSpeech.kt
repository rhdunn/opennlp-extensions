// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.pos

import opennlp.tools.postag.POSModel
import opennlp.tools.postag.POSTagger
import opennlp.tools.postag.POSTaggerME
import java.io.File
import java.io.InputStream
import java.net.URL
import java.nio.file.Path

@Suppress("MemberVisibilityCanBePrivate")
object PartOfSpeech {
    fun tagger(model: POSModel): POSTagger = POSTaggerME(model)

    fun tagger(stream: InputStream): POSTagger = tagger(POSModel(stream))

    fun tagger(file: File): POSTagger = tagger(POSModel(file))

    fun tagger(path: Path): POSTagger = tagger(POSModel(path))

    fun tagger(url: URL): POSTagger = tagger(POSModel(url))

    fun tagger(path: String): POSTagger = when {
        path.contains("://") -> tagger(URL(path))
        else -> tagger(File(path))
    }
}
