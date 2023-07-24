package codegen.boilerplate.api

import codegen.BoilerplateGenBase

object _CompositeTx extends BoilerplateGenBase("CompositeTx", "/api") {
  val allTt = (1 to 22).map(i => s"T$i").mkString(", ")
  val max   = allTt.length
  val maxPad = " " * max

  case class Method(arity: Int) extends TemplateVals(arity) {
    val tt       = (1 to arity).map(i => s"T$i").mkString(", ")
    val ttPadded = tt + padS(max, tt)
    val body     = s"def _compositeTx_$n0[$ttPadded](composites: List[Element]): Tx$n_[$ttPadded, Nothing] with TxMetaData_$n_[$ttPadded] = ???"
  }

  val methods = (1 to 22).map(arity => Method(arity).body).mkString("\n  ")
  val content =
    s"""// GENERATED CODE ********************************
       |package molecule.boilerplate.api
       |
       |import molecule.boilerplate.ast.Model._
       |
       |trait $fileName_[$txs_] {
       |  def _compositeTx_00  $maxPad(composites: List[Element]): Tx0 [$maxPad  Nothing] with TxMetaData_0 $maxPad   = ???
       |  $methods
       |}
       |""".stripMargin
}
