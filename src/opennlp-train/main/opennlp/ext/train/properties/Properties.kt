// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.train.properties

import java.io.File
import java.io.FileReader
import java.util.*

fun properties(path: String?): Properties {
    val properties = Properties()
    path?.let {
        properties.load(FileReader(it))
        properties[BASE_PATH] = File(path).parentFile
    }
    return properties
}

private const val BASE_PATH = "properties.base.path" // file [internal]

fun path(properties: Properties, key: String): File? {
    val path = properties[key] as? String ?: return null
    val base = properties[BASE_PATH] as File
    return base.resolve(path)
}
