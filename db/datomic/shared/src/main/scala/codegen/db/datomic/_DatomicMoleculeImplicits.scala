package codegen.db.datomic

import codegen.DatomicGenBase

object _DatomicMoleculeImplicits extends DatomicGenBase( "package", "") {

  val content = {
    val traits = (1 to 22).map(arity => ImplicitDefs(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db
       |
       |import molecule.boilerplate.markers.NamespaceMarkers._
       |import molecule.core.MoleculeImplicits
       |import molecule.db.datomic.api.DatomicMoleculeApi._
       |import scala.language.implicitConversions
       |
       |package object datomic extends MoleculeImplicits {
       |$traits
       |}""".stripMargin
  }

  case class ImplicitDefs(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""  implicit final override def m[${`A..V`}](molecule: Molecule_$n0[${`A..V`}]): DatomicMoleculeApi_$n0[${`A..V`}] = new DatomicMoleculeApi_$n0(molecule)""".stripMargin
  }
}
