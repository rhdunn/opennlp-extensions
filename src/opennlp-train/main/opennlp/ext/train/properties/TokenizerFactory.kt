// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.train.properties

import opennlp.tools.dictionary.Dictionary
import java.io.FileReader
import java.util.*

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
