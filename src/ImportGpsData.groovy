def file  = new File('../data/fells_loop.gpx')

println file.exists()

def slurper = new XmlSlurper()

def gpx = slurper.parse(file)
println gpx.name

println ''
println gpx.desc

println ''

for(point in gpx.rte.rtept){
  println point.@lat
  println point.@lon
}


gpx.rte.rtept.each{

  println it.@lat
  println it.@lon

}



//writing

def markupBuilder = new groovy.xml.StreamingMarkupBuilder()

def xml = markupBuilder.bind{
  route {
    mkp.comment()

    gpx.rte.rtept.each{ point ->
      routepoint(timestamp: point.time.toString()){

        latitude(point.@lat)

        longitude(point.@lon)
      }

    }
  }
}

def output = new File("../data/fells_loop_temp.gpx")
output.write(xml.toString())
