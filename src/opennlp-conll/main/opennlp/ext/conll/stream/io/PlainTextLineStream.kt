// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.stream.io

import opennlp.tools.util.InputStreamFactory
import opennlp.tools.util.ObjectStream
import opennlp.tools.util.PlainTextByLineStream
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

object PlainTextLineStream {
    fun create(stream: InputStreamFactory, charset: Charset = StandardCharsets.UTF_8): ObjectStream<String> {
        return PlainTextByLineStream(stream, charset)
    }
}
