// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.stream.sample

import opennlp.ext.conll.stream.io.PlainTextLineStream
import opennlp.ext.conll.stream.sample.TokenSampleStream
import opennlp.ext.conll.stream.sentence.ConlluSentenceStream
import opennlp.tools.tokenize.TokenSample
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("TokenSample Stream")
class TokenSampleStreamTest {
    private fun parse(text: String): List<TokenSample> {
        val lines = PlainTextLineStream.create(text)
        val sentences = ConlluSentenceStream(lines)
        val tokens = TokenSampleStream(sentences)
        return generateSequence { tokens.read() }.toList()
    }

    @Test
    @DisplayName("empty")
    fun empty() {
        val tokens = parse("")
        assertThat(tokens.size, `is`(0))
    }
}
