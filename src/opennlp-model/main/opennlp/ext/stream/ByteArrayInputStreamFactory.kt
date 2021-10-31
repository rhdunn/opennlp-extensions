// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.stream

import opennlp.tools.util.InputStreamFactory
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class ByteArrayInputStreamFactory(private val bytes: ByteArray) : InputStreamFactory {
    constructor(string: String, charset: Charset = StandardCharsets.UTF_8) : this(string.toByteArray(charset))

    override fun createInputStream(): InputStream = ByteArrayInputStream(bytes)
}
