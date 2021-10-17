// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.train.properties

import opennlp.tools.sentdetect.SentenceDetectorFactory
import java.util.*

fun sentenceDetectorFactory(properties: Properties): SentenceDetectorFactory {
    val language = language(properties)
    val useTokenEnd = useTokenEnd(properties)
    val abbreviationsDictionary = abbreviationsDictionary(properties)
    val eosCharacters = eosCharacters(properties)
    return SentenceDetectorFactory(language, useTokenEnd, abbreviationsDictionary, eosCharacters)
}

private const val USE_TOKEN_END: String = "use.token.end" // boolean
private const val USE_TOKEN_END_DEFAULT: String = "false"

fun useTokenEnd(properties: Properties): Boolean {
    val value = properties.getOrDefault(USE_TOKEN_END, USE_TOKEN_END_DEFAULT) as String
    return value == "true"
}

private const val EOS_CHARACTERS: String = "eos.characters" // string

fun eosCharacters(properties: Properties): CharArray? {
    val value = properties[EOS_CHARACTERS] as? String
    return value?.toCharArray()
}
