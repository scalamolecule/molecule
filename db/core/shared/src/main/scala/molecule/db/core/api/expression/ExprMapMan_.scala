// GENERATED CODE ********************************
package molecule.db.core.api.expression

import molecule.core.dataModel.*


trait ExprMapMan_1[A, t, Entity1[_, _], Entity2[_, _, _]]
  extends ExprMapTacOps_1[A, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_2[A, B, t, Entity1[_, _, _], Entity2[_, _, _, _]]
  extends ExprMapTacOps_2[A, B, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_3[A, B, C, t, Entity1[_, _, _, _], Entity2[_, _, _, _, _]]
  extends ExprMapTacOps_3[A, B, C, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_4[A, B, C, D, t, Entity1[_, _, _, _, _], Entity2[_, _, _, _, _, _]]
  extends ExprMapTacOps_4[A, B, C, D, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_5[A, B, C, D, E, t, Entity1[_, _, _, _, _, _], Entity2[_, _, _, _, _, _, _]]
  extends ExprMapTacOps_5[A, B, C, D, E, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_6[A, B, C, D, E, F, t, Entity1[_, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_6[A, B, C, D, E, F, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, F, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, F, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, E, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, E, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, E, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, F, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, F, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, E, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, E, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_7[A, B, C, D, E, F, G, t, Entity1[_, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_7[A, B, C, D, E, F, G, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, F, G, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, F, G, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, E, F, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, E, F, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, E, F, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, F, G, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, F, G, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, E, F, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, E, F, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_8[A, B, C, D, E, F, G, H, t, Entity1[_, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_8[A, B, C, D, E, F, G, H, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, F, G, H, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, F, G, H, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, E, F, G, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, E, F, G, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, E, F, G, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, F, G, H, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, F, G, H, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, E, F, G, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, E, F, G, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_9[A, B, C, D, E, F, G, H, I, t, Entity1[_, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_9[A, B, C, D, E, F, G, H, I, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, E, F, G, H, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, E, F, G, H, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, E, F, G, H, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, F, G, H, I, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, E, F, G, H, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, E, F, G, H, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_10[A, B, C, D, E, F, G, H, I, J, t, Entity1[_, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_10[A, B, C, D, E, F, G, H, I, J, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, E, F, G, H, I, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, E, F, G, H, I, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, E, F, G, H, I, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, F, G, H, I, J, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, E, F, G, H, I, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, E, F, G, H, I, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_11[A, B, C, D, E, F, G, H, I, J, K, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, E, F, G, H, I, J, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, E, F, G, H, I, J, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, E, F, G, H, I, J, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, E, F, G, H, I, J, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, E, F, G, H, I, J, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_12[A, B, C, D, E, F, G, H, I, J, K, L, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_13[A, B, C, D, E, F, G, H, I, J, K, L, M, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_15[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_17[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_18[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_19[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, t, t] = _exprMapK(Remove , keys                 )
}


trait ExprMapMan_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _], Entity2[_, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _, _]]
  extends ExprMapTacOps_22[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t, Entity1, Entity2] {
  def apply (                                       ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMap (NoValue, Map.empty[String, t] )
  def apply (map  : Map[String, t]                  ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _exprMapK(Neq    , keys                 )
  def has   (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapV(Has    , vs                   )
  def hasNo (v : t, vs: t*                          ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[t]                             ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, t), pairs: (String, t)*): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, t)]                ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, t] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): Entity1[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, t, t] = _exprMapK(Remove , keys                 )
}
