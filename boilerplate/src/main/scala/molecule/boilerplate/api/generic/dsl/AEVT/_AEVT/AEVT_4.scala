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


trait AEVT_4[A, B, C, D] extends Molecule_04[A, B, C, D] {
  final lazy val e          : AEVT_5_OneM[e         , Long   , A, B, C, D, Long   ] = ???
  final lazy val a          : AEVT_5_OneM[a         , String , A, B, C, D, String ] = ???
  final lazy val v          : AEVT_5_OneM[v         , Any    , A, B, C, D, Any    ] = ???
  final lazy val t          : AEVT_5_OneM[t         , Long   , A, B, C, D, Long   ] = ???
  final lazy val tx         : AEVT_5_OneM[tx        , Long   , A, B, C, D, Long   ] = ???
  final lazy val txInstant  : AEVT_5_OneM[txInstant , Date   , A, B, C, D, Date   ] = ???
  final lazy val op         : AEVT_5_OneM[op        , Boolean, A, B, C, D, Boolean] = ???
}

trait AEVT_4_OneM[Attr, t, A, B, C, D] extends AEVT_4[A, B, C, D] with ExprOneM_4[Attr, t, A, B, C, D, AEVT_4_OneM]

