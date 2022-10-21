/*
* AUTO-GENERATED Molecule DSL for namespace `Log`
*
* To change:
* 1. Edit data model in molecule.boilerplate.api.generic.dsl.dataModel.Log
* 2. `sbt clean compile -Dmolecule=true`
*/
package molecule.boilerplate.api.generic.dsl.Log

import java.util.Date
import molecule.boilerplate.api.expression._
import molecule.boilerplate.api.generic.dsl.Log.Log_._
import molecule.boilerplate.markers.namespaceMarkers._
import scala.language.higherKinds


trait Log_0 extends Molecule_00 {
  final lazy val e          : Log_1_OneM[e         , Long   , Long   ] = ???
  final lazy val a          : Log_1_OneM[a         , String , String ] = ???
  final lazy val v          : Log_1_OneM[v         , Any    , Any    ] = ???
  final lazy val t          : Log_1_OneM[t         , Long   , Long   ] = ???
  final lazy val tx         : Log_1_OneM[tx        , Long   , Long   ] = ???
  final lazy val txInstant  : Log_1_OneM[txInstant , Date   , Date   ] = ???
  final lazy val op         : Log_1_OneM[op        , Boolean, Boolean] = ???
}

