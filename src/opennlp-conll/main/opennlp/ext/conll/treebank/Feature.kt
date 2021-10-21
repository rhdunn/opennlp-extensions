// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

import opennlp.ext.conll.treebank.features.UnknownFeature

// Reference: [CoNLL-X Format](https://ilk.uvt.nl/~emarsi/download/pubs/14964.pdf)
// Reference: [CoNLL-U Format](https://universaldependencies.org/format.html)
interface Feature {
    val name: String?
    val value: String

    class MissingFeatureException : RuntimeException("Missing feature")

    class InvalidFeatureException(feat: String) : RuntimeException("Invalid feature: $feat")

    companion object {
        private fun parseItem(feat: String): Feature = when (feat) {
            "" -> throw MissingFeatureException()
            else -> {
                val parts = feat.split('=')
                when (parts.size) {
                    1 -> UnknownFeature(null, parts[0])
                    2 -> UnknownFeature(parts[0], parts[1])
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
