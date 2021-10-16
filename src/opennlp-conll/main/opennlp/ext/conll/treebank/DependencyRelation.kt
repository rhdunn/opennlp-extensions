// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

// Reference: [CoNLL-X Format](https://ilk.uvt.nl/~emarsi/download/pubs/14964.pdf)
// Reference: [CoNLL-U Format](https://universaldependencies.org/format.html)
data class DependencyRelation(val head: TokenId, val deprel: String) {
    override fun toString(): String = "$head:$deprel"

    class MissingDependencyRelationException : RuntimeException("Missing dependency relation")

    class InvalidDependencyRelationException(rel: String) : RuntimeException("Invalid dependency relation: $rel")

    class InvalidRootException(rel: String) : RuntimeException("Invalid root node dependency relation: $rel")

    companion object {
        val ROOT: DependencyRelation = DependencyRelation(TokenId(0, 0, null), "root")

        fun create(head: String, deprel: String): DependencyRelation? = when {
            head == "_" || deprel == "_" -> null
            else -> {
                val headId =
                    try {
                        TokenId.parse(head)
                    } catch (e: Throwable) {
                        throw InvalidDependencyRelationException("$head:$deprel")
                    }
                when {
                    headId.isId(0) && deprel == "root" -> ROOT
                    headId.isId(0) || deprel == "root" -> throw InvalidRootException("$head:$deprel")
                    else -> DependencyRelation(headId, deprel)
                }
            }
        }

        private fun parseItem(rel: String): DependencyRelation? = when (rel) {
            "" -> throw MissingDependencyRelationException()
            else -> {
                val head = rel.substringBefore(':')
                val deprel = rel.substringAfter(':')
                create(head, deprel)
            }
        }

        fun parse(rels: String): List<DependencyRelation> = when (rels) {
            "_" -> listOf()
            else -> rels.split('|').mapNotNull { parseItem(it) }
        }
    }
}
