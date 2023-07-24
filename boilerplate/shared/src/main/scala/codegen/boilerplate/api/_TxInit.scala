//package codegen.boilerplate.api
//
//import codegen.BoilerplateGenBase
//
//object _TxInit extends BoilerplateGenBase("Nested2TxInit", "/api") {
//    val traits = (0 to 21).map(arity => Trait(arity).body).mkString("\n")
//  val content =
//    s"""// GENERATED CODE ********************************
//       |package molecule.boilerplate.api
//       |
//       |import molecule.boilerplate.ast.Model._
//       |
//       |$traits
//       |""".stripMargin
//
//  case class Trait(arity: Int) extends TemplateVals(arity) {
//    val body =
//      s"""
//         |class TxInit_$n0[${`A..V, `}Tpl, Tx[${`_, _`}]](tx: Tx[${`A..V, `}Seq[Tpl]]) extends Molecule_$n1[${`A..V, `}Seq[Tpl]] {
//         |  def Tx: Tx[${`A..V, `}Seq[Tpl]] = tx
//         |  override private[molecule] val elements: List[Element] = ???
//         |}""".stripMargin
//  }
//}
