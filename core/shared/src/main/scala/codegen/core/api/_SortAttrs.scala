package codegen.core.api

import codegen.CoreGenBase

object _SortAttrs extends CoreGenBase("SortAttrs", "/api") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.api
       |
       |import molecule.base.error.ExecutionError
       |
       |$traits""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Entity[${`_, _`}]] {
         |  protected def _sort(sort: String): Entity[${`A..V`}, t]
         |  protected def _dynsort(i: Int): Entity[${`A..V`}, t] = {
         |    i match {
         |      case 0     => _sort("")
         |      case 1     => _sort("a1")
         |      case -1    => _sort("d1")
         |      case 2     => _sort("a2")
         |      case -2    => _sort("d2")
         |      case 3     => _sort("a3")
         |      case -3    => _sort("d3")
         |      case 4     => _sort("a4")
         |      case -4    => _sort("d4")
         |      case 5     => _sort("a5")
         |      case -5    => _sort("d5")
         |      case other => throw ExecutionError(
         |        s"Please use 1 to 5 for ascending orders and -1 to -5 for descending orders. Found $$other"
         |      )
         |    }
         |  }
         |}
         |trait $fileName_$arity[${`A..V`}, t, Entity[${`_, _`}]] extends ${fileName}Ops_$arity[${`A..V`}, t, Entity] {
         |  def a1: Entity[${`A..V`}, t] = _sort("a1")
         |  def a2: Entity[${`A..V`}, t] = _sort("a2")
         |  def a3: Entity[${`A..V`}, t] = _sort("a3")
         |  def a4: Entity[${`A..V`}, t] = _sort("a4")
         |  def a5: Entity[${`A..V`}, t] = _sort("a5")
         |  def d1: Entity[${`A..V`}, t] = _sort("d1")
         |  def d2: Entity[${`A..V`}, t] = _sort("d2")
         |  def d3: Entity[${`A..V`}, t] = _sort("d3")
         |  def d4: Entity[${`A..V`}, t] = _sort("d4")
         |  def d5: Entity[${`A..V`}, t] = _sort("d5")
         |  def sort(i: Int): Entity[${`A..V`}, t] = _dynsort(i)
         |}""".stripMargin
  }
}
