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


trait VAET_5[A, B, C, D, E] extends Molecule_05[A, B, C, D, E] {
  final lazy val e          : VAET_6_OneM[e         , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val a          : VAET_6_OneM[a         , String , A, B, C, D, E, String ] = ???
  final lazy val v          : VAET_6_OneM[v         , Any    , A, B, C, D, E, Any    ] = ???
  final lazy val t          : VAET_6_OneM[t         , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val tx         : VAET_6_OneM[tx        , Long   , A, B, C, D, E, Long   ] = ???
  final lazy val txInstant  : VAET_6_OneM[txInstant , Date   , A, B, C, D, E, Date   ] = ???
  final lazy val op         : VAET_6_OneM[op        , Boolean, A, B, C, D, E, Boolean] = ???
}

trait VAET_5_OneM[Attr, t, A, B, C, D, E] extends VAET_5[A, B, C, D, E] with ExprOneM_5[Attr, t, A, B, C, D, E, VAET_5_OneM]

