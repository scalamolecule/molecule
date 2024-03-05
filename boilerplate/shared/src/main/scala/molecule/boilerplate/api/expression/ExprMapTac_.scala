// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.base.ast._
import molecule.boilerplate.api._
import molecule.boilerplate.ast.Model._


trait ExprMapTacOps_0[t, Ns1[_], Ns2[_, _]] extends ExprAttr_0[t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[t] = ???
}

trait ExprMapTac_0[t, Ns1[_], Ns2[_, _]]
  extends ExprMapTacOps_0[t, Ns1, Ns2] {
  def apply (                                           ): Ns1[t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, t] = ???
}

trait ExprMapTac_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprMapTacOps_1[A, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, t] = ???
}

trait ExprMapTac_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprMapTacOps_2[A, B, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, t] = ???
}

trait ExprMapTac_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprMapTacOps_3[A, B, C, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, t] = ???
}

trait ExprMapTac_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprMapTacOps_4[A, B, C, D, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, t] = ???
}

trait ExprMapTac_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprMapTacOps_5[A, B, C, D, E, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, t] = ???
}

trait ExprMapTac_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, F, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, F, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, t] = ???
}

trait ExprMapTac_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, F, G, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, F, G, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprMapTac_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprMapTac_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprMapTac_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprMapTac_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprMapTac_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprMapTac_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprMapTac_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprMapTac_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprMapTac_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprMapTac_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprMapTac_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprMapTac_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprMapTac_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprMapTac_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Eq   , a)
  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardMap)(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Neq  , a)
  def has  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(Has  , a)
  def hasNo[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with Card   )(implicit x: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _attrTac(HasNo, a)

  def apply[   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Map[String, t], t] = _attrMan(Eq   , a)
  def not  [   ns1[_, _], ns2[_, _, _]](a: ModelOps_1[Map[String, t], t, ns1, ns2] with CardMap): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Map[String, t], t] = _attrMan(Neq  , a)
  def has  [X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X             , t] = _attrMan(Has  , a)
  def hasNo[X, ns1[_, _], ns2[_, _, _]](a: ModelOps_1[X             , t, ns1, ns2] with Card   ): Ns2[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, X             , t] = _attrMan(HasNo, a)
}


trait ExprMapTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprMapTac (op: Op, maps: Seq[Map[String, t]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
  protected def _exprMapTacK(op: Op, keys: Seq[String        ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
  protected def _exprMapTacV(op: Op, vs  : Seq[t             ]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprMapTac_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  def apply (                                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapTacK(NoValue, Nil                       )
  def apply (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapTacK(Eq     , key +: keys               )
  def apply (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapTacK(Eq     , keys                      )
  def not   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapTacK(Neq    , key +: keys               )
  def not   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapTacK(Neq    , keys                      )
  def has   (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapTacK(Has    , key +: keys               )
  def has   (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapTacK(Has    , keys                      )
  def hasNo (key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapTacK(HasNo  , key +: keys               )
  def hasNo (keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapTacK(HasNo  , keys                      )
  def v     (v    : t, vs: t*                           ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapTacV(HasNo  , v +: vs                   )
  def v     (vs   : Seq[t]                              ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapTacV(HasNo  , vs                        )

  def add   (pair : (String, t), pairs: (String, t)*    ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapTac (Add    , Seq((pair +: pairs).toMap))
  def add   (pairs: Iterable[(String, t)]               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapTac (Add    , Seq(pairs.toMap)          )
  def swap  (ab   : (String, t, t), abs: (String, t, t)*): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapTac (Swap   , abs2maps(ab +: abs)       )
  def swap  (abs  : Seq[(String, t, t)]                 ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapTac (Swap   , abs2maps(abs)             )
  def remove(key  : String, keys: String*               ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapTacK(Remove , key +: keys               )
  def remove(keys : Seq[String]                         ): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapTacK(Remove , keys                      )
  
  def apply[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Eq   , a)
  def not  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Neq  , a)
  def has  [ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(Has  , a)
  def hasNo[ns1[_]](a: ModelOps_0[t, ns1, X2]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _attrTac(HasNo, a)
}
