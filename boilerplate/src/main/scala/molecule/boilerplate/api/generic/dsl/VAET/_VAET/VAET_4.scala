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


trait VAET_4[A, B, C, D] extends Molecule_04[A, B, C, D] {
  final lazy val e          : VAET_5_OneM[e         , Long   , A, B, C, D, Long   ] = ???
  final lazy val a          : VAET_5_OneM[a         , String , A, B, C, D, String ] = ???
  final lazy val v          : VAET_5_OneM[v         , Any    , A, B, C, D, Any    ] = ???
  final lazy val t          : VAET_5_OneM[t         , Long   , A, B, C, D, Long   ] = ???
  final lazy val tx         : VAET_5_OneM[tx        , Long   , A, B, C, D, Long   ] = ???
  final lazy val txInstant  : VAET_5_OneM[txInstant , Date   , A, B, C, D, Date   ] = ???
  final lazy val op         : VAET_5_OneM[op        , Boolean, A, B, C, D, Boolean] = ???
}

trait VAET_4_OneM[Attr, t, A, B, C, D] extends VAET_4[A, B, C, D] with ExprOneM_4[Attr, t, A, B, C, D, VAET_4_OneM]

