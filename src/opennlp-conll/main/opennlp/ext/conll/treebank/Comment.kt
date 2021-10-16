// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

// Reference: [CoNLL-U Format](https://universaldependencies.org/format.html)
data class Comment(val value: String) {
    companion object {
        fun parse(line: String): Comment {
            val text = line.substringAfter('#').trim()
            return Comment(text)
        }
    }
}
