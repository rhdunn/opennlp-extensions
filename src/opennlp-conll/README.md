# opennlp-conll
This is a collection of classes for working with CoNLL-X and CoNLL-U treebanks.

The `opennlp.ext.conll.treebank` package contains the data model classes for
CoNLL based treebanks. It provides a unified model between the data in
CoNLL-X and CoNLL-U files.

The `opennlp.ext.conll.stream.sentence` package contains parsers for the
different treebank formats that provide OpenNLP `ObjectStream` views over the
treebank sentences.
1. `ConllxSentenceStream` is a [CoNLL-X](../../docs/format/conllx.md) file parser.
2. `ConlluSentenceStream` is a [CoNLL-U](../../docs/format/conllu.md) file parser.
3. `SentenceStream` is a helper class for constructing a given sentence stream
   in the specified format from a variety of sources.

The following properties are supported by the sentence streams:

| Parameter     | Type          | Values          | Default |
|---------------|---------------|-----------------|---------|
| `upos.tagset` | `enumeration` | `upos`, `upenn` | `upos`  |
| `xpos.tagset` | `enumeration` | `upos`, `upenn` | `upenn` |

## Sentence Sample Stream
The `opennlp.ext.conll.stream.sample.SentenceSampleStream` class creates
`SentenceSample` values from treebank sentences that can be used to train
a `SentenceModel`.

The following properties are supported by the `SentenceSampleStream` class:

| Parameter              | Type      | Values | Default |
|------------------------|-----------|--------|---------|
| `sentences.per.sample` | `integer` |        | 5       |

## Token Sample Stream
The `opennlp.ext.conll.stream.sample.TokenSampleStream` class creates
`TokenSample` values from treebank sentences that can be used to train
a `TokenizerModel`.

The following properties are supported by the `TokenSampleStream` class:

| Parameter           | Type          | Values          | Default  |
|---------------------|---------------|-----------------|----------|
| `multi.token.words` | `enumeration` | `split`, `join` | `split`  |

## Part of Speech Sample Stream
The `opennlp.ext.conll.stream.sample.POSSampleStream` class creates `POSSample`
values from treebank sentences that can be used to train a `POSModel`.

The following properties are supported by the `POSSampleStream` class:

| Parameter           | Type          | Values                              | Default |
|---------------------|---------------|-------------------------------------|---------|
| `multi.token.words` | `enumeration` | `split`, `join`                     | `split` |
| `pos.tagset`        | `enumeration` | See [pos.tagset](#postagset).       | `UPOS`  |

## Lemma Sample Stream
The `opennlp.ext.conll.stream.sample.LemmaSampleStream` class creates `LemmaSample`
values from treebank sentences that can be used to train a `LemmaModel`.

The following properties are supported by the `LemmaSampleStream` class:

| Parameter           | Type          | Values                              | Default |
|---------------------|---------------|-------------------------------------|---------|
| `multi.token.words` | `enumeration` | `split`, `join`                     | `split` |
| `pos.tagset`        | `enumeration` | See [pos.tagset](#postagset).       | `UPOS`  |

### pos.tagset
The `pos.tagset` property can be one of the following enumeration values:
1. `UPOS`, or `CPOSTAG` -- The universal/coarse part of speech tag.
2. `XPOS`, or `POSTAG` -- The language-specific part of speech tag.
3. `UPOS-PTB`, or `CPOSTAG-PTB` -- The universal part of speech tag followed by the PTB (Penn TreeBank)
   part of speech tag separated by a hyphen, e.g. `DET-DT`.
