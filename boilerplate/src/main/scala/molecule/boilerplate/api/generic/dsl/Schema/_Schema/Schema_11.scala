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


trait Schema_11[A, B, C, D, E, F, G, H, I, J, K] extends Molecule_11[A, B, C, D, E, F, G, H, I, J, K] {
  final lazy val t            : Schema_12_OneM[t           , Long   , A, B, C, D, E, F, G, H, I, J, K, Long   ] = ???
  final lazy val tx           : Schema_12_OneM[tx          , Long   , A, B, C, D, E, F, G, H, I, J, K, Long   ] = ???
  final lazy val txInstant    : Schema_12_OneM[txInstant   , Date   , A, B, C, D, E, F, G, H, I, J, K, Date   ] = ???
  final lazy val attrId       : Schema_12_OneM[attrId      , Long   , A, B, C, D, E, F, G, H, I, J, K, Long   ] = ???
  final lazy val a            : Schema_12_OneM[a           , String , A, B, C, D, E, F, G, H, I, J, K, String ] = ???
  final lazy val part         : Schema_12_OneM[part        , String , A, B, C, D, E, F, G, H, I, J, K, String ] = ???
  final lazy val nsFull       : Schema_12_OneM[nsFull      , String , A, B, C, D, E, F, G, H, I, J, K, String ] = ???
  final lazy val ns           : Schema_12_OneM[ns          , String , A, B, C, D, E, F, G, H, I, J, K, String ] = ???
  final lazy val attr         : Schema_12_OneM[attr        , String , A, B, C, D, E, F, G, H, I, J, K, String ] = ???
  final lazy val ident        : Schema_12_OneM[ident       , String , A, B, C, D, E, F, G, H, I, J, K, String ] = ???
  final lazy val valueType    : Schema_12_OneM[valueType   , String , A, B, C, D, E, F, G, H, I, J, K, String ] = ???
  final lazy val cardinality  : Schema_12_OneM[cardinality , String , A, B, C, D, E, F, G, H, I, J, K, String ] = ???
  final lazy val doc          : Schema_12_OneM[doc         , String , A, B, C, D, E, F, G, H, I, J, K, String ] = ???
  final lazy val unique       : Schema_12_OneM[unique      , String , A, B, C, D, E, F, G, H, I, J, K, String ] = ???
  final lazy val isComponent  : Schema_12_OneM[isComponent , Boolean, A, B, C, D, E, F, G, H, I, J, K, Boolean] = ???
  final lazy val noHistory    : Schema_12_OneM[noHistory   , Boolean, A, B, C, D, E, F, G, H, I, J, K, Boolean] = ???
  final lazy val index        : Schema_12_OneM[index       , Boolean, A, B, C, D, E, F, G, H, I, J, K, Boolean] = ???
  final lazy val fulltext     : Schema_12_OneM[fulltext    , Boolean, A, B, C, D, E, F, G, H, I, J, K, Boolean] = ???
  
  final lazy val ident$       : Schema_12_OneO[ident$      , String , A, B, C, D, E, F, G, H, I, J, K, Option[String ]] = ???
  final lazy val valueType$   : Schema_12_OneO[valueType$  , String , A, B, C, D, E, F, G, H, I, J, K, Option[String ]] = ???
  final lazy val cardinality$ : Schema_12_OneO[cardinality$, String , A, B, C, D, E, F, G, H, I, J, K, Option[String ]] = ???
  final lazy val doc$         : Schema_12_OneO[doc$        , String , A, B, C, D, E, F, G, H, I, J, K, Option[String ]] = ???
  final lazy val unique$      : Schema_12_OneO[unique$     , String , A, B, C, D, E, F, G, H, I, J, K, Option[String ]] = ???
  final lazy val isComponent$ : Schema_12_OneO[isComponent$, Boolean, A, B, C, D, E, F, G, H, I, J, K, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_12_OneO[noHistory$  , Boolean, A, B, C, D, E, F, G, H, I, J, K, Option[Boolean]] = ???
  final lazy val index$       : Schema_12_OneO[index$      , Boolean, A, B, C, D, E, F, G, H, I, J, K, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_12_OneO[fulltext$   , Boolean, A, B, C, D, E, F, G, H, I, J, K, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_11_OneT[t_          , Long   , A, B, C, D, E, F, G, H, I, J, K] = ???
  final lazy val tx_          : Schema_11_OneT[tx_         , Long   , A, B, C, D, E, F, G, H, I, J, K] = ???
  final lazy val txInstant_   : Schema_11_OneT[txInstant_  , Date   , A, B, C, D, E, F, G, H, I, J, K] = ???
  final lazy val attrId_      : Schema_11_OneT[attrId_     , Long   , A, B, C, D, E, F, G, H, I, J, K] = ???
  final lazy val a_           : Schema_11_OneT[a_          , String , A, B, C, D, E, F, G, H, I, J, K] = ???
  final lazy val part_        : Schema_11_OneT[part_       , String , A, B, C, D, E, F, G, H, I, J, K] = ???
  final lazy val nsFull_      : Schema_11_OneT[nsFull_     , String , A, B, C, D, E, F, G, H, I, J, K] = ???
  final lazy val ns_          : Schema_11_OneT[ns_         , String , A, B, C, D, E, F, G, H, I, J, K] = ???
  final lazy val attr_        : Schema_11_OneT[attr_       , String , A, B, C, D, E, F, G, H, I, J, K] = ???
  final lazy val ident_       : Schema_11_OneT[ident_      , String , A, B, C, D, E, F, G, H, I, J, K] = ???
  final lazy val valueType_   : Schema_11_OneT[valueType_  , String , A, B, C, D, E, F, G, H, I, J, K] = ???
  final lazy val cardinality_ : Schema_11_OneT[cardinality_, String , A, B, C, D, E, F, G, H, I, J, K] = ???
  final lazy val doc_         : Schema_11_OneT[doc_        , String , A, B, C, D, E, F, G, H, I, J, K] = ???
  final lazy val unique_      : Schema_11_OneT[unique_     , String , A, B, C, D, E, F, G, H, I, J, K] = ???
  final lazy val isComponent_ : Schema_11_OneT[isComponent_, Boolean, A, B, C, D, E, F, G, H, I, J, K] = ???
  final lazy val noHistory_   : Schema_11_OneT[noHistory_  , Boolean, A, B, C, D, E, F, G, H, I, J, K] = ???
  final lazy val index_       : Schema_11_OneT[index_      , Boolean, A, B, C, D, E, F, G, H, I, J, K] = ???
  final lazy val fulltext_    : Schema_11_OneT[fulltext_   , Boolean, A, B, C, D, E, F, G, H, I, J, K] = ???
}

trait Schema_11_OneM[Attr, t, A, B, C, D, E, F, G, H, I, J, K] extends Schema_11[A, B, C, D, E, F, G, H, I, J, K] with ExprOneM_11[Attr, t, A, B, C, D, E, F, G, H, I, J, K, Schema_11_OneM]
trait Schema_11_OneO[Attr, t, A, B, C, D, E, F, G, H, I, J, K] extends Schema_11[A, B, C, D, E, F, G, H, I, J, K] with ExprOneO_11[Attr, t, A, B, C, D, E, F, G, H, I, J, K, Schema_11_OneO]
trait Schema_11_OneT[Attr, t, A, B, C, D, E, F, G, H, I, J, K] extends Schema_11[A, B, C, D, E, F, G, H, I, J, K] with ExprOneT_11[Attr, t, A, B, C, D, E, F, G, H, I, J, K, Schema_11_OneT]

