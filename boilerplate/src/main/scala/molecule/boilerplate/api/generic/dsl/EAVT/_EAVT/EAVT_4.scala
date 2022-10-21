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


trait EAVT_4[A, B, C, D] extends Molecule_04[A, B, C, D] {
  final lazy val e          : EAVT_5_OneM[e         , Long   , A, B, C, D, Long   ] = ???
  final lazy val a          : EAVT_5_OneM[a         , String , A, B, C, D, String ] = ???
  final lazy val v          : EAVT_5_OneM[v         , Any    , A, B, C, D, Any    ] = ???
  final lazy val t          : EAVT_5_OneM[t         , Long   , A, B, C, D, Long   ] = ???
  final lazy val tx         : EAVT_5_OneM[tx        , Long   , A, B, C, D, Long   ] = ???
  final lazy val txInstant  : EAVT_5_OneM[txInstant , Date   , A, B, C, D, Date   ] = ???
  final lazy val op         : EAVT_5_OneM[op        , Boolean, A, B, C, D, Boolean] = ???
}

trait EAVT_4_OneM[Attr, t, A, B, C, D] extends EAVT_4[A, B, C, D] with ExprOneM_4[Attr, t, A, B, C, D, EAVT_4_OneM]

