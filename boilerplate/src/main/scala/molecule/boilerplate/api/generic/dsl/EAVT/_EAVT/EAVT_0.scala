/*
* AUTO-GENERATED Molecule DSL for namespace `EAVT`
*
* To change:
* 1. Edit data model in molecule.boilerplate.api.generic.dsl.dataModel.EAVT
* 2. `sbt clean compile -Dmolecule=true`
*/
package molecule.boilerplate.api.generic.dsl.EAVT

import java.util.Date
import molecule.boilerplate.api.expression._
import molecule.boilerplate.api.generic.dsl.EAVT.EAVT_._
import molecule.boilerplate.markers.namespaceMarkers._
import scala.language.higherKinds


trait EAVT_0 extends Molecule_00 {
  final lazy val e          : EAVT_1_OneM[e         , Long   , Long   ] = ???
  final lazy val a          : EAVT_1_OneM[a         , String , String ] = ???
  final lazy val v          : EAVT_1_OneM[v         , Any    , Any    ] = ???
  final lazy val t          : EAVT_1_OneM[t         , Long   , Long   ] = ???
  final lazy val tx         : EAVT_1_OneM[tx        , Long   , Long   ] = ???
  final lazy val txInstant  : EAVT_1_OneM[txInstant , Date   , Date   ] = ???
  final lazy val op         : EAVT_1_OneM[op        , Boolean, Boolean] = ???
}

