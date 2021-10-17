// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.train.app

import opennlp.ext.conll.stream.sample.SentenceSampleStream
import opennlp.ext.train.properties.language
import opennlp.ext.train.properties.properties
import opennlp.ext.train.properties.sentenceDetectorFactory
import opennlp.ext.train.properties.trainingParameters
import opennlp.tools.sentdetect.SentenceDetectorME
import java.io.File

fun main(args: Array<String>) {
    if (args.size < 2) {
        println("usage: TRAINING_FILE OUTPUT_MODEL_FILE PROPERTIES_FILE")
        println("usage: TRAINING_FILE OUTPUT_MODEL_FILE")
        return
    }

    val properties = properties(args.getOrNull(2))
    val samples = SentenceSampleStream.load(args[0], properties)
    val outputModelPath = File(args[1])

    val language = language(properties)
    val factory = sentenceDetectorFactory(properties)
    val params = trainingParameters(properties)
    val model = SentenceDetectorME.train(language, samples, factory, params)

    outputModelPath.parentFile.mkdirs()
    model.serialize(outputModelPath)
}
