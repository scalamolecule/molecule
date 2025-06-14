package boilerplate.db.core.api.expression

import boilerplate.db.core.DbCoreBase


object _ExprMapMan extends DbCoreBase("ExprMapMan", "/api/expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.core.api.expression
       |
       |import molecule.core.dataModel.*
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait $fileName_$arity[${`A..V`}, t, Entity1[${`_, _`}], Entity2[${`_, _, _`}]]
         |  extends ExprMapTacOps_$arity[${`A..V`}, t, Entity1, Entity2] {
         |  def apply (                                       ): Entity1[${`A..V`}, t] = _exprMap (NoValue, Map.empty[String, t] )
         |  def apply (map  : Map[String, t]                  ): Entity1[${`A..V`}, t] = _exprMap (Eq     , map                  )
         |  def apply (key  : String                          ): Entity1[${`A..t`}, t] = _exprMapK(Eq     , Seq(key)             )
         |  def not   (key : String, keys: String*            ): Entity1[${`A..t, `}t] = _exprMapK(Neq    , Seq(key) ++ keys     )
         |  def not   (keys: Seq[String]                      ): Entity1[${`A..t, `}t] = _exprMapK(Neq    , keys                 )
         |  def has   (v : t, vs: t*                          ): Entity1[${`A..V, `}t] = _exprMapV(Has    , Seq(v) ++ vs         )
         |  def has   (vs: Seq[t]                             ): Entity1[${`A..V, `}t] = _exprMapV(Has    , vs                   )
         |  def hasNo (v : t, vs: t*                          ): Entity1[${`A..V, `}t] = _exprMapV(HasNo  , Seq(v) ++ vs         )
         |  def hasNo (vs: Seq[t]                             ): Entity1[${`A..V, `}t] = _exprMapV(HasNo  , vs                   )
         |  def add   (pair : (String, t), pairs: (String, t)*): Entity1[${`A..V`}, t] = _exprMap (Add    , (pair +: pairs).toMap)
         |  def add   (pairs: Seq[(String, t)]                ): Entity1[${`A..V`}, t] = _exprMap (Add    , pairs.toMap          )
         |  def remove(key  : String, keys: String*           ): Entity1[${`A..t`}, t] = _exprMapK(Remove , Seq(key) ++ keys     )
         |  def remove(keys : Seq[String]                     ): Entity1[${`A..t`}, t] = _exprMapK(Remove , keys                 )
         |}""".stripMargin
  }
}
