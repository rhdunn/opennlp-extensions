// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank.pos

interface PosTagset {
    operator fun get(tag: String): PosTag?
}
