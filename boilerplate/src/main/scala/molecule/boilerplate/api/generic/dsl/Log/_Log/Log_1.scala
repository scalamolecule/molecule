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


trait Log_1[A] extends Molecule_01[A] {
  final lazy val e          : Log_2_OneM[e         , Long   , A, Long   ] = ???
  final lazy val a          : Log_2_OneM[a         , String , A, String ] = ???
  final lazy val v          : Log_2_OneM[v         , Any    , A, Any    ] = ???
  final lazy val t          : Log_2_OneM[t         , Long   , A, Long   ] = ???
  final lazy val tx         : Log_2_OneM[tx        , Long   , A, Long   ] = ???
  final lazy val txInstant  : Log_2_OneM[txInstant , Date   , A, Date   ] = ???
  final lazy val op         : Log_2_OneM[op        , Boolean, A, Boolean] = ???
}

trait Log_1_OneM[Attr, t, A] extends Log_1[A] with ExprOneM_1[Attr, t, A, Log_1_OneM]

