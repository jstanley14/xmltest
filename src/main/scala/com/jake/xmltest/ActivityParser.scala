package com.jake.xmltest

import scala.xml.NodeSeq

object ActivityParser {
  def main(args: Array[String]): Unit = {
    val activityXML = scala.xml.XML.loadFile("./activity.xml")
    //println(activityXML \\ "element")

    val image = parseImage(activityXML \\ "image")
 //   println(image)

    println(parseData(activityXML))
  }

  def parseData(xml: NodeSeq): Data = {
    val activityType = (xml \ "@type").text match { case "HMT" => HMT }
    val author = (xml \ "@author").text
    val saved = (xml \ "@saved").text
    val version = (xml \ "@version").text.toDouble
    val activity = parseActivity(xml \ "activity")

    Data(activityType, author, saved, version, activity)
  }

  def parseActivity(xml: NodeSeq): Activity = {
    val weight = parseWeight(xml \ "weight")
    val metadata = parseMetadata(xml \ "metadata")
    val containers = (xml \\ "container") map parseContainer

    Activity(weight, metadata, containers)
  }

  def parseWeight(xml: NodeSeq): Weight = {
    val total = (xml \ "total").text.toInt
    val extra = (xml \ "extra").text.toInt

    Weight(total, extra)
  }

  def parseMetadata(xml: NodeSeq): Metadata = {
    val _type = (xml \ "type").text match { case "HMT" => HMT }

    Metadata(_type)
  }

  def parseContainer(xml: NodeSeq): Container = {
    val dimensions = Dimensions((xml \ "@x").text.toInt, (xml \ "@y").text.toInt,
      (xml \ "@width").text.toInt, (xml \ "@height").text.toInt)
    val borderStyle = (xml \ "@borderStyle").text
    val borderColor = (xml \ "@borderColor").text.toInt
    val backgroundColor = (xml \ "@backgroundColor").text.toInt
    val backgroundAlpha = (xml \ "@backgroundAlpha").text.toInt
    val borderPadding = (xml \ "@borderPadding").text.toInt
    val borderThickness = (xml \ "@borderThickness").text.toInt
    val borderRadius = (xml \ "@borderRadius").text.toInt
    val rotation = (xml \ "@rotation").text.toInt
    val appearance = Appearance(borderStyle, borderColor, backgroundColor,
                                backgroundAlpha, borderPadding, borderThickness,
                                borderRadius, rotation)
    val containerType = (xml \ "@type").text
    val elements = (xml \\ "element") map parseElement

    Container(dimensions, appearance, containerType, elements)
  }

  def parseElement(xml: NodeSeq): Element = {
    val elementType = parseElementType(xml \ "type")
    val elementNodes = (xml \ "_") filterNot { _.label == "type" }

    elementNodes.head.label match {
      case "image" => {
        val image = parseImage(elementNodes)
        ImageElement(elementType, image)
      }
      case _ =>
        throw new IllegalArgumentException("only image element parsing implemented")
    }
  }

  def parseElementType(xml: NodeSeq): ElementType = {
    ElementType(xml.text, (xml \ "@toolTip").text)
  }

  def parseImage(imageXML: NodeSeq): Image = {
    val autoplay = stringToBool((imageXML \ "@autoplay").text)
    val maintainAspect = stringToBool((imageXML \ "@maintainAspect").text)
    val mute = stringToBool((imageXML \ "@mute").text)

    val thumbnail = (imageXML \ "thumbnail").text
    val audio = (imageXML \ "audio").text
    val source = (imageXML \ "source").text

    val url = (imageXML \ "url").text

    Image(autoplay, maintainAspect, mute,
      Seq(Url(url), Thumbnail(thumbnail), Audio(source)))
  }

  def stringToBool(s: String): Boolean = s match {
    case "true" => true
    case _ => false
  }

}

