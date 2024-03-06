// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.base.ast._
import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag


trait ExprArrManOps_1[A, t0, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, t] = ???
}

trait ExprArrMan_1[A, t0, Ns1[_, _], Ns2[_, _, _]]
  extends ExprArrManOps_1[A, t0, Ns1, Ns2]
    with Aggregates_1[A, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_2[A, B, t0, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, t] = ???
}

trait ExprArrMan_2[A, B, t0, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprArrManOps_2[A, B, t0, Ns1, Ns2]
    with Aggregates_2[A, B, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_3[A, B, C, t0, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, t] = ???
}

trait ExprArrMan_3[A, B, C, t0, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprArrManOps_3[A, B, C, t0, Ns1, Ns2]
    with Aggregates_3[A, B, C, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_4[A, B, C, D, t0, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, t] = ???
}

trait ExprArrMan_4[A, B, C, D, t0, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprArrManOps_4[A, B, C, D, t0, Ns1, Ns2]
    with Aggregates_4[A, B, C, D, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_5[A, B, C, D, E, t0, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, t] = ???
}

trait ExprArrMan_5[A, B, C, D, E, t0, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprArrManOps_5[A, B, C, D, E, t0, Ns1, Ns2]
    with Aggregates_5[A, B, C, D, E, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_6[A, B, C, D, E, F, t0, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, F, t] = ???
}

trait ExprArrMan_6[A, B, C, D, E, F, t0, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprArrManOps_6[A, B, C, D, E, F, t0, Ns1, Ns2]
    with Aggregates_6[A, B, C, D, E, F, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_7[A, B, C, D, E, F, G, t0, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, t] = ???
}

trait ExprArrMan_7[A, B, C, D, E, F, G, t0, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_7[A, B, C, D, E, F, G, t0, Ns1, Ns2]
    with Aggregates_7[A, B, C, D, E, F, G, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_8[A, B, C, D, E, F, G, H, t0, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprArrMan_8[A, B, C, D, E, F, G, H, t0, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_8[A, B, C, D, E, F, G, H, t0, Ns1, Ns2]
    with Aggregates_8[A, B, C, D, E, F, G, H, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_9[A, B, C, D, E, F, G, H, I, t0, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprArrMan_9[A, B, C, D, E, F, G, H, I, t0, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_9[A, B, C, D, E, F, G, H, I, t0, Ns1, Ns2]
    with Aggregates_9[A, B, C, D, E, F, G, H, I, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_10[A, B, C, D, E, F, G, H, I, J, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprArrMan_10[A, B, C, D, E, F, G, H, I, J, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_10[A, B, C, D, E, F, G, H, I, J, t0, Ns1, Ns2]
    with Aggregates_10[A, B, C, D, E, F, G, H, I, J, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_11[A, B, C, D, E, F, G, H, I, J, K, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprArrMan_11[A, B, C, D, E, F, G, H, I, J, K, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_11[A, B, C, D, E, F, G, H, I, J, K, t0, Ns1, Ns2]
    with Aggregates_11[A, B, C, D, E, F, G, H, I, J, K, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprArrMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t0, Ns1, Ns2]
    with Aggregates_12[A, B, C, D, E, F, G, H, I, J, K, L, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprArrMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t0, Ns1, Ns2]
    with Aggregates_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprArrMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0, Ns1, Ns2]
    with Aggregates_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprArrMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0, Ns1, Ns2]
    with Aggregates_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprArrMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0, Ns1, Ns2]
    with Aggregates_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprArrMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0, Ns1, Ns2]
    with Aggregates_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprArrMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0, Ns1, Ns2]
    with Aggregates_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprArrMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0, Ns1, Ns2]
    with Aggregates_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprArrMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0, Ns1, Ns2]
    with Aggregates_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprArrMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0, Ns1, Ns2]
    with Aggregates_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0, Ns1, Ns2] {
  protected def _exprArrMan[t <: t0: ClassTag](op: Op, arrays: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprArrMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0, Ns1, Ns2]
    with Aggregates_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0, Ns1] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(Eq    , Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(Eq    , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(Eq    , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(Neq   , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(Neq   , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(Has   , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(Has   , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(HasNo , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_]](a: ModelOps_0[t0, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0] = _attrTac(Eq   , a)
  def not  [ns1[_]](a: ModelOps_0[t0, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0] = _attrTac(Neq  , a)
  def has  [ns1[_]](a: ModelOps_0[t0, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_]](a: ModelOps_0[t0, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0] = _attrTac(HasNo, a)
}
