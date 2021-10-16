# OpenNLP Extensions
This is a set of extensions for working with OpenNLP.

## opennlp-conll
This is a collection of classes for working with CoNLL-X and CoNLL-U files.

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

## License
Copyright (C) 2021 Reece H. Dunn

SPDX-License-Identifier: Apache-2.0
