/*
* AUTO-GENERATED Molecule DSL for namespace `AVET`
*
* To change:
* 1. Edit data model in molecule.boilerplate.api.generic.dsl.dataModel.AVET
* 2. `sbt clean compile -Dmolecule=true`
*/
package molecule.boilerplate.api.generic.dsl.AVET

import java.util.Date
import molecule.boilerplate.api.expression._
import molecule.boilerplate.api.generic.dsl.AVET.AVET_._
import molecule.boilerplate.markers.namespaceMarkers._
import scala.language.higherKinds


trait AVET_0 extends Molecule_00 {
  final lazy val e          : AVET_1_OneM[e         , Long   , Long   ] = ???
  final lazy val a          : AVET_1_OneM[a         , String , String ] = ???
  final lazy val v          : AVET_1_OneM[v         , Any    , Any    ] = ???
  final lazy val t          : AVET_1_OneM[t         , Long   , Long   ] = ???
  final lazy val tx         : AVET_1_OneM[tx        , Long   , Long   ] = ???
  final lazy val txInstant  : AVET_1_OneM[txInstant , Date   , Date   ] = ???
  final lazy val op         : AVET_1_OneM[op        , Boolean, Boolean] = ???
}

