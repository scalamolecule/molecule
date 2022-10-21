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


trait AVET_3[A, B, C] extends Molecule_03[A, B, C] {
  final lazy val e          : AVET_4_OneM[e         , Long   , A, B, C, Long   ] = ???
  final lazy val a          : AVET_4_OneM[a         , String , A, B, C, String ] = ???
  final lazy val v          : AVET_4_OneM[v         , Any    , A, B, C, Any    ] = ???
  final lazy val t          : AVET_4_OneM[t         , Long   , A, B, C, Long   ] = ???
  final lazy val tx         : AVET_4_OneM[tx        , Long   , A, B, C, Long   ] = ???
  final lazy val txInstant  : AVET_4_OneM[txInstant , Date   , A, B, C, Date   ] = ???
  final lazy val op         : AVET_4_OneM[op        , Boolean, A, B, C, Boolean] = ???
}

trait AVET_3_OneM[Attr, t, A, B, C] extends AVET_3[A, B, C] with ExprOneM_3[Attr, t, A, B, C, AVET_3_OneM]

