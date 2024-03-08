// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.base.ast._
import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag


trait ExprArrTacOps_0[t, Ns1[_], Ns2[_, _]] extends ExprAttr_0[t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[u] = ???
}

trait ExprArrTac_0[t, Ns1[_], Ns2[_, _]]
  extends ExprArrTacOps_0[t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, u] = ???
}

trait ExprArrTac_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprArrTacOps_1[A, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, u] = ???
}

trait ExprArrTac_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprArrTacOps_2[A, B, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, u] = ???
}

trait ExprArrTac_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprArrTacOps_3[A, B, C, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, u] = ???
}

trait ExprArrTac_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprArrTacOps_4[A, B, C, D, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, u] = ???
}

trait ExprArrTac_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprArrTacOps_5[A, B, C, D, E, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, F, u] = ???
}

trait ExprArrTac_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, u] = ???
}

trait ExprArrTac_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, u] = ???
}

trait ExprArrTac_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, u] = ???
}

trait ExprArrTac_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, u] = ???
}

trait ExprArrTac_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = ???
}

trait ExprArrTac_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = ???
}

trait ExprArrTac_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = ???
}

trait ExprArrTac_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = ???
}

trait ExprArrTac_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = ???
}

trait ExprArrTac_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = ???
}

trait ExprArrTac_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = ???
}

trait ExprArrTac_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = ???
}

trait ExprArrTac_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = ???
}

trait ExprArrTac_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = ???
}

trait ExprArrTac_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = ???
}

trait ExprArrTac_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardArr)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Array[t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Array[t], t, ns1, ns2] with CardArr): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Array[t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X       , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X       , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X       , t] = _attrMan(HasNo, a)
}


trait ExprArrTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprArrTac[u <: t: ClassTag](op: Op, vs: Seq[Array[u]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = ???
}

trait ExprArrTac_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprArrTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  def apply [u <: t: ClassTag](                                   )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(NoValue, Nil                         )
  def apply [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(Eq     , (v +: vs).map(v => Array(v)))
  def apply [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(Eq     , vs.map(v => Array(v))       )
  def apply [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(Eq     , array +: arrays             )
  def apply [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(Eq     , arrays                      )
  def not   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(Neq    , array +: arrays             )
  def not   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(Neq    , arrays                      )
  def has   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(Has    , (v +: vs).map(v => Array(v)))
  def has   [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(Has    , vs.map(v => Array(v))       )
  def has   [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(Has    , array +: arrays             )
  def has   [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(Has    , arrays                      )
  def hasNo [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(HasNo  , (v +: vs).map(v => Array(v)))
  def hasNo [u <: t: ClassTag](vs    : Seq[u]                     )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(HasNo  , vs.map(v => Array(v))       )
  def hasNo [u <: t: ClassTag](array : Array[u], arrays: Array[u]*)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(HasNo  , array +: arrays             )
  def hasNo [u <: t: ClassTag](arrays: Seq[Array[u]]              )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(HasNo  , arrays                      )
  def add   [u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(Add    , Seq((v +: vs).toArray)      )
  def add   [u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(Add    , Seq(vs.toArray)             )
  def remove[u <: t: ClassTag](v     : u, vs: u*                  )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(Remove , Seq((v +: vs).toArray)      )
  def remove[u <: t: ClassTag](vs    : Iterable[u]                )               : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, u] = _exprArrTac(Remove , Seq(vs.toArray)             )
  
  def apply[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Eq   , a)
  def not  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Neq  , a)
  def has  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Has  , a)
  def hasNo[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasNo, a)
}
