package molecule.base.codegen.render

import molecule.base.util.CodeGenTemplate


case class Dsl_CompositeTxs(maxArity: Int) extends CodeGenTemplate("CompositeTx", "/api", "") {
  val allTt  = (1 to maxArity).map(i => s"T$i").mkString(", ")
  val max    = allTt.length
  val maxPad = " " * max

  case class Method(arity: Int) extends TemplateVals(arity) {
    val tt       = (1 to arity).map(i => s"T$i").mkString(", ")
    val ttPadded = tt + padS(max, tt)
    val body     = s"""override def _compositeTx_$n0[$ttPadded](composites: List[Element]): Tx_$n_[$ttPadded, Nothing] with TxMetaData_$n_[$ttPadded] = new Tx_$n_[$ttPadded, Nothing](composites :+ Model.Ref(ns, "tx", "Tx", CardOne)) with TxMetaData_$n_[$ttPadded]"""
  }

  val dummies = if (maxArity == 22) "" else (maxArity + 2 to 23).map(i => s"X$i").mkString(", ", ", ", "")
  val tx_n    = (0 to maxArity).map(i => s"Tx_$i").mkString(", ") + dummies
  val methods = (1 to maxArity).map(arity => Method(arity).body).mkString("\n  ")

  val content =
    s"""
       |
       |class Txs_(ns: String) extends CompositeTx_[$tx_n] {
       |  override def _compositeTx_00  $maxPad(composites: List[Element]): Tx_0 [$maxPad  Nothing] with TxMetaData_0 $maxPad   = new Tx_0 [$maxPad  Nothing](composites :+ Model.Ref(ns, "tx", "Tx", CardOne)) with TxMetaData_0
       |  $methods
       |}""".stripMargin
}
