// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.base.ast._
import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._


trait ExprMapManOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, t] = ???
}

trait ExprMapMan_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprMapManOps_1[A, t, Ns1, Ns2]
    with Aggregates_1[A, t, Ns1] {
  def apply (                                           ): Ns1[A, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, t] = ???
}

trait ExprMapMan_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprMapManOps_2[A, B, t, Ns1, Ns2]
    with Aggregates_2[A, B, t, Ns1] {
  def apply (                                           ): Ns1[A, B, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, t] = ???
}

trait ExprMapMan_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprMapManOps_3[A, B, C, t, Ns1, Ns2]
    with Aggregates_3[A, B, C, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, t] = ???
}

trait ExprMapMan_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprMapManOps_4[A, B, C, D, t, Ns1, Ns2]
    with Aggregates_4[A, B, C, D, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, t] = ???
}

trait ExprMapMan_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprMapManOps_5[A, B, C, D, E, t, Ns1, Ns2]
    with Aggregates_5[A, B, C, D, E, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, t] = ???
}

trait ExprMapMan_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprMapManOps_6[A, B, C, D, E, F, t, Ns1, Ns2]
    with Aggregates_6[A, B, C, D, E, F, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, t] = ???
}

trait ExprMapMan_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprMapManOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2]
    with Aggregates_7[A, B, C, D, E, F, G, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprMapMan_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprMapManOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2]
    with Aggregates_8[A, B, C, D, E, F, G, H, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprMapMan_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapManOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2]
    with Aggregates_9[A, B, C, D, E, F, G, H, I, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprMapMan_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapManOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2]
    with Aggregates_10[A, B, C, D, E, F, G, H, I, J, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprMapMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapManOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2]
    with Aggregates_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprMapMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapManOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2]
    with Aggregates_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprMapMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapManOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2]
    with Aggregates_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprMapMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapManOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2]
    with Aggregates_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprMapMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapManOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2]
    with Aggregates_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprMapMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapManOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2]
    with Aggregates_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprMapMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapManOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2]
    with Aggregates_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprMapMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapManOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2]
    with Aggregates_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprMapMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapManOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2]
    with Aggregates_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprMapMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapManOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2]
    with Aggregates_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprMapMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapManOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2]
    with Aggregates_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprMapMan(op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
  protected def _exprMapMaK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
  protected def _exprMapMaV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprMapMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapManOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2]
    with Aggregates_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapMaK(Eq    , Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapMaK(Eq    , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapMaK(Eq    , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapMaK(Neq   , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapMaK(Neq   , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapMaK(Has   , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapMaK(Has   , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapMaK(HasNo , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapMaK(HasNo , keys                      )
  def getV  (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapMaV(GetV  , v +: vs                   )
  def getV  (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapMaV(GetV  , vs                        )
  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapMan(Add   , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapMan(Add   , Seq(pairs.toMap)          )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapMaK(Remove, key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapMaK(Remove, keys                      )
  
  def apply[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Eq   , a)
  def not  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Neq  , a)
  def has  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Has  , a)
  def hasNo[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasNo, a)
}
