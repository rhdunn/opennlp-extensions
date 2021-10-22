// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank.features.nominal

import opennlp.ext.conll.treebank.Feature
import opennlp.ext.conll.treebank.FeatureSet
import opennlp.ext.conll.treebank.features.UnknownFeatureValue

// Reference: [NounClass](https://universaldependencies.org/u/feat/NounClass.html)
enum class NounClass : Feature {
    Bantu1, Bantu2, Bantu3, Bantu4, Bantu5, Bantu6, Bantu7, Bantu8, Bantu9, Bantu10,
    Bantu11, Bantu12, Bantu13, Bantu14, Bantu15, Bantu16, Bantu17, Bantu18, Bantu19, Bantu20,
    Bantu21, Bantu22, Bantu23,
    Wol1, Wol2, Wol3, Wol4, Wol5, Wol6, Wol7, Wol8, Wol9, Wol10,
    Wol11, Wol12;

    override val type: String = "NounClass"
    override val value: String = name

    override fun toString(): String = "$type=$value"

    companion object : FeatureSet {
        override val type: String = "NounClass"

        override fun create(value: String): Feature {
            return values().find { it.value == value } ?: UnknownFeatureValue(type, value)
        }
    }
}
