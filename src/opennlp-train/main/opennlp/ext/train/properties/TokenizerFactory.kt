// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.train.properties

import opennlp.tools.dictionary.Dictionary
import opennlp.tools.tokenize.TokenizerFactory
import java.io.FileReader
import java.util.*
import java.util.regex.Pattern

fun tokenizerFactory(properties: Properties): TokenizerFactory {
    val language = language(properties)
    val abbreviationsDictionary = abbreviationsDictionary(properties)
    val alphanumericOptimization = alphanumericOptimization(properties)
    val alphanumericPattern = alphanumericPattern(properties)
    return TokenizerFactory(language, abbreviationsDictionary, alphanumericOptimization, alphanumericPattern)
}

private const val LANGUAGE: String = "language" // integer
private const val LANGUAGE_DEFAULT: String = "en"

fun language(properties: Properties): String {
    return properties.getOrDefault(LANGUAGE, LANGUAGE_DEFAULT) as String
}

private const val ABBREVIATIONS_DICTIONARY: String = "abbreviations.dictionary" // path

fun abbreviationsDictionary(properties: Properties): Dictionary? {
    val path = path(properties, ABBREVIATIONS_DICTIONARY) ?: return null
    return FileReader(path).use { Dictionary.parseOneEntryPerLine(it) }
}

private const val ALPHANUMERIC_OPTIMIZATION: String = "alphanumeric.optimization" // boolean
private const val ALPHANUMERIC_OPTIMIZATION_DEFAULT: String = "false"

fun alphanumericOptimization(properties: Properties): Boolean {
    val value = properties.getOrDefault(ALPHANUMERIC_OPTIMIZATION, ALPHANUMERIC_OPTIMIZATION_DEFAULT) as String
    return value == "true"
}

private const val ALPHANUMERIC_PATTERN: String = "alphanumeric.pattern" // regex
private const val ALPHANUMERIC_PATTERN_DEFAULT: String = "^[A-Za-z0-9]+\$"

fun alphanumericPattern(properties: Properties): Pattern {
    val value = properties.getOrDefault(ALPHANUMERIC_PATTERN, ALPHANUMERIC_PATTERN_DEFAULT) as String
    return value.toPattern()
}
