package molecule.boilerplate.markers

object bidirectionalMarkers {

  trait Bidirectional_

  // Bidirectional ref to defining ns (self-reference)
  trait BiSelfRef_ extends Bidirectional_
  trait BiSelfRefAttr_ extends Bidirectional_

  // Bidirectional ref to other ns
  trait BiOtherRef_[revRefAttr] extends Bidirectional_
  trait BiOtherRefAttr_[revRefAttr] extends Bidirectional_

  // Property edge namespace
  trait BiEdge_ extends Bidirectional_

  // Ref from defining ns to edge
  trait BiEdgeRef_[revRefAttr] extends Bidirectional_
  trait BiEdgeRefAttr_[revRefAttr] extends Bidirectional_

  // Edge properties
  trait BiEdgePropAttr_ extends Bidirectional_
  trait BiEdgePropRef_ extends Bidirectional_
  trait BiEdgePropRefAttr_ extends Bidirectional_

  // Ref from edge to target ns
  trait BiTargetRef_[biRefAttr] extends Bidirectional_
  trait BiTargetRefAttr_[biRefAttr] extends Bidirectional_
}
