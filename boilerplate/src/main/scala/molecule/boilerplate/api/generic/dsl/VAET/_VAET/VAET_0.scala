/*
* AUTO-GENERATED Molecule DSL for namespace `VAET`
*
* To change:
* 1. Edit data model in molecule.boilerplate.api.generic.dsl.dataModel.VAET
* 2. `sbt clean compile -Dmolecule=true`
*/
package molecule.boilerplate.api.generic.dsl.VAET

import java.util.Date
import molecule.boilerplate.api.expression._
import molecule.boilerplate.api.generic.dsl.VAET.VAET_._
import molecule.boilerplate.markers.namespaceMarkers._
import scala.language.higherKinds


trait VAET_0 extends Molecule_00 {
  final lazy val e          : VAET_1_OneM[e         , Long   , Long   ] = ???
  final lazy val a          : VAET_1_OneM[a         , String , String ] = ???
  final lazy val v          : VAET_1_OneM[v         , Any    , Any    ] = ???
  final lazy val t          : VAET_1_OneM[t         , Long   , Long   ] = ???
  final lazy val tx         : VAET_1_OneM[tx        , Long   , Long   ] = ???
  final lazy val txInstant  : VAET_1_OneM[txInstant , Date   , Date   ] = ???
  final lazy val op         : VAET_1_OneM[op        , Boolean, Boolean] = ???
}

