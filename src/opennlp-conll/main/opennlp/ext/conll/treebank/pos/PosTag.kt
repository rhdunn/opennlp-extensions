// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank.pos

interface PosTag {
    val tag: String
    val label: String
    val wordClass: WordClass

    data class Unknown(override val tag: String) : PosTag {
        override val label: String = tag
        override val wordClass: WordClass = WordClass.Other

        override fun toString(): String = tag
    }
}
