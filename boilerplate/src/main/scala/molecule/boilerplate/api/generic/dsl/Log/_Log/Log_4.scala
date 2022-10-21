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


trait Log_4[A, B, C, D] extends Molecule_04[A, B, C, D] {
  final lazy val e          : Log_5_OneM[e         , Long   , A, B, C, D, Long   ] = ???
  final lazy val a          : Log_5_OneM[a         , String , A, B, C, D, String ] = ???
  final lazy val v          : Log_5_OneM[v         , Any    , A, B, C, D, Any    ] = ???
  final lazy val t          : Log_5_OneM[t         , Long   , A, B, C, D, Long   ] = ???
  final lazy val tx         : Log_5_OneM[tx        , Long   , A, B, C, D, Long   ] = ???
  final lazy val txInstant  : Log_5_OneM[txInstant , Date   , A, B, C, D, Date   ] = ???
  final lazy val op         : Log_5_OneM[op        , Boolean, A, B, C, D, Boolean] = ???
}

trait Log_4_OneM[Attr, t, A, B, C, D] extends Log_4[A, B, C, D] with ExprOneM_4[Attr, t, A, B, C, D, Log_4_OneM]

