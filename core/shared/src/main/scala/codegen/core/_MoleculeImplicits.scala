package codegen.core

import codegen.CoreGenBase

object _MoleculeImplicits extends CoreGenBase( "MoleculeImplicits", "") {

  val content = {
    val moleculeFactories = (1 to 22).map(arity => MoleculeFactories(arity).body).mkString("\n")
    val compositeFactories = (1 to 22).map(arity => CompositeFactories(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core
       |
       |import molecule.boilerplate.api._
       |import molecule.core.action._
       |import scala.language.implicitConversions
       |
       |trait ${fileName}_ extends Keywords {
       |  implicit def m(molecule: Molecule_00): Actions_00
       |$moleculeFactories
       |
       |  implicit def m(composite: Composite_00): Actions_00
       |$compositeFactories
       |}""".stripMargin
  }

  case class MoleculeFactories(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""  implicit def m[${`A..V`}](molecule: Molecule_$n0[${`A..V`}]): Actions_$n0[${`A..V`}]""".stripMargin
  }

  case class CompositeFactories(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""  implicit def m[$Tn](composite: Composite_$n0[$Tn]): Actions_$n0[$Tn]""".stripMargin
  }
}
