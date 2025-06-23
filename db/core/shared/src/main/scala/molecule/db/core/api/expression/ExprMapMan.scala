package molecule.db.core.api.expression

import molecule.core.dataModel.*
import molecule.db.core.api.Tail
//import scala.Tuple.Tail // requires NonEmptyTuple in scala 3.3.6, not in 3.7


trait ExprMapMan[Tpl <: Tuple, T, This[_ <: Tuple, _], Next[_ <: Tuple, _]]
  extends ExprMapTacOps[Tpl, T, This, Next] {
  def apply (                                       ): This[Tpl           , T] = _exprMap (NoValue, Map.empty[String, T] )
  def apply (map  : Map[String, T]                  ): This[Tpl           , T] = _exprMap (Eq     , map                  )
  def apply (key  : String                          ): This[T *: Tail[Tpl], T] = _exprMapK(Eq     , Seq(key)             )
  def not   (key : String, keys: String*            ): This[T *: Tail[Tpl], T] = _exprMapK(Neq    , Seq(key) ++ keys     )
  def not   (keys: Seq[String]                      ): This[T *: Tail[Tpl], T] = _exprMapK(Neq    , keys                 )
  def has   (v : T, vs: T*                          ): This[Tpl           , T] = _exprMapV(Has    , Seq(v) ++ vs         )
  def has   (vs: Seq[T]                             ): This[Tpl           , T] = _exprMapV(Has    , vs                   )
  def hasNo (v : T, vs: T*                          ): This[Tpl           , T] = _exprMapV(HasNo  , Seq(v) ++ vs         )
  def hasNo (vs: Seq[T]                             ): This[Tpl           , T] = _exprMapV(HasNo  , vs                   )
  def add   (pair : (String, T), pairs: (String, T)*): This[Tpl           , T] = _exprMap (Add    , (pair +: pairs).toMap)
  def add   (pairs: Seq[(String, T)]                ): This[Tpl           , T] = _exprMap (Add    , pairs.toMap          )
  def remove(key  : String, keys: String*           ): This[T *: Tail[Tpl], T] = _exprMapK(Remove , Seq(key) ++ keys     )
  def remove(keys : Seq[String]                     ): This[T *: Tail[Tpl], T] = _exprMapK(Remove , keys                 )
}