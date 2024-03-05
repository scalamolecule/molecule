// GENERATED CODE ********************************
package molecule.boilerplate.api.expression

import molecule.boilerplate.ast.Model._


trait ExprMapOptOps_1[A, t, Ns1[_, _], Ns2[_, _, _]] extends ExprAttr_1[A, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, t] = ???
}

trait ExprMapOpt_1[A, t, Ns1[_, _], Ns2[_, _, _]]
  extends ExprMapOptOps_1[A, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]] extends ExprAttr_2[A, B, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, t] = ???
}

trait ExprMapOpt_2[A, B, t, Ns1[_, _, _], Ns2[_, _, _, _]]
  extends ExprMapOptOps_2[A, B, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]] extends ExprAttr_3[A, B, C, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, t] = ???
}

trait ExprMapOpt_3[A, B, C, t, Ns1[_, _, _, _], Ns2[_, _, _, _, _]]
  extends ExprMapOptOps_3[A, B, C, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]] extends ExprAttr_4[A, B, C, D, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, t] = ???
}

trait ExprMapOpt_4[A, B, C, D, t, Ns1[_, _, _, _, _], Ns2[_, _, _, _, _, _]]
  extends ExprMapOptOps_4[A, B, C, D, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]] extends ExprAttr_5[A, B, C, D, E, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, t] = ???
}

trait ExprMapOpt_5[A, B, C, D, E, t, Ns1[_, _, _, _, _, _], Ns2[_, _, _, _, _, _, _]]
  extends ExprMapOptOps_5[A, B, C, D, E, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]] extends ExprAttr_6[A, B, C, D, E, F, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, F, t] = ???
}

trait ExprMapOpt_6[A, B, C, D, E, F, t, Ns1[_, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_6[A, B, C, D, E, F, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]] extends ExprAttr_7[A, B, C, D, E, F, G, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, F, G, t] = ???
}

trait ExprMapOpt_7[A, B, C, D, E, F, G, t, Ns1[_, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_7[A, B, C, D, E, F, G, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]] extends ExprAttr_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, F, G, H, t] = ???
}

trait ExprMapOpt_8[A, B, C, D, E, F, G, H, t, Ns1[_, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_8[A, B, C, D, E, F, G, H, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, F, G, H, I, t] = ???
}

trait ExprMapOpt_9[A, B, C, D, E, F, G, H, I, t, Ns1[_, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_9[A, B, C, D, E, F, G, H, I, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, t] = ???
}

trait ExprMapOpt_10[A, B, C, D, E, F, G, H, I, J, t, Ns1[_, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_10[A, B, C, D, E, F, G, H, I, J, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = ???
}

trait ExprMapOpt_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = ???
}

trait ExprMapOpt_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = ???
}

trait ExprMapOpt_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = ???
}

trait ExprMapOpt_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = ???
}

trait ExprMapOpt_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = ???
}

trait ExprMapOpt_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = ???
}

trait ExprMapOpt_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = ???
}

trait ExprMapOpt_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = ???
}

trait ExprMapOpt_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = ???
}

trait ExprMapOpt_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = ???
}

trait ExprMapOpt_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapOpt(HasNo, maps                               )
}


trait ExprMapOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]] extends ExprAttr_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2] {
  protected def _exprMapOpt(op: Op, optMaps: Option[Seq[Map[String, t]]]): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = ???
}

trait ExprMapOpt_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Ns2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapOptOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Ns1, Ns2]{
  def apply(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpt(Eq   , pair.map(pair => Seq(Map(pair)))   )
  def apply(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpt(Eq   , pairs.map(_.map(pair => Map(pair))))
  def apply(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpt(Eq   , map.map(map => Seq(map))           )
  def apply(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpt(Eq   , maps                               )
  def not  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpt(Neq  , map.map(map => Seq(map))           )
  def not  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpt(Neq  , maps                               )
  def has  (pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpt(Has  , pair.map(pair => Seq(Map(pair)))   )
  def has  (pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpt(Has  , pairs.map(_.map(pair => Map(pair))))
  def has  (map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpt(Has  , map.map(map => Seq(map))           )
  def has  (maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpt(Has  , maps                               )
  def hasNo(pair : Option[(String, t)]        )(implicit x: X)            : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpt(HasNo, pair.map(pair => Seq(Map(pair)))   )
  def hasNo(pairs: Option[Seq[(String, t)]]   )(implicit x: X, y: X)      : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpt(HasNo, pairs.map(_.map(pair => Map(pair))))
  def hasNo(map  : Option[Map[String, t]]     )(implicit x: X, y: X, z: X): Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpt(HasNo, map.map(map => Seq(map))           )
  def hasNo(maps : Option[Seq[Map[String, t]]])                           : Ns1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapOpt(HasNo, maps                               )
}
