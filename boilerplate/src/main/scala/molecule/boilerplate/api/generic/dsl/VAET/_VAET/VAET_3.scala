/*
* AUTO-GENERATED Molecule DSL for namespace `VAET`
*
* To change:
* 1. Edit data model in molecule.boilerplate.api.generic.dsl.dataModel.VAET
* 2. `sbt clean compile -Dmolecule=true`
*/
package molecule.boilerplate.api.generic.dsl.VAET

import java.util.Date
import molecule.boilerplate.api.expression._
import molecule.boilerplate.api.generic.dsl.VAET.VAET_._
import molecule.boilerplate.markers.namespaceMarkers._
import scala.language.higherKinds


trait VAET_3[A, B, C] extends Molecule_03[A, B, C] {
  final lazy val e          : VAET_4_OneM[e         , Long   , A, B, C, Long   ] = ???
  final lazy val a          : VAET_4_OneM[a         , String , A, B, C, String ] = ???
  final lazy val v          : VAET_4_OneM[v         , Any    , A, B, C, Any    ] = ???
  final lazy val t          : VAET_4_OneM[t         , Long   , A, B, C, Long   ] = ???
  final lazy val tx         : VAET_4_OneM[tx        , Long   , A, B, C, Long   ] = ???
  final lazy val txInstant  : VAET_4_OneM[txInstant , Date   , A, B, C, Date   ] = ???
  final lazy val op         : VAET_4_OneM[op        , Boolean, A, B, C, Boolean] = ???
}

trait VAET_3_OneM[Attr, t, A, B, C] extends VAET_3[A, B, C] with ExprOneM_3[Attr, t, A, B, C, VAET_3_OneM]

