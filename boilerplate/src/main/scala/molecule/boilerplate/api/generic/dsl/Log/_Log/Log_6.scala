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


trait Log_6[A, B, C, D, E, F] extends Molecule_06[A, B, C, D, E, F] {
  final lazy val e          : Log_7_OneM[e         , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val a          : Log_7_OneM[a         , String , A, B, C, D, E, F, String ] = ???
  final lazy val v          : Log_7_OneM[v         , Any    , A, B, C, D, E, F, Any    ] = ???
  final lazy val t          : Log_7_OneM[t         , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val tx         : Log_7_OneM[tx        , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val txInstant  : Log_7_OneM[txInstant , Date   , A, B, C, D, E, F, Date   ] = ???
  final lazy val op         : Log_7_OneM[op        , Boolean, A, B, C, D, E, F, Boolean] = ???
}

trait Log_6_OneM[Attr, t, A, B, C, D, E, F] extends Log_6[A, B, C, D, E, F] with ExprOneM_6[Attr, t, A, B, C, D, E, F, Log_6_OneM]

