/*
* AUTO-GENERATED Molecule DSL for namespace `Schema`
*
* To change:
* 1. Edit data model in molecule.boilerplate.api.generic.dsl.dataModel.Schema
* 2. `sbt clean compile -Dmolecule=true`
*/
package molecule.boilerplate.api.generic.dsl.Schema

import java.util.Date
import molecule.boilerplate.api.expression._
import molecule.boilerplate.api.generic.dsl.Schema.Schema_._
import molecule.boilerplate.markers.namespaceMarkers._
import scala.language.higherKinds


trait Schema_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] extends Molecule_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] {
  final lazy val t            : Schema_22_OneM[t           , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Long   ] = ???
  final lazy val tx           : Schema_22_OneM[tx          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Long   ] = ???
  final lazy val txInstant    : Schema_22_OneM[txInstant   , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Date   ] = ???
  final lazy val attrId       : Schema_22_OneM[attrId      , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Long   ] = ???
  final lazy val a            : Schema_22_OneM[a           , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, String ] = ???
  final lazy val part         : Schema_22_OneM[part        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, String ] = ???
  final lazy val nsFull       : Schema_22_OneM[nsFull      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, String ] = ???
  final lazy val ns           : Schema_22_OneM[ns          , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, String ] = ???
  final lazy val attr         : Schema_22_OneM[attr        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, String ] = ???
  final lazy val ident        : Schema_22_OneM[ident       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, String ] = ???
  final lazy val valueType    : Schema_22_OneM[valueType   , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, String ] = ???
  final lazy val cardinality  : Schema_22_OneM[cardinality , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, String ] = ???
  final lazy val doc          : Schema_22_OneM[doc         , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, String ] = ???
  final lazy val unique       : Schema_22_OneM[unique      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, String ] = ???
  final lazy val isComponent  : Schema_22_OneM[isComponent , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Boolean] = ???
  final lazy val noHistory    : Schema_22_OneM[noHistory   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Boolean] = ???
  final lazy val index        : Schema_22_OneM[index       , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Boolean] = ???
  final lazy val fulltext     : Schema_22_OneM[fulltext    , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Boolean] = ???
  
  final lazy val ident$       : Schema_22_OneO[ident$      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Option[String ]] = ???
  final lazy val valueType$   : Schema_22_OneO[valueType$  , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Option[String ]] = ???
  final lazy val cardinality$ : Schema_22_OneO[cardinality$, String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Option[String ]] = ???
  final lazy val doc$         : Schema_22_OneO[doc$        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Option[String ]] = ???
  final lazy val unique$      : Schema_22_OneO[unique$     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Option[String ]] = ???
  final lazy val isComponent$ : Schema_22_OneO[isComponent$, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_22_OneO[noHistory$  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Option[Boolean]] = ???
  final lazy val index$       : Schema_22_OneO[index$      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_22_OneO[fulltext$   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_21_OneT[t_          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
  final lazy val tx_          : Schema_21_OneT[tx_         , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
  final lazy val txInstant_   : Schema_21_OneT[txInstant_  , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
  final lazy val attrId_      : Schema_21_OneT[attrId_     , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
  final lazy val a_           : Schema_21_OneT[a_          , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
  final lazy val part_        : Schema_21_OneT[part_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
  final lazy val nsFull_      : Schema_21_OneT[nsFull_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
  final lazy val ns_          : Schema_21_OneT[ns_         , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
  final lazy val attr_        : Schema_21_OneT[attr_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
  final lazy val ident_       : Schema_21_OneT[ident_      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
  final lazy val valueType_   : Schema_21_OneT[valueType_  , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
  final lazy val cardinality_ : Schema_21_OneT[cardinality_, String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
  final lazy val doc_         : Schema_21_OneT[doc_        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
  final lazy val unique_      : Schema_21_OneT[unique_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
  final lazy val isComponent_ : Schema_21_OneT[isComponent_, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
  final lazy val noHistory_   : Schema_21_OneT[noHistory_  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
  final lazy val index_       : Schema_21_OneT[index_      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
  final lazy val fulltext_    : Schema_21_OneT[fulltext_   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] = ???
}

trait Schema_21_OneM[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] extends Schema_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with ExprOneM_21[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Schema_21_OneM]
trait Schema_21_OneO[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] extends Schema_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with ExprOneO_21[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Schema_21_OneO]
trait Schema_21_OneT[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] extends Schema_21[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U] with ExprOneT_21[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, Schema_21_OneT]

