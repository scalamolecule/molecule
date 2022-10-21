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


trait AEVT_2[A, B] extends Molecule_02[A, B] {
  final lazy val e          : AEVT_3_OneM[e         , Long   , A, B, Long   ] = ???
  final lazy val a          : AEVT_3_OneM[a         , String , A, B, String ] = ???
  final lazy val v          : AEVT_3_OneM[v         , Any    , A, B, Any    ] = ???
  final lazy val t          : AEVT_3_OneM[t         , Long   , A, B, Long   ] = ???
  final lazy val tx         : AEVT_3_OneM[tx        , Long   , A, B, Long   ] = ???
  final lazy val txInstant  : AEVT_3_OneM[txInstant , Date   , A, B, Date   ] = ???
  final lazy val op         : AEVT_3_OneM[op        , Boolean, A, B, Boolean] = ???
}

trait AEVT_2_OneM[Attr, t, A, B] extends AEVT_2[A, B] with ExprOneM_2[Attr, t, A, B, AEVT_2_OneM]

