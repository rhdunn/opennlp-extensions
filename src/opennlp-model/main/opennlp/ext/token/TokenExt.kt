// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.token

import opennlp.ext.sentence.Sentence
import opennlp.tools.tokenize.Tokenizer
import opennlp.tools.util.Span

fun String.tokenize(spans: Array<Span>): Array<Token> {
    val tokens = mutableListOf<Token>()
    var start = 0

    spans.withIndex().forEach { (index, span) ->
        val textBefore = when (start) {
            span.start -> null
            else -> substring(start, span.start)
        }

        tokens.add(Token(index, textBefore, this, span))
        start = span.end
    }

    return tokens.toTypedArray()
}

fun String.tokenize(tokenizer: Tokenizer): Array<Token> = tokenize(tokenizer.tokenizePos(this))

fun Sentence.tokenize(spans: Array<Span>): Array<Token> {
    val tokens = text.tokenize(spans)
    this.tokens = tokens.map { it.text }.toTypedArray()
    return tokens
}

fun Sentence.tokenize(tokenizer: Tokenizer): Array<Token> = tokenize(tokenizer.tokenizePos(text))
