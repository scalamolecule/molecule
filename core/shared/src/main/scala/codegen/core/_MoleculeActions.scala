package codegen.core

import codegen.CoreGenBase

object _MoleculeActions extends CoreGenBase( "MoleculeActions", "") {

  val content = {
    val moleculeFactories = (1 to 22).map(arity => MoleculeFactories(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core
       |
       |import molecule.core.api._
       |import molecule.core.action._
       |import scala.language.implicitConversions
       |
       |trait $fileName_ extends Keywords {
       |  implicit final class _actions0(molecule: Molecule_00){
       |    def delete = Delete(molecule.elements)
       |  }
       |
       |$moleculeFactories
       |}""".stripMargin
  }

  case class MoleculeFactories(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""  implicit final class _actions$arity[${`A..V`}](molecule: Molecule_$n0[${`A..V`}]) {
         |    def save   = Save(molecule.elements)
         |    def insert = Insert_$arity[${`A..V`}](molecule.elements)
         |    def query  = Query[${`(A..V)`}](molecule.elements)
         |    def update = Update(molecule.elements)
         |    def upsert = Update(molecule.elements, true)
         |    def delete = Delete(molecule.elements)
         |  }
         |  """.stripMargin
  }
}
