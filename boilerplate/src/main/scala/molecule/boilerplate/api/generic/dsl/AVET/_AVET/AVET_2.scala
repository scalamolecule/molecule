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


trait AVET_2[A, B] extends Molecule_02[A, B] {
  final lazy val e          : AVET_3_OneM[e         , Long   , A, B, Long   ] = ???
  final lazy val a          : AVET_3_OneM[a         , String , A, B, String ] = ???
  final lazy val v          : AVET_3_OneM[v         , Any    , A, B, Any    ] = ???
  final lazy val t          : AVET_3_OneM[t         , Long   , A, B, Long   ] = ???
  final lazy val tx         : AVET_3_OneM[tx        , Long   , A, B, Long   ] = ???
  final lazy val txInstant  : AVET_3_OneM[txInstant , Date   , A, B, Date   ] = ???
  final lazy val op         : AVET_3_OneM[op        , Boolean, A, B, Boolean] = ???
}

trait AVET_2_OneM[Attr, t, A, B] extends AVET_2[A, B] with ExprOneM_2[Attr, t, A, B, AVET_2_OneM]

