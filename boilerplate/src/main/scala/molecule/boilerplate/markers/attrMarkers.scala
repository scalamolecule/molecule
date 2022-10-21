package molecule.boilerplate.markers

import java.net.URI
import java.util.{Date, UUID}
import molecule.boilerplate.markers.cardinalityMarkers._

/** Boilerplate interfaces for custom DSL generated from schema definition file.
  * <br><br>
  * Encodes attribute cardinality, type and mode (mandatory, tacit, optional).
  * */
object attrMarkers {

  // Top attribute interfaces --------------------------------------------------------------------

  trait AnyAttr
  trait GenericAttr extends AnyAttr
  trait Attrs[T] extends AnyAttr
  trait EnumAttrs extends Attrs[String]
  trait RefAttrs extends Attrs[Long]


  // Mandatory attributes --------------------------------------------------------------------

  sealed trait Attr[T] extends Attrs[T]

//  trait OneAttr[T] extends Attr[T] with Card1 with OneExpr[T] with OneEqExpr[T]
//  trait OneAttr[T] extends Attr[T] with Card1 with OneExpr[T] 
  trait OneAttr[T] extends Attr[T] with Card1

  trait OneString     extends OneAttr[String    ] //with OneStringExpr
  trait OneInt        extends OneAttr[Int       ]
  trait OneLong       extends OneAttr[Long      ]
  trait OneDouble     extends OneAttr[Double    ]
  trait OneBoolean    extends OneAttr[Boolean   ]
  trait OneDate       extends OneAttr[Date      ]
  trait OneUUID       extends OneAttr[UUID      ]
  trait OneURI        extends OneAttr[URI       ]
  trait OneBigInt     extends OneAttr[BigInt    ]
  trait OneBigDecimal extends OneAttr[BigDecimal]

  // OneAny used for Datom.v - has to be without == method (can't override with Any type)
//  trait OneAny  extends Attr[Any] with Card1 with OneExpr[Any]
  trait OneAny  extends OneAttr[Any]


//  trait ManyAttr[T] extends Attr[T] with Card2 with ManyExpr[T]
  trait ManyAttr[T] extends Attr[T] with Card2

  trait ManyString     extends ManyAttr[String    ] //with ManyStringExpr
  trait ManyInt        extends ManyAttr[Int       ]
  trait ManyLong       extends ManyAttr[Long      ]
  trait ManyDouble     extends ManyAttr[Double    ]
  trait ManyBoolean    extends ManyAttr[Boolean   ]
  trait ManyDate       extends ManyAttr[Date      ]
  trait ManyUUID       extends ManyAttr[UUID      ]
  trait ManyURI        extends ManyAttr[URI       ]
  trait ManyBigInt     extends ManyAttr[BigInt    ]
  trait ManyBigDecimal extends ManyAttr[BigDecimal]


  trait MapAttr[T] extends Attr[T] with Card3 //with MapExpr[T] with MapExprK[T]

  trait MapString     extends MapAttr[String    ] //with ManyStringExpr
  trait MapInt        extends MapAttr[Int       ]
  trait MapLong       extends MapAttr[Long      ]
  trait MapDouble     extends MapAttr[Double    ]
  trait MapBoolean    extends MapAttr[Boolean   ]
  trait MapDate       extends MapAttr[Date      ]
  trait MapUUID       extends MapAttr[UUID      ]
  trait MapURI        extends MapAttr[URI       ]
  trait MapBigInt     extends MapAttr[BigInt    ]
  trait MapBigDecimal extends MapAttr[BigDecimal]


  trait EnumAttr extends EnumAttrs
  trait OneEnum   extends EnumAttr with Card1 // with OneExpr [String]
  trait ManyEnums extends EnumAttr with Card2 // with ManyExpr[String]


  trait RefAttr extends RefAttrs
  trait OneRefAttr  extends RefAttr with Card1 // with OneExpr [Long]
  trait ManyRefAttr extends RefAttr with Card2 // with ManyExpr[Long]


  // Tacit attributes --------------------------------------------------------------------

  trait Attr_[T] extends Attrs[T]

  trait OneAttr_[T] extends Attr_[T] with Card1 // with OneExpr_[T]
//    with OneEqExpr[T] with OneNeqExpr_[T]

  trait OneString_     extends OneAttr_[String    ] // with OneStringExpr
  trait OneInt_        extends OneAttr_[Int       ]
  trait OneLong_       extends OneAttr_[Long      ]
  trait OneDouble_     extends OneAttr_[Double    ]
  trait OneBoolean_    extends OneAttr_[Boolean   ]
  trait OneDate_       extends OneAttr_[Date      ]
  trait OneUUID_       extends OneAttr_[UUID      ]
  trait OneURI_        extends OneAttr_[URI       ]
  trait OneBigInt_     extends OneAttr_[BigInt    ]
  trait OneBigDecimal_ extends OneAttr_[BigDecimal]

  // OneAny used for Datom.v - has to be without == and != methods (can't override with Any type)
  // Without EqExpr[T] and NeqExpr_[T]
//  trait OneAny_ extends Attr_[Any] with Card1 with OneExpr_[Any]
  trait OneAny_ extends OneAttr_[Any]


  trait ManyAttr_[T] extends Attr_[T] with Card2 // with ManyExpr_[T]

  trait ManyString_     extends ManyAttr_[String    ] // with ManyStringExpr
  trait ManyInt_        extends ManyAttr_[Int       ]
  trait ManyLong_       extends ManyAttr_[Long      ]
  trait ManyDouble_     extends ManyAttr_[Double    ]
  trait ManyBoolean_    extends ManyAttr_[Boolean   ]
  trait ManyDate_       extends ManyAttr_[Date      ]
  trait ManyUUID_       extends ManyAttr_[UUID      ]
  trait ManyURI_        extends ManyAttr_[URI       ]
  trait ManyBigInt_     extends ManyAttr_[BigInt    ]
  trait ManyBigDecimal_ extends ManyAttr_[BigDecimal]


  trait MapAttr_[T] extends Attr_[T] with Card3 // with MapExpr_[T] with MapExprK_[T]

  trait MapString_     extends MapAttr_[String    ] // with ManyStringExpr
  trait MapInt_        extends MapAttr_[Int       ]
  trait MapLong_       extends MapAttr_[Long      ]
  trait MapDouble_     extends MapAttr_[Double    ]
  trait MapBoolean_    extends MapAttr_[Boolean   ]
  trait MapDate_       extends MapAttr_[Date      ]
  trait MapUUID_       extends MapAttr_[UUID      ]
  trait MapURI_        extends MapAttr_[URI       ]
  trait MapBigInt_     extends MapAttr_[BigInt    ]
  trait MapBigDecimal_ extends MapAttr_[BigDecimal]


  trait EnumAttr_ extends EnumAttrs
  trait OneEnum_    extends EnumAttr_ with Card1 // with OneExpr_ [String]
  trait ManyEnums_  extends EnumAttr_ with Card2 // with ManyExpr_[String]


  trait RefAttr_ extends RefAttrs
  trait OneRefAttr_  extends RefAttr_ with Card1 // with OneExpr_ [Long]
  trait ManyRefAttr_ extends RefAttr_ with Card2 // with ManyExpr_[Long]


  // Optional attributes --------------------------------------------------------------------

  trait Attr$[T] extends Attrs[T]

  trait OneAttr$[T] extends Attr$[T] with Card1 // with OneExpr$[T]

  trait OneString$     extends OneAttr$[String    ]
  trait OneInt$        extends OneAttr$[Int       ]
  trait OneLong$       extends OneAttr$[Long      ]
  trait OneDouble$     extends OneAttr$[Double    ]
  trait OneBoolean$    extends OneAttr$[Boolean   ]
  trait OneDate$       extends OneAttr$[Date      ]
  trait OneUUID$       extends OneAttr$[UUID      ]
  trait OneURI$        extends OneAttr$[URI       ]
  trait OneBigInt$     extends OneAttr$[BigInt    ]
  trait OneBigDecimal$ extends OneAttr$[BigDecimal]
  trait OneAny$        extends OneAttr$[Any       ]


  trait ManyAttr$[T] extends Attr$[T] with Card2 // with ManyExpr$[T]

  trait ManyString$     extends ManyAttr$[String    ]
  trait ManyInt$        extends ManyAttr$[Int       ]
  trait ManyLong$       extends ManyAttr$[Long      ]
  trait ManyDouble$     extends ManyAttr$[Double    ]
  trait ManyBoolean$    extends ManyAttr$[Boolean   ]
  trait ManyDate$       extends ManyAttr$[Date      ]
  trait ManyUUID$       extends ManyAttr$[UUID      ]
  trait ManyURI$        extends ManyAttr$[URI       ]
  trait ManyBigInt$     extends ManyAttr$[BigInt    ]
  trait ManyBigDecimal$ extends ManyAttr$[BigDecimal]


  trait MapAttr$[T] extends Attr$[T] with Card3 // with MapExpr$[T]

  trait MapString$     extends MapAttr$[String    ]
  trait MapInt$        extends MapAttr$[Int       ]
  trait MapLong$       extends MapAttr$[Long      ]
  trait MapDouble$     extends MapAttr$[Double    ]
  trait MapBoolean$    extends MapAttr$[Boolean   ]
  trait MapDate$       extends MapAttr$[Date      ]
  trait MapUUID$       extends MapAttr$[UUID      ]
  trait MapURI$        extends MapAttr$[URI       ]
  trait MapBigInt$     extends MapAttr$[BigInt    ]
  trait MapBigDecimal$ extends MapAttr$[BigDecimal]


  trait EnumAttr$ extends EnumAttrs
  trait OneEnum$    extends EnumAttr$ with Card1 // with OneExpr$ [String     ]
  trait ManyEnums$  extends EnumAttr$ with Card2 // with ManyExpr$[Set[String]]


  trait RefAttr$ extends RefAttrs
  trait OneRefAttr$  extends RefAttr$ with Card1 // with OneExpr$ [Long     ]
  trait ManyRefAttr$ extends RefAttr$ with Card2 // with ManyExpr$[Set[Long]]
}
