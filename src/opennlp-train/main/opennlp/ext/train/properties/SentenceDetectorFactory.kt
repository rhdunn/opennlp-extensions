// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.train.properties

import java.util.*

private const val USE_TOKEN_END: String = "use.token.end" // boolean
private const val USE_TOKEN_END_DEFAULT: String = "false"

fun useTokenEnd(properties: Properties): Boolean {
    val value = properties.getOrDefault(USE_TOKEN_END, USE_TOKEN_END_DEFAULT) as String
    return value == "true"
}
