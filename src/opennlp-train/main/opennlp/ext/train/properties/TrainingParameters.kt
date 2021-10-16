// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.train.properties

import opennlp.tools.ml.EventTrainer
import opennlp.tools.util.TrainingParameters
import java.util.*

fun trainingParameters(properties: Properties): TrainingParameters {
    val params = TrainingParameters()
    params.put(TrainingParameters.ALGORITHM_PARAM, algorithm(properties))
    params.put(TrainingParameters.TRAINER_TYPE_PARAM, EventTrainer.EVENT_VALUE)
    params.put(TrainingParameters.ITERATIONS_PARAM, iterations(properties))
    params.put(TrainingParameters.CUTOFF_PARAM, cutoff(properties))
    params.put(TrainingParameters.THREADS_PARAM, threads(properties))
    return params
}

private const val ALGORITHM: String = "training.algorithm" // string -- one of: "maxent" | "perceptron"
private const val ALGORITHM_DEFAULT: String = "maxent"

fun algorithm(properties: Properties): String {
    val value = properties.getOrDefault(ALGORITHM, ALGORITHM_DEFAULT) as String
    return value.uppercase()
}

private const val ITERATIONS: String = "training.iterations" // integer
private const val ITERATIONS_DEFAULT: String = "100"

fun iterations(properties: Properties): Int {
    val value = properties.getOrDefault(ITERATIONS, ITERATIONS_DEFAULT) as String
    return value.toInt()
}

private const val CUTOFF: String = "training.cutoff" // integer
private const val CUTOFF_DEFAULT: String = "5"

fun cutoff(properties: Properties): Int {
    val value = properties.getOrDefault(CUTOFF, CUTOFF_DEFAULT) as String
    return value.toInt()
}

private const val THREADS: String = "training.threads" // integer
private const val THREADS_DEFAULT: String = "4"

fun threads(properties: Properties): Int {
    val value = properties.getOrDefault(THREADS, THREADS_DEFAULT) as String
    return value.toInt()
}
