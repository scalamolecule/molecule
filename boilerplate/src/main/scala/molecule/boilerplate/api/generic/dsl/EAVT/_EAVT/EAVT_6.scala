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


trait EAVT_6[A, B, C, D, E, F] extends Molecule_06[A, B, C, D, E, F] {
  final lazy val e          : EAVT_7_OneM[e         , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val a          : EAVT_7_OneM[a         , String , A, B, C, D, E, F, String ] = ???
  final lazy val v          : EAVT_7_OneM[v         , Any    , A, B, C, D, E, F, Any    ] = ???
  final lazy val t          : EAVT_7_OneM[t         , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val tx         : EAVT_7_OneM[tx        , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val txInstant  : EAVT_7_OneM[txInstant , Date   , A, B, C, D, E, F, Date   ] = ???
  final lazy val op         : EAVT_7_OneM[op        , Boolean, A, B, C, D, E, F, Boolean] = ???
}

trait EAVT_6_OneM[Attr, t, A, B, C, D, E, F] extends EAVT_6[A, B, C, D, E, F] with ExprOneM_6[Attr, t, A, B, C, D, E, F, EAVT_6_OneM]

