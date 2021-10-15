// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

// Reference: [CoNLL-X Format](https://ilk.uvt.nl/~emarsi/download/pubs/14964.pdf)
// Reference: [CoNLL-U Format](https://universaldependencies.org/format.html)
data class DependencyRelation(val head: Int, val deprel: String) {
    override fun toString(): String = "$head:$deprel"

    class MissingDependencyRelationException: RuntimeException("Missing dependency relation")

    class InvalidDependencyRelationException(rel: String): RuntimeException("Invalid dependency relation: $rel")

    companion object {
        fun create(head: String, deprel: String): DependencyRelation? = when {
            head == "_" || deprel == "_" -> null
            else -> {
                val headIndex = head.toIntOrNull() ?: throw InvalidDependencyRelationException("$head:$deprel")
                DependencyRelation(headIndex, deprel)
            }
        }

        private fun parseItem(rel: String): DependencyRelation? = when (rel) {
            "" -> throw MissingDependencyRelationException()
            else -> {
                val parts = rel.split(':')
                when (parts.size) {
                    2 -> create(parts[0], parts[1])
                    else -> throw InvalidDependencyRelationException(rel)
                }
            }
        }

        fun parse(rels: String): List<DependencyRelation> = when (rels) {
            "_" -> listOf()
            else -> rels.split('|').mapNotNull { parseItem(it) }
        }
    }
}
