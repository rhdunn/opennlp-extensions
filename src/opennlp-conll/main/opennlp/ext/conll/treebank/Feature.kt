// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

// Reference: [CoNLL-X Format](https://ilk.uvt.nl/~emarsi/download/pubs/14964.pdf)
// Reference: [CoNLL-U Format](https://universaldependencies.org/format.html)
data class Feature(val name: String?, val value: String) {
    override fun toString(): String = when (name) {
        null -> value
        else -> "$name=$value"
    }

    class MissingFeatureException : RuntimeException("Missing feature")

    class InvalidFeatureException(feat: String) : RuntimeException("Invalid feature: $feat")

    companion object {
        private fun parseItem(feat: String): Feature = when (feat) {
            "" -> throw MissingFeatureException()
            else -> {
                val parts = feat.split('=')
                when (parts.size) {
                    1 -> Feature(null, parts[0])
                    2 -> Feature(parts[0], parts[1])
                    else -> throw InvalidFeatureException(feat)
                }
            }
        }

        fun parse(feats: String): List<Feature> = when (feats) {
            "_" -> listOf()
            else -> feats.split('|').map { parseItem(it) }
        }
    }
}
