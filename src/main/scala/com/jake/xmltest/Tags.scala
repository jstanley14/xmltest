package com.jake.xmltest

import java.util.Date

case class Data(activityType: ActivityType, author: String, saved: Date,
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








//import java.util.Date
//
//sealed trait ElementType
//
//case class Source(source: String)
//case class Audio(source: Source)
//case class Thumbnail(path: String)
//case class Url(path: String)
//
//case class Image(autoplay: Boolean, maintainAspect: Boolean, mute: Boolean,
//                  url: Url, thumbnail: Thumbnail, audio: Audio) extends ElementType
//
//case class Type(_type: String, tooltip: String)
//
//case class Element(_type: Type, elm: ElementType)
//case class Elements(elms: Seq[Element])
//
//case class Container(x: Int, y: Int, width: Int, height: Int, borderStyle: String,
//                      borderColor: Int, backgroundColor: Int, backgroundAlpha: Int,
//                      borderPadding: Int, borderThickness: Int, borderRadius: Int,
//                      rotation: Int, _type: String)
//case class Containers(containers: Seq[Container])
//
//case class Metadata(_type: Type)
//
//case class Total(pts: Int)
//case class Extra(pts: Int)
//case class Weight(total: Total, extra: Extra)
//
//case class Activity(weight: Weight, metadata: Metadata, containers: Containers)
//
//case class Data(_type: Type, author: String, saved: Date, version: Double, activity: Activity)
