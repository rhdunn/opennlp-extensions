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
