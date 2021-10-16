// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank

// Reference: [CoNLL-U Format](https://universaldependencies.org/format.html)
data class Comment(val name: String?, val value: String) {
    companion object {
        fun parse(line: String): Comment {
            val parts = line.substring(1).trim().split(RE_EQUALS)
            return when (parts.size) {
                2 -> Comment(parts[0], parts[1])
                else -> Comment(null, parts[0])
            }
        }

        private val RE_EQUALS = "\\s*=\\s*".toRegex()
    }
}
