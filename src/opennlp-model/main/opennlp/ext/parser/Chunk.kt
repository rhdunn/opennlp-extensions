// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.parser

import opennlp.tools.chunker.Chunker
import opennlp.tools.chunker.ChunkerME
import opennlp.tools.chunker.ChunkerModel
import java.io.File
import java.io.InputStream
import java.net.URL
import java.nio.file.Path

@Suppress("MemberVisibilityCanBePrivate")
object Chunk {
    fun chunker(model: ChunkerModel): Chunker = ChunkerME(model)

    fun chunker(stream: InputStream): Chunker = chunker(ChunkerModel(stream))

    fun chunker(file: File): Chunker = chunker(ChunkerModel(file))

    fun chunker(path: Path): Chunker = chunker(ChunkerModel(path))

    fun chunker(url: URL): Chunker = chunker(ChunkerModel(url))

    fun chunker(path: String): Chunker = when {
        path.contains("://") -> chunker(URL(path))
        else -> chunker(File(path))
    }
}
