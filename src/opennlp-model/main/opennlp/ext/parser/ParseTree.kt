// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.parser

import opennlp.tools.parser.Parser
import opennlp.tools.parser.ParserFactory
import opennlp.tools.parser.ParserModel
import java.io.File
import java.io.InputStream
import java.net.URL
import java.nio.file.Path

@Suppress("MemberVisibilityCanBePrivate")
object ParseTree {
    fun parser(model: ParserModel): Parser = ParserFactory.create(model)

    fun parser(stream: InputStream): Parser = parser(ParserModel(stream))

    fun parser(file: File): Parser = parser(ParserModel(file))

    fun parser(path: Path): Parser = parser(ParserModel(path))

    fun parser(url: URL): Parser = parser(ParserModel(url))

    fun parser(path: String): Parser = when {
        path.contains("://") -> parser(URL(path))
        else -> parser(File(path))
    }
}
