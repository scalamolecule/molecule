package codegen.core

import codegen.CoreGenBase

object _MoleculeImplicits extends CoreGenBase( "MoleculeImplicits", "") {

  val content = {
    val traits = (1 to 22).map(arity => ImplicitDefs(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core
       |
       |import molecule.boilerplate.api._
       |import molecule.core.api._
       |import scala.language.implicitConversions
       |
       |trait ${fileName}_ {
       |$traits
       |}""".stripMargin
  }

  case class ImplicitDefs(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""  implicit def m[${`A..V`}](molecule: Molecule_$n0[${`A..V`}]): MoleculeApi_$n0[${`A..V`}]""".stripMargin
  }
}
