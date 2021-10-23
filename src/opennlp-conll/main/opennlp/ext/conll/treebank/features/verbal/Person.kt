// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank.features.verbal

import opennlp.ext.conll.treebank.Feature
import opennlp.ext.conll.treebank.FeatureSet
import opennlp.ext.conll.treebank.features.UnknownFeatureValue

// Reference: [Person](https://universaldependencies.org/u/feat/Person.html)
enum class Person(override val value: String) : Feature {
    Zero("0"), First("1"), Second("2"), Third("3"), Fourth("4");

    override val type: String = "Person"

    override fun toString(): String = "$type=$value"

    companion object : FeatureSet {
        override val type: String = "Person"

        override fun create(value: String): Feature {
            return values().find { it.value == value } ?: UnknownFeatureValue(type, value)
        }
    }
}
