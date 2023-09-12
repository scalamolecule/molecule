package codegen.boilerplate.api.expression

import codegen.BoilerplateGenBase


object _ExprOneTac extends BoilerplateGenBase("ExprOneTac", "/api/expression") {
  val content = {
    val traits = (0 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api.expression
       |
       |import molecule.base.ast._
       |import molecule.boilerplate.api._
       |import molecule.boilerplate.api.Keywords.unify
       |import molecule.boilerplate.ast.Model._
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val attrExprs = if (arity == 22) {
      s"""
         |  def apply[ns1[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[${`A..V`}, t] = _attrTac(Eq , a)
         |  def not  [ns1[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[${`A..V`}, t] = _attrTac(Neq, a)
         |  def <    [ns1[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[${`A..V`}, t] = _attrTac(Lt , a)
         |  def <=   [ns1[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[${`A..V`}, t] = _attrTac(Le , a)
         |  def >    [ns1[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[${`A..V`}, t] = _attrTac(Gt , a)
         |  def >=   [ns1[_]](a: ModelOps_0[t, ns1, X2] with CardOne): Ns1[${`A..V`}, t] = _attrTac(Ge , a)""".stripMargin
    } else {
      s"""
         |  def apply[ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[${`A..V, `}t] = _attrTac(Eq , a)
         |  def not  [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[${`A..V, `}t] = _attrTac(Neq, a)
         |  def <    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[${`A..V, `}t] = _attrTac(Lt , a)
         |  def <=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[${`A..V, `}t] = _attrTac(Le , a)
         |  def >    [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[${`A..V, `}t] = _attrTac(Gt , a)
         |  def >=   [ns1[_], ns2[_, _]](a: ModelOps_0[t, ns1, ns2] with CardOne): Ns1[${`A..V, `}t] = _attrTac(Ge , a)
         |
         |  def apply[ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[${`A..V, `}t, t] = _attrMan(Eq , a)
         |  def not  [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[${`A..V, `}t, t] = _attrMan(Neq, a)
         |  def <    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[${`A..V, `}t, t] = _attrMan(Lt , a)
         |  def <=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[${`A..V, `}t, t] = _attrMan(Le , a)
         |  def >    [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[${`A..V, `}t, t] = _attrMan(Gt , a)
         |  def >=   [ns1[_, _], ns2[_, _, _]](a: ModelOps_1[t, t, ns1, ns2]): Ns2[${`A..V, `}t, t] = _attrMan(Ge , a)""".stripMargin
    }

    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends ExprAttr_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  protected def _exprOneTac(op: Op, vs: Seq[t]): Ns1[${`A..V, `}t] with CardOne = ???
         |}
         |
         |trait $fileName_$arity[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]]
         |  extends ${fileName}Ops_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  def apply()                : Ns1[${`A..V, `}t] with CardOne = _exprOneTac(NoValue, Nil       )
         |  def apply(unify: unify    ): Ns1[${`A..V, `}t] with CardOne = _exprOneTac(Unify  , Nil       )
         |  def apply(v    : t, vs: t*): Ns1[${`A..V, `}t] with CardOne = _exprOneTac(Eq     , v +: vs   )
         |  def apply(vs   : Seq[t]   ): Ns1[${`A..V, `}t] with CardOne = _exprOneTac(Eq     , vs        )
         |  def not  (v    : t, vs: t*): Ns1[${`A..V, `}t] with CardOne = _exprOneTac(Neq    , v +: vs   )
         |  def not  (vs   : Seq[t]   ): Ns1[${`A..V, `}t] with CardOne = _exprOneTac(Neq    , vs        )
         |  def <    (upper: t        ): Ns1[${`A..V, `}t] with CardOne = _exprOneTac(Lt     , Seq(upper))
         |  def <=   (upper: t        ): Ns1[${`A..V, `}t] with CardOne = _exprOneTac(Le     , Seq(upper))
         |  def >    (lower: t        ): Ns1[${`A..V, `}t] with CardOne = _exprOneTac(Gt     , Seq(lower))
         |  def >=   (lower: t        ): Ns1[${`A..V, `}t] with CardOne = _exprOneTac(Ge     , Seq(lower))
         |  $attrExprs
         |}
         |trait $fileName_${arity}_String[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends $fileName_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  def startsWith(prefix: t      ): Ns1[${`A..V, `}t] with CardOne = _exprOneTac(StartsWith, Seq(prefix))
         |  def endsWith  (suffix: t      ): Ns1[${`A..V, `}t] with CardOne = _exprOneTac(EndsWith  , Seq(suffix))
         |  def contains  (needle: t      ): Ns1[${`A..V, `}t] with CardOne = _exprOneTac(Contains  , Seq(needle))
         |  def matches   (regex : t      ): Ns1[${`A..V, `}t] with CardOne = _exprOneTac(Matches   , Seq(regex) )
         |}
         |trait $fileName_${arity}_Number[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends $fileName_$arity[${`A..V, `}t, Ns1, Ns2] {
         |  def %(divider: t, remainder: t): Ns1[${`A..V, `}t] with CardOne = _exprOneTac(Remainder , Seq(divider, remainder))
         |  def even                       : Ns1[${`A..V, `}t] with CardOne = _exprOneTac(Even      , Nil                    )
         |  def odd                        : Ns1[${`A..V, `}t] with CardOne = _exprOneTac(Odd       , Nil                    )
         |}""".stripMargin
//         |trait $fileName_${arity}_Decimal[${`A..V, `}t, Ns1[${`_, _`}], Ns2[${`_, _, _`}]] extends $fileName_$arity[${`A..V, `}t, Ns1, Ns2] {
//         |  def whole                      : Ns1[${`A..V, `}t] with CardOne = _exprOneTac(Whole     , Nil)
//         |  def fractional                 : Ns1[${`A..V, `}t] with CardOne = _exprOneTac(Fractional, Nil)
//         |}""".stripMargin
  }
}
