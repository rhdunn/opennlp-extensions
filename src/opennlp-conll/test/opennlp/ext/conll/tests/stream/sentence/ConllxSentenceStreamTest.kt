// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.stream.sentence

import opennlp.ext.conll.stream.io.PlainTextLineStream
import opennlp.ext.conll.stream.sentence.ConllxSentenceStream
import opennlp.ext.conll.treebank.*
import opennlp.ext.conll.treebank.features.nominal.Number
import opennlp.ext.pos.tags.UPennTags
import opennlp.ext.pos.tags.UPosTags
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("CoNLL-X Sentence Stream")
class ConllxSentenceStreamTest {
    private val token1 = TokenId(1, 1, null)
    private val token2 = TokenId(2, 2, null)
    private val token3 = TokenId(3, 3, null)

    private fun parse(text: String): List<Sentence> {
        val lines = PlainTextLineStream.create(text)
        val sentences = ConllxSentenceStream(lines)
        return generateSequence { sentences.read() }.toList()
    }

    @Test
    @DisplayName("empty")
    fun empty() {
        val sentences = parse("")
        assertThat(sentences.size, `is`(0))
    }

    @Nested
    @DisplayName("word lines")
    inner class WordLines {
        private fun wordLine(text: String): WordLine {
            val sentences = parse(text)
            assertThat(sentences.size, `is`(1))
            assertThat(sentences[0].wordLines.size, `is`(1))
            return sentences[0].wordLines[0]
        }

        @Test
        @DisplayName("full annotation")
        fun fullAnnotation() {
            val token = wordLine("2\thats\that\tNOUN\tNN\tNumber=Plur\t1\tdet\t3\ttest")
            assertThat(token.id, `is`(token2))
            assertThat(token.form, `is`("hats"))
            assertThat(token.lemma, `is`("hat"))
            assertThat(token.upos, `is`(UPosTags.NOUN))
            assertThat(token.xpos, `is`(UPennTags.NN))
            assertThat(token.feats, `is`(listOf(Number.Plur)))
            assertThat(token.dep, `is`(DependencyRelation(token1, "det")))
            assertThat(token.deps, `is`(listOf(DependencyRelation(token3, "test"))))
            assertThat(token.misc, `is`(listOf()))

            assertThat(token.postag(POSTagset.Universal), `is`(UPosTags.NOUN))
            assertThat(token.postag(POSTagset.LanguageSpecific), `is`(UPennTags.NN))
        }

        @Test
        @DisplayName("minimal annotation")
        fun minimalAnnotation() {
            val token = wordLine("2\thats\that\t_\t_\t_\t_\t_\t_\t_")
            assertThat(token.id, `is`(token2))
            assertThat(token.form, `is`("hats"))
            assertThat(token.lemma, `is`("hat"))
            assertThat(token.upos, `is`(nullValue()))
            assertThat(token.xpos, `is`(nullValue()))
            assertThat(token.feats, `is`(listOf()))
            assertThat(token.dep, `is`(nullValue()))
            assertThat(token.deps, `is`(listOf()))
            assertThat(token.misc, `is`(listOf()))

            assertThat(token.postag(POSTagset.Universal), `is`(nullValue()))
            assertThat(token.postag(POSTagset.LanguageSpecific), `is`(nullValue()))
        }

        @Test
        @DisplayName("underscore form")
        fun underscoreForm() {
            val token = wordLine("2\t_\t_\t_\t_\t_\t_\t_\t_\t_")
            assertThat(token.id, `is`(token2))
            assertThat(token.form, `is`("_"))
            assertThat(token.lemma, `is`("_"))
            assertThat(token.upos, `is`(nullValue()))
            assertThat(token.xpos, `is`(nullValue()))
            assertThat(token.feats, `is`(listOf()))
            assertThat(token.dep, `is`(nullValue()))
            assertThat(token.deps, `is`(listOf()))
            assertThat(token.misc, `is`(listOf()))

            assertThat(token.postag(POSTagset.Universal), `is`(nullValue()))
            assertThat(token.postag(POSTagset.LanguageSpecific), `is`(nullValue()))
        }

        @Test
        @DisplayName("wrong number of fields")
        fun wrongNumberOfFields() {
            val e = assertThrows<WordLine.InvalidWorldLineException> { wordLine("1\tinvalid") }
            assertThat(e.message, `is`("Invalid word line: 1\tinvalid"))
        }
    }

    @Test
    @DisplayName("multiple word lines")
    fun multipleWordLines() {
        val sentences = parse(
            """
            1	the	the	DET	DT	_	_	_	_	_
            2	hats	hat	NOUN	NN	Number=Plur	1	det	3	test
            """.trimIndent()
        )
        assertThat(sentences.size, `is`(1))

        var token = sentences[0].wordLines[0]
        assertThat(token.id, `is`(token1))
        assertThat(token.form, `is`("the"))
        assertThat(token.lemma, `is`("the"))
        assertThat(token.upos, `is`(UPosTags.DET))
        assertThat(token.xpos, `is`(UPennTags.DT))
        assertThat(token.feats, `is`(listOf()))
        assertThat(token.dep, `is`(nullValue()))
        assertThat(token.deps, `is`(listOf()))
        assertThat(token.misc, `is`(listOf()))

        token = sentences[0].wordLines[1]
        assertThat(token.id, `is`(token2))
        assertThat(token.form, `is`("hats"))
        assertThat(token.lemma, `is`("hat"))
        assertThat(token.upos, `is`(UPosTags.NOUN))
        assertThat(token.xpos, `is`(UPennTags.NN))
        assertThat(token.feats, `is`(listOf(Number.Plur)))
        assertThat(token.dep, `is`(DependencyRelation(token1, "det")))
        assertThat(token.deps, `is`(listOf(DependencyRelation(token3, "test"))))
        assertThat(token.misc, `is`(listOf()))
    }

    @Test
    @DisplayName("multiple sentences")
    fun multipleSentences() {
        val sentences = parse(
            """
            1	the	the	DET	DT	_	_	_	_	_

            2	hats	hat	NOUN	NN	Number=Plur	1	det	3	test
            """.trimIndent()
        )
        assertThat(sentences.size, `is`(2))

        var token = sentences[0].wordLines[0]
        assertThat(token.id, `is`(token1))
        assertThat(token.form, `is`("the"))
        assertThat(token.lemma, `is`("the"))
        assertThat(token.upos, `is`(UPosTags.DET))
        assertThat(token.xpos, `is`(UPennTags.DT))
        assertThat(token.feats, `is`(listOf()))
        assertThat(token.dep, `is`(nullValue()))
        assertThat(token.deps, `is`(listOf()))
        assertThat(token.misc, `is`(listOf()))

        token = sentences[1].wordLines[0]
        assertThat(token.id, `is`(token2))
        assertThat(token.form, `is`("hats"))
        assertThat(token.lemma, `is`("hat"))
        assertThat(token.upos, `is`(UPosTags.NOUN))
        assertThat(token.xpos, `is`(UPennTags.NN))
        assertThat(token.feats, `is`(listOf(Number.Plur)))
        assertThat(token.dep, `is`(DependencyRelation(token1, "det")))
        assertThat(token.deps, `is`(listOf(DependencyRelation(token3, "test"))))
        assertThat(token.misc, `is`(listOf()))
    }
}
