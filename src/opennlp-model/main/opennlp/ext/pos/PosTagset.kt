// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.pos

import opennlp.ext.pos.tags.UPennTags
import opennlp.ext.pos.tags.UPosTags

interface PosTagset {
    operator fun get(tag: String): PosTag

    class UnsupportedTagsetException(tagset: String) : RuntimeException("Unsupported POS tagset: $tagset")

    companion object {
        fun create(tagset: String): PosTagset = when (tagset) {
            "upos" -> UPosTags
            "upenn" -> UPennTags
            else -> throw UnsupportedTagsetException(tagset)
        }
    }
}
