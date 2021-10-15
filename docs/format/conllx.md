# CoNLL-X Format

The file is UTF-8 encoded and consists of one or more *sentences*.

## Sentence
A *sentence* is a sequence of *word lines*.

Sentences are separated by a single blank line.

## Word Line
| Index | Name    | Format   | Description |
|-------|---------|----------|-------------|
| 1     | ID      | token id | The identifier for the token in the current sentence. |
| 2     | FORM    | string   | The text value of the token. |
| 3     | LEMMA   | string   | The lemma associated with the FORM string. |
| 4     | CPOSTAG | string   | The coarse part of speech tag of the token. |
| 5     | POSTAG  | string   | The fine-grained language specific part of speech tag of the token. |
| 6     | FEATS   | features | The set of syntactic and morphological features for the token. |
| 7     | HEAD    | integer  | The head of the *dependency relation*. |
| 8     | DEPREL  | string   | The relationship type of the *dependency relation*. |
| 9     | PHEAD   | integer  | The head of the *projected dependency relation*. |
| 10    | PDEPREL | string   | The relationship type of the *projected dependency relation*. |

A *token id* is an integer value that starts at 1 for the first token in the
sentence and increases by one for each new token.

A *features* list is a `|` separated unordered set of feature values.

A *feature* is a `Name=Value` construct where `Name` is the name of the feature,
and `Value` is its corresponding value. __Example:__ `Tense=Past`.

The *dependency relation* is the corresponding `HEAD` and `DEPREL` pair of values.

The *projected dependency relation* is the corresponding `PHEAD` and `PDEPREL`
pair of values.

## References
* *CoNLL-X Format*. See [https://ilk.uvt.nl/~emarsi/download/pubs/14964.pdf]().
