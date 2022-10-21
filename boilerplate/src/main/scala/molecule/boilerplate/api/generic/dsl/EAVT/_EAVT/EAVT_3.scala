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


trait EAVT_3[A, B, C] extends Molecule_03[A, B, C] {
  final lazy val e          : EAVT_4_OneM[e         , Long   , A, B, C, Long   ] = ???
  final lazy val a          : EAVT_4_OneM[a         , String , A, B, C, String ] = ???
  final lazy val v          : EAVT_4_OneM[v         , Any    , A, B, C, Any    ] = ???
  final lazy val t          : EAVT_4_OneM[t         , Long   , A, B, C, Long   ] = ???
  final lazy val tx         : EAVT_4_OneM[tx        , Long   , A, B, C, Long   ] = ???
  final lazy val txInstant  : EAVT_4_OneM[txInstant , Date   , A, B, C, Date   ] = ???
  final lazy val op         : EAVT_4_OneM[op        , Boolean, A, B, C, Boolean] = ???
}

trait EAVT_3_OneM[Attr, t, A, B, C] extends EAVT_3[A, B, C] with ExprOneM_3[Attr, t, A, B, C, EAVT_3_OneM]

