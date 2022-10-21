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


trait Schema_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] extends Molecule_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] {
  final lazy val t            : Schema_21_OneM[t           , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Long   ] = ???
  final lazy val tx           : Schema_21_OneM[tx          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Long   ] = ???
  final lazy val txInstant    : Schema_21_OneM[txInstant   , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Date   ] = ???
  final lazy val attrId       : Schema_21_OneM[attrId      , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Long   ] = ???
  final lazy val a            : Schema_21_OneM[a           , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, String ] = ???
  final lazy val part         : Schema_21_OneM[part        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, String ] = ???
  final lazy val nsFull       : Schema_21_OneM[nsFull      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, String ] = ???
  final lazy val ns           : Schema_21_OneM[ns          , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, String ] = ???
  final lazy val attr         : Schema_21_OneM[attr        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, String ] = ???
  final lazy val ident        : Schema_21_OneM[ident       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, String ] = ???
  final lazy val valueType    : Schema_21_OneM[valueType   , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, String ] = ???
  final lazy val cardinality  : Schema_21_OneM[cardinality , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, String ] = ???
  final lazy val doc          : Schema_21_OneM[doc         , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, String ] = ???
  final lazy val unique       : Schema_21_OneM[unique      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, String ] = ???
  final lazy val isComponent  : Schema_21_OneM[isComponent , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Boolean] = ???
  final lazy val noHistory    : Schema_21_OneM[noHistory   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Boolean] = ???
  final lazy val index        : Schema_21_OneM[index       , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Boolean] = ???
  final lazy val fulltext     : Schema_21_OneM[fulltext    , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Boolean] = ???
  
  final lazy val ident$       : Schema_21_OneO[ident$      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Option[String ]] = ???
  final lazy val valueType$   : Schema_21_OneO[valueType$  , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Option[String ]] = ???
  final lazy val cardinality$ : Schema_21_OneO[cardinality$, String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Option[String ]] = ???
  final lazy val doc$         : Schema_21_OneO[doc$        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Option[String ]] = ???
  final lazy val unique$      : Schema_21_OneO[unique$     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Option[String ]] = ???
  final lazy val isComponent$ : Schema_21_OneO[isComponent$, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_21_OneO[noHistory$  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Option[Boolean]] = ???
  final lazy val index$       : Schema_21_OneO[index$      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_21_OneO[fulltext$   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_20_OneT[t_          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
  final lazy val tx_          : Schema_20_OneT[tx_         , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
  final lazy val txInstant_   : Schema_20_OneT[txInstant_  , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
  final lazy val attrId_      : Schema_20_OneT[attrId_     , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
  final lazy val a_           : Schema_20_OneT[a_          , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
  final lazy val part_        : Schema_20_OneT[part_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
  final lazy val nsFull_      : Schema_20_OneT[nsFull_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
  final lazy val ns_          : Schema_20_OneT[ns_         , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
  final lazy val attr_        : Schema_20_OneT[attr_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
  final lazy val ident_       : Schema_20_OneT[ident_      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
  final lazy val valueType_   : Schema_20_OneT[valueType_  , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
  final lazy val cardinality_ : Schema_20_OneT[cardinality_, String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
  final lazy val doc_         : Schema_20_OneT[doc_        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
  final lazy val unique_      : Schema_20_OneT[unique_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
  final lazy val isComponent_ : Schema_20_OneT[isComponent_, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
  final lazy val noHistory_   : Schema_20_OneT[noHistory_  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
  final lazy val index_       : Schema_20_OneT[index_      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
  final lazy val fulltext_    : Schema_20_OneT[fulltext_   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] = ???
}

trait Schema_20_OneM[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] extends Schema_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with ExprOneM_20[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Schema_20_OneM]
trait Schema_20_OneO[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] extends Schema_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with ExprOneO_20[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Schema_20_OneO]
trait Schema_20_OneT[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] extends Schema_20[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T] with ExprOneT_20[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, Schema_20_OneT]

