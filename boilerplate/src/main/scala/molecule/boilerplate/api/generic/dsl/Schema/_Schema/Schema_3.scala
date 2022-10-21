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


trait Schema_3[A, B, C] extends Molecule_03[A, B, C] {
  final lazy val t            : Schema_4_OneM[t           , Long   , A, B, C, Long   ] = ???
  final lazy val tx           : Schema_4_OneM[tx          , Long   , A, B, C, Long   ] = ???
  final lazy val txInstant    : Schema_4_OneM[txInstant   , Date   , A, B, C, Date   ] = ???
  final lazy val attrId       : Schema_4_OneM[attrId      , Long   , A, B, C, Long   ] = ???
  final lazy val a            : Schema_4_OneM[a           , String , A, B, C, String ] = ???
  final lazy val part         : Schema_4_OneM[part        , String , A, B, C, String ] = ???
  final lazy val nsFull       : Schema_4_OneM[nsFull      , String , A, B, C, String ] = ???
  final lazy val ns           : Schema_4_OneM[ns          , String , A, B, C, String ] = ???
  final lazy val attr         : Schema_4_OneM[attr        , String , A, B, C, String ] = ???
  final lazy val ident        : Schema_4_OneM[ident       , String , A, B, C, String ] = ???
  final lazy val valueType    : Schema_4_OneM[valueType   , String , A, B, C, String ] = ???
  final lazy val cardinality  : Schema_4_OneM[cardinality , String , A, B, C, String ] = ???
  final lazy val doc          : Schema_4_OneM[doc         , String , A, B, C, String ] = ???
  final lazy val unique       : Schema_4_OneM[unique      , String , A, B, C, String ] = ???
  final lazy val isComponent  : Schema_4_OneM[isComponent , Boolean, A, B, C, Boolean] = ???
  final lazy val noHistory    : Schema_4_OneM[noHistory   , Boolean, A, B, C, Boolean] = ???
  final lazy val index        : Schema_4_OneM[index       , Boolean, A, B, C, Boolean] = ???
  final lazy val fulltext     : Schema_4_OneM[fulltext    , Boolean, A, B, C, Boolean] = ???
  
  final lazy val ident$       : Schema_4_OneO[ident$      , String , A, B, C, Option[String ]] = ???
  final lazy val valueType$   : Schema_4_OneO[valueType$  , String , A, B, C, Option[String ]] = ???
  final lazy val cardinality$ : Schema_4_OneO[cardinality$, String , A, B, C, Option[String ]] = ???
  final lazy val doc$         : Schema_4_OneO[doc$        , String , A, B, C, Option[String ]] = ???
  final lazy val unique$      : Schema_4_OneO[unique$     , String , A, B, C, Option[String ]] = ???
  final lazy val isComponent$ : Schema_4_OneO[isComponent$, Boolean, A, B, C, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_4_OneO[noHistory$  , Boolean, A, B, C, Option[Boolean]] = ???
  final lazy val index$       : Schema_4_OneO[index$      , Boolean, A, B, C, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_4_OneO[fulltext$   , Boolean, A, B, C, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_3_OneT[t_          , Long   , A, B, C] = ???
  final lazy val tx_          : Schema_3_OneT[tx_         , Long   , A, B, C] = ???
  final lazy val txInstant_   : Schema_3_OneT[txInstant_  , Date   , A, B, C] = ???
  final lazy val attrId_      : Schema_3_OneT[attrId_     , Long   , A, B, C] = ???
  final lazy val a_           : Schema_3_OneT[a_          , String , A, B, C] = ???
  final lazy val part_        : Schema_3_OneT[part_       , String , A, B, C] = ???
  final lazy val nsFull_      : Schema_3_OneT[nsFull_     , String , A, B, C] = ???
  final lazy val ns_          : Schema_3_OneT[ns_         , String , A, B, C] = ???
  final lazy val attr_        : Schema_3_OneT[attr_       , String , A, B, C] = ???
  final lazy val ident_       : Schema_3_OneT[ident_      , String , A, B, C] = ???
  final lazy val valueType_   : Schema_3_OneT[valueType_  , String , A, B, C] = ???
  final lazy val cardinality_ : Schema_3_OneT[cardinality_, String , A, B, C] = ???
  final lazy val doc_         : Schema_3_OneT[doc_        , String , A, B, C] = ???
  final lazy val unique_      : Schema_3_OneT[unique_     , String , A, B, C] = ???
  final lazy val isComponent_ : Schema_3_OneT[isComponent_, Boolean, A, B, C] = ???
  final lazy val noHistory_   : Schema_3_OneT[noHistory_  , Boolean, A, B, C] = ???
  final lazy val index_       : Schema_3_OneT[index_      , Boolean, A, B, C] = ???
  final lazy val fulltext_    : Schema_3_OneT[fulltext_   , Boolean, A, B, C] = ???
}

trait Schema_3_OneM[Attr, t, A, B, C] extends Schema_3[A, B, C] with ExprOneM_3[Attr, t, A, B, C, Schema_3_OneM]
trait Schema_3_OneO[Attr, t, A, B, C] extends Schema_3[A, B, C] with ExprOneO_3[Attr, t, A, B, C, Schema_3_OneO]
trait Schema_3_OneT[Attr, t, A, B, C] extends Schema_3[A, B, C] with ExprOneT_3[Attr, t, A, B, C, Schema_3_OneT]

