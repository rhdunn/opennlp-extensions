// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.stream.properties

import java.util.*

const val MULTI_TOKEN_WORDS: String = "multi.token.words" // string -- one of: "split" | "join"

class InvalidMultiTokenWordsValue(value: String) : RuntimeException("Invalid $MULTI_TOKEN_WORDS property value: $value")

fun multiTokenWords(properties: Properties): MultiTokenWords {
    return when (val value = properties.getOrDefault(MULTI_TOKEN_WORDS, MultiTokenWords.Split.value) as String) {
        MultiTokenWords.Split.value -> MultiTokenWords.Split
        MultiTokenWords.Join.value -> MultiTokenWords.Join
        else -> throw InvalidMultiTokenWordsValue(value)
    }
}
