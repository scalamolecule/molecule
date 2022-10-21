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


trait EAVT_1[A] extends Molecule_01[A] {
  final lazy val e          : EAVT_2_OneM[e         , Long   , A, Long   ] = ???
  final lazy val a          : EAVT_2_OneM[a         , String , A, String ] = ???
  final lazy val v          : EAVT_2_OneM[v         , Any    , A, Any    ] = ???
  final lazy val t          : EAVT_2_OneM[t         , Long   , A, Long   ] = ???
  final lazy val tx         : EAVT_2_OneM[tx        , Long   , A, Long   ] = ???
  final lazy val txInstant  : EAVT_2_OneM[txInstant , Date   , A, Date   ] = ???
  final lazy val op         : EAVT_2_OneM[op        , Boolean, A, Boolean] = ???
}

trait EAVT_1_OneM[Attr, t, A] extends EAVT_1[A] with ExprOneM_1[Attr, t, A, EAVT_1_OneM]

