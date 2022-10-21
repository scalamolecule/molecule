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


trait Schema_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] extends Molecule_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] {
  final lazy val t            : Schema_15_OneM[t           , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, Long   ] = ???
  final lazy val tx           : Schema_15_OneM[tx          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, Long   ] = ???
  final lazy val txInstant    : Schema_15_OneM[txInstant   , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, Date   ] = ???
  final lazy val attrId       : Schema_15_OneM[attrId      , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N, Long   ] = ???
  final lazy val a            : Schema_15_OneM[a           , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, String ] = ???
  final lazy val part         : Schema_15_OneM[part        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, String ] = ???
  final lazy val nsFull       : Schema_15_OneM[nsFull      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, String ] = ???
  final lazy val ns           : Schema_15_OneM[ns          , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, String ] = ???
  final lazy val attr         : Schema_15_OneM[attr        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, String ] = ???
  final lazy val ident        : Schema_15_OneM[ident       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, String ] = ???
  final lazy val valueType    : Schema_15_OneM[valueType   , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, String ] = ???
  final lazy val cardinality  : Schema_15_OneM[cardinality , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, String ] = ???
  final lazy val doc          : Schema_15_OneM[doc         , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, String ] = ???
  final lazy val unique       : Schema_15_OneM[unique      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, String ] = ???
  final lazy val isComponent  : Schema_15_OneM[isComponent , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Boolean] = ???
  final lazy val noHistory    : Schema_15_OneM[noHistory   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Boolean] = ???
  final lazy val index        : Schema_15_OneM[index       , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Boolean] = ???
  final lazy val fulltext     : Schema_15_OneM[fulltext    , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Boolean] = ???
  
  final lazy val ident$       : Schema_15_OneO[ident$      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, Option[String ]] = ???
  final lazy val valueType$   : Schema_15_OneO[valueType$  , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, Option[String ]] = ???
  final lazy val cardinality$ : Schema_15_OneO[cardinality$, String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, Option[String ]] = ???
  final lazy val doc$         : Schema_15_OneO[doc$        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, Option[String ]] = ???
  final lazy val unique$      : Schema_15_OneO[unique$     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N, Option[String ]] = ???
  final lazy val isComponent$ : Schema_15_OneO[isComponent$, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_15_OneO[noHistory$  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Option[Boolean]] = ???
  final lazy val index$       : Schema_15_OneO[index$      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_15_OneO[fulltext$   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_14_OneT[t_          , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
  final lazy val tx_          : Schema_14_OneT[tx_         , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
  final lazy val txInstant_   : Schema_14_OneT[txInstant_  , Date   , A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
  final lazy val attrId_      : Schema_14_OneT[attrId_     , Long   , A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
  final lazy val a_           : Schema_14_OneT[a_          , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
  final lazy val part_        : Schema_14_OneT[part_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
  final lazy val nsFull_      : Schema_14_OneT[nsFull_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
  final lazy val ns_          : Schema_14_OneT[ns_         , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
  final lazy val attr_        : Schema_14_OneT[attr_       , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
  final lazy val ident_       : Schema_14_OneT[ident_      , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
  final lazy val valueType_   : Schema_14_OneT[valueType_  , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
  final lazy val cardinality_ : Schema_14_OneT[cardinality_, String , A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
  final lazy val doc_         : Schema_14_OneT[doc_        , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
  final lazy val unique_      : Schema_14_OneT[unique_     , String , A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
  final lazy val isComponent_ : Schema_14_OneT[isComponent_, Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
  final lazy val noHistory_   : Schema_14_OneT[noHistory_  , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
  final lazy val index_       : Schema_14_OneT[index_      , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
  final lazy val fulltext_    : Schema_14_OneT[fulltext_   , Boolean, A, B, C, D, E, F, G, H, I, J, K, L, M, N] = ???
}

trait Schema_14_OneM[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] extends Schema_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] with ExprOneM_14[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Schema_14_OneM]
trait Schema_14_OneO[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] extends Schema_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] with ExprOneO_14[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Schema_14_OneO]
trait Schema_14_OneT[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N] extends Schema_14[A, B, C, D, E, F, G, H, I, J, K, L, M, N] with ExprOneT_14[Attr, t, A, B, C, D, E, F, G, H, I, J, K, L, M, N, Schema_14_OneT]

