// Copyright (C) 2021 Reece H. Dunn. SPDX-License-Identifier: Apache-2.0
package opennlp.ext.conll.treebank.pos.tags

import opennlp.ext.conll.treebank.pos.PosTag
import opennlp.ext.conll.treebank.pos.PosTagset
import opennlp.ext.conll.treebank.pos.WordClass

@Suppress("MemberVisibilityCanBePrivate")
enum class UPosPennTags(val upos: PosTag, val xpos: PosTag) : PosTag {
// region adjectives
    ADJ_AFX(UPosTags.ADJ, UPennTags.AFX),
    ADJ_FW(UPosTags.ADJ, UPennTags.FW),
    ADJ_GW(UPosTags.ADJ, UPennTags.GW),
    ADJ_JJ(UPosTags.ADJ, UPennTags.JJ),
    ADJ_JJR(UPosTags.ADJ, UPennTags.JJR),
    ADJ_JJS(UPosTags.ADJ, UPennTags.JJS),
    ADJ_NN(UPosTags.ADJ, UPennTags.NN),
    ADJ_NNP(UPosTags.ADJ, UPennTags.NNP),
// endregion
// region adpositions
    ADP_GW(UPosTags.ADP, UPennTags.GW),
    ADP_IN(UPosTags.ADP, UPennTags.IN),
    ADP_NNP(UPosTags.ADP, UPennTags.NNP),
    ADP_RB(UPosTags.ADP, UPennTags.RB),
    ADP_RP(UPosTags.ADP, UPennTags.RP),
    ADP_TO(UPosTags.ADP, UPennTags.TO),
// endregion
// region adverbs
    ADV_AFX(UPosTags.ADV, UPennTags.AFX),
    ADV_CC(UPosTags.ADV, UPennTags.CC),
    ADV_FW(UPosTags.ADV, UPennTags.FW),
    ADV_GW(UPosTags.ADV, UPennTags.GW),
    ADV_IN(UPosTags.ADV, UPennTags.IN),
    ADV_NNP(UPosTags.ADV, UPennTags.NNP),
    ADV_RB(UPosTags.ADV, UPennTags.RB),
    ADV_RBR(UPosTags.ADV, UPennTags.RBR),
    ADV_RBS(UPosTags.ADV, UPennTags.RBS),
    ADV_RP(UPosTags.ADV, UPennTags.RP),
    ADV_WRB(UPosTags.ADV, UPennTags.WRB),
// endregion
// region auxiliary
    AUX_MD(UPosTags.AUX, UPennTags.MD),
    AUX_VB(UPosTags.AUX, UPennTags.VB),
    AUX_VBD(UPosTags.AUX, UPennTags.VBD),
    AUX_VBG(UPosTags.AUX, UPennTags.VBG),
    AUX_VBN(UPosTags.AUX, UPennTags.VBN),
    AUX_VBP(UPosTags.AUX, UPennTags.VBP),
    AUX_VBZ(UPosTags.AUX, UPennTags.VBZ),
// endregion
// region coordinating conjunction
    CCONJ_CC(UPosTags.CCONJ, UPennTags.CC),
// endregion
// region determiner
    DET_DT(UPosTags.DET, UPennTags.DT),
    DET_PDT(UPosTags.DET, UPennTags.PDT),
    DET_WDT(UPosTags.DET, UPennTags.WDT),
// endregion
// region interjection
    INTJ_UH(UPosTags.INTJ, UPennTags.UH),
// endregion
// region noun
    NOUN_AFX(UPosTags.NOUN, UPennTags.AFX),
    NOUN_GW(UPosTags.NOUN, UPennTags.GW),
    NOUN_NN(UPosTags.NOUN, UPennTags.NN),
    NOUN_NNP(UPosTags.NOUN, UPennTags.NNP),
    NOUN_NNS(UPosTags.NOUN, UPennTags.NNS),
    NOUN_VBG(UPosTags.NOUN, UPennTags.VBG),
// endregion
// region numeral
    NUM_CD(UPosTags.NUM, UPennTags.CD),
    NUM_GW(UPosTags.NUM, UPennTags.GW),
    NUM_LS(UPosTags.NUM, UPennTags.LS),
    NUM_NN(UPosTags.NUM, UPennTags.NN),
    NUM_NNP(UPosTags.NUM, UPennTags.NNP),
// endregion
// region particle
    PART_POS(UPosTags.PART, UPennTags.POS),
    PART_RB(UPosTags.PART, UPennTags.RB),
    PART_TO(UPosTags.PART, UPennTags.TO),
// endregion
// region pronoun
    PRON_DT(UPosTags.PRON, UPennTags.DT),
    PRON_EX(UPosTags.PRON, UPennTags.EX),
    PRON_GW(UPosTags.PRON, UPennTags.GW),
    PRON_NN(UPosTags.PRON, UPennTags.NN),
    PRON_PRP(UPosTags.PRON, UPennTags.PRP),
    PRON_PRPS(UPosTags.PRON, UPennTags.PRPS),
    PRON_WDT(UPosTags.PRON, UPennTags.WDT),
    PRON_WP(UPosTags.PRON, UPennTags.WP),
    PRON_WPS(UPosTags.PRON, UPennTags.WPS),
// endregion
// region proper noun
    PROPN_NNP(UPosTags.PROPN, UPennTags.NNP),
    PROPN_NNPS(UPosTags.PROPN, UPennTags.NNPS),
// endregion
// region punctuation
    PUNCT_COLON(UPosTags.PUNCT, UPennTags.COLON),
    PUNCT_COMMA(UPosTags.PUNCT, UPennTags.COMMA),
    PUNCT_GW(UPosTags.PUNCT, UPennTags.GW),
    PUNCT_HYPH(UPosTags.PUNCT, UPennTags.HYPH),
    PUNCT_LQUO(UPosTags.PUNCT, UPennTags.LQUO),
    PUNCT_LRB(UPosTags.PUNCT, UPennTags.LRB),
    PUNCT_NFP(UPosTags.PUNCT, UPennTags.NFP),
    PUNCT_RQUO(UPosTags.PUNCT, UPennTags.RQUO),
    PUNCT_RBR(UPosTags.PUNCT, UPennTags.RBR),
    PUNCT_SENT(UPosTags.PUNCT, UPennTags.SENT),
    PUNCT_SYM(UPosTags.PUNCT, UPennTags.SYM),
// endregion
// region subordinating conjunction
    SCONJ_GW(UPosTags.SCONJ, UPennTags.GW),
    SCONJ_IN(UPosTags.SCONJ, UPennTags.IN),
    SCONJ_WRB(UPosTags.SCONJ, UPennTags.WRB),
// endregion
// region symbol
    SYM_DOLLOR(UPosTags.SYM, UPennTags.DOLLAR),
    SYM_COMMA(UPosTags.SYM, UPennTags.COMMA),
    SYM_HYPH(UPosTags.SYM, UPennTags.HYPH),
    SYM_IN(UPosTags.SYM, UPennTags.IN),
    SYM_NFP(UPosTags.SYM, UPennTags.NFP),
    SYM_NN(UPosTags.SYM, UPennTags.NN),
    SYM_NNS(UPosTags.SYM, UPennTags.NNS),
    SYM_SYM(UPosTags.SYM, UPennTags.SYM),
    SYM_UH(UPosTags.SYM, UPennTags.UH),
// endregion
// region verb
    VERB_GW(UPosTags.VERB, UPennTags.GW),
    VERB_NNP(UPosTags.VERB, UPennTags.NNP),
    VERB_NNS(UPosTags.VERB, UPennTags.NNS),
    VERB_VB(UPosTags.VERB, UPennTags.VB),
    VERB_VBD(UPosTags.VERB, UPennTags.VBD),
    VERB_VBG(UPosTags.VERB, UPennTags.VBG),
    VERB_VBN(UPosTags.VERB, UPennTags.VBN),
    VERB_VBP(UPosTags.VERB, UPennTags.VBP),
    VERB_VBZ(UPosTags.VERB, UPennTags.VBZ),
// endregion
// region other
    X_DOLLAR(UPosTags.X, UPennTags.DOLLAR),
    X_ADD(UPosTags.X, UPennTags.ADD),
    X_AFX(UPosTags.X, UPennTags.AFX),
    X_FW(UPosTags.X, UPennTags.FW),
    X_GW(UPosTags.X, UPennTags.GW),
    X_HYPH(UPosTags.X, UPennTags.HYPH),
    X_IN(UPosTags.X, UPennTags.IN),
    X_JJ(UPosTags.X, UPennTags.JJ),
    X_LS(UPosTags.X, UPennTags.LS),
    X_NN(UPosTags.X, UPennTags.NN),
    X_NNP(UPosTags.X, UPennTags.NNP),
    X_PRP(UPosTags.X, UPennTags.PRP),
    X_RB(UPosTags.X, UPennTags.RB),
    X_VB(UPosTags.X, UPennTags.VB),
    X_WP(UPosTags.X, UPennTags.WP),
    X_WRB(UPosTags.X, UPennTags.WRB),
    X_XX(UPosTags.X, UPennTags.XX);
// endregion

    override val tag: String by lazy { "$upos-$xpos" }

    override val label: String by lazy { "$upos ($xpos)" }

    override val wordClass: WordClass
        get() = xpos.wordClass

    override fun toString(): String = tag

    companion object : PosTagset {
        override fun get(tag: String): PosTag? = when (tag) {
            "_" -> null
            else -> values().find { it.tag == tag } ?: PosTag.Unknown(tag)
        }
    }
}
