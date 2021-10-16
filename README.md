# OpenNLP Extensions
This is a set of extensions for working with OpenNLP.
1. [opennlp-conll](#opennlp-conll) &ndash; a collection of classes for working
   with CoNLL-X and CoNLL-U treebanks.
2. [opennlp-train](#opennlp-train) &ndash; a collection of classes for training
   different OpenNLP models.

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

The `opennlp.ext.conll.stream.sample` package contains streams for creating
different training sample streams.
1. `TokenSampleStream` creates token samples from treebank sentences.

## opennlp-train
This is a collection of classes for training different OpenNLP models.

The following properties are supported for `TrainingParameters` objects:

| Parameter             | Type      | Default  |
|-----------------------|-----------|----------|
| `training.algorithm`  | `string`  | `maxent` |
| `training.iterations` | `integer` | 100      |
| `training.cutoff`     | `integer` | 5        |
| `training.threads`    | `integer` | 4        |

The following properties are supported for `TokenizerFactory` objects:

| Parameter                   | Type      | Default  |
|-----------------------------|-----------|----------|
| `language`                  | `string`  | `en`     |
| `abbreviations.dictionary`  | `path`    |          |
| `alphanumeric.optimization` | `boolean` | `false`  |

## License
Copyright (C) 2021 Reece H. Dunn

SPDX-License-Identifier: Apache-2.0
