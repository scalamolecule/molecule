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


trait VAET_2[A, B] extends Molecule_02[A, B] {
  final lazy val e          : VAET_3_OneM[e         , Long   , A, B, Long   ] = ???
  final lazy val a          : VAET_3_OneM[a         , String , A, B, String ] = ???
  final lazy val v          : VAET_3_OneM[v         , Any    , A, B, Any    ] = ???
  final lazy val t          : VAET_3_OneM[t         , Long   , A, B, Long   ] = ???
  final lazy val tx         : VAET_3_OneM[tx        , Long   , A, B, Long   ] = ???
  final lazy val txInstant  : VAET_3_OneM[txInstant , Date   , A, B, Date   ] = ???
  final lazy val op         : VAET_3_OneM[op        , Boolean, A, B, Boolean] = ???
}

trait VAET_2_OneM[Attr, t, A, B] extends VAET_2[A, B] with ExprOneM_2[Attr, t, A, B, VAET_2_OneM]

