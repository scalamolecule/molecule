// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.base.ast._
import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag


trait ExprArrManOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, u] = ???
}

trait ExprArrMan_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprArrManOps_1[A, t, Ns1, Ns2]
    with Aggregates_1[A, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, u] = ???
}

trait ExprArrMan_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprArrManOps_2[A, B, t, Ns1, Ns2]
    with Aggregates_2[A, B, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, u] = ???
}

trait ExprArrMan_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprArrManOps_3[A, B, C, t, Ns1, Ns2]
    with Aggregates_3[A, B, C, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, u] = ???
}

trait ExprArrMan_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprArrManOps_4[A, B, C, D, t, Ns1, Ns2]
    with Aggregates_4[A, B, C, D, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, u] = ???
}

trait ExprArrMan_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprArrManOps_5[A, B, C, D, E, t, Ns1, Ns2]
    with Aggregates_5[A, B, C, D, E, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, F, u] = ???
}

trait ExprArrMan_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprArrManOps_6[A, B, C, D, E, F, t, Ns1, Ns2]
    with Aggregates_6[A, B, C, D, E, F, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, u] = ???
}

trait ExprArrMan_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2]
    with Aggregates_7[A, B, C, D, E, F, G, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, u] = ???
}

trait ExprArrMan_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2]
    with Aggregates_8[A, B, C, D, E, F, G, H, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, u] = ???
}

trait ExprArrMan_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2]
    with Aggregates_9[A, B, C, D, E, F, G, H, I, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, u] = ???
}

trait ExprArrMan_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2]
    with Aggregates_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = ???
}

trait ExprArrMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2]
    with Aggregates_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = ???
}

trait ExprArrMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2]
    with Aggregates_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = ???
}

trait ExprArrMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2]
    with Aggregates_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = ???
}

trait ExprArrMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2]
    with Aggregates_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = ???
}

trait ExprArrMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2]
    with Aggregates_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = ???
}

trait ExprArrMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2]
    with Aggregates_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = ???
}

trait ExprArrMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2]
    with Aggregates_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = ???
}

trait ExprArrMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2]
    with Aggregates_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = ???
}

trait ExprArrMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2]
    with Aggregates_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = ???
}

trait ExprArrMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2]
    with Aggregates_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = ???
}

trait ExprArrMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2]
    with Aggregates_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprArrMan[u <: t: ClassTag](op: Op, arrays: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = ???
}

trait ExprArrMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2]
    with Aggregates_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(Eq    , Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(Eq    , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(Eq    , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(Eq    , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(Eq    , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(Neq   , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(Neq   , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(Has   , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(Has   , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(Has   , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(Has   , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(HasNo , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(HasNo , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(HasNo , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(HasNo , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(Add   , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(Add   , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(Remove, Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrMan(Remove, Seq(vs.toArray)             )
  
  def apply[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Eq   , a)
  def not  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Neq  , a)
  def has  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Has  , a)
  def hasNo[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasNo, a)
}
