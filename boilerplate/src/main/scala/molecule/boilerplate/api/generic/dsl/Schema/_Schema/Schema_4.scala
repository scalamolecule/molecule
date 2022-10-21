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


trait Schema_4[A, B, C, D] extends Molecule_04[A, B, C, D] {
  final lazy val t            : Schema_5_OneM[t           , Long   , A, B, C, D, Long   ] = ???
  final lazy val tx           : Schema_5_OneM[tx          , Long   , A, B, C, D, Long   ] = ???
  final lazy val txInstant    : Schema_5_OneM[txInstant   , Date   , A, B, C, D, Date   ] = ???
  final lazy val attrId       : Schema_5_OneM[attrId      , Long   , A, B, C, D, Long   ] = ???
  final lazy val a            : Schema_5_OneM[a           , String , A, B, C, D, String ] = ???
  final lazy val part         : Schema_5_OneM[part        , String , A, B, C, D, String ] = ???
  final lazy val nsFull       : Schema_5_OneM[nsFull      , String , A, B, C, D, String ] = ???
  final lazy val ns           : Schema_5_OneM[ns          , String , A, B, C, D, String ] = ???
  final lazy val attr         : Schema_5_OneM[attr        , String , A, B, C, D, String ] = ???
  final lazy val ident        : Schema_5_OneM[ident       , String , A, B, C, D, String ] = ???
  final lazy val valueType    : Schema_5_OneM[valueType   , String , A, B, C, D, String ] = ???
  final lazy val cardinality  : Schema_5_OneM[cardinality , String , A, B, C, D, String ] = ???
  final lazy val doc          : Schema_5_OneM[doc         , String , A, B, C, D, String ] = ???
  final lazy val unique       : Schema_5_OneM[unique      , String , A, B, C, D, String ] = ???
  final lazy val isComponent  : Schema_5_OneM[isComponent , Boolean, A, B, C, D, Boolean] = ???
  final lazy val noHistory    : Schema_5_OneM[noHistory   , Boolean, A, B, C, D, Boolean] = ???
  final lazy val index        : Schema_5_OneM[index       , Boolean, A, B, C, D, Boolean] = ???
  final lazy val fulltext     : Schema_5_OneM[fulltext    , Boolean, A, B, C, D, Boolean] = ???
  
  final lazy val ident$       : Schema_5_OneO[ident$      , String , A, B, C, D, Option[String ]] = ???
  final lazy val valueType$   : Schema_5_OneO[valueType$  , String , A, B, C, D, Option[String ]] = ???
  final lazy val cardinality$ : Schema_5_OneO[cardinality$, String , A, B, C, D, Option[String ]] = ???
  final lazy val doc$         : Schema_5_OneO[doc$        , String , A, B, C, D, Option[String ]] = ???
  final lazy val unique$      : Schema_5_OneO[unique$     , String , A, B, C, D, Option[String ]] = ???
  final lazy val isComponent$ : Schema_5_OneO[isComponent$, Boolean, A, B, C, D, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_5_OneO[noHistory$  , Boolean, A, B, C, D, Option[Boolean]] = ???
  final lazy val index$       : Schema_5_OneO[index$      , Boolean, A, B, C, D, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_5_OneO[fulltext$   , Boolean, A, B, C, D, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_4_OneT[t_          , Long   , A, B, C, D] = ???
  final lazy val tx_          : Schema_4_OneT[tx_         , Long   , A, B, C, D] = ???
  final lazy val txInstant_   : Schema_4_OneT[txInstant_  , Date   , A, B, C, D] = ???
  final lazy val attrId_      : Schema_4_OneT[attrId_     , Long   , A, B, C, D] = ???
  final lazy val a_           : Schema_4_OneT[a_          , String , A, B, C, D] = ???
  final lazy val part_        : Schema_4_OneT[part_       , String , A, B, C, D] = ???
  final lazy val nsFull_      : Schema_4_OneT[nsFull_     , String , A, B, C, D] = ???
  final lazy val ns_          : Schema_4_OneT[ns_         , String , A, B, C, D] = ???
  final lazy val attr_        : Schema_4_OneT[attr_       , String , A, B, C, D] = ???
  final lazy val ident_       : Schema_4_OneT[ident_      , String , A, B, C, D] = ???
  final lazy val valueType_   : Schema_4_OneT[valueType_  , String , A, B, C, D] = ???
  final lazy val cardinality_ : Schema_4_OneT[cardinality_, String , A, B, C, D] = ???
  final lazy val doc_         : Schema_4_OneT[doc_        , String , A, B, C, D] = ???
  final lazy val unique_      : Schema_4_OneT[unique_     , String , A, B, C, D] = ???
  final lazy val isComponent_ : Schema_4_OneT[isComponent_, Boolean, A, B, C, D] = ???
  final lazy val noHistory_   : Schema_4_OneT[noHistory_  , Boolean, A, B, C, D] = ???
  final lazy val index_       : Schema_4_OneT[index_      , Boolean, A, B, C, D] = ???
  final lazy val fulltext_    : Schema_4_OneT[fulltext_   , Boolean, A, B, C, D] = ???
}

trait Schema_4_OneM[Attr, t, A, B, C, D] extends Schema_4[A, B, C, D] with ExprOneM_4[Attr, t, A, B, C, D, Schema_4_OneM]
trait Schema_4_OneO[Attr, t, A, B, C, D] extends Schema_4[A, B, C, D] with ExprOneO_4[Attr, t, A, B, C, D, Schema_4_OneO]
trait Schema_4_OneT[Attr, t, A, B, C, D] extends Schema_4[A, B, C, D] with ExprOneT_4[Attr, t, A, B, C, D, Schema_4_OneT]

