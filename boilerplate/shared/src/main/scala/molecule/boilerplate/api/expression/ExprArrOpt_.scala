// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag


trait ExprArrOptOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, u] = ???
}

trait ExprArrOpt_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprArrOptOps_1[A, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, u] = ???
}

trait ExprArrOpt_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprArrOptOps_2[A, B, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, u] = ???
}

trait ExprArrOpt_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprArrOptOps_3[A, B, C, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, u] = ???
}

trait ExprArrOpt_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprArrOptOps_4[A, B, C, D, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, u] = ???
}

trait ExprArrOpt_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprArrOptOps_5[A, B, C, D, E, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, F, u] = ???
}

trait ExprArrOpt_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_6[A, B, C, D, E, F, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, F, G, u] = ???
}

trait ExprArrOpt_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, F, G, H, u] = ???
}

trait ExprArrOpt_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, F, G, H, I, u] = ???
}

trait ExprArrOpt_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, F, G, H, I, J, u] = ???
}

trait ExprArrOpt_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = ???
}

trait ExprArrOpt_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = ???
}

trait ExprArrOpt_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = ???
}

trait ExprArrOpt_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = ???
}

trait ExprArrOpt_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = ???
}

trait ExprArrOpt_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = ???
}

trait ExprArrOpt_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = ???
}

trait ExprArrOpt_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = ???
}

trait ExprArrOpt_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = ???
}

trait ExprArrOpt_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = ???
}

trait ExprArrOpt_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = ???
}

trait ExprArrOpt_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrOpt(HasNo, arrays                        )
}


trait ExprArrOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprArrOpt[u <: t: ClassTag](op: Op, optArrays: Option[Seq[Array[u]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = ???
}

trait ExprArrOpt_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2]{
  def apply[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrOpt(Eq   , v.map(v => Seq(Array(v)))     )
  def apply[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrOpt(Eq   , vs.map(_.map(v => Array(v)))  )
  def apply[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrOpt(Eq   , array.map(array => Seq(array)))
  def apply[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrOpt(Eq   , arrays                        )
  def not  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrOpt(Neq  , array.map(array => Seq(array)))
  def not  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrOpt(Neq  , arrays                        )
  def has  [u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrOpt(Has  , v.map(v => Seq(Array(v)))     )
  def has  [u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrOpt(Has  , vs.map(_.map(v => Array(v)))  )
  def has  [u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrOpt(Has  , array.map(array => Seq(array)))
  def has  [u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrOpt(Has  , arrays                        )
  def hasNo[u <: t: ClassTag](v     : Option[u]            )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrOpt(HasNo, v.map(v => Seq(Array(v)))     )
  def hasNo[u <: t: ClassTag](vs    : Option[Seq[u]]       )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrOpt(HasNo, vs.map(_.map(v => Array(v)))  )
  def hasNo[u <: t: ClassTag](array : Option[Array[u]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrOpt(HasNo, array.map(array => Seq(array)))
  def hasNo[u <: t: ClassTag](arrays: Option[Seq[Array[u]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrOpt(HasNo, arrays                        )
}
