// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.train.app

import opennlp.ext.conll.stream.sample.TokenSampleStream
import opennlp.ext.train.properties.properties
import opennlp.ext.train.properties.tokenizerFactory
import opennlp.ext.train.properties.trainingParameters
import opennlp.tools.tokenize.TokenizerME
import java.io.File

fun main(args: Array<String>) {
    if (args.size < 2) {
        println("usage: TRAINING_FILE OUTPUT_MODEL_FILE PROPERTIES_FILE")
        println("usage: TRAINING_FILE OUTPUT_MODEL_FILE")
        return
    }

    val samples = TokenSampleStream.load(args[0])
    val outputModelPath = File(args[1])
    val properties = properties(args.getOrNull(2))

    val factory = tokenizerFactory(properties)
    val params = trainingParameters(properties)
    val model = TokenizerME.train(samples, factory, params)

    outputModelPath.parentFile.mkdirs()
    model.serialize(outputModelPath)
}
