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


trait EAVT_2[A, B] extends Molecule_02[A, B] {
  final lazy val e          : EAVT_3_OneM[e         , Long   , A, B, Long   ] = ???
  final lazy val a          : EAVT_3_OneM[a         , String , A, B, String ] = ???
  final lazy val v          : EAVT_3_OneM[v         , Any    , A, B, Any    ] = ???
  final lazy val t          : EAVT_3_OneM[t         , Long   , A, B, Long   ] = ???
  final lazy val tx         : EAVT_3_OneM[tx        , Long   , A, B, Long   ] = ???
  final lazy val txInstant  : EAVT_3_OneM[txInstant , Date   , A, B, Date   ] = ???
  final lazy val op         : EAVT_3_OneM[op        , Boolean, A, B, Boolean] = ???
}

trait EAVT_2_OneM[Attr, t, A, B] extends EAVT_2[A, B] with ExprOneM_2[Attr, t, A, B, EAVT_2_OneM]

