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


trait AVET_4[A, B, C, D] extends Molecule_04[A, B, C, D] {
  final lazy val e          : AVET_5_OneM[e         , Long   , A, B, C, D, Long   ] = ???
  final lazy val a          : AVET_5_OneM[a         , String , A, B, C, D, String ] = ???
  final lazy val v          : AVET_5_OneM[v         , Any    , A, B, C, D, Any    ] = ???
  final lazy val t          : AVET_5_OneM[t         , Long   , A, B, C, D, Long   ] = ???
  final lazy val tx         : AVET_5_OneM[tx        , Long   , A, B, C, D, Long   ] = ???
  final lazy val txInstant  : AVET_5_OneM[txInstant , Date   , A, B, C, D, Date   ] = ???
  final lazy val op         : AVET_5_OneM[op        , Boolean, A, B, C, D, Boolean] = ???
}

trait AVET_4_OneM[Attr, t, A, B, C, D] extends AVET_4[A, B, C, D] with ExprOneM_4[Attr, t, A, B, C, D, AVET_4_OneM]

