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


trait Schema_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] extends Molecule_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] {
  final lazy val t            : Schema_17_OneM[t           , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Long   ] = ???
  final lazy val tx           : Schema_17_OneM[tx          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Long   ] = ???
  final lazy val txInstant    : Schema_17_OneM[txInstant   , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Date   ] = ???
  final lazy val attrId       : Schema_17_OneM[attrId      , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Long   ] = ???
  final lazy val a            : Schema_17_OneM[a           , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, String ] = ???
  final lazy val part         : Schema_17_OneM[part        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, String ] = ???
  final lazy val nsFull       : Schema_17_OneM[nsFull      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, String ] = ???
  final lazy val ns           : Schema_17_OneM[ns          , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, String ] = ???
  final lazy val attr         : Schema_17_OneM[attr        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, String ] = ???
  final lazy val ident        : Schema_17_OneM[ident       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, String ] = ???
  final lazy val valueType    : Schema_17_OneM[valueType   , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, String ] = ???
  final lazy val cardinality  : Schema_17_OneM[cardinality , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, String ] = ???
  final lazy val doc          : Schema_17_OneM[doc         , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, String ] = ???
  final lazy val unique       : Schema_17_OneM[unique      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, String ] = ???
  final lazy val isComponent  : Schema_17_OneM[isComponent , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Boolean] = ???
  final lazy val noHistory    : Schema_17_OneM[noHistory   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Boolean] = ???
  final lazy val index        : Schema_17_OneM[index       , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Boolean] = ???
  final lazy val fulltext     : Schema_17_OneM[fulltext    , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Boolean] = ???
  
  final lazy val ident$       : Schema_17_OneO[ident$      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Option[String ]] = ???
  final lazy val valueType$   : Schema_17_OneO[valueType$  , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Option[String ]] = ???
  final lazy val cardinality$ : Schema_17_OneO[cardinality$, String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Option[String ]] = ???
  final lazy val doc$         : Schema_17_OneO[doc$        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Option[String ]] = ???
  final lazy val unique$      : Schema_17_OneO[unique$     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Option[String ]] = ???
  final lazy val isComponent$ : Schema_17_OneO[isComponent$, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_17_OneO[noHistory$  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Option[Boolean]] = ???
  final lazy val index$       : Schema_17_OneO[index$      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_17_OneO[fulltext$   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_16_OneT[t_          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
  final lazy val tx_          : Schema_16_OneT[tx_         , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
  final lazy val txInstant_   : Schema_16_OneT[txInstant_  , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
  final lazy val attrId_      : Schema_16_OneT[attrId_     , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
  final lazy val a_           : Schema_16_OneT[a_          , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
  final lazy val part_        : Schema_16_OneT[part_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
  final lazy val nsFull_      : Schema_16_OneT[nsFull_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
  final lazy val ns_          : Schema_16_OneT[ns_         , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
  final lazy val attr_        : Schema_16_OneT[attr_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
  final lazy val ident_       : Schema_16_OneT[ident_      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
  final lazy val valueType_   : Schema_16_OneT[valueType_  , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
  final lazy val cardinality_ : Schema_16_OneT[cardinality_, String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
  final lazy val doc_         : Schema_16_OneT[doc_        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
  final lazy val unique_      : Schema_16_OneT[unique_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
  final lazy val isComponent_ : Schema_16_OneT[isComponent_, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
  final lazy val noHistory_   : Schema_16_OneT[noHistory_  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
  final lazy val index_       : Schema_16_OneT[index_      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
  final lazy val fulltext_    : Schema_16_OneT[fulltext_   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] = ???
}

trait Schema_16_OneM[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] extends Schema_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with ExprOneM_16[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Schema_16_OneM]
trait Schema_16_OneO[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] extends Schema_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with ExprOneO_16[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Schema_16_OneO]
trait Schema_16_OneT[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] extends Schema_16[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P] with ExprOneT_16[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Schema_16_OneT]

