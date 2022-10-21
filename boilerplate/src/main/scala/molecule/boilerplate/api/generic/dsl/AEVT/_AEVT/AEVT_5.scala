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


trait AEVT_5[A, B, C, D, E] extends Molecule_05[A, B, C, D, E] {
  final lazy val e          : AEVT_6_OneM[e         , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val a          : AEVT_6_OneM[a         , String , A, B, C, D, E, String ] = ???
  final lazy val v          : AEVT_6_OneM[v         , Any    , A, B, C, D, E, Any    ] = ???
  final lazy val t          : AEVT_6_OneM[t         , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val tx         : AEVT_6_OneM[tx        , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val txInstant  : AEVT_6_OneM[txInstant , Date   , A, B, C, D, E, Date   ] = ???
  final lazy val op         : AEVT_6_OneM[op        , Boolean, A, B, C, D, E, Boolean] = ???
}

trait AEVT_5_OneM[Attr, t, A, B, C, D, E] extends AEVT_5[A, B, C, D, E] with ExprOneM_5[Attr, t, A, B, C, D, E, AEVT_5_OneM]

