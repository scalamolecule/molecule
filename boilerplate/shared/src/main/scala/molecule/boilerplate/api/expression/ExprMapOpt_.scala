// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.ast.Model._


trait ExprMapOptOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, t] = ???
}

trait ExprMapOpt_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprMapOptOps_1[A, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, t] = ???
}

trait ExprMapOpt_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprMapOptOps_2[A, B, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, t] = ???
}

trait ExprMapOpt_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprMapOptOps_3[A, B, C, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, t] = ???
}

trait ExprMapOpt_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprMapOptOps_4[A, B, C, D, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, t] = ???
}

trait ExprMapOpt_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprMapOptOps_5[A, B, C, D, E, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, F, t] = ???
}

trait ExprMapOpt_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_6[A, B, C, D, E, F, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, F, G, t] = ???
}

trait ExprMapOpt_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprMapOpt_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprMapOpt_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprMapOpt_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprMapOpt_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprMapOpt_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprMapOpt_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprMapOpt_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprMapOpt_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprMapOpt_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprMapOpt_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprMapOpt_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprMapOpt_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprMapOpt_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprMapOpt_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpK(HasNo, keys           )
}


trait ExprMapOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprMapOpK(op: Op, optKeys: Option[Seq[String]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprMapOpt_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2]{
  def apply(key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpK(Eq   , key.map(Seq(_)))
  def apply(keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpK(Eq   , keys           )
  def not  (key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpK(Neq  , key.map(Seq(_)))
  def not  (keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpK(Neq  , keys           )
  def has  (key : Option[String]     )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpK(Has  , key.map(Seq(_)))
  def has  (keys: Option[Seq[String]])(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpK(Has  , keys           )
  def hasNo(key : Option[String]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpK(HasNo, key.map(Seq(_)))
  def hasNo(keys: Option[Seq[String]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpK(HasNo, keys           )
}
