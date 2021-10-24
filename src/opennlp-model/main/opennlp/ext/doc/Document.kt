// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.doc

import opennlp.tools.doccat.DoccatModel
import opennlp.tools.doccat.DocumentCategorizer
import opennlp.tools.doccat.DocumentCategorizerME
import java.io.File
import java.io.InputStream
import java.net.URL
import java.nio.file.Path

@Suppress("MemberVisibilityCanBePrivate")
object Document {
    fun categorizer(model: DoccatModel): DocumentCategorizer = DocumentCategorizerME(model)

    fun categorizer(stream: InputStream): DocumentCategorizer = categorizer(DoccatModel(stream))

    fun categorizer(file: File): DocumentCategorizer = categorizer(DoccatModel(file))

    fun categorizer(path: Path): DocumentCategorizer = categorizer(DoccatModel(path))

    fun categorizer(url: URL): DocumentCategorizer = categorizer(DoccatModel(url))

    fun categorizer(path: String): DocumentCategorizer = when {
        path.contains("://") -> categorizer(URL(path))
        else -> categorizer(File(path))
    }
}
