package com.jake.xmltest

object ActivityParser {
  def main(args: Array[String]): Unit = {
    val activityXML = scala.xml.XML.loadFile("./activity.xml")
    println(activityXML \\ "element")
  }
}
