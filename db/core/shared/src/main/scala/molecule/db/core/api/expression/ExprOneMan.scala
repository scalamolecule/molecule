package molecule.db.core.api.expression

import molecule.base.metaModel.CardOne
import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.qm
import molecule.db.core.api.*


trait ExprOneManOps[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends FilterAttr[Tpl, T, This, Next] {
  protected def _exprOneMan(
    op: Op, vs: Seq[T], binding: Boolean = false
  ): This[Tpl, T] & SortAttrs[Tpl, T, This] & CardOne = ???
}

trait ExprOneMan[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprOneManOps[Tpl, T, This, Next]
    with Aggregates[Tpl, T, This]
    with SortAttrs[Tpl, T, This]
    {
  def apply(                ) = _exprOneMan(NoValue, Nil         )
  def apply(v    : T, vs: T*) = _exprOneMan(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[T]   ) = _exprOneMan(Eq     , vs          )
  def not  (v    : T, vs: T*) = _exprOneMan(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[T]   ) = _exprOneMan(Neq    , vs          )
  def <    (upper: T        ) = _exprOneMan(Lt     , Seq(upper)  )
  def <=   (upper: T        ) = _exprOneMan(Le     , Seq(upper)  )
  def >    (lower: T        ) = _exprOneMan(Gt     , Seq(lower)  )
  def >=   (lower: T        ) = _exprOneMan(Ge     , Seq(lower)  )

  def apply(v    : qm) = _exprOneMan(Eq , Nil, true)
  def not  (v    : qm) = _exprOneMan(Neq, Nil, true)
  def <    (upper: qm) = _exprOneMan(Lt , Nil, true)
  def <=   (upper: qm) = _exprOneMan(Le , Nil, true)
  def >    (lower: qm) = _exprOneMan(Gt , Nil, true)
  def >=   (lower: qm) = _exprOneMan(Ge , Nil, true)

  def apply(a: Molecule_0 & CardOne) = _filterAttrManTac(Eq , a)
  def not  (a: Molecule_0 & CardOne) = _filterAttrManTac(Neq, a)
  def <    (a: Molecule_0 & CardOne) = _filterAttrManTac(Lt , a)
  def <=   (a: Molecule_0 & CardOne) = _filterAttrManTac(Le , a)
  def >    (a: Molecule_0 & CardOne) = _filterAttrManTac(Gt , a)
  def >=   (a: Molecule_0 & CardOne) = _filterAttrManTac(Ge , a)

  def apply[ns[_ <: Tuple, _]](a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = _filterAttrManMan(Eq , a)
  def not  [ns[_ <: Tuple, _]](a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = _filterAttrManMan(Neq, a)
  def <    [ns[_ <: Tuple, _]](a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = _filterAttrManMan(Lt , a)
  def <=   [ns[_ <: Tuple, _]](a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = _filterAttrManMan(Le , a)
  def >    [ns[_ <: Tuple, _]](a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = _filterAttrManMan(Gt , a)
  def >=   [ns[_ <: Tuple, _]](a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = _filterAttrManMan(Ge , a)
}

trait ExprOneMan_String[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprOneMan[Tpl, T, This, Next] {
  def startsWith(prefix: T)                = _exprOneMan(StartsWith                  , Seq(prefix)            )
  def endsWith  (suffix: T)                = _exprOneMan(EndsWith                    , Seq(suffix)            )
  def contains  (needle: T)                = _exprOneMan(Contains                    , Seq(needle)            )
  def matches   (regex : T)                = _exprOneMan(Matches                     , Seq(regex)             )
  def +         (str   : T)                = _exprOneMan(AttrOp.Append               , Seq(str)               )
  def prepend   (str   : T)                = _exprOneMan(AttrOp.Prepend              , Seq(str)               )
  def substring (start: Int, end: Int)     = _exprOneMan(AttrOp.SubString(start, end), Nil                    )
  def replaceAll(regex: T, replacement: T) = _exprOneMan(AttrOp.ReplaceAll           , Seq(regex, replacement))
  def toLower                              = _exprOneMan(AttrOp.ToLower              , Nil                    )
  def toUpper                              = _exprOneMan(AttrOp.ToUpper              , Nil                    )

  def startsWith(prefix: qm) = _exprOneMan(StartsWith, Nil, true)
  def endsWith  (suffix: qm) = _exprOneMan(EndsWith  , Nil, true)
  def contains  (needle: qm) = _exprOneMan(Contains  , Nil, true)
  def matches   (regex : qm) = _exprOneMan(Matches   , Nil, true)
}

trait ExprOneMan_Enum[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprOneManOps[Tpl, T, This, Next]
    with SortAttrs[Tpl, T, This] {
  def apply(             ) = _exprOneMan(NoValue, Nil                                      )
  def apply(v : T, vs: T*) = _exprOneMan(Eq     , (v +: vs).map(_.toString.asInstanceOf[T]))
  def apply(vs: Seq[T]   ) = _exprOneMan(Eq     , vs       .map(_.toString.asInstanceOf[T]))
  def not  (v : T, vs: T*) = _exprOneMan(Neq    , (v +: vs).map(_.toString.asInstanceOf[T]))
  def not  (vs: Seq[T]   ) = _exprOneMan(Neq    , vs       .map(_.toString.asInstanceOf[T]))

  def apply(v : qm) = _exprOneMan(Eq , Nil, true)
  def not  (v : qm) = _exprOneMan(Neq, Nil, true)
}

trait ExprOneMan_Integer[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprOneMan_Number[Tpl, T, This, Next] {
  def even                        = _exprOneMan(Even         , Nil                    )
  def odd                         = _exprOneMan(Odd          , Nil                    )
  def %(divider: T, remainder: T) = _exprOneMan(Remainder    , Seq(divider, remainder))
}

trait ExprOneMan_Decimal[Tpl <: Tuple, t, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprOneMan_Number[Tpl, t, This, Next] {
  def ceil  = _exprOneMan(AttrOp.Ceil  , Nil)
  def floor = _exprOneMan(AttrOp.Floor , Nil)
}

trait ExprOneMan_Number[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprOneMan[Tpl, T, This, Next] {
  def +(v: T) = _exprOneMan(AttrOp.Plus  , Seq(v))
  def -(v: T) = _exprOneMan(AttrOp.Minus , Seq(v))
  def *(v: T) = _exprOneMan(AttrOp.Times , Seq(v))
  def /(v: T) = _exprOneMan(AttrOp.Divide, Seq(v))
  def negate  = _exprOneMan(AttrOp.Negate, Nil   )
  def abs     = _exprOneMan(AttrOp.Abs   , Nil   )
  def absNeg  = _exprOneMan(AttrOp.AbsNeg, Nil   )
}

trait ExprOneMan_Boolean[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprOneMan[Tpl, T, This, Next] {
  def &&(bool: T) = _exprOneMan(AttrOp.And, Seq(bool))
  def ||(bool: T) = _exprOneMan(AttrOp.Or , Seq(bool))
  def !           = _exprOneMan(AttrOp.Not, Nil      )
}

