package codegen.boilerplate.api

import codegen.BoilerplateGenBase

object _SortAttrs extends BoilerplateGenBase("SortAttrs", "/api") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |molecule.boilerplate.api
       |
       |$traits""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns[${`_, _`}]] {
         |  protected def _addSort(sort: String): Ns[${`A..V`}, t]
         |}
         |trait ${fileName}_$arity[${`A..V`}, t, Ns[${`_, _`}]] extends ${fileName}Ops_$arity[${`A..V`}, t, Ns] {
         |  def a1: Ns[${`A..V`}, t] = _addSort("a1")
         |  def a2: Ns[${`A..V`}, t] = _addSort("a2")
         |  def a3: Ns[${`A..V`}, t] = _addSort("a3")
         |  def a4: Ns[${`A..V`}, t] = _addSort("a4")
         |  def a5: Ns[${`A..V`}, t] = _addSort("a5")
         |  def d1: Ns[${`A..V`}, t] = _addSort("d1")
         |  def d2: Ns[${`A..V`}, t] = _addSort("d2")
         |  def d3: Ns[${`A..V`}, t] = _addSort("d3")
         |  def d4: Ns[${`A..V`}, t] = _addSort("d4")
         |  def d5: Ns[${`A..V`}, t] = _addSort("d5")
         |}""".stripMargin
  }
}
