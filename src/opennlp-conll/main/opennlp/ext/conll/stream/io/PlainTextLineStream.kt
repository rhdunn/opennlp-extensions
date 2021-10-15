// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.stream.io

import opennlp.tools.util.InputStreamFactory
import opennlp.tools.util.MarkableFileInputStreamFactory
import opennlp.tools.util.ObjectStream
import opennlp.tools.util.PlainTextByLineStream
import java.io.File
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

object PlainTextLineStream {
    fun create(stream: InputStreamFactory, charset: Charset = StandardCharsets.UTF_8): ObjectStream<String> {
        return PlainTextByLineStream(stream, charset)
    }

    fun create(bytes: ByteArray, charset: Charset = StandardCharsets.UTF_8): ObjectStream<String> {
        return create(ByteArrayInputStreamFactory(bytes), charset)
    }

    fun create(string: String): ObjectStream<String> {
        return create(ByteArrayInputStreamFactory(string))
    }

    fun load(file: File, charset: Charset = StandardCharsets.UTF_8): ObjectStream<String> {
        return create(MarkableFileInputStreamFactory(file), charset)
    }

    fun load(path: String, charset: Charset = StandardCharsets.UTF_8): ObjectStream<String> {
        return load(File(path), charset)
    }
}
