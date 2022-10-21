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


trait AEVT_3[A, B, C] extends Molecule_03[A, B, C] {
  final lazy val e          : AEVT_4_OneM[e         , Long   , A, B, C, Long   ] = ???
  final lazy val a          : AEVT_4_OneM[a         , String , A, B, C, String ] = ???
  final lazy val v          : AEVT_4_OneM[v         , Any    , A, B, C, Any    ] = ???
  final lazy val t          : AEVT_4_OneM[t         , Long   , A, B, C, Long   ] = ???
  final lazy val tx         : AEVT_4_OneM[tx        , Long   , A, B, C, Long   ] = ???
  final lazy val txInstant  : AEVT_4_OneM[txInstant , Date   , A, B, C, Date   ] = ???
  final lazy val op         : AEVT_4_OneM[op        , Boolean, A, B, C, Boolean] = ???
}

trait AEVT_3_OneM[Attr, t, A, B, C] extends AEVT_3[A, B, C] with ExprOneM_3[Attr, t, A, B, C, AEVT_3_OneM]

