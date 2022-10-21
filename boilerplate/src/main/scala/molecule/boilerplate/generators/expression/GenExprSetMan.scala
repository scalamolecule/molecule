package molecule.boilerplate.generators.expression

import molecule.boilerplate.generators._Template

object GenExprSetMan extends _Template( "exprSetM", "expression") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""package molecule.boilerplate.api.expression
       |
       |import molecule.boilerplate.api.aggregates._
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
         |  extends Aggregates_$arity[Attr, t, ${`A..N`}, Ns]
         |    with SortAttrs_$arity[Attr, t, ${`A..N`}, Ns] {
         |  def apply  (v    : t, vs: t*)             : $nsOut with Vs  = ???
         |  def apply  (vs   : Seq[t])                : $nsOut with CVs = ???
         |  def apply  (set  : Set[t], sets: Set[t]*) : $nsOut with Cs  = ???
         |  def apply  (sets : Seq[Set[t]])           : $nsOut with CCs = ???
         |  def ==     (set  : Set[t])                : $nsOut with Cs  = ???
         |  def ==     (set  : Set[t], sets: Set[t]*) : $nsOut with Cs  = ???
         |  def ==     (sets : Seq[Set[t]])           : $nsOut with CCs = ???
         |  def not    (v    : t, vs: t*)             : $nsOut with Vs  = ???
         |  def not    (vs   : Seq[t])                : $nsOut with CVs = ???
         |  def not    (set  : Set[t], sets: Set[t]*) : $nsOut with Cs  = ???
         |  def not    (sets : Seq[Set[t]])           : $nsOut with CCs = ???
         |  def !=     (set  : Set[t])                : $nsOut with Cs  = ???
         |  def !=     (set  : Set[t], sets: Set[t]*) : $nsOut with Cs  = ???
         |  def !=     (sets : Seq[Set[t]])           : $nsOut with CCs = ???
         |  def <      (upper: t)                     : $nsOut          = ???
         |  def <=     (upper: t)                     : $nsOut          = ???
         |  def >      (lower: t)                     : $nsOut          = ???
         |  def >=     (lower: t)                     : $nsOut          = ???
         |  def assert (v    : t, vs: t*)             : $nsOut          = ???
         |  def assert (vs   : Seq[t])                : $nsOut          = ???
         |  def replace(ab   : (t, t), abs: (t, t)*)  : $nsOut          = ???
         |  def replace(abs  : Seq[(t, t)])           : $nsOut          = ???
         |  def retract(v    : t, vs: t*)             : $nsOut          = ???
         |  def retract(vs   : Seq[t])                : $nsOut          = ???
         |}""".stripMargin
  }
}
