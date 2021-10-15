// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

// Reference: [CoNLL-X Format](https://ilk.uvt.nl/~emarsi/download/pubs/14964.pdf)
// Reference: [CoNLL-U Format](https://universaldependencies.org/format.html)
data class TokenId(
    override val start: Int,
    override val endInclusive: Int
): ClosedRange<Int> {

    override fun toString(): String = when {
        start == endInclusive -> start.toString()
        else -> "$start-$endInclusive"
    }

    class InvalidTokenIdException(id: String): RuntimeException("Invalid token ID: $id")

    companion object {
        fun parse(id: String): TokenId = when {
            // ConLL-U token range
            id.contains('-') -> {
                val parts = id.split('-')
                when (parts.size) {
                    2 -> {
                        val start = parts[0].toIntOrNull() ?: throw InvalidTokenIdException(id)
                        val end = parts[1].toIntOrNull() ?: throw InvalidTokenIdException(id)
                        if (start > end) throw InvalidTokenIdException(id)
                        TokenId(start, end)
                    }
                    else -> throw InvalidTokenIdException(id)
                }
            }
            // ConLL-X token counter
            else -> {
                val start = id.toIntOrNull() ?: throw InvalidTokenIdException(id)
                TokenId(start, start)
            }
        }
    }
}
