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


trait VAET_1[A] extends Molecule_01[A] {
  final lazy val e          : VAET_2_OneM[e         , Long   , A, Long   ] = ???
  final lazy val a          : VAET_2_OneM[a         , String , A, String ] = ???
  final lazy val v          : VAET_2_OneM[v         , Any    , A, Any    ] = ???
  final lazy val t          : VAET_2_OneM[t         , Long   , A, Long   ] = ???
  final lazy val tx         : VAET_2_OneM[tx        , Long   , A, Long   ] = ???
  final lazy val txInstant  : VAET_2_OneM[txInstant , Date   , A, Date   ] = ???
  final lazy val op         : VAET_2_OneM[op        , Boolean, A, Boolean] = ???
}

trait VAET_1_OneM[Attr, t, A] extends VAET_1[A] with ExprOneM_1[Attr, t, A, VAET_1_OneM]

