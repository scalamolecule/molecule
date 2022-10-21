/*
* AUTO-GENERATED Molecule DSL for namespace `AEVT`
*
* To change:
* 1. Edit data model in molecule.boilerplate.api.generic.dsl.dataModel.AEVT
* 2. `sbt clean compile -Dmolecule=true`
*/
package molecule.boilerplate.api.generic.dsl.AEVT

import java.util.Date
import molecule.boilerplate.api.expression._
import molecule.boilerplate.api.generic.dsl.AEVT.AEVT_._
import molecule.boilerplate.markers.namespaceMarkers._
import scala.language.higherKinds


trait AEVT_6[A, B, C, D, E, F] extends Molecule_06[A, B, C, D, E, F] {
  final lazy val e          : AEVT_7_OneM[e         , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val a          : AEVT_7_OneM[a         , String , A, B, C, D, E, F, String ] = ???
  final lazy val v          : AEVT_7_OneM[v         , Any    , A, B, C, D, E, F, Any    ] = ???
  final lazy val t          : AEVT_7_OneM[t         , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val tx         : AEVT_7_OneM[tx        , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val txInstant  : AEVT_7_OneM[txInstant , Date   , A, B, C, D, E, F, Date   ] = ???
  final lazy val op         : AEVT_7_OneM[op        , Boolean, A, B, C, D, E, F, Boolean] = ???
}

trait AEVT_6_OneM[Attr, t, A, B, C, D, E, F] extends AEVT_6[A, B, C, D, E, F] with ExprOneM_6[Attr, t, A, B, C, D, E, F, AEVT_6_OneM]

