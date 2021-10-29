// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.stream.sentence

import opennlp.ext.conll.stream.properties.uposTagset
import opennlp.ext.conll.stream.properties.xposTagset
import opennlp.ext.conll.treebank.*
import opennlp.ext.pos.PosTagset
import opennlp.ext.pos.tags.UPennTags
import opennlp.ext.pos.tags.UPosTags
import opennlp.tools.util.FilterObjectStream
import opennlp.tools.util.ObjectStream
import java.util.*

// Reference: [CoNLL-U Format](https://universaldependencies.org/format.html)
class ConlluSentenceStream(
    stream: ObjectStream<String>,
    private val uposTagset: PosTagset = UPosTags,
    private val xposTagset: PosTagset = UPennTags
) : FilterObjectStream<String, Sentence>(stream) {
    constructor(stream: ObjectStream<String>, properties: Properties) : this(
        stream,
        uposTagset(properties),
        xposTagset(properties)
    )

    override fun read(): Sentence? {
        var line: String? = samples.read() ?: return null

        val comments = mutableListOf<Comment>()
        val wordLines = mutableListOf<WordLine>()
        while (!line.isNullOrEmpty()) {
            when {
                line.startsWith('#') -> comments.add(Comment.parse(line))
                else -> wordLines.add(parseWordLine(line.split('\t')))
            }
            line = samples.read()
        }
        return Sentence(comments, wordLines)
    }

    private fun parseWordLine(fields: List<String>): WordLine = when (fields.size) {
        10 -> WordLine(
            TokenId.parse(fields[ID]),
            fields[FORM],
            fields[LEMMA],
            uposTagset[fields[UPOS]],
            xposTagset[fields[XPOS]],
            Feature.parse(fields[FEATS]),
            DependencyRelation.create(fields[HEAD], fields[DEPREL]),
            DependencyRelation.parse(fields[DEPS]),
            Feature.parse(fields[MISC])
        )
        else -> throw WordLine.InvalidWorldLineException(fields.joinToString("\t"))
    }

    companion object {
        private const val ID = 0
        private const val FORM = 1
        private const val LEMMA = 2
        private const val UPOS = 3
        private const val XPOS = 4
        private const val FEATS = 5
        private const val HEAD = 6
        private const val DEPREL = 7
        private const val DEPS = 8
        private const val MISC = 9
    }
}
