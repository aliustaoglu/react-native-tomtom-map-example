import Foundation
import TomTomOnlineSDKMaps

class TomTomMap: TTMapView {
  
  @objc var mapZoom: NSNumber!
  @objc var mapCenter: NSDictionary = [:]
  @objc var onMapReady: RCTDirectEventBlock?
  @objc var markers: NSArray!
  
  override init(frame: CGRect) {
    super.init(frame: frame)
  }
  
  override func didSetProps(_ changedProps: [String]!) {
    let mapView = TTMapView(frame: self.bounds)
    let lat = self.mapCenter.object(forKey: "lat") as! Double
    let lng = self.mapCenter.object(forKey: "lng") as! Double
    
    let zoom = self.mapZoom as! Double
    
    for i in 0...self.markers.count-1 {
      let marker = self.markers[i] as! NSDictionary
      let mLat = marker.object(forKey: "lat") as! Double
      let mLng = marker.object(forKey: "lng") as! Double
      let lbl = marker.object(forKey: "label") as! String
      
      let anno = TTAnnotation(coordinate: CLLocationCoordinate2D( latitude: mLat,longitude: mLng))
      self.annotationManager.add(anno)
    }
    
    mapView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
    self.center(on: CLLocationCoordinate2D( latitude: lat,longitude: lng), withZoom: zoom )
    
    self.onMapReady!([:])
  }
  
  
  
  
  
  required init(coder aDecoder: NSCoder) {
    fatalError("init(coder:) has not been implemented")
  }
  
  
}
