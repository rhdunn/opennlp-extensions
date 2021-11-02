# opennlp-train
This is a collection of helper classes and functions for training OpenNLP
models.

## Properties

### Training Parameters
The following properties are supported for creating `TrainingParameters`
objects:

| Parameter             | Type          | Values                 | Default  |
|-----------------------|---------------|------------------------|----------|
| `training.algorithm`  | `enumeration` | `maxent`, `perceptron` | `maxent` |
| `training.iterations` | `integer`     |                        | 100      |
| `training.cutoff`     | `integer`     |                        | 5        |
| `training.threads`    | `integer`     |                        | 4        |

### Sentence Detector Factory
The following properties are supported for creating `SentenceDetectorFactory`
objects:

| Parameter                   | Type      | Values          | Default |
|-----------------------------|-----------|-----------------|---------|
| `language`                  | `string`  | ISO 639         | `en`    |
| `abbreviations.dictionary`  | `path`    |                 |         |
| `use.token.end`             | `boolean` | `true`, `false` | `false` |
| `eos.characters`            | `string`  |                 |         |

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

### Sentence Model Trainer
The following properties are supported by the
`opennlp.ext.train.app.SentenceModelTrainerAppKt` application to train a
`SentenceModel`:

| Parameter                   | Type          | Values                              | Default          |
|-----------------------------|---------------|-------------------------------------|------------------|
| `training.algorithm`        | `enumeration` | `maxent`, `perceptron`              | `maxent`         |
| `training.iterations`       | `integer`     |                                     | 100              |
| `training.cutoff`           | `integer`     |                                     | 5                |
| `training.threads`          | `integer`     |                                     | 4                |
| `language`                  | `string`      | ISO 639                             | `en`             |
| `abbreviations.dictionary`  | `path`        |                                     |                  |
| `use.token.end`             | `boolean`     | `true`, `false`                     | `false`          |
| `eos.characters`            | `string`      |                                     |                  |
| `sentences.per.sample`      | `integer`     |                                     | 5                |
| `upos.tagset`               | `enumeration` | `upos`, `upenn`                     | `upos`           |
| `xpos.tagset`               | `enumeration` | `upos`, `upenn`                     | `upenn`          |

### Tokenier Model Trainer
The following properties are supported by the
`opennlp.ext.train.app.TokeizerModelTrainerAppKt` application to train a
`TokenizerModel`:

| Parameter                   | Type          | Values                              | Default          |
|-----------------------------|---------------|-------------------------------------|------------------|
| `training.algorithm`        | `enumeration` | `maxent`, `perceptron`              | `maxent`         |
| `training.iterations`       | `integer`     |                                     | 100              |
| `training.cutoff`           | `integer`     |                                     | 5                |
| `training.threads`          | `integer`     |                                     | 4                |
| `language`                  | `string`      | ISO 639                             | `en`             |
| `abbreviations.dictionary`  | `path`        |                                     |                  |
| `alphanumeric.optimization` | `boolean`     | `true`, `false`                     | `false`          |
| `alphanumeric.pattern`      | `regex`       |                                     | `^[A-Za-z0-9]+$` |
| `multi.token.words`         | `enumeration` | `split`, `join`                     | `split`          |
| `upos.tagset`               | `enumeration` | `upos`, `upenn`                     | `upos`           |
| `xpos.tagset`               | `enumeration` | `upos`, `upenn`                     | `upenn`          |

### Part of Speech Model Trainer
The following properties are supported by the
`opennlp.ext.train.app.POSModelTrainerAppKt` application to train a `POSModel`:

| Parameter             | Type          | Values                              | Default  |
|-----------------------|---------------|-------------------------------------|----------|
| `training.algorithm`  | `enumeration` | `maxent`, `perceptron`              | `maxent` |
| `training.iterations` | `integer`     |                                     | 100      |
| `training.cutoff`     | `integer`     |                                     | 5        |
| `training.threads`    | `integer`     |                                     | 4        |
| `language`            | `string`      | ISO 639                             | `en`     |
| `pos.dictionary`      | `path`        |                                     |          |
| `multi.token.words`   | `enumeration` | `split`, `join`                     | `split`  |
| `pos.tagset`          | `enumeration` | See [pos.tagset](#postagset).       | `UPOS`   |
| `upos.tagset`         | `enumeration` | `upos`, `upenn`                     | `upos`   |
| `xpos.tagset`         | `enumeration` | `upos`, `upenn`                     | `upenn`  |

### Lemmatizer Model Trainer
The following properties are supported by the
`opennlp.ext.train.app.LemmatizerModelTrainerAppKt` application to train a
`LemmatizerModel`:

| Parameter             | Type          | Values                              | Default  |
|-----------------------|---------------|-------------------------------------|----------|
| `training.algorithm`  | `enumeration` | `maxent`, `perceptron`              | `maxent` |
| `training.iterations` | `integer`     |                                     | 100      |
| `training.cutoff`     | `integer`     |                                     | 5        |
| `training.threads`    | `integer`     |                                     | 4        |
| `language`            | `string`      | ISO 639                             | `en`     |
| `multi.token.words`   | `enumeration` | `split`, `join`                     | `split`  |
| `pos.tagset`          | `enumeration` | See [pos.tagset](#postagset).       | `UPOS`   |
| `upos.tagset`         | `enumeration` | `upos`, `upenn`                     | `upos`   |
| `xpos.tagset`         | `enumeration` | `upos`, `upenn`                     | `upenn`  |

## Enumeration Types

### pos.tagset
The `pos.tagset` property can be one of the following enumeration values:
1. `UPOS`, or `CPOSTAG` -- The universal/coarse part of speech tag.
2. `XPOS`, or `POSTAG` -- The language-specific part of speech tag.
3. `UPOS-PTB`, or `CPOSTAG-PTB` -- The universal part of speech tag followed by the PTB (Penn TreeBank)
   part of speech tag separated by a hyphen, e.g. `DET-DT`.
