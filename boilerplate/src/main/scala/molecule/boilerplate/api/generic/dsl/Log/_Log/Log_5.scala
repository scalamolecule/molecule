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


trait Log_5[A, B, C, D, E] extends Molecule_05[A, B, C, D, E] {
  final lazy val e          : Log_6_OneM[e         , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val a          : Log_6_OneM[a         , String , A, B, C, D, E, String ] = ???
  final lazy val v          : Log_6_OneM[v         , Any    , A, B, C, D, E, Any    ] = ???
  final lazy val t          : Log_6_OneM[t         , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val tx         : Log_6_OneM[tx        , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val txInstant  : Log_6_OneM[txInstant , Date   , A, B, C, D, E, Date   ] = ???
  final lazy val op         : Log_6_OneM[op        , Boolean, A, B, C, D, E, Boolean] = ???
}

trait Log_5_OneM[Attr, t, A, B, C, D, E] extends Log_5[A, B, C, D, E] with ExprOneM_5[Attr, t, A, B, C, D, E, Log_5_OneM]

