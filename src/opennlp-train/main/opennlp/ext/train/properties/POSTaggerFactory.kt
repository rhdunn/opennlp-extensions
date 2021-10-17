// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.train.properties

import opennlp.tools.postag.POSDictionary
import java.io.FileInputStream
import java.util.*

private const val POS_DICTIONARY: String = "pos.dictionary" // path

fun posDictionary(properties: Properties): POSDictionary? {
    val path = path(properties, POS_DICTIONARY) ?: return null
    return FileInputStream(path).use { POSDictionary.create(it) }
}
