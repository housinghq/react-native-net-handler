
#if __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#else
#import <React/RCTBridgeModule.h>
#endif
#import "RNNetInfo-Swift.h"

@interface RNNetInfo : NSObject <RCTBridgeModule>
@property (atomic, readonly) ReachabilityHandler *handler;
@end
  
