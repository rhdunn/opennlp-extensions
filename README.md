# OpenNLP Extensions
This is a set of extensions for working with OpenNLP.
1. [opennlp-conll](#opennlp-conll) is a collection of classes for working
   with CoNLL-X and CoNLL-U treebanks.
2. [opennlp-train](#opennlp-train) is a collection of helper classes and
   functions for training different OpenNLP models.

These extensions provide several [Training Applications](#training-applications)
for creating the different OpenNLP model objects using the supported treebank
files and configuration properties.

## opennlp-conll
This is a collection of classes for working with CoNLL-X and CoNLL-U treebanks.

The `opennlp.ext.conll.treebank` package contains the data model classes for
CoNLL based treebanks. It provides a unified model between the data in
CoNLL-X and CoNLL-U files.

The `opennlp.ext.conll.stream.sentence` package contains parsers for the
different treebank formats that provide OpenNLP `ObjectStream` views over the
treebank sentences.
1. `ConllxSentenceStream` is a CoNLL-X file parser.
2. `ConlluSentenceStream` is a CoNLL-U file parser.
3. `SentenceStream` is a helper class for constructing a given sentence stream
   in the specified format from a variety of sources.

### Token Sample Stream
The `opennlp.ext.conll.stream.sample.TokenSampleStream` class creates
`TokenSample` values from treebank sentences that can be used to train
a `TokenizerModel`.

The following properties are supported by the `TokenSampleStream` class:

| Parameter           | Type          | Values          | Default  |
|---------------------|---------------|-----------------|----------|
| `multi.token.words` | `enumeration` | `split`, `join` | `split`  |

### Part of Speech Sample Stream
The `opennlp.ext.conll.stream.sample.POSSampleStream` class creates `POSSample`
values from treebank sentences that can be used to train a `POSModel`.

The following properties are supported by the `POSSampleStream` class:

| Parameter           | Type          | Values                              | Default |
|---------------------|---------------|-------------------------------------|---------|
| `multi.token.words` | `enumeration` | `split`, `join`                     | `split` |
| `pos.tagset`        | `enumeration` | `UPOS`, `XPOS`, `CPOSTAG`, `POSTAG` | `UPOS`  |

### Lemma Sample Stream
The `opennlp.ext.conll.stream.sample.LemmaSampleStream` class creates `LemmaSample`
values from treebank sentences that can be used to train a `LemmaModel`.

The following properties are supported by the `LemmaSampleStream` class:

| Parameter           | Type          | Values                              | Default |
|---------------------|---------------|-------------------------------------|---------|
| `multi.token.words` | `enumeration` | `split`, `join`                     | `split` |
| `pos.tagset`        | `enumeration` | `UPOS`, `XPOS`, `CPOSTAG`, `POSTAG` | `UPOS`  |

## opennlp-train
This is a collection of helper classes and functions for training OpenNLP
models.

### Training Parameters
The following properties are supported for creating `TrainingParameters`
objects:

| Parameter             | Type          | Values                 | Default  |
|-----------------------|---------------|------------------------|----------|
| `training.algorithm`  | `enumeration` | `maxent`, `perceptron` | `maxent` |
| `training.iterations` | `integer`     |                        | 100      |
| `training.cutoff`     | `integer`     |                        | 5        |
| `training.threads`    | `integer`     |                        | 4        |

### Tokenizer Factory
The following properties are supported for creating `TokenizerFactory` objects:

| Parameter                   | Type      | Values          | Default          |
|-----------------------------|-----------|-----------------|------------------|
| `language`                  | `string`  | ISO 639         | `en`             |
| `abbreviations.dictionary`  | `path`    |                 |                  |
| `alphanumeric.optimization` | `boolean` | `true`, `false` | `false`          |
| `alphanumeric.pattern`      | `regex`   |                 | `^[A-Za-z0-9]+$` |

### Part of Speech Tagger Factory
The following properties are supported for creating `POSTaggerFactory` objects:

| Parameter         | Type   | Values | Default |
|-------------------|--------|--------|---------|
| `pos.dictionary`  | `path` |        |         |

## Training Applications
The training applications are located in`opennlp.ext.train.app`.
1. `POSModelTrainerAppKt` trains a `POSModel`.
2. `TokenizerModelTrainerAppKt` trains a `TokenizerModel`.

The applications have the following command line arguments:

    TRAINING_FILE OUTPUT_MODEL_FILE PROPERTIES_FILE
    TRAINING_FILE OUTPUT_MODEL_FILE

The `TRAINING_FILE` has one of the following supported extensions:
1. `conllx` for CoNLL-X treebank files;
2. `conllu` for CoNLL-U treebank files.

The `OUTPUT_MODEL_FILE` is the name of the file to save the created model to,
e.g. `en-tokens.bin`. Any missing directories will be created.

The `PROPERTIES_FILE` is a Java properties file (`property=value` lines). Each
application supports different sets of properies.
1. `POSModelTrainerAppKt` supports [Training Parameters](#training-parameters),
   [Part of Speech Tagger Factory](#part-of-speech-tagger-factory), and
   [Part of Speech Sample Stream](#part-of-speech-sample-stream) parameters.
2. `TokenizerModelTrainerAppKt` supports [Training Parameters](#training-parameters),
   [Tokenizer Factory](#tokenizer-factory), and
   [Token Sample Stream](#token-sample-stream) parameters.

## License
Copyright (C) 2021 Reece H. Dunn

SPDX-License-Identifier: Apache-2.0
