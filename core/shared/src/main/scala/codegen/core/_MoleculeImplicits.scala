package codegen.core

import codegen.CoreGenBase

object _MoleculeImplicits extends CoreGenBase( "MoleculeImplicits", "") {

  val content = {
    val moleculeFactories = (1 to 22).map(arity => MoleculeFactories(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core
       |
       |import molecule.boilerplate.api._
       |import molecule.core.action._
       |import scala.language.implicitConversions
       |
       |trait $fileName_ extends Keywords {
       |  implicit final def _molecule(molecule: Molecule_00): Actions_00 = new Actions_00(molecule)
       |$moleculeFactories
       |}""".stripMargin
  }

  case class MoleculeFactories(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""  implicit final def _molecule[${`A..V`}](molecule: Molecule_$n0[${`A..V`}]): Actions_$n0[${`A..V`}] = new Actions_$n0[${`A..V`}](molecule)""".stripMargin
  }
}
