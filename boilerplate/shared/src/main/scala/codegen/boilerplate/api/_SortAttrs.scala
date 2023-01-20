package codegen.boilerplate.api

import codegen.BoilerplateGenBase

object _SortAttrs extends BoilerplateGenBase("SortAttrs", "/api") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api
       |
       |$traits""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns[${`_, _`}]] {
         |  protected def _sort(sort: String): Ns[${`A..V`}, t]
         |}
         |trait ${fileName}_$arity[${`A..V`}, t, Ns[${`_, _`}]] extends ${fileName}Ops_$arity[${`A..V`}, t, Ns] {
         |  def a1: Ns[${`A..V`}, t] = _sort("a1")
         |  def a2: Ns[${`A..V`}, t] = _sort("a2")
         |  def a3: Ns[${`A..V`}, t] = _sort("a3")
         |  def a4: Ns[${`A..V`}, t] = _sort("a4")
         |  def a5: Ns[${`A..V`}, t] = _sort("a5")
         |  def d1: Ns[${`A..V`}, t] = _sort("d1")
         |  def d2: Ns[${`A..V`}, t] = _sort("d2")
         |  def d3: Ns[${`A..V`}, t] = _sort("d3")
         |  def d4: Ns[${`A..V`}, t] = _sort("d4")
         |  def d5: Ns[${`A..V`}, t] = _sort("d5")
         |}""".stripMargin
  }
}
