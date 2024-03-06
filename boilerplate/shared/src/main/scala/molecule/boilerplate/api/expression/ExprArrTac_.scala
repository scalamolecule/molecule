// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.base.ast._
import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag


trait ExprArrTacOps_0[t0, Ns1[_], Ns2[_, _]] extends ExprAttr_0[t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[t] = ???
}

trait ExprArrTac_0[t0, Ns1[_], Ns2[_, _]]
  extends ExprArrTacOps_0[t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_1[A, t0, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, t] = ???
}

trait ExprArrTac_1[A, t0, Ns1[_, _], Ns2[_, _, _]]
  extends ExprArrTacOps_1[A, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_2[A, B, t0, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, t] = ???
}

trait ExprArrTac_2[A, B, t0, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprArrTacOps_2[A, B, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_3[A, B, C, t0, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, t] = ???
}

trait ExprArrTac_3[A, B, C, t0, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprArrTacOps_3[A, B, C, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_4[A, B, C, D, t0, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, t] = ???
}

trait ExprArrTac_4[A, B, C, D, t0, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprArrTacOps_4[A, B, C, D, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_5[A, B, C, D, E, t0, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, t] = ???
}

trait ExprArrTac_5[A, B, C, D, E, t0, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprArrTacOps_5[A, B, C, D, E, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_6[A, B, C, D, E, F, t0, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, F, t] = ???
}

trait ExprArrTac_6[A, B, C, D, E, F, t0, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_6[A, B, C, D, E, F, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_7[A, B, C, D, E, F, G, t0, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, t] = ???
}

trait ExprArrTac_7[A, B, C, D, E, F, G, t0, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_7[A, B, C, D, E, F, G, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_8[A, B, C, D, E, F, G, H, t0, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprArrTac_8[A, B, C, D, E, F, G, H, t0, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_8[A, B, C, D, E, F, G, H, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_9[A, B, C, D, E, F, G, H, I, t0, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprArrTac_9[A, B, C, D, E, F, G, H, I, t0, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_9[A, B, C, D, E, F, G, H, I, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_10[A, B, C, D, E, F, G, H, I, J, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprArrTac_10[A, B, C, D, E, F, G, H, I, J, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_10[A, B, C, D, E, F, G, H, I, J, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprArrTac_11[A, B, C, D, E, F, G, H, I, J, K, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprArrTac_12[A, B, C, D, E, F, G, H, I, J, K, L, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprArrTac_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprArrTac_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprArrTac_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprArrTac_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprArrTac_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprArrTac_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprArrTac_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprArrTac_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprArrTac_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t0, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t0] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Array[t0], t0] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t0], t0, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Array[t0], t0] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X        , t0] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X        , t0, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X        , t0] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0, Ns1, Ns2] {
  protected def _exprArrTac[t <: t0: ClassTag](op: Op, vs: Seq[Array[t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprArrTac_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0, Ns1, Ns2] {
  def apply [t <: t0: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(NoValue, Nil                         )
  def apply [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(Eq     , array +: arrays             )
  def apply [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(Eq     , arrays                      )
  def not   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(Neq    , array +: arrays             )
  def not   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(Neq    , arrays                      )
  def has   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(Has    , array +: arrays             )
  def has   [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(Has    , arrays                      )
  def hasNo [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [t <: t0: ClassTag](vs    : Seq[t]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [t <: t0: ClassTag](array : Array[t], arrays: Array[t]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [t <: t0: ClassTag](arrays: Seq[Array[t]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(HasNo  , arrays                      )
  def add   [t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[t <: t0: ClassTag](v     : t, vs: t*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[t <: t0: ClassTag](vs    : Iterable[t]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_]](a: ModelOps_0[t0, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0] = _attrTac(Eq   , a)
  def not  [ns1[_]](a: ModelOps_0[t0, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0] = _attrTac(Neq  , a)
  def has  [ns1[_]](a: ModelOps_0[t0, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0] = _attrTac(Has  , a)
  def hasNo[ns1[_]](a: ModelOps_0[t0, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t0] = _attrTac(HasNo, a)
}
