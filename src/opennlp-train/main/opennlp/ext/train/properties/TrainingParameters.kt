// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.train.properties

import opennlp.tools.util.TrainingParameters
import java.util.*

fun trainingParameters(properties: Properties): TrainingParameters {
    val params = TrainingParameters()
    params.put(TrainingParameters.ALGORITHM_PARAM, algorithm(properties))
    return params
}

private const val ALGORITHM: String = "training.algorithm" // string -- one of: "maxent" | "perceptron"
private const val ALGORITHM_DEFAULT: String = "maxent"

fun algorithm(properties: Properties): String {
    val value = properties.getOrDefault(ALGORITHM, ALGORITHM_DEFAULT) as String
    return value.uppercase()
}
