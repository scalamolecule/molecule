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


trait AEVT_1[A] extends Molecule_01[A] {
  final lazy val e          : AEVT_2_OneM[e         , Long   , A, Long   ] = ???
  final lazy val a          : AEVT_2_OneM[a         , String , A, String ] = ???
  final lazy val v          : AEVT_2_OneM[v         , Any    , A, Any    ] = ???
  final lazy val t          : AEVT_2_OneM[t         , Long   , A, Long   ] = ???
  final lazy val tx         : AEVT_2_OneM[tx        , Long   , A, Long   ] = ???
  final lazy val txInstant  : AEVT_2_OneM[txInstant , Date   , A, Date   ] = ???
  final lazy val op         : AEVT_2_OneM[op        , Boolean, A, Boolean] = ???
}

trait AEVT_1_OneM[Attr, t, A] extends AEVT_1[A] with ExprOneM_1[Attr, t, A, AEVT_1_OneM]

