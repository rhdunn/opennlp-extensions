// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.token

import opennlp.tools.tokenize.Tokenizer
import opennlp.tools.tokenize.TokenizerME
import opennlp.tools.tokenize.TokenizerModel
import opennlp.tools.util.Span
import java.io.File
import java.io.InputStream
import java.net.URL
import java.nio.file.Path

@Suppress("MemberVisibilityCanBePrivate")
data class Token(val index: Int, val textBefore: String?, val text: String, val span: Span) {
    companion object {
        fun tokenizer(model: TokenizerModel): Tokenizer = TokenizerME(model)

        fun tokenizer(stream: InputStream): Tokenizer = tokenizer(TokenizerModel(stream))

        fun tokenizer(file: File): Tokenizer = tokenizer(TokenizerModel(file))

        fun tokenizer(path: Path): Tokenizer = tokenizer(TokenizerModel(path))

        fun tokenizer(url: URL): Tokenizer = tokenizer(TokenizerModel(url))

        fun tokenizer(path: String): Tokenizer = when {
            path.contains("://") -> tokenizer(URL(path))
            else -> tokenizer(File(path))
        }
    }
}
