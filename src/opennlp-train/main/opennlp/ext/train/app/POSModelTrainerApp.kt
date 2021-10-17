// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.train.app

import opennlp.ext.conll.stream.sample.POSSampleStream
import opennlp.ext.train.properties.*
import opennlp.tools.postag.POSTaggerME
import java.io.File

fun main(args: Array<String>) {
    if (args.size < 2) {
        println("usage: TRAINING_FILE OUTPUT_MODEL_FILE PROPERTIES_FILE")
        println("usage: TRAINING_FILE OUTPUT_MODEL_FILE")
        return
    }

    val properties = properties(args.getOrNull(2))
    val samples = POSSampleStream.load(args[0], properties)
    val outputModelPath = File(args[1])

    val language = language(properties)
    val factory = posTaggerFactory(properties)
    val params = trainingParameters(properties)
    val model = POSTaggerME.train(language, samples, params, factory)

    outputModelPath.parentFile.mkdirs()
    model.serialize(outputModelPath)
}
