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


trait Log_3[A, B, C] extends Molecule_03[A, B, C] {
  final lazy val e          : Log_4_OneM[e         , Long   , A, B, C, Long   ] = ???
  final lazy val a          : Log_4_OneM[a         , String , A, B, C, String ] = ???
  final lazy val v          : Log_4_OneM[v         , Any    , A, B, C, Any    ] = ???
  final lazy val t          : Log_4_OneM[t         , Long   , A, B, C, Long   ] = ???
  final lazy val tx         : Log_4_OneM[tx        , Long   , A, B, C, Long   ] = ???
  final lazy val txInstant  : Log_4_OneM[txInstant , Date   , A, B, C, Date   ] = ???
  final lazy val op         : Log_4_OneM[op        , Boolean, A, B, C, Boolean] = ???
}

trait Log_3_OneM[Attr, t, A, B, C] extends Log_3[A, B, C] with ExprOneM_3[Attr, t, A, B, C, Log_3_OneM]

