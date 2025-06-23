package molecule.db.core.api.expression

import molecule.base.metaModel.CardOne
import molecule.core.dataModel.*
import molecule.core.dataModel.Keywords.qm
import molecule.db.core.api.*


trait ExprOneTacOps[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends FilterAttr[Tpl, T, This, Next] {
  protected def _exprOneTac(op: Op, vs: Seq[T], binding: Boolean = false): This[Tpl, T] & CardOne = ???
}

trait ExprOneTac[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprOneTacOps[Tpl, T, This, Next] {
  def apply(                ) = _exprOneTac(NoValue, Nil         )
  def apply(v    : T, vs: T*) = _exprOneTac(Eq     , Seq(v) ++ vs)
  def apply(vs   : Seq[T]   ) = _exprOneTac(Eq     , vs          )
  def not  (v    : T, vs: T*) = _exprOneTac(Neq    , Seq(v) ++ vs)
  def not  (vs   : Seq[T]   ) = _exprOneTac(Neq    , vs          )
  def <    (upper: T        ) = _exprOneTac(Lt     , Seq(upper)  )
  def <=   (upper: T        ) = _exprOneTac(Le     , Seq(upper)  )
  def >    (lower: T        ) = _exprOneTac(Gt     , Seq(lower)  )
  def >=   (lower: T        ) = _exprOneTac(Ge     , Seq(lower)  )

  def apply(v    : qm) = _exprOneTac(Eq , Nil, true)
  def not  (v    : qm) = _exprOneTac(Neq, Nil, true)
  def <    (upper: qm) = _exprOneTac(Lt , Nil, true)
  def <=   (upper: qm) = _exprOneTac(Le , Nil, true)
  def >    (lower: qm) = _exprOneTac(Gt , Nil, true)
  def >=   (lower: qm) = _exprOneTac(Ge , Nil, true)

  def apply(a: Molecule_0 & CardOne) = _filterAttrTacTac(Eq , a)
  def not  (a: Molecule_0 & CardOne) = _filterAttrTacTac(Neq, a)
  def <    (a: Molecule_0 & CardOne) = _filterAttrTacTac(Lt , a)
  def <=   (a: Molecule_0 & CardOne) = _filterAttrTacTac(Le , a)
  def >    (a: Molecule_0 & CardOne) = _filterAttrTacTac(Gt , a)
  def >=   (a: Molecule_0 & CardOne) = _filterAttrTacTac(Ge , a)

  def apply[ns[_ <: Tuple, _]](a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = _filterAttrTacMan(Eq , a)
  def not  [ns[_ <: Tuple, _]](a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = _filterAttrTacMan(Neq, a)
  def <    [ns[_ <: Tuple, _]](a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = _filterAttrTacMan(Lt , a)
  def <=   [ns[_ <: Tuple, _]](a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = _filterAttrTacMan(Le , a)
  def >    [ns[_ <: Tuple, _]](a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = _filterAttrTacMan(Gt , a)
  def >=   [ns[_ <: Tuple, _]](a: Molecule & SortAttrsOps[Tuple1[T], T, ns]) = _filterAttrTacMan(Ge , a)
}

trait ExprOneTac_String[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprOneTac[Tpl, T, This, Next] {
  def startsWith(prefix: T) = _exprOneTac(StartsWith, Seq(prefix))
  def endsWith  (suffix: T) = _exprOneTac(EndsWith  , Seq(suffix))
  def contains  (needle: T) = _exprOneTac(Contains  , Seq(needle))
  def matches   (regex : T) = _exprOneTac(Matches   , Seq(regex) )

  def startsWith(prefix: qm) = _exprOneTac(StartsWith, Nil, true)
  def endsWith  (suffix: qm) = _exprOneTac(EndsWith  , Nil, true)
  def contains  (needle: qm) = _exprOneTac(Contains  , Nil, true)
  def matches   (regex : qm) = _exprOneTac(Matches   , Nil, true)
}

trait ExprOneTac_Enum[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprOneTacOps[Tpl, T, This, Next] {
  def apply(             ) = _exprOneTac(NoValue, Nil                                      )
  def apply(v : T, vs: T*) = _exprOneTac(Eq     , (v +: vs).map(_.toString.asInstanceOf[T]))
  def apply(vs: Seq[T]   ) = _exprOneTac(Eq     , vs       .map(_.toString.asInstanceOf[T]))
  def not  (v : T, vs: T*) = _exprOneTac(Neq    , (v +: vs).map(_.toString.asInstanceOf[T]))
  def not  (vs: Seq[T]   ) = _exprOneTac(Neq    , vs       .map(_.toString.asInstanceOf[T]))

  def apply(v : qm) = _exprOneTac(Eq , Nil, true)
  def not  (v : qm) = _exprOneTac(Neq, Nil, true)
}

trait ExprOneTac_Integer[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprOneTac[Tpl, T, This, Next] {
  def even                        = _exprOneTac(Even      , Nil                    )
  def odd                         = _exprOneTac(Odd       , Nil                    )
  def %(divider: T, remainder: T) = _exprOneTac(Remainder , Seq(divider, remainder))
}

