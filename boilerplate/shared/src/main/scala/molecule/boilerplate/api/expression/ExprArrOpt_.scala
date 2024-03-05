// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag


trait ExprArrOptOps_1[A, t0, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, t] = ???
}

trait ExprArrOpt_1[A, t0, Ns1[_, _], Ns2[_, _, _]]
  extends ExprArrOptOps_1[A, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_2[A, B, t0, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, t] = ???
}

trait ExprArrOpt_2[A, B, t0, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprArrOptOps_2[A, B, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_3[A, B, C, t0, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, t] = ???
}

trait ExprArrOpt_3[A, B, C, t0, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprArrOptOps_3[A, B, C, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_4[A, B, C, D, t0, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, t] = ???
}

trait ExprArrOpt_4[A, B, C, D, t0, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprArrOptOps_4[A, B, C, D, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_5[A, B, C, D, E, t0, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, t] = ???
}

trait ExprArrOpt_5[A, B, C, D, E, t0, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprArrOptOps_5[A, B, C, D, E, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_6[A, B, C, D, E, F, t0, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, F, t] = ???
}

trait ExprArrOpt_6[A, B, C, D, E, F, t0, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_6[A, B, C, D, E, F, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_7[A, B, C, D, E, F, G, t0, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, F, G, t] = ???
}

trait ExprArrOpt_7[A, B, C, D, E, F, G, t0, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_7[A, B, C, D, E, F, G, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_8[A, B, C, D, E, F, G, H, t0, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprArrOpt_8[A, B, C, D, E, F, G, H, t0, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_8[A, B, C, D, E, F, G, H, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_9[A, B, C, D, E, F, G, H, I, t0, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprArrOpt_9[A, B, C, D, E, F, G, H, I, t0, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_9[A, B, C, D, E, F, G, H, I, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_10[A, B, C, D, E, F, G, H, I, J, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprArrOpt_10[A, B, C, D, E, F, G, H, I, J, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_10[A, B, C, D, E, F, G, H, I, J, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprArrOpt_11[A, B, C, D, E, F, G, H, I, J, K, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprArrOpt_12[A, B, C, D, E, F, G, H, I, J, K, L, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprArrOpt_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprArrOpt_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprArrOpt_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprArrOpt_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprArrOpt_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprArrOpt_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprArrOpt_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprArrOpt_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprArrOpt_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0, Ns1, Ns2] {
  protected def _exprArrOpt[t <: t0: ClassTag](op: Op, optArrays: Option[Seq[Array[t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprArrOpt_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0, Ns1, Ns2]{
  def apply[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrOpt(Eq   , arrays                        )
  def not  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrOpt(Neq  , arrays                        )
  def has  [t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrOpt(Has  , arrays                        )
  def hasNo[t <: t0: ClassTag](v     : Option[t]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[t <: t0: ClassTag](vs    : Option[Seq[t]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[t <: t0: ClassTag](array : Option[Array[t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[t <: t0: ClassTag](arrays: Option[Seq[Array[t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrOpt(HasNo, arrays                        )
}
