// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.token

import opennlp.tools.tokenize.Tokenizer
import opennlp.tools.util.Span

fun String.tokenize(spans: Array<Span>): Array<Token> {
    val tokens = mutableListOf<Token>()
    var start = 0

    spans.forEach { span ->
        val textBefore = when (start) {
            span.start -> null
            else -> substring(start, span.start)
        }

        val token = substring(span.start, span.end)
        tokens.add(Token(textBefore, token))
        start = span.end
    }

    return tokens.toTypedArray()
}

fun String.tokenize(tokenizer: Tokenizer): Array<Token> = tokenize(tokenizer.tokenizePos(this))
