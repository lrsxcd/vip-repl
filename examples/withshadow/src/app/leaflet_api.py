



def create_map(center_lat, center_lng, zoom, markers, filters):
  """Creating html snippet for react-leaflet map
  
  Arguments:
      center_lat {float} -- map center latitude
      center_lng {float} -- map center logntitude
      zoom {int} -- zoom in number between 1 and 30
      markers {string} -- markers that are created with create_markers
      filters {string} -- filters that are created with create_filters 
  
  Returns:
      string  -- html snippet of a react-leaflet string
  """
  return f"<map center=[{center_lat}, {center_lng} zoom={zoom}]>{markers}</map>{filters}"



def create_markers(data, lat_name="lat", lng_name="lng"):
  """[summary]
  
  Arguments:
      data {list of dicts} -- list of marker
      lat_name {str} -- field name for latitude
      lng_name {str} -- field name for latitude

  """
  results = ""
  for m in data:
    lat = m.get(lat_name)
    lng = m.get(lng_name)
    if lat and lng:
      results += "<marker center=[{center_lat}, {center_lng} ></marker>"
  return results
def create_filter(ftype, on_change=None):
  """creates a filter and functionality
  
  Arguments:
      ftype {str} -- options are  
  
  Keyword Arguments:
      on_change {[type]} -- [description] (default: {None})
  """
  if ftype == "input":
    return "<input />"
  else:
    return "<input />"




"""test create map
"""
print(create_map(1.2,1.5, 15,"",""))