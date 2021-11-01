// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.stream

import opennlp.tools.util.ObjectStream

fun <T> ObjectStream<T>.forEach(action: (T) -> Unit) {
    var item: T? = read()
    while (item != null) {
        action(item)
        item = read()
    }
}

fun <T> ObjectStream<T>.forEachIndexed(action: (index: Int, T) -> Unit) {
    var index = 0
    var item: T? = read()
    while (item != null) {
        action(index++, item)
        item = read()
    }
}
