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


trait Log_2[A, B] extends Molecule_02[A, B] {
  final lazy val e          : Log_3_OneM[e         , Long   , A, B, Long   ] = ???
  final lazy val a          : Log_3_OneM[a         , String , A, B, String ] = ???
  final lazy val v          : Log_3_OneM[v         , Any    , A, B, Any    ] = ???
  final lazy val t          : Log_3_OneM[t         , Long   , A, B, Long   ] = ???
  final lazy val tx         : Log_3_OneM[tx        , Long   , A, B, Long   ] = ???
  final lazy val txInstant  : Log_3_OneM[txInstant , Date   , A, B, Date   ] = ???
  final lazy val op         : Log_3_OneM[op        , Boolean, A, B, Boolean] = ???
}

trait Log_2_OneM[Attr, t, A, B] extends Log_2[A, B] with ExprOneM_2[Attr, t, A, B, Log_2_OneM]

