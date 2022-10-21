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


trait EAVT_5[A, B, C, D, E] extends Molecule_05[A, B, C, D, E] {
  final lazy val e          : EAVT_6_OneM[e         , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val a          : EAVT_6_OneM[a         , String , A, B, C, D, E, String ] = ???
  final lazy val v          : EAVT_6_OneM[v         , Any    , A, B, C, D, E, Any    ] = ???
  final lazy val t          : EAVT_6_OneM[t         , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val tx         : EAVT_6_OneM[tx        , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val txInstant  : EAVT_6_OneM[txInstant , Date   , A, B, C, D, E, Date   ] = ???
  final lazy val op         : EAVT_6_OneM[op        , Boolean, A, B, C, D, E, Boolean] = ???
}

trait EAVT_5_OneM[Attr, t, A, B, C, D, E] extends EAVT_5[A, B, C, D, E] with ExprOneM_5[Attr, t, A, B, C, D, E, EAVT_5_OneM]

