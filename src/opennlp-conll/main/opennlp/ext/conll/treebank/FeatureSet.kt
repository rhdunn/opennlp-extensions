// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

interface FeatureSet {
    val type: String

    fun create(value: String): Feature
}
