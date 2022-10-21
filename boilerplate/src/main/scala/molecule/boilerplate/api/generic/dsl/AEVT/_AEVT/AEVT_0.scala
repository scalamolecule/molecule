/*
* AUTO-GENERATED Molecule DSL for namespace `AEVT`
*
* To change:
* 1. Edit data model in molecule.boilerplate.api.generic.dsl.dataModel.AEVT
* 2. `sbt clean compile -Dmolecule=true`
*/
package molecule.boilerplate.api.generic.dsl.AEVT

import java.util.Date
import molecule.boilerplate.api.expression._
import molecule.boilerplate.api.generic.dsl.AEVT.AEVT_._
import molecule.boilerplate.markers.namespaceMarkers._
import scala.language.higherKinds


trait AEVT_0 extends Molecule_00 {
  final lazy val e          : AEVT_1_OneM[e         , Long   , Long   ] = ???
  final lazy val a          : AEVT_1_OneM[a         , String , String ] = ???
  final lazy val v          : AEVT_1_OneM[v         , Any    , Any    ] = ???
  final lazy val t          : AEVT_1_OneM[t         , Long   , Long   ] = ???
  final lazy val tx         : AEVT_1_OneM[tx        , Long   , Long   ] = ???
  final lazy val txInstant  : AEVT_1_OneM[txInstant , Date   , Date   ] = ???
  final lazy val op         : AEVT_1_OneM[op        , Boolean, Boolean] = ???
}

