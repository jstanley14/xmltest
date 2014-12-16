package com.jake.xmltest

case class Data(activityType: ActivityType, author: String, saved: String,
                version: Double, activity: Activity)
case class Activity(weight: Weight, metadata: Metadata, containers: Seq[Container])
case class Weight(total: Int, extra: Int)
case class Metadata(activityType: ActivityType)

sealed trait ActivityType
case object HMT extends ActivityType

case class Dimensions(x: Int, y: Int, width: Int, height: Int)
case class Appearance(borderStyle: String, borderColor: Int, backgroundColor: Int,
                      backgroundAlpha: Int, borderPadding: Int, borderThickness: Int,
                      borderRadius: Int, rotation: Int)
case class Container(dimensions: Dimensions, appearance: Appearance,
                     containerType: String, elements: Seq[Element])

sealed trait Element
case class ElementType(elementType: String, toolTip: String)
case class ImageElement(elementType: ElementType, content: Image) extends Element

case class Image(autoplay: Boolean, maintainAspect: Boolean, mute: Boolean, contents: Seq[Tag])

sealed trait Tag
case class Url(path: String) extends Tag
case class Thumbnail(path: String) extends Tag
case class Audio(source: String) extends Tag
