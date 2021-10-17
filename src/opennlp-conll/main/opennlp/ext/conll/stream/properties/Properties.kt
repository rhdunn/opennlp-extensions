// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.stream.properties

import opennlp.ext.conll.treebank.POSTagset
import java.util.*

class InvalidPropertyValue(key: String, value: String) : RuntimeException("Invalid $key property value: $value")

const val MULTI_TOKEN_WORDS: String = "multi.token.words" // string -- one of: "split" | "join"

fun multiTokenWords(properties: Properties): MultiTokenWords {
    return when (val value = properties.getOrDefault(MULTI_TOKEN_WORDS, MultiTokenWords.Split.value) as String) {
        MultiTokenWords.Split.value -> MultiTokenWords.Split
        MultiTokenWords.Join.value -> MultiTokenWords.Join
        else -> throw InvalidPropertyValue(MULTI_TOKEN_WORDS, value)
    }
}

const val POS_TAGSET: String = "pos.tagset" // string -- one of: "UPOS" | "XPOS"

fun posTagset(properties: Properties): POSTagset {
    return when (val value = properties.getOrDefault(POS_TAGSET, POSTagset.Universal.conlluField) as String) {
        POSTagset.LanguageSpecific.conlluField -> POSTagset.LanguageSpecific
        POSTagset.LanguageSpecific.conllxField -> POSTagset.LanguageSpecific
        POSTagset.Universal.conlluField -> POSTagset.Universal
        POSTagset.Universal.conllxField -> POSTagset.Universal
        else -> throw InvalidPropertyValue(POS_TAGSET, value)
    }
}

private const val SENTENCES_PER_SAMPLE: String = "sentences.per.sample" // integer
private const val SENTENCES_PER_SAMPLE_DEFAULT: String = "5"

fun sentencesPerSample(properties: Properties): Int {
    val value = properties.getOrDefault(SENTENCES_PER_SAMPLE, SENTENCES_PER_SAMPLE_DEFAULT) as String
    return value.toIntOrNull() ?: throw InvalidPropertyValue(SENTENCES_PER_SAMPLE, value)
}
