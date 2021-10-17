// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.train.properties

import opennlp.tools.postag.POSDictionary
import opennlp.tools.postag.POSTaggerFactory
import java.io.FileInputStream
import java.util.*

fun posTaggerFactory(properties: Properties): POSTaggerFactory {
    val posDictionary = posDictionary(properties)
    return POSTaggerFactory(null, null, posDictionary)
}

private const val POS_DICTIONARY: String = "pos.dictionary" // path

fun posDictionary(properties: Properties): POSDictionary? {
    val path = path(properties, POS_DICTIONARY) ?: return null
    return FileInputStream(path).use { POSDictionary.create(it) }
}
