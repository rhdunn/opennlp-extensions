# CoNLL-U Format

The file is UTF-8 encoded with posix line endings (`LF`) and consists of one or
more *sentences*.

A *comment* is a line starting with a `#` character.

## Sentences
A *sentence* is a sequence of *word lines*.

Sentences are separated by a single blank line.

Sentences may have comments associated with them.

A *metadata comment* is a sentence comment of the form `# name = value`. The
following metadata comments are supported:
1. `sent_id` &ndash; the sentence ID unique across the treebank;
2. `text` &ndash; the text of the sentence;
3. `text_[lang]` &ndash; the text of the sentence translated to `lang`;
4. `translit` &ndash; the transliteration of the sentence.

A *document boundary comment* can either be a block comment of the form
`# newdoc`, or a *metadata comment* with a name of `newdoc id`.

A *paragraph boundary comment* can either be a block comment of the form
`# newpar`, or a *metadata comment* with a name of `newpar id`.

## Word Lines
| Index | Name    | Format               | Description |
|-------|---------|----------------------|-------------|
| 1     | ID      | token id             | The identifier for the token in the current sentence. |
| 2     | FORM    | string               | The text value of the token. |
| 3     | LEMMA   | string               | The lemma associated with the FORM string. |
| 4     | UPOS    | string               | The universal part of speech tag of the token. |
| 5     | XPOS    | string               | The language specific part of speech tag of the token. |
| 6     | FEATS   | features             | The set of syntactic and morphological features for the token. |
| 7     | HEAD    | token id             | The head of the *dependency relation*. |
| 8     | DEPREL  | string               | The relationship type of the *dependency relation*. |
| 9     | DEPS    | dependency relations | The head of the *projected dependency relation*. |
| 10    | MISC    | features             | The relationship type of the *projected dependency relation*. |

A *token id* can either be:
1. a *token counter* &ndash; an integer value that starts at 1 for the first token in the
   sentence and increases by one for each new token;
2. a *token range* &ndash; a pair of integers of the form `start-end`;
3. an *empty node token counter* &ndash; a pair of integers of the form `counterId.nodeId`.

A *features* list is a `|` separated unordered set of *feature* values.

A *feature* is a `Name=Value` construct where `Name` is the name of the feature,
and `Value` is its corresponding value. __Example:__ `Tense=Past`. A feature in
the `MISC` field can omit the name and just be a value.

A *dependency relations* list is a `|` separated set of *dependency relation* values
of the form `HEAD:DEPREL`, ordered by the `HEAD` component.

The *dependency relation* is the corresponding `HEAD` and `DEPREL` pair of values.

## References
* *CoNLL-U Format*. See [https://universaldependencies.org/format.html]().
