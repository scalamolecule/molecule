/*
* AUTO-GENERATED Molecule DSL for namespace `AVET`
*
* To change:
* 1. Edit data model in molecule.boilerplate.api.generic.dsl.dataModel.AVET
* 2. `sbt clean compile -Dmolecule=true`
*/
package molecule.boilerplate.api.generic.dsl.AVET

import java.util.Date
import molecule.boilerplate.api.expression._
import molecule.boilerplate.api.generic.dsl.AVET.AVET_._
import molecule.boilerplate.markers.namespaceMarkers._
import scala.language.higherKinds


trait AVET_6[A, B, C, D, E, F] extends Molecule_06[A, B, C, D, E, F] {
  final lazy val e          : AVET_7_OneM[e         , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val a          : AVET_7_OneM[a         , String , A, B, C, D, E, F, String ] = ???
  final lazy val v          : AVET_7_OneM[v         , Any    , A, B, C, D, E, F, Any    ] = ???
  final lazy val t          : AVET_7_OneM[t         , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val tx         : AVET_7_OneM[tx        , Long   , A, B, C, D, E, F, Long   ] = ???
  final lazy val txInstant  : AVET_7_OneM[txInstant , Date   , A, B, C, D, E, F, Date   ] = ???
  final lazy val op         : AVET_7_OneM[op        , Boolean, A, B, C, D, E, F, Boolean] = ???
}

trait AVET_6_OneM[Attr, t, A, B, C, D, E, F] extends AVET_6[A, B, C, D, E, F] with ExprOneM_6[Attr, t, A, B, C, D, E, F, AVET_6_OneM]

