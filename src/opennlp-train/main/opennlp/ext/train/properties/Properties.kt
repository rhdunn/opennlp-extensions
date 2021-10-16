// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.train.properties

import java.io.FileReader
import java.util.*

fun properties(path: String?): Properties {
    val properties = Properties()
    path?.let {
        properties.load(FileReader(it))
    }
    return properties
}
