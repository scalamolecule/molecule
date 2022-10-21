package molecule.boilerplate.generators.expression

import molecule.boilerplate.generators._Template

object GenExprSetOpt extends _Template( "exprSetO", "expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.api.sortAttrs._
       |import molecule.boilerplate.markers.argKindMarkers._
       |
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |trait ${Name}_$arity[Attr, t, ${`A..N`}, $nsIn]
         |  extends SortAttrs_$arity[Attr, t, ${`A..N`}, Ns] {
         |  def apply(v    : Option[t])          : $nsOut with Vs  = ???
         |  def apply(vs   : Option[Seq[t]])     : $nsOut with CVs = ???
         |  def apply(set  : Option[Set[t]])     : $nsOut with Cs  = ???
         |  def apply(sets : Option[Seq[Set[t]]]): $nsOut with CCs = ???
         |  def ==   (set  : Option[Set[t]])     : $nsOut with Cs  = ???
         |  def ==   (sets : Option[Seq[Set[t]]]): $nsOut with CCs = ???
         |  def not  (v    : Option[t])          : $nsOut with Vs  = ???
         |  def not  (vs   : Option[Seq[t]])     : $nsOut with CVs = ???
         |  def not  (set  : Option[Set[t]])     : $nsOut with Cs  = ???
         |  def not  (sets : Option[Seq[Set[t]]]): $nsOut with CCs = ???
         |  def !=   (set  : Option[Set[t]])     : $nsOut with Cs  = ???
         |  def !=   (sets : Option[Seq[Set[t]]]): $nsOut with CCs = ???
         |  def <    (upper: Option[t])          : $nsOut          = ???
         |  def <=   (upper: Option[t])          : $nsOut          = ???
         |  def >    (lower: Option[t])          : $nsOut          = ???
         |  def >=   (lower: Option[t])          : $nsOut          = ???
         |}""".stripMargin
  }
}
