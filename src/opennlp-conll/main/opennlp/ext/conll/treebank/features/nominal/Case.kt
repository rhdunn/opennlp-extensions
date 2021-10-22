// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank.features.nominal

import opennlp.ext.conll.treebank.Feature
import opennlp.ext.conll.treebank.FeatureSet
import opennlp.ext.conll.treebank.features.UnknownFeatureValue

// Reference: [Case](https://universaldependencies.org/u/feat/Case.html)
enum class Case : Feature {
    Abs, Acc, Erg, Nom, // values
    Abe, Ben, Cau, Cmp, Cns, Com, Dat, Dis, Equ, Gen, Ins, Par, Tem, Tra, Voc, // non-core
    Abl, Add, Ade, All, Del, Ela, Ess, Ill, Ine, Lat, Loc, Per, Sub, Sup, Ter; // local

    override val type: String = "Case"
    override val value: String = name

    override fun toString(): String = "$type=$value"

    companion object : FeatureSet {
        override val type: String = "Case"

        override fun create(value: String): Feature {
            return values().find { it.value == value } ?: UnknownFeatureValue(type, value)
        }
    }
}
