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


trait VAET_6[A, B, C, D, E, F] extends Molecule_06[A, B, C, D, E, F] {
  final lazy val e          : VAET_7_OneM[e         , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val a          : VAET_7_OneM[a         , String , A, B, C, D, E, F, String ] = ???
  final lazy val v          : VAET_7_OneM[v         , Any    , A, B, C, D, E, F, Any    ] = ???
  final lazy val t          : VAET_7_OneM[t         , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val tx         : VAET_7_OneM[tx        , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val txInstant  : VAET_7_OneM[txInstant , Date   , A, B, C, D, E, F, Date   ] = ???
  final lazy val op         : VAET_7_OneM[op        , Boolean, A, B, C, D, E, F, Boolean] = ???
}

trait VAET_6_OneM[Attr, t, A, B, C, D, E, F] extends VAET_6[A, B, C, D, E, F] with ExprOneM_6[Attr, t, A, B, C, D, E, F, VAET_6_OneM]

