// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.tests.stream.sentence

import opennlp.ext.conll.stream.io.PlainTextLineStream
import opennlp.ext.conll.stream.sentence.ConlluSentenceStream
import opennlp.ext.conll.treebank.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("CoNLL-U Sentence Stream")
class ConlluSentenceStreamTest {
    private fun parse(text: String): List<Sentence> {
        val lines = PlainTextLineStream.create(text)
        val sentences = ConlluSentenceStream(lines)
        return generateSequence { sentences.read() }.toList()
    }

    @Test
    @DisplayName("empty")
    fun empty() {
        val sentences = parse("")
        assertThat(sentences.size, `is`(0))
    }

    @Nested
    @DisplayName("comments")
    inner class Comments {
        @Test
        @DisplayName("no comments")
        fun noComments() {
            val sentence = parse(
                """
                2	hats	hat	NOUN	NN	Number=Plur	1	det	3:test	SpaceAfter=No
                """.trimIndent()
            )[0]
            assertThat(sentence.comments.size, `is`(0))
        }

        @Test
        @DisplayName("comment")
        fun comment() {
            val sentence = parse(
                """
                # Lorem ipsum dolor.
                2	hats	hat	NOUN	NN	Number=Plur	1	det	3:test	SpaceAfter=No
                """.trimIndent()
            )[0]
            assertThat(sentence.comments[0], `is`(Comment(null, "Lorem ipsum dolor.")))
            assertThat(sentence.comments.size, `is`(1))
        }
    }

    @Nested
    @DisplayName("sentence metadata comments")
    inner class SentenceMetadataComments {
        @Test
        @DisplayName("sent_id")
        fun sentId() {
            val sentence = parse(
                """
                # sent_id = lorem-ipsum
                2	hats	hat	NOUN	NN	Number=Plur	1	det	3:test	SpaceAfter=No
                """.trimIndent()
            )[0]
            assertThat(sentence.comments[0], `is`(Comment("sent_id", "lorem-ipsum")))
            assertThat(sentence.comments.size, `is`(1))

            assertThat(sentence.sentenceId, `is`("lorem-ipsum"))
        }

        @Test
        @DisplayName("text")
        fun text() {
            val sentence = parse(
                """
                # text = The hats.
                2	hats	hat	NOUN	NN	Number=Plur	1	det	3:test	SpaceAfter=No
                """.trimIndent()
            )[0]
            assertThat(sentence.comments[0], `is`(Comment("text", "The hats.")))
            assertThat(sentence.comments.size, `is`(1))

            assertThat(sentence.sentenceId, `is`(nullValue()))
        }

        @Test
        @DisplayName("sent_id and text")
        fun sentIdAndText() {
            val sentence = parse(
                """
                # sent_id = lorem-1
                # text = The hats.
                2	hats	hat	NOUN	NN	Number=Plur	1	det	3:test	SpaceAfter=No
                """.trimIndent()
            )[0]
            assertThat(sentence.comments[0], `is`(Comment("sent_id", "lorem-1")))
            assertThat(sentence.comments[1], `is`(Comment("text", "The hats.")))
            assertThat(sentence.comments.size, `is`(2))

            assertThat(sentence.sentenceId, `is`("lorem-1"))
        }
    }

    @Nested
    @DisplayName("Word Lines")
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
            val token = wordLine("2\thats\that\tNOUN\tNN\tNumber=Plur\t1\tdet\t3:test\tSpaceAfter=No")
            assertThat(token.id, `is`(TokenId(2, 2, null)))
            assertThat(token.form, `is`("hats"))
            assertThat(token.lemma, `is`("hat"))
            assertThat(token.upos, `is`("NOUN"))
            assertThat(token.xpos, `is`("NN"))
            assertThat(token.feats, `is`(listOf(Feature("Number", "Plur"))))
            assertThat(token.dep, `is`(DependencyRelation(1, "det")))
            assertThat(token.deps, `is`(listOf(DependencyRelation(3, "test"))))
            assertThat(token.misc, `is`(listOf(Feature("SpaceAfter", "No"))))
        }

        @Test
        @DisplayName("minimal annotation")
        fun minimalAnnotation() {
            val token = wordLine("2\thats\that\t_\t_\t_\t_\t_\t_\t_")
            assertThat(token.id, `is`(TokenId(2, 2, null)))
            assertThat(token.form, `is`("hats"))
            assertThat(token.lemma, `is`("hat"))
            assertThat(token.upos, `is`(nullValue()))
            assertThat(token.xpos, `is`(nullValue()))
            assertThat(token.feats, `is`(listOf()))
            assertThat(token.dep, `is`(nullValue()))
            assertThat(token.deps, `is`(listOf()))
            assertThat(token.misc, `is`(listOf()))
        }

        @Test
        @DisplayName("underscore form")
        fun underscoreForm() {
            val token = wordLine("2\t_\t_\t_\t_\t_\t_\t_\t_\t_")
            assertThat(token.id, `is`(TokenId(2, 2, null)))
            assertThat(token.form, `is`("_"))
            assertThat(token.lemma, `is`("_"))
            assertThat(token.upos, `is`(nullValue()))
            assertThat(token.xpos, `is`(nullValue()))
            assertThat(token.feats, `is`(listOf()))
            assertThat(token.dep, `is`(nullValue()))
            assertThat(token.deps, `is`(listOf()))
            assertThat(token.misc, `is`(listOf()))
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
            2	hats	hat	NOUN	NN	Number=Plur	1	det	3:test	SpaceAfter=No
            """.trimIndent()
        )
        assertThat(sentences.size, `is`(1))

        var token = sentences[0].wordLines[0]
        assertThat(token.id, `is`(TokenId(1, 1, null)))
        assertThat(token.form, `is`("the"))
        assertThat(token.lemma, `is`("the"))
        assertThat(token.upos, `is`("DET"))
        assertThat(token.xpos, `is`("DT"))
        assertThat(token.feats, `is`(listOf()))
        assertThat(token.dep, `is`(nullValue()))
        assertThat(token.deps, `is`(listOf()))
        assertThat(token.misc, `is`(listOf()))

        token = sentences[0].wordLines[1]
        assertThat(token.id, `is`(TokenId(2, 2, null)))
        assertThat(token.form, `is`("hats"))
        assertThat(token.lemma, `is`("hat"))
        assertThat(token.upos, `is`("NOUN"))
        assertThat(token.xpos, `is`("NN"))
        assertThat(token.feats, `is`(listOf(Feature("Number", "Plur"))))
        assertThat(token.dep, `is`(DependencyRelation(1, "det")))
        assertThat(token.deps, `is`(listOf(DependencyRelation(3, "test"))))
        assertThat(token.misc, `is`(listOf(Feature("SpaceAfter", "No"))))
    }

    @Test
    @DisplayName("multiple sentences")
    fun multipleSentences() {
        val sentences = parse(
            """
            1	the	the	DET	DT	_	_	_	_	_

            2	hats	hat	NOUN	NN	Number=Plur	1	det	3:test	SpaceAfter=No
            """.trimIndent()
        )
        assertThat(sentences.size, `is`(2))

        var token = sentences[0].wordLines[0]
        assertThat(token.id, `is`(TokenId(1, 1, null)))
        assertThat(token.form, `is`("the"))
        assertThat(token.lemma, `is`("the"))
        assertThat(token.upos, `is`("DET"))
        assertThat(token.xpos, `is`("DT"))
        assertThat(token.feats, `is`(listOf()))
        assertThat(token.dep, `is`(nullValue()))
        assertThat(token.deps, `is`(listOf()))
        assertThat(token.misc, `is`(listOf()))

        token = sentences[1].wordLines[0]
        assertThat(token.id, `is`(TokenId(2, 2, null)))
        assertThat(token.form, `is`("hats"))
        assertThat(token.lemma, `is`("hat"))
        assertThat(token.upos, `is`("NOUN"))
        assertThat(token.xpos, `is`("NN"))
        assertThat(token.feats, `is`(listOf(Feature("Number", "Plur"))))
        assertThat(token.dep, `is`(DependencyRelation(1, "det")))
        assertThat(token.deps, `is`(listOf(DependencyRelation(3, "test"))))
        assertThat(token.misc, `is`(listOf(Feature("SpaceAfter", "No"))))
    }
}
