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

// Reference: [CoNLL-X Format](https://ilk.uvt.nl/~emarsi/download/pubs/14964.pdf)
class ConllxSentenceStream(
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

        val wordLines = mutableListOf<WordLine>()
        while (!line.isNullOrEmpty()) {
            wordLines.add(parseWordLine(line.split('\t')))
            line = samples.read()
        }
        return Sentence(listOf(), wordLines)
    }

    private fun parseWordLine(fields: List<String>): WordLine = when (fields.size) {
        10 -> WordLine(
            TokenId.parse(fields[ID]),
            fields[FORM],
            fields[LEMMA],
            fields[CPOSTAG].takeIf { it != "_" }?.let { uposTagset[it] },
            fields[POSTAG].takeIf { it != "_" }?.let { xposTagset[it] },
            Feature.parse(fields[FEATS]),
            DependencyRelation.create(fields[HEAD], fields[DEPREL]),
            listOfNotNull(DependencyRelation.create(fields[PHEAD], fields[PDEPREL])),
            listOf()
        )
        else -> throw WordLine.InvalidWorldLineException(fields.joinToString("\t"))
    }

    companion object {
        private const val ID = 0
        private const val FORM = 1
        private const val LEMMA = 2
        private const val CPOSTAG = 3
        private const val POSTAG = 4
        private const val FEATS = 5
        private const val HEAD = 6
        private const val DEPREL = 7
        private const val PHEAD = 8
        private const val PDEPREL = 9
    }
}
