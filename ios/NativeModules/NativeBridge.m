#import <Foundation/Foundation.h>
#import "React/RCTViewManager.h"

@interface RCT_EXTERN_MODULE(MapViewManager, RCTViewManager)
  RCT_EXPORT_VIEW_PROPERTY(mapZoom, NSNumber)
  RCT_EXPORT_VIEW_PROPERTY(mapCenter, NSDictionary)
  RCT_EXPORT_VIEW_PROPERTY(markers, NSArray)
  RCT_EXPORT_VIEW_PROPERTY(onMapReady, RCTDirectEventBlock)
@end
