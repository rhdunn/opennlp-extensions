// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

// Reference: [CoNLL-X Format](https://ilk.uvt.nl/~emarsi/download/pubs/14964.pdf)
// Reference: [CoNLL-U Format](https://universaldependencies.org/format.html)
data class TokenId(
    override val start: Int,
    override val endInclusive: Int,
    val emptyNodeIndex: Int? = null
) : ClosedRange<Int> {
    fun isId(id: Int): Boolean = start == id && endInclusive == id && emptyNodeIndex == null

    enum class Type {
        Counter,
        Range,
        EmptyNodeCounter,
    }

    val type: Type = when {
        emptyNodeIndex != null -> Type.EmptyNodeCounter
        start != endInclusive -> Type.Range
        else -> Type.Counter
    }

    override fun toString(): String = when {
        emptyNodeIndex != null -> "$start.$emptyNodeIndex"
        start == endInclusive -> start.toString()
        else -> "$start-$endInclusive"
    }

    class MissingTokenIdException : RuntimeException("Missing token ID")

    class InvalidTokenIdException(id: String) : RuntimeException("Invalid token ID: $id")

    companion object {
        private fun split(id: String, separator: Char, isRange: Boolean): Pair<Int, Int> {
            val parts = id.split(separator)
            return when (parts.size) {
                2 -> {
                    val start = parts[0].toIntOrNull() ?: throw InvalidTokenIdException(id)
                    val end = parts[1].toIntOrNull() ?: throw InvalidTokenIdException(id)
                    if (isRange && start > end) throw InvalidTokenIdException(id)
                    start to end
                }
                else -> throw InvalidTokenIdException(id)
            }
        }

        fun parse(id: String): TokenId = when {
            id == "" -> throw MissingTokenIdException()
            // ConLL-U token range
            id.contains('-') -> split(id, '-', isRange = true).let {
                TokenId(it.first, it.second)
            }
            // ConLL-U empty node token counter
            id.contains('.') -> split(id, '.', isRange = false).let {
                TokenId(it.first, it.first, it.second)
            }
            // ConLL-X token counter
            else -> {
                val start = id.toIntOrNull() ?: throw InvalidTokenIdException(id)
                TokenId(start, start)
            }
        }
    }
}
