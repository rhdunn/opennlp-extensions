// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

// Reference: [CoNLL-X Format](https://ilk.uvt.nl/~emarsi/download/pubs/14964.pdf)
data class TokenId(
    val start: Int
) {
    override fun toString(): String = start.toString()

    class InvalidTokenIdException(id: String): RuntimeException("Invalid token ID: $id")

    companion object {
        fun parse(id: String): TokenId {
            val start = id.toIntOrNull() ?: throw InvalidTokenIdException(id)
            return TokenId(start)
        }
    }
}
